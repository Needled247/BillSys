package com.bill.action;

import com.bill.dao.BillSysDAO;
import com.bill.pojo.gtao_Phone_User;
import com.bill.tool.BillSysTool;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Created by HP on 14-3-12.
 */
public class ViewUserAction extends ActionSupport {
    private BillSysDAO billService;

    private int iDisplayStart;
    private int iDisplayLength;
    private int sEcho;
    private String sSearch;

    public String getsSearch() {
        return sSearch;
    }

    public void setsSearch(String sSearch) {
        this.sSearch = sSearch;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public int getsEcho() {
        return sEcho;
    }

    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    public void setBillService(BillSysDAO billService) {
        this.billService = billService;
    }

    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        setiDisplayLength(Integer.parseInt(request.getParameter("iDisplayLength")));
        setiDisplayStart(Integer.parseInt(request.getParameter("iDisplayStart")));
        setsEcho(Integer.parseInt(request.getParameter("sEcho")));
        setsSearch(request.getParameter("sSearch"));
        List li = billService.viewUserPage(iDisplayStart,iDisplayLength,sSearch);
        int total = billService.getUserCount(sSearch);
        String json = null;
        json = tojson(li,total);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=GBK");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
        return null;
    }

    public String tojson(List list,int count)
    {
        String json = null; // 返回的json数据
        StringBuilder sb = list2Json(list);
        json = "{\"sEcho\":"+sEcho+",\"iTotalRecords\":"+count+",\"iTotalDisplayRecords\":"+count+",\"aaData\":"+sb+"}";
        return json;
    }

    public StringBuilder list2Json(List li){
        Iterator it = li.iterator();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(it.hasNext()){
            gtao_Phone_User bean = (gtao_Phone_User)it.next();
            sb.append("[")
                    .append("\"" + bean.getId() + "\",")
                    .append("\"" + bean.getUserid() + "\",")
                    .append("\"" + bean.getLongNum() + "\",")
                    .append("\"" + bean.getTactics()+"\",")
                    .append("\"" + bean.getItime() +"\",")
                    .append("\"" + BillSysTool.tblName2departmetName(bean.getTbl())+"\"")
                    .append("],");
        }
        if(sb.length()>1){
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        sb.append("]");
        return sb;
    }
}
