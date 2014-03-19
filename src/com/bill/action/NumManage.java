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
 * Time: 下午5:46
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
     * type:num 按号码查询;type:area 按地区查询
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
            //地域查询 JSON
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
                            .append("\"免费\",")
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
            //号码查询
            Map map = billService.SearchByNum(phoneNum);
            String key = "";
            List value = new ArrayList();
            sb.append("[");
            //如果map接收到的数据不为空
            if(map!=null){
                //遍历hashmap得到键值对
                Set keys = map.keySet( );
                if(keys != null) {
                    Iterator iterator = keys.iterator( );
                    while(iterator.hasNext( )) {
                        key = (String)iterator.next();
                        value = (List)map.get(key);
                    }
                }
            }
            //map为空，返回空json串
            else {
                System.out.println("map为空！");
            }
            //拼接json串
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
                            .append("\"免费\",")
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
            area = "长辛店";
        }
        else if(tbl.equals("gtao_Phone_nc")||tbl=="gtao_Phone_nc"){
            area = "南厂";
        }
        else if(tbl.equals("gtao_Phone_qt")||tbl=="gtao_Phone_qt"){
            area = "青塔";
        }
        else if(tbl.equals("gtao_Phone_lx")||tbl=="gtao_Phone_lx"){
            area = "良乡";
        }
        else if(tbl.equals("gtao_Phone_sh")||tbl=="gtao_Phone_sh"){
            area = "三环";
        }
        else if(tbl.equals("gtao_Phone_zy")||tbl=="gtao_Phone_zy"){
            area = "正阳";
        }
        else if(tbl.equals("gtao_Phone_ky")||tbl=="gtao_Phone_ky"){
            area = "开阳";
        }
        else if(tbl.equals("gtao_Phone_xyy")||tbl=="gtao_Phone_xyy"){
            area = "晓月苑";
        }
        if(tbl.equals("gtao_Phone_bc_sale")||tbl=="gtao_Phone_bc"){
            area = "长辛店（付费）";
        }
        else if(tbl.equals("gtao_Phone_nc_sale")||tbl=="gtao_Phone_nc"){
            area = "南厂（付费）";
        }
        else if(tbl.equals("gtao_Phone_qt_sale")||tbl=="gtao_Phone_qt"){
            area = "青塔（付费）";
        }
        else if(tbl.equals("gtao_Phone_lx_sale")||tbl=="gtao_Phone_lx"){
            area = "良乡（付费）";
        }
        else if(tbl.equals("gtao_Phone_sh_sale")||tbl=="gtao_Phone_sh"){
            area = "三环（付费）";
        }
        else if(tbl.equals("gtao_Phone_zy_sale")||tbl=="gtao_Phone_zy"){
            area = "正阳（付费）";
        }
        else if(tbl.equals("gtao_Phone_ky_sale")||tbl=="gtao_Phone_ky"){
            area = "开阳（付费）";
        }
        else if(tbl.equals("gtao_Phone_xyy_sale")||tbl=="gtao_Phone_xyy"){
            area = "晓月苑（付费）";
        }
        return area;
    }
}
