package com.bill.pojo;

/**
 * Author:蒋浩
 * Date: 13-5-6
 * Time: 下午3:46
 * 数据表BillSys_User的映射类
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
