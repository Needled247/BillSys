package com.bill.action;

import com.bill.bean.TBL_USERSINFO;
import com.bill.dao.BillSysDAOImpl;
import com.bill.pojo.gtao_Phone_User;
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
 * Date: 13-8-17
 * Time: ÏÂÎç4:15
 * To change this template use File | Settings | File Templates.
 */
public class BillingPrintAction extends ActionSupport {
    private String longNum;
    BillSysDAOImpl impl = new BillSysDAOImpl();

    public String getLongNum() {
        return longNum;
    }

    public void setLongNum(String longNum) {
        this.longNum = longNum;
    }

    @Override
    public String execute() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=GBK");
        PrintWriter out = response.getWriter();
        StringBuilder sb = new StringBuilder();
        sb.append("{\"tables\":[");
        List userInfoList = new ArrayList();
        userInfoList = impl.getUserInfoByNum(longNum);
        Iterator it = userInfoList.iterator();
        gtao_Phone_User user = null;
        String userid = "";
        if (it.hasNext()){
            user= (gtao_Phone_User)it.next();
            sb.append("{\"userid\":\""+user.getUserid()+"\",");
            sb.append("\"mobile\":\""+user.getMobile()+"\",");
            sb.append("\"gate\":\""+user.getGate()+"\",");
            sb.append("\"longNum\":\""+user.getLongNum()+"\",");
            sb.append("\"shortNum\":\""+user.getShortNum()+"\",");
            sb.append("\"ip\":\""+user.getPhoneIp()+"\",");
            sb.append("\"vlan\":\""+user.getVlan()+"\",");
            userid = user.getUserid();
        }                   //name,address,mac,installer,installtime
        List radiusList = new ArrayList();
        radiusList = impl.getUserInfoFromRadius(user.getUserid());
        Iterator radiusIter = radiusList.iterator();
        TBL_USERSINFO radiusBean = null;
        if (radiusIter.hasNext()){
            radiusBean = (TBL_USERSINFO)radiusIter.next();
            sb.append("\"name\":\""+new String(radiusBean.getSUSERNAME().getBytes("ISO-8859-1"),"GBK")+"\",")
            .append("\"address\":\""+new String(impl.getUserAddress(userid).getBytes("ISO-8859-1"),"GBK")+"\",")
            .append("\"mac\":\""+new String(radiusBean.getSPOSTCODE().getBytes("ISO-8859-1"),"GBK")+"\"}");
        }
        sb.append("]}");
        out.print(sb);
        out.flush();
        out.close();
        return null;
    }
}
