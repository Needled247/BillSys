package com.bill.action;

import com.bill.dao.BillSysDAOImpl;
import com.bill.pojo.BillSys_User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * Author:蒋浩
 * Date: 13-5-6
 * Time: 下午3:18
 * 功能：登陆验证Action
 */
public class loginCheck extends ActionSupport{
    private String username;
    private String password;
    private String result;
    private InputStream inputStream;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private Map<String , String> session;

    public Map<String, String> getSession() {
        return session;
    }

    public void setSession(Map<String, String> session) {
        this.session = session;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String execute(){
        BillSys_User user = new BillSys_User();
        user.setUsername(username);
        user.setPassword(password);
        String level = new BillSysDAOImpl().loginCheck(user);
        if(level!=null){
            HttpServletRequest request = ServletActionContext.getRequest();
            request.getSession().setAttribute("uid",username);
            request.getSession().setAttribute("lid",level);
            inputStream = new ByteArrayInputStream(level.getBytes());
            return  SUCCESS;
        }
        else{
            inputStream = new ByteArrayInputStream("error".getBytes());
            return SUCCESS;
        }
    }
}
