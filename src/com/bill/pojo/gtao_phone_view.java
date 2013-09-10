package com.bill.pojo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-5-23
 * Time: ÉÏÎç9:56
 * To change this template use File | Settings | File Templates.
 */
public class gtao_phone_view {
    private Integer id;
    private String longNum;
    private String shortNum;
    private String ipAdd;
    private String userId;
    private String isHandle;
    private Date upTime ;
    private String type;
    private String mobile;
    private String ip;
    private String vlan;
    private String Installer;
    private String InstallTime;
    private String tbl;
    private String gate;

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getTbl() {
        return tbl;
    }

    public void setTbl(String tbl) {
        this.tbl = tbl;
    }

    public String getInstallTime() {
        return InstallTime;
    }

    public void setInstallTime(String installTime) {
        InstallTime = installTime;
    }

    public String getVlan() {
        return vlan;
    }

    public void setVlan(String vlan) {
        this.vlan = vlan;
    }

    public String getInstaller() {
        return Installer;
    }

    public void setInstaller(String installer) {
        Installer = installer;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLongNum() {
        return longNum;
    }

    public void setLongNum(String longNum) {
        this.longNum = longNum;
    }

    public String getShortNum() {
        return shortNum;
    }

    public void setShortNum(String shortNum) {
        this.shortNum = shortNum;
    }

    public String getIpAdd() {
        return ipAdd;
    }

    public void setIpAdd(String ipAdd) {
        this.ipAdd = ipAdd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHandle() {
        return isHandle;
    }

    public void setHandle(String handle) {
        isHandle = handle;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
