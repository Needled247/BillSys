package com.bill.bean;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 13-6-26
 * Time: ÏÂÎç2:43
 * To change this template use File | Settings | File Templates.
 */
public class tbl_billInfo {
    private int ID;
    private int Sid;
    private short ucFree;
    private short ucChargeParty;
    private Date StartTime;
    private Date EndTime;
    private int dwConversationTime;
    private short UcCallerAddressNature;
    private String ucCallerNumber;
    private String ucCallerDepartment;
    private String ucCallerName;
    private String dwCallerID;
    private short ucCalledAddressNature;
    private String ucCalledNumber;
    private String ucOrgCalledNumber;
    private int wCentrexGroupNumber;
    private String ucCallerCtxNumber;
    private String ucCalledCtxNumber;
    private int wTrunkGroupIn;
    private int dwTrunkCircuitIn;
    private int wTrunkGroupOut;
    private int dwTrunkCircuitOut;
    private short ucCallerProtocol;
    private short ucCalledProtocol;
    private short ucCallerSignalling;
    private short ucCalledSignalling;
    private int wCallerMGId;
    private int wCalledMGId;
    private short ucCallerCategory;
    private short ucCallType;
    private short ucCallAttribute;
    private int dwCallerRTPIPAddress;
    private int dwCalledRTPIPAddress;
    private double fCallFee;
    private int dwCallID;
    private Integer ucOrientation;
    private short ucCallFlag;
    private short ucBillAttribute;
    private short ucUnicallType;
    private String ucUnicallRealCallerNumber;
    private String ucUnicallRealCalledNumber;
    private int subPbxID;
    private String neIP;
    private int domainID;
    private short restrictFlag;
    private String restrictId;
    private String areaCode;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSid() {
        return Sid;
    }

    public void setSid(int sid) {
        Sid = sid;
    }

    public short getUcFree() {
        return ucFree;
    }

    public void setUcFree(short ucFree) {
        this.ucFree = ucFree;
    }

    public short getUcChargeParty() {
        return ucChargeParty;
    }

    public void setUcChargeParty(short ucChargeParty) {
        this.ucChargeParty = ucChargeParty;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public int getDwConversationTime() {
        return dwConversationTime;
    }

    public void setDwConversationTime(int dwConversationTime) {
        this.dwConversationTime = dwConversationTime;
    }

    public short getUcCallerAddressNature() {
        return UcCallerAddressNature;
    }

    public void setUcCallerAddressNature(short ucCallerAddressNature) {
        UcCallerAddressNature = ucCallerAddressNature;
    }

    public String getUcCallerNumber() {
        return ucCallerNumber;
    }

    public void setUcCallerNumber(String ucCallerNumber) {
        this.ucCallerNumber = ucCallerNumber;
    }

    public String getUcCallerDepartment() {
        return ucCallerDepartment;
    }

    public void setUcCallerDepartment(String ucCallerDepartment) {
        this.ucCallerDepartment = ucCallerDepartment;
    }

    public String getUcCallerName() {
        return ucCallerName;
    }

    public void setUcCallerName(String ucCallerName) {
        this.ucCallerName = ucCallerName;
    }

    public String getDwCallerID() {
        return dwCallerID;
    }

    public void setDwCallerID(String dwCallerID) {
        this.dwCallerID = dwCallerID;
    }

    public short getUcCalledAddressNature() {
        return ucCalledAddressNature;
    }

    public void setUcCalledAddressNature(short ucCalledAddressNature) {
        this.ucCalledAddressNature = ucCalledAddressNature;
    }

    public String getUcCalledNumber() {
        return ucCalledNumber;
    }

    public void setUcCalledNumber(String ucCalledNumber) {
        this.ucCalledNumber = ucCalledNumber;
    }

    public String getUcOrgCalledNumber() {
        return ucOrgCalledNumber;
    }

    public void setUcOrgCalledNumber(String ucOrgCalledNumber) {
        this.ucOrgCalledNumber = ucOrgCalledNumber;
    }

    public int getwCentrexGroupNumber() {
        return wCentrexGroupNumber;
    }

    public void setwCentrexGroupNumber(int wCentrexGroupNumber) {
        this.wCentrexGroupNumber = wCentrexGroupNumber;
    }

    public String getUcCallerCtxNumber() {
        return ucCallerCtxNumber;
    }

    public void setUcCallerCtxNumber(String ucCallerCtxNumber) {
        this.ucCallerCtxNumber = ucCallerCtxNumber;
    }

    public String getUcCalledCtxNumber() {
        return ucCalledCtxNumber;
    }

    public void setUcCalledCtxNumber(String ucCalledCtxNumber) {
        this.ucCalledCtxNumber = ucCalledCtxNumber;
    }

    public int getwTrunkGroupIn() {
        return wTrunkGroupIn;
    }

    public void setwTrunkGroupIn(int wTrunkGroupIn) {
        this.wTrunkGroupIn = wTrunkGroupIn;
    }

    public int getDwTrunkCircuitIn() {
        return dwTrunkCircuitIn;
    }

    public void setDwTrunkCircuitIn(int dwTrunkCircuitIn) {
        this.dwTrunkCircuitIn = dwTrunkCircuitIn;
    }

    public int getwTrunkGroupOut() {
        return wTrunkGroupOut;
    }

    public void setwTrunkGroupOut(int wTrunkGroupOut) {
        this.wTrunkGroupOut = wTrunkGroupOut;
    }

    public int getDwTrunkCircuitOut() {
        return dwTrunkCircuitOut;
    }

    public void setDwTrunkCircuitOut(int dwTrunkCircuitOut) {
        this.dwTrunkCircuitOut = dwTrunkCircuitOut;
    }

    public short getUcCallerProtocol() {
        return ucCallerProtocol;
    }

    public void setUcCallerProtocol(short ucCallerProtocol) {
        this.ucCallerProtocol = ucCallerProtocol;
    }

    public short getUcCalledProtocol() {
        return ucCalledProtocol;
    }

    public void setUcCalledProtocol(short ucCalledProtocol) {
        this.ucCalledProtocol = ucCalledProtocol;
    }

    public short getUcCallerSignalling() {
        return ucCallerSignalling;
    }

    public void setUcCallerSignalling(short ucCallerSignalling) {
        this.ucCallerSignalling = ucCallerSignalling;
    }

    public short getUcCalledSignalling() {
        return ucCalledSignalling;
    }

    public void setUcCalledSignalling(short ucCalledSignalling) {
        this.ucCalledSignalling = ucCalledSignalling;
    }

    public int getwCallerMGId() {
        return wCallerMGId;
    }

    public void setwCallerMGId(int wCallerMGId) {
        this.wCallerMGId = wCallerMGId;
    }

    public int getwCalledMGId() {
        return wCalledMGId;
    }

    public void setwCalledMGId(int wCalledMGId) {
        this.wCalledMGId = wCalledMGId;
    }

    public short getUcCallerCategory() {
        return ucCallerCategory;
    }

    public void setUcCallerCategory(short ucCallerCategory) {
        this.ucCallerCategory = ucCallerCategory;
    }

    public short getUcCallType() {
        return ucCallType;
    }

    public void setUcCallType(short ucCallType) {
        this.ucCallType = ucCallType;
    }

    public short getUcCallAttribute() {
        return ucCallAttribute;
    }

    public void setUcCallAttribute(short ucCallAttribute) {
        this.ucCallAttribute = ucCallAttribute;
    }

    public int getDwCallerRTPIPAddress() {
        return dwCallerRTPIPAddress;
    }

    public void setDwCallerRTPIPAddress(int dwCallerRTPIPAddress) {
        this.dwCallerRTPIPAddress = dwCallerRTPIPAddress;
    }

    public int getDwCalledRTPIPAddress() {
        return dwCalledRTPIPAddress;
    }

    public void setDwCalledRTPIPAddress(int dwCalledRTPIPAddress) {
        this.dwCalledRTPIPAddress = dwCalledRTPIPAddress;
    }

    public double getfCallFee() {
        return fCallFee;
    }

    public void setfCallFee(double fCallFee) {
        this.fCallFee = fCallFee;
    }

    public int getDwCallID() {
        return dwCallID;
    }

    public void setDwCallID(int dwCallID) {
        this.dwCallID = dwCallID;
    }

    public Integer getUcOrientation() {
        return ucOrientation;
    }

    public void setUcOrientation(Integer ucOrientation) {
        this.ucOrientation = ucOrientation;
    }

    public short getUcCallFlag() {
        return ucCallFlag;
    }

    public void setUcCallFlag(short ucCallFlag) {
        this.ucCallFlag = ucCallFlag;
    }

    public short getUcBillAttribute() {
        return ucBillAttribute;
    }

    public void setUcBillAttribute(short ucBillAttribute) {
        this.ucBillAttribute = ucBillAttribute;
    }

    public short getUcUnicallType() {
        return ucUnicallType;
    }

    public void setUcUnicallType(short ucUnicallType) {
        this.ucUnicallType = ucUnicallType;
    }

    public String getUcUnicallRealCallerNumber() {
        return ucUnicallRealCallerNumber;
    }

    public void setUcUnicallRealCallerNumber(String ucUnicallRealCallerNumber) {
        this.ucUnicallRealCallerNumber = ucUnicallRealCallerNumber;
    }

    public String getUcUnicallRealCalledNumber() {
        return ucUnicallRealCalledNumber;
    }

    public void setUcUnicallRealCalledNumber(String ucUnicallRealCalledNumber) {
        this.ucUnicallRealCalledNumber = ucUnicallRealCalledNumber;
    }

    public int getSubPbxID() {
        return subPbxID;
    }

    public void setSubPbxID(int subPbxID) {
        this.subPbxID = subPbxID;
    }

    public String getNeIP() {
        return neIP;
    }

    public void setNeIP(String neIP) {
        this.neIP = neIP;
    }

    public int getDomainID() {
        return domainID;
    }

    public void setDomainID(int domainID) {
        this.domainID = domainID;
    }

    public short getRestrictFlag() {
        return restrictFlag;
    }

    public void setRestrictFlag(short restrictFlag) {
        this.restrictFlag = restrictFlag;
    }

    public String getRestrictId() {
        return restrictId;
    }

    public void setRestrictId(String restrictId) {
        this.restrictId = restrictId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
