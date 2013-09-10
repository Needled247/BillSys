package com.bill.tool;

import com.bill.bean.tbl_billInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-5-27
 * Time: 上午10:06
 * To change this template use File | Settings | File Templates.
 */
public class BillSysTool {
    public String NullStrFormatter(String formatStr){
        if(formatStr==null){
            formatStr="";
        }
        return formatStr;
    }

    public String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String itime = sdf.format(date);
        return itime;
    }

    public String getToday(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 格式转换：String→Date
     * @param date
     * @return Date
     */
    public Date Str2Date(String date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        try{
            d = dateFormat.parse(date);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 秒转分
     * @param second
     * @return
     */
    public int second2Minute(int second){
        int minute = 0;
        if(second<60){
            minute = 1;
        }
        else {
            if(second%60==0){
                minute = second/60;
            }
            else {
                minute = second/60+1;
            }
        }
        return minute;
    }


    /**
     * 获取参数日期月最后一天
     * @param date
     * @return Date
     */
    public Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.roll(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 表名营业厅转换
     * @param tbl
     * @return
     */
    public String tblName2departmetName(String tbl){
        String department = "";
        if(tbl.equals("gtao_Phone_zy")){
            department = "正阳";
        }
        if(tbl.equals("")){
            department = "空";
        }
        else if(tbl.equals("gtao_Phone_ky")){
            department = "开阳";
        }
        else if(tbl.equals("gtao_Phone_qt")){
            department = "青塔";
        }
        else if(tbl.equals("gtao_Phone_lx")){
            department = "良乡";
        }
        else if(tbl.equals("gtao_Phone_sh")){
            department = "三环";
        }
        else if(tbl.equals("gtao_Phone_nc")){
            department = "南厂";
        }
        else if(tbl.equals("gtao_Phone_bc")){
            department = "北厂";
        }
        else if(tbl.equals("gtao_Phone_xyy")){
            department = "晓月苑";
        }
        else if(tbl.equals("gtao_Phone_bc_sale")){
            department = "长辛店（付费）";
        }
        else if(tbl.equals("gtao_Phone_zy_sale")){
            department = "正阳（付费）";
        }
        else if(tbl.equals("gtao_Phone_ky_sale")){
            department = "开阳（付费）";
        }
        else if(tbl.equals("gtao_Phone_qt_sale")){
            department = "青塔（付费）";
        }
        else if(tbl.equals("gtao_Phone_lx_sale")){
            department = "良乡（付费）";
        }
        else if(tbl.equals("gtao_Phone_sh_sale")){
            department = "三环（付费）";
        }
        else if(tbl.equals("gtao_Phone_xyy_sale")){
            department = "晓月苑（付费）";
        }
        return department;
    }

    @SuppressWarnings("deprecation")
    public boolean getExcel(String sheetName,OutputStream outputStream,List li){
        boolean flag = false;
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet("sheet1");
        HSSFRow row = sheet1.createRow((short)0);
        HSSFCell cell = row.createCell((short)0);
        cell.setCellValue("编号");
        row.createCell((short)1).setCellValue("主叫号码");
        row.createCell((short)2).setCellValue("被叫号码");
        row.createCell((short)3).setCellValue("会话属性");
        row.createCell((short)4).setCellValue("开始时间");
        row.createCell((short)5).setCellValue("结束时间");
        row.createCell((short)6).setCellValue("通话时长");
        Iterator it = li.iterator();
        int i = 1;
        while (it.hasNext()){
            tbl_billInfo billInfo = (tbl_billInfo)it.next();
            row=sheet1.createRow((short)i);
            row.createCell((short)0).setCellValue(i);
            row.createCell((short)1).setCellValue(billInfo.getUcCallerNumber());
            row.createCell((short)2).setCellValue(billInfo.getUcCalledNumber());
            row.createCell((short)3).setCellValue(billInfo.getUcCallAttribute());
            row.createCell((short)4).setCellValue(billInfo.getStartTime().toString());
            row.createCell((short)5).setCellValue(billInfo.getEndTime().toString());
            row.createCell((short)6).setCellValue(billInfo.getDwConversationTime());
            i++;
        }
        try{
            System.out.println("开始写入Excel");
            outputStream.flush();
            wb.write(outputStream);
            flag = true;
            outputStream.close();
        }
        catch   (IOException e){
            flag = false;
            e.printStackTrace();
        }
        System.out.println( "Excel写入完成");
        return  flag;
    }

    /**
     * 比对时间
     * @param target
     * @param beginTime
     * @param endTime
     * @return
     */
    public boolean compareTime(Date target,String beginTime,String endTime){
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(beginTime==null||beginTime.equals("")){
            return false;
        }
        if(endTime==null||endTime.equals("")){
            return false;
        }
        String targetStr = sdf.format(target);
        int begin = Integer.parseInt(beginTime);
        int end = Integer.parseInt(endTime);
        //建立小时数组
        int[] timeArr = new int[24];
        for(int i=0;i<=23;i++){
            timeArr[i] = i;
        }
        List<Integer> li = new ArrayList<Integer>();
        if(begin>end){
            //链表1
            for(int i=begin;i<timeArr.length;i++){
                li.add(timeArr[i]);
            }
            //链表2
            for(int i=0;i<end;i++){
                li.add(timeArr[i]);
            }
        }
        targetStr = targetStr.substring(targetStr.lastIndexOf(" ")+1,targetStr.indexOf(":"));
        int time = Integer.parseInt(targetStr);
        if(li.contains(time)){
            flag = true;
        }
        return flag;
    }

    /**
     * 用户通话记录表导出
     * @param sheetName
     * @param outputStream
     * @param li
     * @return
     */
    @SuppressWarnings("deprecation")
    public boolean getUserCalledExcel(String sheetName,OutputStream outputStream,List li){
        boolean flag = false;
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet("sheet1");
        HSSFRow row = sheet1.createRow((short)0);
        HSSFCell cell = row.createCell((short)0);
        cell.setCellValue("编号");
        row.createCell((short)1).setCellValue("主叫号码");
        row.createCell((short)2).setCellValue("被叫号码");
        row.createCell((short)3).setCellValue("会话属性");
        row.createCell((short)4).setCellValue("开始时间");
        row.createCell((short)5).setCellValue("结束时间");
        row.createCell((short)6).setCellValue("通话时长");
        row.createCell((short)7).setCellValue("费用");
        Iterator it = li.iterator();
        int i = 1;
        while (it.hasNext()){
            tbl_billInfo billInfo = (tbl_billInfo)it.next();
            row=sheet1.createRow((short)i);
            row.createCell((short)0).setCellValue(i);
            row.createCell((short)1).setCellValue(billInfo.getUcCallerNumber());
            row.createCell((short)2).setCellValue(billInfo.getUcCalledNumber());
            row.createCell((short)3).setCellValue(billInfo.getUcCallAttribute());
            row.createCell((short)4).setCellValue(billInfo.getStartTime().toString());
            row.createCell((short)5).setCellValue(billInfo.getEndTime().toString());
            row.createCell((short)6).setCellValue(billInfo.getDwConversationTime());
            row.createCell((short)7).setCellValue(billInfo.getAreaCode());
            i++;
        }
        try{
            System.out.println("开始写入Excel");
            outputStream.flush();
            wb.write(outputStream);
            flag = true;
            outputStream.close();
        }
        catch   (IOException e){
            flag = false;
            e.printStackTrace();
        }
        System.out.println( "Excel写入完成");
        return  flag;
    }

    /**
     * 通话属性码转文字
     * @param CallAttribute
     * @return String
     */
    public String AttributeCode2Str(short CallAttribute){
        String AttributeStr = "";
        switch (CallAttribute){
            case 255:
                AttributeStr = "通配";
                break;
            case 0:
                AttributeStr = "局内呼叫";
                break;
            case 1:
                AttributeStr = "本地呼叫";
                break;
            case 2:
                AttributeStr = "本地长途";
                break;
            case 3:
                AttributeStr = "国内长途";
                break;
            case 4:
                AttributeStr = "国际长途";
                break;
            case 5:
                AttributeStr = "新业务";
                break;
            case 7:
                AttributeStr = "数据呼叫";
                break;
            case 8:
                AttributeStr = "测试呼叫";
                break;
            case 10:
                AttributeStr = "半自动话务员呼叫";
                break;
            case 11:
                AttributeStr = "属性未知";
                break;
            default:
                break;
        }
        return AttributeStr;
    }
}
