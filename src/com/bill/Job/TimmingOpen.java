package com.bill.Job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-9-13
 * Time: 下午5:20
 * To change this template use File | Settings | File Templates.
 */
public class TimmingOpen implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务正在运行...");
    }
}
