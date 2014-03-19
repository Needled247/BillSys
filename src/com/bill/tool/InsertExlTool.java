package com.bill.tool;

import com.bill.pojo.gtao_Phone_bc_sale;
import com.bill.pojo.gtao_phone_view;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-6-8
 * Time: 上午9:58
 * To change this template use File | Settings | File Templates.
 */

public class InsertExlTool {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertDB(String filePath,String tbl){
        int flag = 0;
        int InsNum = 0;//插入的条数。
        String fileName = filePath.substring(filePath.lastIndexOf("\\")+1,filePath.lastIndexOf("."));
        if((fileName.equals("sale")||fileName=="sale")&&(tbl.contains("sale")))
        try {
            //文件流指向excel文件
            FileInputStream fin=new FileInputStream(filePath);
            HSSFWorkbook workbook=new HSSFWorkbook(fin);//创建工作薄
            HSSFSheet sheet=workbook.getSheetAt(0);//得到工作表
            HSSFRow row=null;//对应excel的行
            HSSFCell cell=null;//对应excel的列
            int totalRow=sheet.getLastRowNum();//得到excel的总记录条数
            //jdbcTemplate
            if(tbl.contains("sale")){
                if(fileName.equals("sale")||fileName=="sale"){
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
                        Object[] param = {sale.getLongNum(),sale.getShortNum(),sale.getIp(),sale.getMoney(),sale.getVlan()};
                        int count = jdbcTemplate.queryForInt(sql,param);
                        if(count==0){
                            //插入
                            sql = "insert into "+tbl+"(longNum,shortNum,ip,money,vlan,gate) values (?,?,?,?,?,?)";
                            final gtao_Phone_bc_sale finalSale = sale;
                            InsNum+=jdbcTemplate.update(sql, new PreparedStatementSetter() {
                                @Override
                                public void setValues(PreparedStatement pstmt) throws SQLException {
                                    pstmt.setString(1, finalSale.getLongNum());
                                    pstmt.setString(2, finalSale.getShortNum());
                                    pstmt.setString(3, finalSale.getIp());
                                    pstmt.setString(4, finalSale.getMoney());
                                    pstmt.setString(5, finalSale.getVlan());
                                    pstmt.setString(6, finalSale.getGate());
                                }
                            });
                        }
                    }
                    fin.close();
              }
                else {
                    InsNum = 0;
                    throw new FileNotFoundException();
                }
            }
            else {
                if((fileName.equals("free")||fileName=="free")){
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
                        Object[] param = {view.getLongNum(),view.getShortNum(),view.getIp(),view.getVlan()};
                        int count = jdbcTemplate.queryForInt(sql,param);
                        if(count==0){
                            sql = "insert into "+tbl+"(longNum,shortNum,ip,vlan,gate) values (?,?,?,?,?)";
                            final gtao_phone_view finalView = view;
                            InsNum += jdbcTemplate.update(sql,new PreparedStatementSetter() {
                                @Override
                                public void setValues(PreparedStatement pstmt) throws SQLException {
                                    pstmt.setString(1, finalView.getLongNum());
                                    pstmt.setString(2, finalView.getShortNum());
                                    pstmt.setString(3, finalView.getIp());
                                    pstmt.setString(4, finalView.getVlan());
                                    pstmt.setString(5, finalView.getGate());
                                }
                            });
                        }
                    }
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
        flag = InsNum;
        return flag;
    }
}
