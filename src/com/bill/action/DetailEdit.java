package com.bill.action;

import com.bill.dao.BillSysDAOImpl;
import com.bill.pojo.*;
import com.bill.tool.BillSysTool;
import com.bill.tool.SshTool;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-5-27
 * Time: 上午11:41
 * To change this template use File | Settings | File Templates.
 */
public class DetailEdit extends ActionSupport {
    private String editType;
    private String phoneNum;
    private String userid;
    private String shortNum;
    private String mobile;
    private String phoneIp;
    private String vlan;
    private String installer;
    private String installTime;
    private String ipadd;
    private String finalNum;
    private String finalUser;
    private String tbl;
    private String userGroup;
    private String Stored;
    private String gate;
    private String protocal;
    private String isPay;
    private String Balance;
    private String opentime;
    private String endtime;
    BillSysDAOImpl impl = new BillSysDAOImpl();

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String pay) {
        isPay = pay;
    }

    public String getProtocal() {
        return protocal;
    }

    public void setProtocal(String protocal) {
        this.protocal = protocal;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getStored() {
        return Stored;
    }

    public void setStored(String stored) {
        Stored = stored;
    }

    public String getTbl() {
        return tbl;
    }

    public void setTbl(String tbl) {
        this.tbl = tbl;
    }

    public String getFinalNum() {
        return finalNum;
    }

    public void setFinalNum(String finalNum) {
        this.finalNum = finalNum;
    }

    public String getFinalUser() {
        return finalUser;
    }

    public void setFinalUser(String finalUser) {
        this.finalUser = finalUser;
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getPhoneIp() {
        return phoneIp;
    }

    public void setPhoneIp(String phoneIp) {
        this.phoneIp = phoneIp;
    }

    public String getVlan() {
        return vlan;
    }

    public void setVlan(String vlan) {
        this.vlan = vlan;
    }

    public String getInstaller() {
        return installer;
    }

    public void setInstaller(String installer) {
        this.installer = installer;
    }

    public String getInstallTime() {
        return installTime;
    }

    public void setInstallTime(String installTime) {
        this.installTime = installTime;
    }

    public String getIpadd() {
        return ipadd;
    }

    public void setIpadd(String ipadd) {
        this.ipadd = ipadd;
    }

    @Override
    public String execute() throws Exception {
        boolean flag = false;
        BillSysTool billSysTool = new BillSysTool();
        SshTool sshTool = new SshTool();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        String regIp="";
        if(phoneIp!=null){
            regIp = phoneIp.substring(0,phoneIp.lastIndexOf("/"));
        }
        //编辑
        //开通时间是当天
        if(editType.equals("update")&&(opentime.equals(billSysTool.getToday())|| Date.valueOf(opentime).before(Date.valueOf(billSysTool.getToday())))){
            flag = sshTool.CreateUser(phoneNum, shortNum, regIp, protocal, userid);
            if(flag){
               if(this.updateDetailApply("已开通")){
                    out.print("update_success");
                 }
                else{
                    out.print("error");
                 }
            }
            else {
                out.print("reg_error");
            }
        }
        //开通时间不是当天
        else if(editType.equals("update")&&!opentime.equals(billSysTool.getToday())){
            flag = sshTool.pre_Oppening(phoneNum, shortNum, regIp, userid, protocal);
            if(flag){
                if(this.updateDetailApply("未开通")){
                    out.print("update_success");
                }
                else{
                    out.print("error");
                }
            }
            else {
                out.print("reg_error");
            }
        }
        //初始化
        if(editType.equals("init")||editType=="init"){
            flag = this.initDetailApply();
            if(flag){
                out.print("init_success");
            }
            else {
                out.print("error");
            }
        }
        //首页初始化
        if(editType.equals("page_init")||editType=="page_init"){
            flag = this.initDetailApply();
            if(flag){
                response.sendRedirect("Success.jsp");
            }
            else {
                out.print("Error.jsp");
            }
        }
        //开户
        if(editType.equals("create")){
            boolean creatMark = false;
            //付费号码  开通时间是今天
            if(tbl.contains("sale")&&opentime.equals(billSysTool.getToday())){
                if(sshTool.CreateUser(phoneNum,shortNum,regIp,protocal,userid)){
                    gtao_Phone_bc_sale sale = new gtao_Phone_bc_sale();
                    sale.setLongNum(phoneNum);
                    sale.setShortNum(shortNum);
                    sale.setUserId(userid);
                    sale.setType(protocal);
                    sale.setMobile(mobile);
                    sale.setIp(phoneIp);
                    sale.setInstaller(installer);
                    sale.setInstallTime(installTime);
                    sale.setGate(gate);
                    sale.setIsPay(isPay);
                    this.impl.createAccount(null,sale,tbl,phoneNum);
                    if(this.CreateUser("已开通")){
                        out.print("success");
                    }
                    else {
                        out.print("error");
                    }
                }
                else {
                    out.print("reg_error");
                }
            }
            //付费号码  开通时间不是当天
            else if(tbl.contains("sale")&&!opentime.equals(billSysTool.getToday())){
                if(sshTool.pre_Oppening(phoneNum,shortNum,regIp,userid,protocal)){
                    gtao_Phone_bc_sale sale = new gtao_Phone_bc_sale();
                    sale.setLongNum(phoneNum);
                    sale.setShortNum(shortNum);
                    sale.setUserId(userid);
                    sale.setType(protocal);
                    sale.setMobile(mobile);
                    sale.setIp(phoneIp);
                    sale.setInstaller(installer);
                    sale.setInstallTime(installTime);
                    sale.setGate(gate);
                    sale.setIsPay(isPay);
                    this.impl.createAccount(null,sale,tbl,phoneNum);
                    if(this.CreateUser("未开通")){
                        out.print("success");
                    }
                    else {
                        out.print("error");
                    }
                }
                else {
                    out.print("reg_error");
                }
            }
            //免费号码 开通时间是当天
            else if(!tbl.contains("sale")&&opentime.equals(billSysTool.getToday())) {
                if(sshTool.CreateUser(phoneNum,shortNum,regIp,protocal,userid)){
                    gtao_phone_view view = new gtao_phone_view();
                    view.setLongNum(phoneNum);
                    view.setUserId(userid);
                    view.setShortNum(shortNum);
                    view.setMobile(mobile);
                    view.setType(protocal);
                    view.setIp(phoneIp);
                    view.setVlan(vlan);
                    view.setInstaller(installer);
                    view.setInstallTime(installTime);
                    view.setGate(gate);
                    this.impl.createAccount(view,null,tbl,phoneNum);
                    if(this.CreateUser("已开通")){
                        out.print("success");
                    }
                    else {
                        out.print("error");
                    }
                }
                else {
                    out.print("reg_error");
                }
            }
            //免费号码  开通时间不是当天
            else if(!tbl.contains("sale")&&!opentime.equals(billSysTool.getToday())){
                if(sshTool.pre_Oppening(phoneNum,shortNum,regIp,userid,protocal)){
                    gtao_phone_view view = new gtao_phone_view();
                    view.setLongNum(phoneNum);
                    view.setUserId(userid);
                    view.setShortNum(shortNum);
                    view.setMobile(mobile);
                    view.setType(protocal);
                    view.setIp(phoneIp);
                    view.setVlan(vlan);
                    view.setInstaller(installer);
                    view.setInstallTime(installTime);
                    view.setGate(gate);
                    this.impl.createAccount(view,null,tbl,phoneNum);
                    if(this.CreateUser("未开通")){
                        out.print("success");
                    }
                    else {
                        out.print("error");
                    }
                }
                else {
                    out.print("reg_error");
                }
            }
        }
        //回收资源
        out.flush();
        out.close();
        return null;
    }

    public boolean CreateUser(String status){
        boolean flag = false;
        gtao_Phone_User user = new gtao_Phone_User();
        user.setLongNum(phoneNum);
        user.setUserid(userid);
        user.setShortNum(shortNum);
        user.setItime(new BillSysTool().getCurrentTime());
        user.setMobile(mobile);
        user.setPhoneIp(phoneIp);
        user.setVlan(vlan);
        user.setStored(Stored);
        user.setTactics(userGroup);
        user.setTbl(tbl);
        user.setGate(gate);
        user.setProtocal(protocal);
        user.setBalance(Balance);
        user.setEmail(opentime); //开通时间
        user.setMaturityTime(endtime);
        if(impl.userRegister(user)){
            BillSys_User userInfo = new BillSys_User();
            userInfo.setUsername(userid);
            userInfo.setPassword(phoneNum);
            userInfo.setLevel(2);
            //addUser
            impl.addUser(userInfo);
            flag = true;
        }
        else {
            flag = false;
        }
        return flag;
    }

    /**
     * 功能：更新数据库订单数据。
     * @return flag
     */
    public boolean updateDetailApply(String status){
        boolean flag = false;
        gtao_phone_view view = new gtao_phone_view();
        view.setLongNum(phoneNum);
        view.setUserId(userid);
        view.setShortNum(shortNum);
        view.setMobile(mobile);
        view.setType(protocal);
        view.setIp(phoneIp);
        view.setVlan(vlan);
        view.setInstaller(installer);
        view.setInstallTime(installTime);
        view.setGate(gate);
        String tbl_name = this.AreaSelect(ipadd);
        flag = impl.editApplyDetail(view,tbl_name,finalNum,finalUser);
        if(flag){
            gtao_Phone_User user = new gtao_Phone_User();
            user.setLongNum(phoneNum);
            user.setUserid(userid);
            user.setShortNum(shortNum);
            user.setItime(new BillSysTool().getCurrentTime());
            user.setMobile(mobile);
            user.setPhoneIp(phoneIp);
            user.setVlan(vlan);
            user.setBalance(Balance);
            user.setTactics(userGroup);
            user.setTbl(tbl_name);
            user.setGate(gate);
            user.setProtocal(protocal);
            user.setMaturityTime(endtime);
            user.setEmail(opentime);  //开通时间
            user.setStatus(status);
            try{
                flag = impl.userRegister(user);
                BillSys_User userInfo = new BillSys_User();
                userInfo.setUsername(userid);
                userInfo.setPassword(phoneNum);
                userInfo.setLevel(2);
                //addUser
                flag = impl.addUser(userInfo);
            }
            catch (HibernateException e){
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }


    /**
     * 功能：初始化申请数据，清空值
     * @return
     */
    public boolean initDetailApply(){
        boolean flag = false;
        String tbl_name = this.AreaSelect(ipadd);
        flag = impl.initApplyDetail(tbl_name,finalNum,finalUser);
        return flag;
    }

    /**
     * 功能：根据IP地址获取地域
     * @param ipadd
     * @return 地域相应表名。
     */
    public String AreaSelect(String ipadd){
        String tbl_name = "";
        String ipStr = ipadd.substring(0, ipadd.indexOf(".",ipadd.indexOf(".")+1 ));
        List<gtao_Phone_MIME> mimeLi = new ArrayList<gtao_Phone_MIME>();
        mimeLi = impl.getAreaMime();
        Iterator<gtao_Phone_MIME> it = mimeLi.iterator();
        for(gtao_Phone_MIME gpm:mimeLi){
            if(ipStr.matches(gpm.getIpregex())){
                tbl_name = gpm.getDepartment();
            }
        }
        return tbl_name;
    }
}
