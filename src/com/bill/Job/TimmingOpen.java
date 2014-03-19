package com.bill.Job;

import com.bill.dao.BillSysDAO;
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
 * Time: ����5:20
 */
public class TimmingOpen implements Job{
    private BillSysDAO billService;

    public void setBillService(BillSysDAO billService) {
        this.billService = billService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SshTool register = new SshTool();
        List li = new ArrayList();
        li = billService.getAllUser();
        Iterator iterator = li.iterator();
        gtao_Phone_User user = null;
        BillSysTool tool = new BillSysTool();
        String today = tool.getToday();
        while (iterator.hasNext()){
            user = (gtao_Phone_User)iterator.next();
            //��ͨʱ��ƥ��
            if(tool.NullStrFormatter(user.getEmail()).equals(today)){
                //���ÿ�ͨ����
                if(register.pre_OpenningCompetence(user.getShortNum(),"open")){
                    user.setStatus("�ѿ�ͨ");
                    System.out.println(user.getUserid()+"�ѿ�ͨ");
                    billService.updateUserInfo(user);
                }
            }
            //����ʱ��ƥ��
            if(tool.NullStrFormatter(user.getMaturityTime()).equals(today)){
                //�ر�Ȩ��
                if(register.pre_OpenningCompetence(user.getShortNum(),"close")){
                    user.setStatus("ͣ��");
                    System.out.println(user.getUserid()+"��ͣ��");
                    billService.updateUserInfo(user);
                }
            }
        }
    }
}
