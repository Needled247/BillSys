package com.bill.action;

import com.bill.dao.BillSysDAO;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-10-24
 * Time: ÉÏÎç9:55
 * To change this template use File | Settings | File Templates.
 */
public class MonthlyAction extends ActionSupport {
    private String userClass;
    private String type;
    private String year;
    private String month;
    private BillSysDAO billService;

    public BillSysDAO getBillService() {
        return billService;
    }

    public void setBillService(BillSysDAO billService) {
        this.billService = billService;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String execute() throws Exception {
        if(type.equals("out")){
            this.monthlyExecute();
        }
        if(type.equals("cost")){
            this.costExecute();
        }
        return null;
    }

    public void monthlyExecute() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=GBK");
        PrintWriter out = response.getWriter();
        String tbl = "tbl_billInfo"+year+month;
        List li = new ArrayList();
        li = billService.getOverTimeUser(tbl,userClass);
        if(!li.isEmpty()){
            Iterator it = li.iterator();
        }
    }

    public void costExecute() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=GBK");
        PrintWriter out = response.getWriter();
    }
}
