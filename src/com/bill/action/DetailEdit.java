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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-5-27
 * Time: ����11:41
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
    BillSysDAOImpl impl = new BillSysDAOImpl();

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
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        String regIp="";
        if(phoneIp!=null){
            regIp = phoneIp.substring(0,phoneIp.lastIndexOf("/"));
        }
        //�༭
        if(editType.equals("update")||editType=="update"){
            flag = new SshTool().CreateUser(phoneNum,shortNum,regIp,protocal,userid);
            if(flag){
               if(this.updateDetailApply()){
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
        //��ʼ��
        if(editType.equals("init")||editType=="init"){
            flag = this.initDetailApply();
            if(flag){
                out.print("init_success");
            }
            else {
                out.print("error");
            }
        }
        //��ҳ��ʼ��
        if(editType.equals("page_init")||editType=="page_init"){
            flag = this.initDetailApply();
            if(flag){
                response.sendRedirect("Success.jsp");
            }
            else {
                out.print("Error.jsp");
            }
        }
        //����
        if(editType.equals("create")){
            boolean creatMark = false;
            if(tbl.contains("sale")){
                if(new SshTool().CreateUser(phoneNum,shortNum,regIp,protocal,userid)){
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
                    creatMark = impl.createAccount(null,sale,tbl,phoneNum);
                }
                else {
                    out.print("reg_error");
                }
            }
            else {
                if(new SshTool().CreateUser(phoneNum,shortNum,regIp,protocal,userid)){
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
                    creatMark = impl.createAccount(view,null,tbl,phoneNum);
                }
                else {
                    out.print("reg_error");
                }
            }
            if(creatMark){
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
                if(impl.userRegister(user)){
                    BillSys_User userInfo = new BillSys_User();
                    userInfo.setUsername(userid);
                    userInfo.setPassword(phoneNum);
                    userInfo.setLevel(2);
                    //addUser
                    impl.addUser(userInfo);
                    out.print("success");
                }
                else {
                    out.print("error");
                }
            }
        }
        //������Դ
        out.flush();
        out.close();
        return null;
    }

    /**
     * ���ܣ��������ݿⶩ�����ݡ�
     * @return flag
     */
    public boolean updateDetailApply(){
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
     * ���ܣ���ʼ���������ݣ����ֵ
     * @return
     */
    public boolean initDetailApply(){
        boolean flag = false;
        String tbl_name = this.AreaSelect(ipadd);
        flag = impl.initApplyDetail(tbl_name,finalNum,finalUser);
        return flag;
    }

    /**
     * ���ܣ�����IP��ַ��ȡ����
     * @param ipadd
     * @return ������Ӧ������
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
