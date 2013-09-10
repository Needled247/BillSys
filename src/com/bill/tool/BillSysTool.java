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
 * Time: ����10:06
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
     * ��ʽת����String��Date
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
     * ��ת��
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
     * ��ȡ�������������һ��
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
     * ����Ӫҵ��ת��
     * @param tbl
     * @return
     */
    public String tblName2departmetName(String tbl){
        String department = "";
        if(tbl.equals("gtao_Phone_zy")){
            department = "����";
        }
        if(tbl.equals("")){
            department = "��";
        }
        else if(tbl.equals("gtao_Phone_ky")){
            department = "����";
        }
        else if(tbl.equals("gtao_Phone_qt")){
            department = "����";
        }
        else if(tbl.equals("gtao_Phone_lx")){
            department = "����";
        }
        else if(tbl.equals("gtao_Phone_sh")){
            department = "����";
        }
        else if(tbl.equals("gtao_Phone_nc")){
            department = "�ϳ�";
        }
        else if(tbl.equals("gtao_Phone_bc")){
            department = "����";
        }
        else if(tbl.equals("gtao_Phone_xyy")){
            department = "����Է";
        }
        else if(tbl.equals("gtao_Phone_bc_sale")){
            department = "�����꣨���ѣ�";
        }
        else if(tbl.equals("gtao_Phone_zy_sale")){
            department = "���������ѣ�";
        }
        else if(tbl.equals("gtao_Phone_ky_sale")){
            department = "���������ѣ�";
        }
        else if(tbl.equals("gtao_Phone_qt_sale")){
            department = "���������ѣ�";
        }
        else if(tbl.equals("gtao_Phone_lx_sale")){
            department = "���磨���ѣ�";
        }
        else if(tbl.equals("gtao_Phone_sh_sale")){
            department = "���������ѣ�";
        }
        else if(tbl.equals("gtao_Phone_xyy_sale")){
            department = "����Է�����ѣ�";
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
        cell.setCellValue("���");
        row.createCell((short)1).setCellValue("���к���");
        row.createCell((short)2).setCellValue("���к���");
        row.createCell((short)3).setCellValue("�Ự����");
        row.createCell((short)4).setCellValue("��ʼʱ��");
        row.createCell((short)5).setCellValue("����ʱ��");
        row.createCell((short)6).setCellValue("ͨ��ʱ��");
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
            System.out.println("��ʼд��Excel");
            outputStream.flush();
            wb.write(outputStream);
            flag = true;
            outputStream.close();
        }
        catch   (IOException e){
            flag = false;
            e.printStackTrace();
        }
        System.out.println( "Excelд�����");
        return  flag;
    }

    /**
     * �ȶ�ʱ��
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
        //����Сʱ����
        int[] timeArr = new int[24];
        for(int i=0;i<=23;i++){
            timeArr[i] = i;
        }
        List<Integer> li = new ArrayList<Integer>();
        if(begin>end){
            //����1
            for(int i=begin;i<timeArr.length;i++){
                li.add(timeArr[i]);
            }
            //����2
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
     * �û�ͨ����¼����
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
        cell.setCellValue("���");
        row.createCell((short)1).setCellValue("���к���");
        row.createCell((short)2).setCellValue("���к���");
        row.createCell((short)3).setCellValue("�Ự����");
        row.createCell((short)4).setCellValue("��ʼʱ��");
        row.createCell((short)5).setCellValue("����ʱ��");
        row.createCell((short)6).setCellValue("ͨ��ʱ��");
        row.createCell((short)7).setCellValue("����");
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
            System.out.println("��ʼд��Excel");
            outputStream.flush();
            wb.write(outputStream);
            flag = true;
            outputStream.close();
        }
        catch   (IOException e){
            flag = false;
            e.printStackTrace();
        }
        System.out.println( "Excelд�����");
        return  flag;
    }

    /**
     * ͨ��������ת����
     * @param CallAttribute
     * @return String
     */
    public String AttributeCode2Str(short CallAttribute){
        String AttributeStr = "";
        switch (CallAttribute){
            case 255:
                AttributeStr = "ͨ��";
                break;
            case 0:
                AttributeStr = "���ں���";
                break;
            case 1:
                AttributeStr = "���غ���";
                break;
            case 2:
                AttributeStr = "���س�;";
                break;
            case 3:
                AttributeStr = "���ڳ�;";
                break;
            case 4:
                AttributeStr = "���ʳ�;";
                break;
            case 5:
                AttributeStr = "��ҵ��";
                break;
            case 7:
                AttributeStr = "���ݺ���";
                break;
            case 8:
                AttributeStr = "���Ժ���";
                break;
            case 10:
                AttributeStr = "���Զ�����Ա����";
                break;
            case 11:
                AttributeStr = "����δ֪";
                break;
            default:
                break;
        }
        return AttributeStr;
    }
}
