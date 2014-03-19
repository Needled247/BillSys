package com.bill.action;

import com.bill.dao.BillSysDAO;
import com.bill.dao.BillSysDAOImpl;
import com.bill.pojo.BillSys_User;
import com.bill.pojo.gtao_Phone_User;
import com.bill.tool.SshTool;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-6-22
 * Time: 下午3:39
 * To change this template use File | Settings | File Templates.
 */
public class EditUser extends ActionSupport {
    private int id;
    private String phoneNum;
    private String userId;
    private String shortNum;
    private String mobile;
    private String time;
    private String ipadd;
    private String vlan;
    private String tactics;
    private String status;
    private String balance;
    private String stored;
    private String mtime;
    private String type;
    private String tbl;
    private String protocal;
    private String gate;
    private String opentime;
    private BillSysDAO billService;

    public BillSysDAO getBillService() {
        return billService;
    }

    public void setBillService(BillSysDAO billService) {
        this.billService = billService;
    }

    private List userGroupList = new ArrayList();

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getProtocal() {
        return protocal;
    }

    public void setProtocal(String protocal) {
        this.protocal = protocal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShortNum() {
        return shortNum;
    }

    public void setShortNum(String shortNum) {
        this.shortNum = shortNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIpadd() {
        return ipadd;
    }

    public void setIpadd(String ipadd) {
        this.ipadd = ipadd;
    }

    public String getVlan() {
        return vlan;
    }

    public void setVlan(String vlan) {
        this.vlan = vlan;
    }

    public String getTactics() {
        return tactics;
    }

    public void setTactics(String tactics) {
        this.tactics = tactics;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTbl() {
        return tbl;
    }

    public void setTbl(String tbl) {
        this.tbl = tbl;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getStored() {
        return stored;
    }

    public void setStored(String stored) {
        this.stored = stored;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    @Override
    public String execute() throws Exception {
        SshTool tool = new SshTool();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        /**
         * type==update,修改用户信息
         */
        if(type.equals("update")||type=="update"){
            gtao_Phone_User user = new gtao_Phone_User();
            user.setId(id);
            user.setLongNum(phoneNum);
            user.setShortNum(shortNum);
            user.setUserid(userId);
            user.setMobile(mobile);
            user.setItime(time);
            user.setLastUpd(this.getNow());
            user.setMaturityTime(mtime);
            user.setPhoneIp(ipadd);
            user.setVlan(vlan);
            user.setStatus(status);
            user.setBalance(balance);
            user.setTactics(tactics);
            user.setStored(stored);
            user.setProtocal(protocal);
            user.setTbl(tbl);
            user.setGate(gate);
            user.setEmail(opentime);
            boolean flag = billService.updateUserInfo(user);
            if(flag){
                out.print("success");
            }
            else {
                out.print("fail");
            }
            out.flush();
            out.close();
            //update end.
        }
        /**
         * 删除用户
         */
        if(type.equals("delete")||type=="delete"){
            BillSys_User userInfo = new BillSys_User();
            userInfo.setUsername(userId);
            userInfo.setPassword(mobile);
            userInfo.setLevel(2);
            boolean flag = false;
            gtao_Phone_User user = new gtao_Phone_User();
            user.setId(id);
            user.setLongNum(phoneNum);
            if(tbl.contains("sale")){
                if(tool.DelUser(phoneNum, shortNum, ipadd, protocal, userId)){
                    if(billService.delInfoFromUser(id,phoneNum)){
                        flag = billService.initSaleApplyDetail(phoneNum, userId, tbl);
                        //REMOVE USER
                    }
                }
            }
            else {
                if(tool.DelUser(phoneNum,shortNum,ipadd,protocal,userId)){
                    if(billService.delInfoFromUser(id,phoneNum)){
                        flag = billService.initApplyDetail(tbl, phoneNum, userId);
                        //REMOVE USER
                    }
                }
            }
            if(flag){
                out.print("success");
            }
            else{
                out.print("fail");
            }
            out.flush();
            out.close();
        }
        //开通市话
        if(type.equals("openLocal")){
            if(tool.userCallCompetenceManage(shortNum,type)){
                out.print("openLocalSuccess");
            }
            else {
                out.print("fail");
            }
        }
        //关闭市话
        if(type.equals("offLocal")){
            if(tool.userCallCompetenceManage(shortNum,type)){
                out.print("offLocalSuccess");
            }
            else {
                out.print("fail");
            }
        }
        //开通长途
        if(type.equals("openLong")){
            if(tool.userCallCompetenceManage(shortNum,type)){
                out.print("openLongSuccess");
            }
            else {
                out.print("fail");
            }
        }
        //关闭长途
        if(type.equals("offLong")){
            if(tool.userCallCompetenceManage(shortNum,type)){
                out.print("closeLongSuccess");
            }
            else {
                out.print("fail");
            }
        }
        out.flush();
        out.close();
        return null;
    }

    public String getNow(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        return time;
    }

    public String getUserGroupList(){
        userGroupList = new BillSysDAOImpl().getAllGroup();
        return null;
    }
}
