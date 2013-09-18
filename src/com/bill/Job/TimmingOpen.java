package com.bill.Job;

import com.bill.dao.BillSysDAOImpl;
import com.bill.pojo.gtao_Phone_User;
import com.bill.tool.BillSysTool;
import com.bill.tool.SshTool;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-9-13
 * Time: 下午5:20
 */
public class TimmingOpen implements Job{
    BillSysDAOImpl impl = new BillSysDAOImpl();
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SshTool register = new SshTool();
        List li = new ArrayList();
        li = impl.getAllUser();
        Iterator iterator = li.iterator();
        gtao_Phone_User user = null;
        BillSysTool tool = new BillSysTool();
        String today = tool.getToday();
        while (iterator.hasNext()){
            user = (gtao_Phone_User)iterator.next();
            //开通时间匹配
            if(user.getEmail().equals(today)){
                //调用开通方法
                register.pre_OpenningCompetence(user.getShortNum(),"open");
            }
            //到期时间匹配
            if(user.getMaturityTime().equals(today)){
                //关闭权限
                register.pre_OpenningCompetence(user.getShortNum(),"close");
            }
        }
    }
}
