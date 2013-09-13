package com.bill.tool;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 定时任务
 * Author:蒋浩
 * Date: 13-9-10
 * Time: 下午2:07
 */
public class TimerTool  extends HttpServlet{
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("任务调度Servlet已加载");
        new QuartzManager().TaskBegin();
    }
}
