package com.bill.action;

import com.bill.bean.tbl_billInfo;
import com.bill.dao.BillSysDAOImpl;
import com.bill.tool.BillSysTool;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-6-26
 * Time: 下午5:45
 * To change this template use File | Settings | File Templates.
 */
public class CallHistory extends ActionSupport {
    private String year;
    private String month;
    private String phoneNum;
    private String callType;
    private String type;
    private String datefrom;
    private String dateto;
    private String longNum2;

    public String getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public String getDateto() {
        return dateto;
    }

    public void setDateto(String dateto) {
        this.dateto = dateto;
    }

    public String getLongNum2() {
        return longNum2;
    }

    public void setLongNum2(String longNum2) {
        this.longNum2 = longNum2;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        /**
         * type==init,初始化页面时加载的部分。
         */
        if(type.equals("init")||type=="init"){
            String dateStr = year+month;
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/json;charset=GBK");
            PrintWriter out = response.getWriter();
            List li = new ArrayList();
            li = new BillSysDAOImpl().getCallHistoryByMonth(dateStr,new BillSysTool().getToday());
            tbl_billInfo bill = new tbl_billInfo();
            Iterator it = li.iterator();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            while (it.hasNext()){
                bill = (tbl_billInfo)it.next();
                sb.append("[")
                        .append("\""+bill.getID()+"\",")
                        .append("\"" + bill.getUcCallerNumber() + "\",")
                        .append("\""+bill.getUcCalledNumber()+"\",")
                        .append("\""+bill.getUcCallAttribute()+"\",")
                        .append("\"" + sdf.format(bill.getStartTime()) + "\",")
                        .append("\""+sdf.format(bill.getEndTime())+"\",")
                        .append("\""+bill.getDwConversationTime()+"\"")
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
         * type==search
         */
        if(type.equals("search")||type=="search"){
            String dateStr = year+month;
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/json;charset=GBK");
            PrintWriter out = response.getWriter();
            List li = new ArrayList();
            li = new BillSysDAOImpl().getCallHistory(dateStr,phoneNum,callType);
            request.getSession().setAttribute("bill_month_list",li);
            tbl_billInfo info = new tbl_billInfo();
            Iterator it = li.iterator();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            while (it.hasNext()){
                info = (tbl_billInfo)it.next();
                sb.append("[")
                        .append("\""+info.getID()+"\",")
                        .append("\"" + info.getUcCallerNumber() + "\",")
                        .append("\""+info.getUcCalledNumber()+"\",")
                        .append("\""+info.getUcCallAttribute()+"\",")
                        .append("\""+sdf.format(info.getStartTime())+"\",")
                        .append("\""+sdf.format(info.getEndTime())+"\",")
                        .append("\"" + info.getDwConversationTime() + "\"")
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
         * type==dateSearch，按时间段查询
         */
        if(type.equals("dateSearch")||type=="dateSearch"){
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/json;charset=GBK");
            PrintWriter out = response.getWriter();
            StringBuilder sb = new StringBuilder();
            List li = new BillSysDAOImpl().getCallHistoryByDate(datefrom,dateto,longNum2);
            request.getSession().setAttribute("bill_date_list",li);
            Iterator it = li.iterator();
            tbl_billInfo bill = new tbl_billInfo();
            sb.append("[");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            while (it.hasNext()){
                bill = (tbl_billInfo)it.next();
                sb.append("[")
                        .append("\""+bill.getID()+"\",")
                        .append("\"" + bill.getUcCallerNumber() + "\",")
                        .append("\""+bill.getUcCalledNumber()+"\",")
                        .append("\""+bill.getUcCallAttribute()+"\",")
                        .append("\"" + sdf.format(bill.getStartTime()) + "\",")
                        .append("\""+sdf.format(bill.getEndTime())+"\",")
                        .append("\""+bill.getDwConversationTime()+"\"")
                        .append("],");
            }
            if(sb.length()>2){
                sb.deleteCharAt(sb.lastIndexOf(","));
            }
            sb.append("]");
            out.print(sb);
            out.flush();
            out.close();
        }
        return null;
    }
}
