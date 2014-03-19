package com.bill.action;

import com.bill.dao.BillSysDAO;
import com.bill.pojo.gtao_Phone_bc_sale;
import com.bill.pojo.gtao_phone_view;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-5-30
 * Time: ����5:46
 * To change this template use File | Settings | File Templates.
 */
public class NumManage extends ActionSupport {
    private String type;
    private String area;
    private String phoneNum;
    private String status;
    private BillSysDAO billService;

    public BillSysDAO getBillService() {
        return billService;
    }

    public void setBillService(BillSysDAO billService) {
        this.billService = billService;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * type:num �������ѯ;type:area ��������ѯ
     * @return null
     * @throws Exception
     */
    @Override
    public String execute() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=GBK");
        PrintWriter out = response.getWriter();
        List li = new ArrayList();
        StringBuilder sb = new StringBuilder();
        if(type.equals("area")){
            //�����ѯ JSON
            li = billService.SearchByArea(area,status);
            if(area.contains("sale")){
                gtao_Phone_bc_sale sale = null;
                Iterator saleIter = li.iterator();
                sb.append("[");
                while (saleIter.hasNext()){
                    sale = (gtao_Phone_bc_sale)saleIter.next();
                    sb.append("[")
                            .append("\""+sale.getLongNum()+"\",")
                            .append("\""+sale.getIp()+"\",")
                            .append("\""+sale.getVlan()+"\",")
                            .append("\""+this.Tbl2Str(sale.getTbl())+"\",")
                            .append("\""+sale.getMoney()+"\",")
                            .append("\""+sale.getGate()+"\"");
                    sb.append("],");
                }
                if(sb.length()>1){
                    sb.deleteCharAt(sb.lastIndexOf(","));
                }
                sb.append("]");
            }
            else{
                gtao_phone_view view = null;
                Iterator freeIter = li.iterator();
                sb.append("[");
                while (freeIter.hasNext()){
                    view = (gtao_phone_view)freeIter.next();
                    sb.append("[")
                            .append("\""+view.getLongNum()+"\",")
                            .append("\""+view.getIp()+"\",")
                            .append("\""+view.getVlan()+"\",")
                            .append("\""+this.Tbl2Str(view.getTbl())+"\",")
                            .append("\"���\",")
                            .append("\""+view.getGate()+"\"");
                    sb.append("],");
                }
                if(sb.length()>1){
                    sb.deleteCharAt(sb.lastIndexOf(","));
                }
                sb.append("]");
            }
        }
        else if(type.equals("num")||type=="num"){
            //�����ѯ
            Map map = billService.SearchByNum(phoneNum);
            String key = "";
            List value = new ArrayList();
            sb.append("[");
            //���map���յ������ݲ�Ϊ��
            if(map!=null){
                //����hashmap�õ���ֵ��
                Set keys = map.keySet( );
                if(keys != null) {
                    Iterator iterator = keys.iterator( );
                    while(iterator.hasNext( )) {
                        key = (String)iterator.next();
                        value = (List)map.get(key);
                    }
                }
            }
            //mapΪ�գ����ؿ�json��
            else {
                System.out.println("mapΪ�գ�");
            }
            //ƴ��json��
            if(key.equals("free")||key=="free"){
                gtao_phone_view view = new gtao_phone_view();
                Iterator it = value.iterator();
                while (it.hasNext()){
                    view = (gtao_phone_view)it.next();
                    sb.append("[")
                            .append("\""+view.getLongNum()+"\",")
                            .append("\""+view.getIp()+"\",")
                            .append("\""+view.getVlan()+"\",")
                            .append("\""+this.Tbl2Str(view.getTbl())+"\",")
                            .append("\"���\",")
                            .append("\""+view.getGate()+"\"");
                    sb.append("],");
                }
                sb.deleteCharAt(sb.lastIndexOf(","));
            }
            if(key.equals("sale")||key=="sale"){
                gtao_Phone_bc_sale sale = new gtao_Phone_bc_sale();
                Iterator it = value.iterator();
                while (it.hasNext()){
                    sale = (gtao_Phone_bc_sale)it.next();
                    sb.append("[")
                            .append("\""+sale.getLongNum()+"\",")
                            .append("\""+sale.getIp()+"\",")
                            .append("\""+sale.getVlan()+"\",")
                            .append("\""+this.Tbl2Str(sale.getTbl())+"\"")
                            .append("\""+sale.getMoney()+"\",")
                            .append("\""+sale.getGate()+"\"");
                    sb.append("]");
                }
            }
            sb.append("]");
        }
        out.println(sb);
        out.flush();
        out.close();
        return null;
    }

    public String Tbl2Str(String tbl){
        String area = "";
        if(tbl.equals("gtao_Phone_bc")||tbl=="gtao_Phone_bc"){
            area = "������";
        }
        else if(tbl.equals("gtao_Phone_nc")||tbl=="gtao_Phone_nc"){
            area = "�ϳ�";
        }
        else if(tbl.equals("gtao_Phone_qt")||tbl=="gtao_Phone_qt"){
            area = "����";
        }
        else if(tbl.equals("gtao_Phone_lx")||tbl=="gtao_Phone_lx"){
            area = "����";
        }
        else if(tbl.equals("gtao_Phone_sh")||tbl=="gtao_Phone_sh"){
            area = "����";
        }
        else if(tbl.equals("gtao_Phone_zy")||tbl=="gtao_Phone_zy"){
            area = "����";
        }
        else if(tbl.equals("gtao_Phone_ky")||tbl=="gtao_Phone_ky"){
            area = "����";
        }
        else if(tbl.equals("gtao_Phone_xyy")||tbl=="gtao_Phone_xyy"){
            area = "����Է";
        }
        if(tbl.equals("gtao_Phone_bc_sale")||tbl=="gtao_Phone_bc"){
            area = "�����꣨���ѣ�";
        }
        else if(tbl.equals("gtao_Phone_nc_sale")||tbl=="gtao_Phone_nc"){
            area = "�ϳ������ѣ�";
        }
        else if(tbl.equals("gtao_Phone_qt_sale")||tbl=="gtao_Phone_qt"){
            area = "���������ѣ�";
        }
        else if(tbl.equals("gtao_Phone_lx_sale")||tbl=="gtao_Phone_lx"){
            area = "���磨���ѣ�";
        }
        else if(tbl.equals("gtao_Phone_sh_sale")||tbl=="gtao_Phone_sh"){
            area = "���������ѣ�";
        }
        else if(tbl.equals("gtao_Phone_zy_sale")||tbl=="gtao_Phone_zy"){
            area = "���������ѣ�";
        }
        else if(tbl.equals("gtao_Phone_ky_sale")||tbl=="gtao_Phone_ky"){
            area = "���������ѣ�";
        }
        else if(tbl.equals("gtao_Phone_xyy_sale")||tbl=="gtao_Phone_xyy"){
            area = "����Է�����ѣ�";
        }
        return area;
    }
}
