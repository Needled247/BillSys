package com.bill.pojo;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-5-28
 * Time: 下午11:21
 * To change this template use File | Settings | File Templates.
 */
public class gtao_Phone_User {
    private Integer id;
    private String userid;
    private String mobile;
    private String phoneIp;
    private String vlan;
    private String longNum;
    private String shortNum;
    private String itime;
    private String lastUpd;
    private String Tactics;//策略
    private String status;
    private String email;
    private String balance;//余额
    private String stored;//预存
    private String MaturityTime;//到期时间
    private String tbl;
    private String gate;
    private String protocal;

    public String getProtocal() {
        return protocal;
    }

    public void setProtocal(String protocal) {
        this.protocal = protocal;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoneIp() {
        return phoneIp;
    }

    public void setPhoneIp(String phoneIp) {
        this.phoneIp = phoneIp;
    }

    public String getVlan() {
        return vlan;
    }

    public void setVlan(String vlan) {
        this.vlan = vlan;
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

    public String getItime() {
        return itime;
    }

    public void setItime(String itime) {
        this.itime = itime;
    }

    public String getLastUpd() {
        return lastUpd;
    }

    public void setLastUpd(String lastUpd) {
        this.lastUpd = lastUpd;
    }

    public String getTactics() {
        return Tactics;
    }

    public void setTactics(String tactics) {
        Tactics = tactics;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getStored() {
        return stored;
    }

    public void setStored(String stored) {
        this.stored = stored;
    }

    public String getMaturityTime() {
        return MaturityTime;
    }

    public void setMaturityTime(String maturityTime) {
        MaturityTime = maturityTime;
    }
}
