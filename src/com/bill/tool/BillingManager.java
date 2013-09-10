package com.bill.tool;

import com.bill.dao.BillSysDAOImpl;
import com.bill.pojo.gtao_phone_celue;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Author:JH
 * Date: 13-8-2
 * Time: ����4:50
 * �Ʒѹ������࣬����9500���������Զ�����ʡ�
 */
public class BillingManager {
    BillSysDAOImpl impl = new BillSysDAOImpl();
    /*
    ��----------------------------------���û���������-------------------------------!
    1��Ҫ�õ��û�һ��ͨ���ķ��ʣ���Ҫ�ĵ�һ���������û��ĳ�������˺�ACCOUNT��
    �Ա�õ����û������ĸ��û���GROUP��
    2��Ȼ��ͨ����ѯ�����õ��û���ͨ���б�LIST��������ȡ���õ�ÿ��ͨ����ʱ��TIME��ͨ������ATTRIBUTE��
    �Ż�ʱ��Balance��
    3����ÿ�μ���֮ǰҪ���жϸô�ͨ���Ƿ�Ϊ�û����С�
    4�������жϸ��û����û��飬�Ƿ�������ʱ�䣬
    ���û�����Ȩ��˳������ʱ��>������ʱ���1>����ʱ���2>����ʱ���3��>������ʱ��1>����ʱ��2>����ʱ��3��
    ���������ʱ�䣨һ��Ϊ300���ӣ�
            | 1���ȼ�����û�����ͨ�����ܳ���
            |2���Ƿ񳬹�������ʱ���������Ʒѣ�����������ʱ��
    5�����û������ʱ����ѳ�������ʱ�䣺
            |1������ͨ����¼List
            |2����ȡ�ô�ͨ����CallAttribute��ͨ�����ԣ���dwConversationTime��ͨ��ʱ�䣩�� StartTime����ʼʱ�䣩
            ����û����û��飨userGroup����Ϊ�����������
            |3�����������Ӧ�ö���һ�����շ�����Ϊ����ֵ�Ĺ��з���������һ����4������������ô�ͨ���ķ��ʡ�
            public String getFinalFee(String CallAttribute,String dwConversationTime,String userGroup��String StartTime);
            |4�������ڲ������Ȳ�ѯ���ݿ�õ����û����Ӧͨ�����ԣ�CallAttribute�������ݣ����浽һ��Bean�С�
            �ڶ������жϸô�ͨ��ʱ��StartTime�Ƿ��ڡ�����ʱ��Ρ��ڣ�����ڣ����ȴ���
            �����������ڡ�����ʱ�Ρ��ڣ��ж�dwConversationTime��ͨ��ʱ�䣩�����Ƿ���ڡ�����ʱ��1����������ڣ���ȥ������ʱ��1��
            ���Է��ʣ���ʣ�µ�ʱ���롰����ʱ��2���ȶԣ��Դ����ơ�

            P��S���û����ڵĺ���ǰ׺Ӧ���ǹ̶��ģ��ڽ����û���ʱ����Ӧ�ò��롰ǰ׺���ֶι̶���ֵ��
     */

    /**
     * ���û��������ɣ����㵥��ͨ�����շ��ʡ�
     * @param CallAttribute
     * @param dwConversationTime
     * @param userGroup
     * @param StartTime
     * @return String
     */
    public String getFinalFee(short CallAttribute,int dwConversationTime,String userGroup,Date StartTime,int balance,String longNum,String shortNum,String CallerNum,String CalledNum,String convTime,int totalTime){
        int finalBalance = balance;
        int finalConvTime = Integer.parseInt(convTime);
        String finalFee = "";
        int shengyu = finalBalance - totalTime;
        int benci = new BillSysTool().second2Minute(dwConversationTime);
        if(balance>0){
            //δ��������ʱ��
            if(shengyu>benci){
                return "����ʱ��ʣ�ࣺ"+(finalBalance-totalTime-benci );
            }
            //ʣ��ʱ��С�ڱ���ʱ�䣬����ʣ��ʱ�䲻һ��Ϊ0
            else {
                //����ʣ��ʱ��
                if(shengyu>0){
                    finalFee =  this.Charge(CallAttribute,userGroup,benci-shengyu,StartTime);
                }
                //û��ʣ��ʱ��
                else{
                    finalFee =  this.Charge(CallAttribute,userGroup,benci,StartTime);
                }
            }
        }
        else {
            finalFee =  this.Charge(CallAttribute,userGroup,benci,StartTime);
        }
        return finalFee;
    }

    /**
     * �����Ʒѷ���
     * @param CallAttribute
     * @param userGroup
     * @param minute
     * @return
     */
    public String Charge(short CallAttribute,String userGroup,int minute,Date StartTime){
        String charge = "";
        //���õ��û���ļƷѲ���
        String groupTbl = impl.getTblNameByGroupName(userGroup);
        //����+������Ϊ������ѯ�ƷѲ��ԣ��浽BEAN��
        List li = new ArrayList();
        li = impl.getCelueBean(groupTbl,CallAttribute);
        gtao_phone_celue celue = null;
        BillSysTool tool  = new BillSysTool();
        Iterator it = li.iterator();
        while (it.hasNext()){
            //�õ���
            celue = (gtao_phone_celue)it.next();
        }
        //�õ���beanΪ�գ���ӡ������Ϣ
        if(celue==null){
            charge = "error";
        }
        //bean����ֵ����ʼ�Ʒ�
        else {
            //����ͨ��ʱ���Ƿ���ʱ���1�������ʱ��1�Ʒ�
            if(!celue.getSPECIALTIMEBEGIN1().equals("")&&!celue.getSPECIALTIMEEND1().equals("")&&tool.compareTime(StartTime,celue.getSPECIALTIMEBEGIN1(),celue.getSPECIALTIMEEND1())){
                if(celue.getSPECIALTIMEFEE1()==null||celue.getSPECIALTIMEFEE1().equals("")){
                    charge = "error";
                }
                else {
                    charge = Float.parseFloat(celue.getSPECIALTIMEFEE1())*minute+"";
                }
            }
            //����ͨ��ʱ���Ƿ���ʱ���2�������ʱ��2�Ʒ�
            else if(!celue.getSPECIALTIMEBEGIN2().equals("")&&!celue.getSPECIALTIMEEND2().equals("")&&tool.compareTime(StartTime,celue.getSPECIALTIMEBEGIN2(),celue.getSPECIALTIMEEND2())){
                if(celue.getSPECIALTIMEFEE2()==null||celue.getSPECIALTIMEFEE2().equals("")){
                    charge = "error";
                }
                else {
                    charge = Float.parseFloat(celue.getSPECIALTIMEFEE1())*minute+"";
                }
            }
            //����ͨ��ʱ���Ƿ���ʱ���3�������ʱ��3�Ʒ�
            else if(!celue.getSPECIALTIMEBEGIN3().equals("")&&!celue.getSPECIALTIMEEND3().equals("")&&tool.compareTime(StartTime,celue.getSPECIALTIMEBEGIN3(),celue.getSPECIALTIMEEND3())){
                if(celue.getSPECIALTIMEFEE3()==null||celue.getSPECIALTIMEFEE3().equals("")){
                    charge = "error";
                }
                else {
                    charge = Float.parseFloat(celue.getSPECIALTIMEFEE1())*minute+"";
                }
            }
            //��������ʱ�Σ�������ͨ��ʱ���Ʒѡ�
            else {
                //  ��*��Ϊͨ�������ʾ����ʱ�䲦�򣬲���೤ʱ��۸񶼲���䡣
                if(celue.getRATESTIME1().equals("*")){
                    charge = minute*Float.parseFloat(celue.getRATES1())+"";
                }
                // ���Բ�ͨ�䣬��3��ʱ��+����ʱ���շ�
                else {
                    int a =Integer.parseInt(celue.getRATESTIME1());
                    int b = Integer.parseInt(celue.getRATESTIME1())+Integer.parseInt(celue.getRATESTIME2());
                    int c = Integer.parseInt(celue.getRATESTIME1())+Integer.parseInt(celue.getRATESTIME2())+Integer.parseInt(celue.getRATESTIME3());
                    //���ж�ͨ��ʱ��Ͳ�����3��ʱ��Ĵ�С
                    if(minute<=a){
                        charge = Float.parseFloat(celue.getRATES1())+"";
                    }
                    else if(minute>a&&minute<=b){
                        if(celue.getRATESTIME2().equals("0")){
                            charge = Float.parseFloat(celue.getRATES1())+Float.parseFloat(celue.getOTHERFEE())*(minute-a)+"";
                        }
                        else {
                            charge = Float.parseFloat(celue.getRATES1())+Float.parseFloat(celue.getRATES2())*(minute-a)+"";
                        }
                    }
                    else if(minute>b&&minute<=c){
                        if(!celue.getRATESTIME2().equals("0")&&celue.getRATESTIME3().equals("0")){
                            charge = Float.parseFloat(celue.getRATES1())+Float.parseFloat(celue.getRATES2())*Integer.parseInt(celue.getRATESTIME2())+Float.parseFloat(celue.getOTHERFEE())*(minute-b)+"";
                        }
                        else if(celue.getRATESTIME2().equals("0")&&celue.getRATESTIME3().equals("0")){
                            charge = Float.parseFloat(celue.getRATES1())+Float.parseFloat(celue.getOTHERFEE())*(minute-a)+"";
                        }
                        else {
                            charge = Float.parseFloat(celue.getRATES1())+Float.parseFloat(celue.getRATES2())*Integer.parseInt(celue.getRATESTIME2())+Float.parseFloat(celue.getRATES3())*(minute-b)+"";
                        }
                    }
                    else if(minute>c){
                        if(!celue.getRATESTIME2().equals("0")&&celue.getRATESTIME3().equals("0")){
                            charge = Float.parseFloat(celue.getRATES1())+Float.parseFloat(celue.getRATES2())*Integer.parseInt(celue.getRATESTIME2())+Float.parseFloat(celue.getOTHERFEE())*(minute-b)+"";
                        }
                        else if(celue.getRATESTIME2().equals("0")&&celue.getRATESTIME3().equals("0")){
                            charge = Float.parseFloat(celue.getRATES1())+Float.parseFloat(celue.getOTHERFEE())*(minute-a)+"";
                        }
                        else {
                            charge = Float.parseFloat(celue.getRATES1())+Float.parseFloat(celue.getRATES2())*Integer.parseInt(celue.getRATESTIME2())+Float.parseFloat(celue.getRATES3())*Integer.parseInt(celue.getRATESTIME3())+Float.parseFloat(celue.getOTHERFEE())*(minute-c)+"";
                        }
                    }
                }
            }
        }
        return charge;
    }
}
