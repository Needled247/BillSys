package com.bill.action;

import com.bill.dao.BillSysDAOImpl;
import com.opensymphony.xwork2.ActionSupport;

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
        return null;
    }
}
