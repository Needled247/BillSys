package com.bill.action;

import com.bill.bean.tbl_billInfo;
import com.bill.dao.BillSysDAOImpl;
import com.bill.tool.BillSysTool;
import com.bill.tool.BillingManager;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: JH
 * Date: 13-8-2
 * Time: 上午9:59
 * 查询用户话单Action
 */
public class UserCallAction extends ActionSupport {
    private String type;
    private String startTime;
    private String endTime;
    private String month;
    private String longNum;
    private String shortNum;
    private String userGroup;
    private String balance;
    private BillSysDAOImpl impl = new BillSysDAOImpl();
    BillingManager manager = new BillingManager();
    BillSysTool tool = new BillSysTool();

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getLongNum() {
        return longNum;
    }

    public void setLongNum(String longNum) {
        this.longNum = longNum;
    }

    public String getShortNum() {
        return shortNum;
    }

    public void setShortNum(String shortNum) {
        this.shortNum = shortNum;
    }

    @Override
    public String execute(){
        if(type.equals("init")){
            try{
                this.UserCalledPageInit();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(type.equals("all")){
            try {
                this.getUserAllCalled();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(type.equals("month")){
            try{
                this.UserCalledPageInit();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else if(type.equals("time")){
            try{
                this.getUserCalledByTime();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 1、页面初始化方法，生成用户电话数据JSON
     * 2、按月查询用户通话记录
     * @throws IOException
     */
    public void UserCalledPageInit() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=GBK");
        PrintWriter out = response.getWriter();
        List li = new ArrayList();
        li = impl.getUserCalledDetail(longNum,shortNum,month,null,null);
        List<tbl_billInfo> listExport = new ArrayList<tbl_billInfo>();
        String convTime = impl.getMonthConversation(month,longNum,shortNum,0);
        Iterator it = li.iterator();
        tbl_billInfo billInfo = null;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int totalTime = 0;
        int convsationTime = 0;
        String callFee = "";
        while (it.hasNext()){
            billInfo = (tbl_billInfo)it.next();
            tbl_billInfo billList = new tbl_billInfo();
            convsationTime = tool.second2Minute(billInfo.getDwConversationTime());
            sb.append("[")
                    .append("\"" + billInfo.getUcCallerNumber() + "\",")
                    .append("\""+billInfo.getUcCalledNumber()+"\",")
                    .append("\""+tool.AttributeCode2Str(billInfo.getUcCallAttribute())+"\",")
                    .append("\""+billInfo.getStartTime()+"\",")
                    .append("\"" + billInfo.getEndTime() + "\",")
                    .append("\"" + convsationTime + "分钟\",");
            billList.setUcCallerNumber(billInfo.getUcCallerNumber());
            billList.setUcCalledNumber(billInfo.getUcCalledNumber());
            billList.setUcCallAttribute(billInfo.getUcCallAttribute());
            billList.setStartTime(billInfo.getStartTime());
            billList.setEndTime(billInfo.getEndTime());
            billList.setDwConversationTime(convsationTime);
                if(billInfo.getUcCalledNumber().equals(longNum)||billInfo.getUcCalledNumber().equals(shortNum)){
                    sb.append("\"被叫免费\"");
                    billList.setAreaCode("被叫免费");
                }
                else {
                    callFee = manager.getFinalFee(billInfo.getUcCallAttribute(), billInfo.getDwConversationTime(), userGroup, billInfo.getStartTime(), Integer.parseInt(balance), longNum, shortNum, billInfo.getUcCallerNumber(), billInfo.getUcCalledNumber(),convTime,totalTime);
                    sb.append("\"" + callFee + "\"");
                    totalTime += convsationTime;
                    billList.setAreaCode(callFee);
                }
                sb.append("],");
                listExport.add(billList);
        }
        if(sb.length()>2){
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        sb.append("]");
        HttpServletRequest request = ServletActionContext.getRequest();
        request.getSession().setAttribute("userCalledList",listExport);
        out.println(sb);
        out.flush();
        out.close();
    }

    /**
     * 获取用户全部通话记录
     * @throws IOException
     */
    public void getUserAllCalled() throws IOException{
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=GBK");
        PrintWriter out = response.getWriter();
        List li = new ArrayList();
        li = impl.getUserCalledDetail(longNum,shortNum,null,null,null);
        Iterator it = li.iterator();
        tbl_billInfo billInfo = null;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (it.hasNext()){
            billInfo = (tbl_billInfo)it.next();
            sb.append("[")
                    .append("\""+billInfo.getUcCallerNumber()+"\",")
                    .append("\""+billInfo.getUcCalledNumber()+"\",")
                    .append("\""+tool.AttributeCode2Str(billInfo.getUcCallAttribute())+"\",")
                    .append("\""+billInfo.getStartTime()+"\",")
                    .append("\"" + billInfo.getEndTime() + "\",")
                    .append("\"" + tool.second2Minute(billInfo.getDwConversationTime()) + "\",")
                    .append("\"--\"")
                    .append("],");
        }
        if(sb.length()>2){
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        sb.append("]");
        out.println(sb);
        out.flush();
        out.close();
    }

    /**
     * 获取用户时间段通话
     * @throws IOException
     */
    public void getUserCalledByTime() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=GBK");
        PrintWriter out = response.getWriter();
        List li = new ArrayList();
        li = impl.getUserCalledDetail(longNum,shortNum,null,startTime,endTime);
        Iterator it = li.iterator();
        tbl_billInfo billInfo = null;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (it.hasNext()){
            billInfo = (tbl_billInfo)it.next();
            sb.append("[")
                    .append("\""+billInfo.getUcCallerNumber()+"\",")
                    .append("\""+billInfo.getUcCalledNumber()+"\",")
                    .append("\""+tool.AttributeCode2Str(billInfo.getUcCallAttribute())+"\",")
                    .append("\""+billInfo.getStartTime()+"\",")
                    .append("\"" + billInfo.getEndTime() + "\",")
                    .append("\"" + tool.second2Minute(billInfo.getDwConversationTime()) + "\",")
                    .append("\"--\"")
                    .append("],");
        }
        if(sb.length()>2){
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        sb.append("]");
        out.println(sb);
        out.flush();
        out.close();
    }

}
