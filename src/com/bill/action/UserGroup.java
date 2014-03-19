package com.bill.action;

import com.bill.dao.BillSysDAO;
import com.bill.pojo.gtao_phone_group;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-6-20
 * Time: 上午11:18
 * To change this template use File | Settings | File Templates.
 */
public class UserGroup extends ActionSupport {
    private int id;
    private String userGroup;
    private String celueTbl;
    private String groupDetail;
    private String editType;
    private BillSysDAO billService;

    public BillSysDAO getBillService() {
        return billService;
    }

    public void setBillService(BillSysDAO billService) {
        this.billService = billService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getCelueTbl() {
        return celueTbl;
    }

    public void setCelueTbl(String celueTbl) {
        this.celueTbl = celueTbl;
    }

    public String getGroupDetail() {
        return groupDetail;
    }

    public void setGroupDetail(String groupDetail) {
        this.groupDetail = groupDetail;
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }

    @Override
    public String execute() throws Exception {
        /**
         * 查询所有用户组 type==search
         */
        if(editType.equals("search")||editType=="search"){
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/json;charset=GBK");
            PrintWriter out = response.getWriter();
            List li = new ArrayList();
            li = billService.getAllGroup();
            Iterator it = li.iterator();
            StringBuilder sb = new StringBuilder();
            gtao_phone_group group = new gtao_phone_group();
            sb.append("[");
            while (it.hasNext()){
                group = (gtao_phone_group)it.next();
                sb.append("[")
                        .append("\""+group.getId()+"\",")
                        .append("\""+group.getUserGroup()+"\",")
                        .append("\""+group.getGroupDetail()+"\"")
                        .append("],");
            }
            sb.append("]");
            sb.deleteCharAt(sb.lastIndexOf(","));
            out.print(sb);
            out.flush();
            out.close();
        }
        /**
         * editType == new  新建用户组策略
         */
        if(editType.equals("new")||editType=="new"){
            gtao_phone_group group = new gtao_phone_group();
            group.setUserGroup(userGroup);
            group.setGroupDetail(groupDetail);
            String celueTbl = "gtao_phone_celue"+userGroup;
            group.setCelueTbl(celueTbl);
            boolean flag = billService.createCelueTbl(celueTbl);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=GBK");
            PrintWriter out = response.getWriter();
            if(flag){
                 if(billService.newUserGroup(group)){
                     out.print("success");
                 }
            }
            else {
                out.print("fail");
            }
            out.flush();
            out.close();
        }
        /**
         * editType==del,删除用户组策略
         */
        if(editType.equals("del")||editType=="del"){
            boolean flag = billService.deleteUserGroup(userGroup);
            if(flag){
                HttpServletResponse response = ServletActionContext.getResponse();
                response.sendRedirect("UserGroup.jsp");
            }
        }
        return null;
    }
}
