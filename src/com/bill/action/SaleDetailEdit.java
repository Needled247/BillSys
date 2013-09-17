package com.bill.action;

import com.bill.dao.BillSysDAOImpl;
import com.bill.pojo.BillSys_User;
import com.bill.pojo.gtao_Phone_User;
import com.bill.pojo.gtao_Phone_bc_sale;
import com.bill.tool.BillSysTool;
import com.bill.tool.SshTool;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-5-27
 * Time: 上午11:41
 * To change this template use File | Settings | File Templates.
 */
public class SaleDetailEdit extends ActionSupport {
    private String editType;
    private String phoneNum;
    private String userid;
    private String shortNum;
    private String mobile;
    private String phoneIp;
    private String vlan;
    private String installer;
    private String installTime;
    private String money;
    private String pay;
    private String userGroup;
    private String Stored;
    private String finalNum;
    private String finalUser;
    private String protocal;
    private String gate;
    private String tbl;
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

    public String getTbl() {
        return tbl;
    }

    public void setTbl(String tbl) {
        this.tbl = tbl;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
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

    @Override
    public String execute() throws Exception {
        boolean flag = false;
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=GBK");
        String regIp = "";
        if(phoneIp!=null){
            regIp = phoneIp.substring(0,phoneIp.lastIndexOf("/"));
        }
        PrintWriter out = response.getWriter();
        //编辑
        if(editType.equals("update")){
            flag = new SshTool().CreateUser(phoneNum,shortNum,regIp,protocal,userid);
            if(flag){
                if(this.updateSaleDetailApply()){
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
            flag = this.initSaleDetailApply();
            if(flag){
                out.print("init_success");
            }
            else {
                out.print("error");
            }
        }
        //首页初始化
        if(editType.equals("page_init")||editType=="page_init"){
            flag = this.initSaleDetailApply();
            if(flag){
                response.sendRedirect("sSuccess.jsp");
            }
            else {
                out.print("<script type=\"text/javascript\">alert(\"初始化失败！请重试...\");</script>");
            }
        }
        //回收资源
        out.flush();
        out.close();
        return null;
    }

    /**
     * 功能：更新数据库订单数据。
     * @return flag
     */
    public boolean updateSaleDetailApply(){
        boolean flag = false;
        gtao_Phone_bc_sale sale = new gtao_Phone_bc_sale();
        sale.setLongNum(phoneNum);
        sale.setUserId(userid);
        sale.setShortNum(shortNum);
        sale.setMobile(mobile);
        sale.setIp(phoneIp);
        sale.setVlan(vlan);
        sale.setType(protocal);
        sale.setInstaller(installer);
        sale.setInstallTime(installTime);
        sale.setMoney(money);
        sale.setIsPay(pay);
        sale.setGate(gate);
        flag = impl.editSaleApplyDetail(sale,finalNum,finalUser,tbl);
        if(flag){
            gtao_Phone_User user = new gtao_Phone_User();
            user.setLongNum(phoneNum);
            user.setUserid(userid);
            user.setShortNum(shortNum);
            user.setMobile(mobile);
            user.setPhoneIp(phoneIp);
            user.setVlan(vlan);
            user.setGate(gate);
            user.setBalance(Balance);
            user.setTactics(userGroup);
            user.setProtocal(protocal);
            user.setTbl(tbl);
            user.setMaturityTime(endtime);
            user.setEmail(opentime); //开通时间
            user.setItime(new BillSysTool().getCurrentTime());
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
                flag = false;
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 功能：初始化申请数据，清空值
     * @return
     */
    public boolean initSaleDetailApply(){
        boolean flag = false;
        flag = impl.initSaleApplyDetail(finalNum,finalUser,tbl);
        return flag;
    }
}
