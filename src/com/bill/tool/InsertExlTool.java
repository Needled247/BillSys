package com.bill.tool;

import com.bill.pojo.gtao_Phone_bc_sale;
import com.bill.pojo.gtao_phone_view;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-6-8
 * Time: 上午9:58
 * To change this template use File | Settings | File Templates.
 */

public class InsertExlTool {
    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    public int insertDB(String filePath,String tbl){
        int flag = 0;
        int InsNum = 0;//插入的条数。
        String fileName = filePath.substring(filePath.lastIndexOf("\\")+1,filePath.lastIndexOf("."));
        if((fileName.equals("sale")||fileName=="sale")&&(tbl.contains("sale")))
        System.out.println(fileName);
        try {
            //文件流指向excel文件
            FileInputStream fin=new FileInputStream(filePath);
            HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄
            HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表
            HSSFRow row=null;//对应excel的行
            HSSFCell cell=null;//对应excel的列
            int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
            //hibernate
            sessionFactory = configureSessionFactory();
            if(tbl.contains("sale")){
                if(fileName.equals("sale")||fileName=="sale"){
                    Session session = sessionFactory.openSession();
                    Transaction transaction = session.beginTransaction();
                    gtao_Phone_bc_sale sale = null;
                    for(int i=1;i<=totalRow;i++){
                        sale = new gtao_Phone_bc_sale();
                        row=sheet.getRow(i);
                        if(row.getCell(0)!=null){
                            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                            sale.setLongNum(row.getCell(0).getStringCellValue());
                        }
                        if(row.getCell(1)!=null){
                            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                            sale.setShortNum(row.getCell(1).getStringCellValue());
                        }
                        if(row.getCell(2)!=null){
                            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                            sale.setIp(row.getCell(2).getStringCellValue());
                        }
                        if(row.getCell(3)!=null){
                            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                            sale.setMoney(row.getCell(3).getStringCellValue());
                        }
                        if(row.getCell(4)!=null){
                            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                            sale.setVlan(row.getCell(4).getStringCellValue());
                        }
                        if(row.getCell(5)!=null){
                            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                            sale.setGate(row.getCell(5).getStringCellValue());
                        }
                        //检查
                        String sql = "select count(*) from "+tbl+" where longNum=? and shortNum=? and ip=? and money=? and vlan=?";
                        Query query = session.createSQLQuery(sql);
                        query.setParameter(0,sale.getLongNum());
                        query.setParameter(1,sale.getShortNum());
                        query.setParameter(2,sale.getIp());
                        query.setParameter(3,sale.getMoney());
                        query.setParameter(4,sale.getVlan());
                        List li = query.list();
                        Iterator it = li.iterator();
                        while (it.hasNext()){
                            int x = (Integer)it.next();
                            if(x==0){
                                //插入
                                sql = "insert into "+tbl+"(longNum,shortNum,ip,money,vlan,gate) values (?,?,?,?,?,?)";
                                query = session.createSQLQuery(sql);
                                query.setParameter(0,sale.getLongNum());
                                query.setParameter(1,sale.getShortNum());
                                query.setParameter(2,sale.getIp());
                                query.setParameter(3,sale.getMoney());
                                query.setParameter(4,sale.getVlan());
                                query.setParameter(5, sale.getGate());
                                InsNum+=query.executeUpdate();
                                if(i%30==0){
                                    session.flush();
                                    session.clear();
                                }
                            }
                        }
                    }
                    transaction.commit();
                    session.close();
                    fin.close();
              }
                else {
                    InsNum = 0;
                    throw new FileNotFoundException();
                }
            }
            else {
                if((fileName.equals("free")||fileName=="free")){
                    Session session = sessionFactory.openSession();
                    Transaction transaction = session.beginTransaction();

                    gtao_phone_view view = null;
                    for(int i=1;i<=totalRow;i++){
                        view = new gtao_phone_view();
                        row=sheet.getRow(i);
                        if(row.getCell(0)!=null){
                            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                            view.setLongNum(row.getCell(0).getStringCellValue());
                        }
                        if(row.getCell(1)!=null){
                            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                            view.setShortNum(row.getCell(1).getStringCellValue());
                        }
                        if(row.getCell(2)!=null){
                            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                            view.setIp(row.getCell(2).getStringCellValue());
                        }
                        if(row.getCell(3)!=null){
                            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                            view.setVlan(row.getCell(3).getStringCellValue());
                        }
                        if(row.getCell(4)!=null){
                            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                            view.setGate(row.getCell(4).getStringCellValue());
                        }
                        //检查
                        String sql = "select count(*) from "+tbl+" where longNum=? and shortNum=? and ip=? and vlan=?";
                        Query query = session.createSQLQuery(sql);
                        query.setParameter(0,view.getLongNum());
                        query.setParameter(1,view.getShortNum());
                        query.setParameter(2,view.getIp());
                        query.setParameter(3,view.getVlan());
                        List li = query.list();
                        Iterator it = li.iterator();
                        while (it.hasNext()){
                            int x = (Integer)it.next();
                            if(x==0){
                                //插入
                                query = null;
                                sql = "insert into "+tbl+"(longNum,shortNum,ip,vlan,gate) values (?,?,?,?,?)";
                                query = session.createSQLQuery(sql);
                                query.setParameter(0,view.getLongNum());
                                query.setParameter(1,view.getShortNum());
                                query.setParameter(2,view.getIp());
                                query.setParameter(3,view.getVlan());
                                query.setParameter(4,view.getGate());
                                InsNum+=query.executeUpdate();
                                if(i%30==0){
                                    session.flush();
                                    session.clear();
                                }
                            }
                        }
                    }
                    transaction.commit();
                    session.close();
                    fin.close();
                 }
                  else {
                      InsNum = 0;
                      throw new FileNotFoundException();
                  }
                }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        finally {
            sessionFactory.close();
        }
        flag = InsNum;
        return flag;
    }

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
}
