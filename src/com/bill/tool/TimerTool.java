package com.bill.tool;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * ��ʱ����
 * Author:����
 * Date: 13-9-10
 * Time: ����2:07
 */
public class TimerTool  extends HttpServlet{
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("�������Servlet�Ѽ���");
        new QuartzManager().TaskBegin();
    }
}
