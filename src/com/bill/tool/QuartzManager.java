package com.bill.tool;

import com.bill.Job.TimmingOpen;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-9-13
 * Time: 上午9:47
 * Quartz任务调度管理类
 */
public class QuartzManager {
    private static String JOB_NAME = "TIMING_OPEN";
    private static String JOB_GROUP = "BILL_JOB";
    private static String TRIGGER_NAME = "TIMING_OPEN_TRIGGER";
    private static String TRIGGER_GROUP = "BILL_TRIGGER";

    public void TaskBegin(){
        try{
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            //1秒间隔，永远重复执行
            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(TRIGGER_NAME,TRIGGER_GROUP)
                    .startAt(DateBuilder.nextGivenSecondDate(null,2)).withSchedule(simpleScheduleBuilder).build();
            JobDetail jobDetail = JobBuilder.newJob(TimmingOpen.class).withIdentity(JOB_NAME,JOB_GROUP).build();
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
        }
        catch (SchedulerException e){
            e.printStackTrace();
        }
    }
}
