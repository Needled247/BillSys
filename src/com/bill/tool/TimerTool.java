package com.bill.tool;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

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
        System.out.println("Timer Scheduler Start!");

    }

    /**
     * Quartz实现定时执行任务
     */
    protected void initScheduler(ServletConfig config){
        try{
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
