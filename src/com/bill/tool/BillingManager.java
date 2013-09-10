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
 * Time: 下午4:50
 * 计费管理工具类，过滤9500话单计算自定义费率。
 */
public class BillingManager {
    BillSysDAOImpl impl = new BillSysDAOImpl();
    /*
    ！----------------------------------单用户话单生成-------------------------------!
    1、要得到用户一次通话的费率，需要的第一个参数是用户的长号码或账号ACCOUNT，
    以便得到该用户属于哪个用户组GROUP。
    2、然后通过查询条件得到用户的通话列表LIST，逐条读取，得到每次通话的时间TIME，通话属性ATTRIBUTE，
    优惠时间Balance。
    3、在每次计算之前要先判断该次通话是否为用户主叫。
    4、首先判断该用户的用户组，是否有赠送时间，
    ！用户组内权重顺序：赠送时间>（特殊时间段1>特殊时间段2>特殊时间段3）>（费率时长1>费率时长2>费率时长3）
    如果有赠送时间（一般为300分钟）
            | 1、先计算该用户该月通话的总长。
            |2、是否超过该赠送时长？正常计费：继续扣赠送时间
    5、如果没有赠送时间或已超出赠送时间：
            |1、迭代通话记录List
            |2、获取该次通话的CallAttribute（通话属性）、dwConversationTime（通话时间）、 StartTime（开始时间）
            与该用户的用户组（userGroup）作为参数传到这里。
            |3、这个类里面应该定义一个最终费用作为返回值的公有方法由上面一条的4个参数计算出该次通话的费率。
            public String getFinalFee(String CallAttribute,String dwConversationTime,String userGroup，String StartTime);
            |4、方法内部：首先查询数据库得到该用户组对应通话属性（CallAttribute）的数据，保存到一个Bean中。
            第二步：判断该次通话时间StartTime是否在“特殊时间段”内，如果在，优先处理。
            第三步：不在“特殊时段”内，判断dwConversationTime（通话时间）长度是否大于“费率时长1”，如果大于，减去“费率时长1”
            乘以费率，将剩下的时间与“费率时长2”比对，以此类推。

            P·S：用户组内的呼叫前缀应该是固定的，在建立用户组时建表应该插入“前缀”字段固定的值。
     */

    /**
     * 单用户话单生成，计算单次通话最终费率。
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
            //未超出赠送时间
            if(shengyu>benci){
                return "赠送时间剩余："+(finalBalance-totalTime-benci );
            }
            //剩余时间小于本次时间，但是剩余时间不一定为0
            else {
                //还有剩余时间
                if(shengyu>0){
                    finalFee =  this.Charge(CallAttribute,userGroup,benci-shengyu,StartTime);
                }
                //没有剩余时间
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
     * 正常计费方法
     * @param CallAttribute
     * @param userGroup
     * @param minute
     * @return
     */
    public String Charge(short CallAttribute,String userGroup,int minute,Date StartTime){
        String charge = "";
        //先拿到用户组的计费策略
        String groupTbl = impl.getTblNameByGroupName(userGroup);
        //表名+属性作为参数查询计费策略，存到BEAN里
        List li = new ArrayList();
        li = impl.getCelueBean(groupTbl,CallAttribute);
        gtao_phone_celue celue = null;
        BillSysTool tool  = new BillSysTool();
        Iterator it = li.iterator();
        while (it.hasNext()){
            //拿到啦
            celue = (gtao_phone_celue)it.next();
        }
        //拿到的bean为空，打印错误信息
        if(celue==null){
            charge = "error";
        }
        //bean里有值，开始计费
        else {
            //计算通话时间是否在时间段1里，按特殊时段1计费
            if(!celue.getSPECIALTIMEBEGIN1().equals("")&&!celue.getSPECIALTIMEEND1().equals("")&&tool.compareTime(StartTime,celue.getSPECIALTIMEBEGIN1(),celue.getSPECIALTIMEEND1())){
                if(celue.getSPECIALTIMEFEE1()==null||celue.getSPECIALTIMEFEE1().equals("")){
                    charge = "error";
                }
                else {
                    charge = Float.parseFloat(celue.getSPECIALTIMEFEE1())*minute+"";
                }
            }
            //计算通话时间是否在时间段2里，按特殊时段2计费
            else if(!celue.getSPECIALTIMEBEGIN2().equals("")&&!celue.getSPECIALTIMEEND2().equals("")&&tool.compareTime(StartTime,celue.getSPECIALTIMEBEGIN2(),celue.getSPECIALTIMEEND2())){
                if(celue.getSPECIALTIMEFEE2()==null||celue.getSPECIALTIMEFEE2().equals("")){
                    charge = "error";
                }
                else {
                    charge = Float.parseFloat(celue.getSPECIALTIMEFEE1())*minute+"";
                }
            }
            //计算通话时间是否在时间段3里，按特殊时段3计费
            else if(!celue.getSPECIALTIMEBEGIN3().equals("")&&!celue.getSPECIALTIMEEND3().equals("")&&tool.compareTime(StartTime,celue.getSPECIALTIMEBEGIN3(),celue.getSPECIALTIMEEND3())){
                if(celue.getSPECIALTIMEFEE3()==null||celue.getSPECIALTIMEFEE3().equals("")){
                    charge = "error";
                }
                else {
                    charge = Float.parseFloat(celue.getSPECIALTIMEFEE1())*minute+"";
                }
            }
            //不在特殊时段，按正常通话时长计费。
            else {
                //  “*”为通配符，表示不分时间拨打，拨打多长时间价格都不会变。
                if(celue.getRATESTIME1().equals("*")){
                    charge = minute*Float.parseFloat(celue.getRATES1())+"";
                }
                // 策略不通配，按3段时间+其他时间收费
                else {
                    int a =Integer.parseInt(celue.getRATESTIME1());
                    int b = Integer.parseInt(celue.getRATESTIME1())+Integer.parseInt(celue.getRATESTIME2());
                    int c = Integer.parseInt(celue.getRATESTIME1())+Integer.parseInt(celue.getRATESTIME2())+Integer.parseInt(celue.getRATESTIME3());
                    //先判断通话时间和策略中3段时间的大小
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
