package com.bill.pojo;

/**
 * Author:����
 * Date: 13-5-6
 * Time: ����3:46
 * ���ݱ�BillSys_User��ӳ����
 */
public class BillSys_User
{
    private Integer id;        //id
    private String username;    //username
    private String password;    //password
    private Integer level;      //level

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
