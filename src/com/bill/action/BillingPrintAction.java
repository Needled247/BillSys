package com.bill.action;

import com.bill.dao.BillSysDAOImpl;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-8-17
 * Time: ����4:15
 * To change this template use File | Settings | File Templates.
 */
public class BillingPrintAction extends ActionSupport {
    BillSysDAOImpl impl = new BillSysDAOImpl();

    @Override
    public String execute() throws Exception {
        //TODO����view�в�ѯ������ע���û���JSON:ID/����/�û��˺�/����ʱ��/Ӫҵ��/
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=GBK");
        PrintWriter out = response.getWriter();

        return null;
    }
}
