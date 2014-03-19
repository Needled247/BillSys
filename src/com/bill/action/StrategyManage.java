package com.bill.action;

import com.bill.dao.BillSysDAO;
import com.bill.pojo.gtao_phone_celue;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-6-17
 * Time: 下午4:18
 * To change this template use File | Settings | File Templates.
 */
public class StrategyManage extends ActionSupport {
    private String type;
    private String userGroup;
    private String profile;
    private String prefix;
    private String ratestime1;
    private String rates1;
    private String ratestime2;
    private String rates2;
    private String ratestime3;
    private String rates3;
    private String otherfee;
    private String specialtime1;
    private String specialtimeend1;
    private String specialtime2;
    private String specialtimeend2;
    private String specialtime3;
    private String specialtimeend3;
    private String specialfee1;
    private String specialfee2;
    private String specialfee3;
    private int id;
    private BillSysDAO billService;

    public BillSysDAO getBillService() {
        return billService;
    }

    public void setBillService(BillSysDAO billService) {
        this.billService = billService;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFinalPrefix() {
        return finalPrefix;
    }

    public void setFinalPrefix(String finalPrefix) {
        this.finalPrefix = finalPrefix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String finalPrefix;

    public String getRatestime1() {
        return ratestime1;
    }

    public void setRatestime1(String ratestime1) {
        this.ratestime1 = ratestime1;
    }

    public String getRates1() {
        return rates1;
    }

    public void setRates1(String rates1) {
        this.rates1 = rates1;
    }

    public String getRatestime2() {
        return ratestime2;
    }

    public void setRatestime2(String ratestime2) {
        this.ratestime2 = ratestime2;
    }

    public String getRates2() {
        return rates2;
    }

    public void setRates2(String rates2) {
        this.rates2 = rates2;
    }

    public String getRatestime3() {
        return ratestime3;
    }

    public void setRatestime3(String ratestime3) {
        this.ratestime3 = ratestime3;
    }

    public String getRates3() {
        return rates3;
    }

    public void setRates3(String rates3) {
        this.rates3 = rates3;
    }

    public String getOtherfee() {
        return otherfee;
    }

    public void setOtherfee(String otherfee) {
        this.otherfee = otherfee;
    }

    public String getSpecialtime1() {
        return specialtime1;
    }

    public void setSpecialtime1(String specialtime1) {
        this.specialtime1 = specialtime1;
    }

    public String getSpecialtimeend1() {
        return specialtimeend1;
    }

    public void setSpecialtimeend1(String specialtimeend1) {
        this.specialtimeend1 = specialtimeend1;
    }

    public String getSpecialtime2() {
        return specialtime2;
    }

    public void setSpecialtime2(String specialtime2) {
        this.specialtime2 = specialtime2;
    }

    public String getSpecialtimeend2() {
        return specialtimeend2;
    }

    public void setSpecialtimeend2(String specialtimeend2) {
        this.specialtimeend2 = specialtimeend2;
    }

    public String getSpecialtime3() {
        return specialtime3;
    }

    public void setSpecialtime3(String specialtime3) {
        this.specialtime3 = specialtime3;
    }

    public String getSpecialtimeend3() {
        return specialtimeend3;
    }

    public void setSpecialtimeend3(String specialtimeend3) {
        this.specialtimeend3 = specialtimeend3;
    }

    public String getSpecialfee1() {
        return specialfee1;
    }

    public void setSpecialfee1(String specialfee1) {
        this.specialfee1 = specialfee1;
    }

    public String getSpecialfee2() {
        return specialfee2;
    }

    public void setSpecialfee2(String specialfee2) {
        this.specialfee2 = specialfee2;
    }

    public String getSpecialfee3() {
        return specialfee3;
    }

    public void setSpecialfee3(String specialfee3) {
        this.specialfee3 = specialfee3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String execute() throws Exception {
        //准备response
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=GBK");
        PrintWriter out = response.getWriter();
        StringBuilder sb = new StringBuilder();
        /**
         * type = search 查看所有计费策略，页面初始化。
         */
        if(type.equals("search")||type=="search"){
            List li = new ArrayList();
            String tblName = billService.getTblNameByGroupId(userGroup);
            li = billService.getAllCelue(tblName);
            gtao_phone_celue celue = new gtao_phone_celue();
            Iterator it = li.iterator();
            sb.append("[");
            while (it.hasNext()){
                celue = (gtao_phone_celue)it.next();
                sb.append("[")
                        .append("\""+celue.getID()+"\",")
                        .append("\""+celue.getSPREFIX()+"\",")
                        .append("\""+celue.getSPROFILE()+"\"")
                        .append("],");
            }
            //去除最后一个逗号
            if(sb.length()>1){
                sb.deleteCharAt(sb.lastIndexOf(","));
            }
            sb.append("]");
            out.println(sb);
        }
        /**
         * type==update ，保存修改后的数据
         */
        if(type.equals("update")||type=="update"){
            gtao_phone_celue celue = new gtao_phone_celue();
            celue.setID(id);
            celue.setSPROFILE(profile);
            celue.setSPREFIX(prefix);
            celue.setRATESTIME1(ratestime1);
            celue.setRATESTIME2(ratestime2);
            celue.setRATESTIME3(ratestime3);
            celue.setRATES1(rates1);
            celue.setRATES2(rates2);
            celue.setRATES3(rates3);
            celue.setOTHERFEE(otherfee);
            celue.setSPECIALTIMEBEGIN1(specialtime1);
            celue.setSPECIALTIMEBEGIN2(specialtime2);
            celue.setSPECIALTIMEBEGIN3(specialtime3);
            celue.setSPECIALTIMEEND1(specialtimeend1);
            celue.setSPECIALTIMEEND2(specialtimeend2);
            celue.setSPECIALTIMEEND3(specialtimeend3);
            celue.setSPECIALTIMEFEE1(specialfee1);
            celue.setSPECIALTIMEFEE2(specialfee2);
            celue.setSPECIALTIMEFEE3(specialfee3);
            boolean flag = billService.updateStrategyInfo(celue,userGroup);
            response.setContentType("text/html;charset=GBK");
            out = response.getWriter();
            if (flag){
                out.print("success");
            }
            else {
                out.print("fail");
            }
        }
        /**
         * type==save，保存新策略
         */
        if(type.equals("save")||type=="save"){
            //装载bean
            gtao_phone_celue celue = new gtao_phone_celue();
            celue.setSPROFILE(profile);
            celue.setSPREFIX(prefix);
            celue.setRATESTIME1(ratestime1);
            celue.setRATESTIME2(ratestime2);
            celue.setRATESTIME3(ratestime3);
            celue.setRATES1(rates1);
            celue.setRATES2(rates2);
            celue.setRATES3(rates3);
            celue.setOTHERFEE(otherfee);
            celue.setSPECIALTIMEBEGIN1(specialtime1);
            celue.setSPECIALTIMEBEGIN2(specialtime2);
            celue.setSPECIALTIMEBEGIN3(specialtime3);
            celue.setSPECIALTIMEEND1(specialtimeend1);
            celue.setSPECIALTIMEEND2(specialtimeend2);
            celue.setSPECIALTIMEEND3(specialtimeend3);
            celue.setSPECIALTIMEFEE1(specialfee1);
            celue.setSPECIALTIMEFEE2(specialfee2);
            celue.setSPECIALTIMEFEE3(specialfee3);
            //调用dao方法保存
            boolean flag = billService.saveStrategyInfo(celue,userGroup);
            response.setContentType("text/html;charset=GBK");
            out = response.getWriter();
            if (flag){
                out.print("success");
            }
            else {
                out.print("fail");
            }
        }
        /**
         * type==del,删除计费策略
         */
        if(type.equals("del")||type=="del"){
            boolean flag = billService.deleteStrategyInfo(prefix,userGroup);
            if(flag){
                response.sendRedirect("UserGroup.jsp");
            }
        }
        out.flush();
        out.close();
        return null;
    }
}
