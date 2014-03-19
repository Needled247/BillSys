package com.bill.action;

import com.bill.dao.BillSysDAO;
import com.bill.pojo.gtao_Phone_bc_sale;
import com.bill.pojo.gtao_phone_view;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-6-4
 * Time: ÉÏÎç11:01
 * To change this template use File | Settings | File Templates.
 */
public class EditNumInfo extends ActionSupport {
    private String userId;
    private String phoneNum;
    private String shortNum;
    private String mobile;
    private String phoneIp;
    private String vlan;
    private String tblName;
    private String EditType;
    private String finalNum;
    private String money;
    private String gate;
    private BillSysDAO billService;

    public BillSysDAO getBillService() {
        return billService;
    }

    public void setBillService(BillSysDAO billService) {
        this.billService = billService;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFinalNum() {
        return finalNum;
    }

    public void setFinalNum(String finalNum) {
        this.finalNum = finalNum;
    }

    public String getEditType() {
        return EditType;
    }

    public void setEditType(String editType) {
        EditType = editType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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

    public String getTblName() {
        return tblName;
    }

    public void setTblName(String tblName) {
        this.tblName = tblName;
    }

    @Override
    public String execute() throws Exception {
        boolean flag = false;
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=GBK");
        PrintWriter out = response.getWriter();
        //Ãâ·Ñ±à¼­
        if(EditType.equals("freeUpdate")||EditType=="freeUpdate"){
            gtao_phone_view view = new gtao_phone_view();
            view.setUserId(userId);
            view.setLongNum(phoneNum);
            view.setShortNum(shortNum);
            view.setMobile(mobile);
            view.setIpAdd(phoneIp);
            view.setVlan(vlan);
            view.setGate(gate);
            flag = billService.editFreeNumInfo(view,tblName, finalNum);
            if(flag){
                out.print("success");
            }
            else {
                out.print("error");
            }
        }
        //Ãâ·ÑÉ¾³ý
        else if(EditType.equals("freeDelete")||EditType=="freeDelete"){
            flag = billService.delFreeNumInfo(phoneNum,tblName);
            if(flag){
                out.print("success");
            }
            else {
                out.print("error");
            }
        }
        //Ãâ·Ñ³õÊ¼»¯
        else if(EditType.equals("freeInit")||EditType=="freeInit"){
            flag = billService.initFreeNum(phoneNum,tblName);
            if(flag){
                out.print("init_success");
            }
            else {
                out.print("init_error");
            }
        }
        //¸¶·Ñ±à¼­
        else if(EditType.equals("saleUpdate")||EditType=="saleUpdate"){
            gtao_Phone_bc_sale sale = new gtao_Phone_bc_sale();
            sale.setUserId(userId);
            sale.setLongNum(phoneNum);
            sale.setShortNum(shortNum);
            sale.setMobile(mobile);
            sale.setIpAdd(phoneIp);
            sale.setVlan(vlan);
            sale.setMoney(money);
            sale.setGate(gate);
            flag = billService.editSaleNumInfo(sale,phoneNum);
            if(flag){
                out.print("success");
            }
            else {
                out.print("error");
            }
        }
        //¸¶·ÑÉ¾³ý
        else if(EditType.equals("saleDelete")||EditType=="saleDelete"){
            flag = billService.delSaleNumInfo(phoneNum);
            if(flag){
                out.print("success");
            }
            else {
                out.print("error");
            }
        }
        out.flush();
        out.close();
        return null;
    }
}
