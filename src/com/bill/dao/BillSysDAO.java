package com.bill.dao;

import com.bill.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * User: JH
 * Date: 13-5-23
 * Time: ÏÂÎç4:28
 * DAO½Ó¿Ú
 */
public interface BillSysDAO {
    public String loginCheck(BillSys_User user);
    public List getApplyList();
    public List getSaleApplyList();
    public List getApplyDetail(gtao_phone_view view);
    public List getSaleApplyDetail(gtao_Phone_bc_sale sale,String tbl);
    public List getAreaMime();
    public boolean userRegister(gtao_Phone_User user);
    public boolean editApplyDetail(gtao_phone_view view,String tbl_name,String pnum,String user);
    public boolean initApplyDetail(String tbl_name,String pnum,String user);
    public boolean editSaleApplyDetail(gtao_Phone_bc_sale sale,String pnum,String user,String tbl);
    public boolean initSaleApplyDetail(String pnum,String user,String tbl);
    public List getAllUser();
    public boolean updateUserInfo(gtao_Phone_User user);
    public List SearchByArea(String tbl,String status);
    public Map SearchByNum(String phoneNum);
    public List getUserByNum(gtao_Phone_User user);
    public List getUserById(String userid);
    public List getAreaInfoByNum(String tbl,String phoneNum);
    public List getSaleInfoByNum(String phoneNum);
    public boolean editFreeNumInfo(gtao_phone_view view,String tbl,String pnum);
    public boolean editSaleNumInfo(gtao_Phone_bc_sale sale,String pnum);
    public boolean delFreeNumInfo(String phoneNum,String tbl);
    public boolean delSaleNumInfo(String phoneNum);
    public int excel2db(gtao_Phone_bc_sale sale,gtao_phone_view view,String tbl);
    public List getAllFeeProfile();
    public List getAllCelue(String tbl);
    public String getTblNameByGroupId(String groupId);
    public String getTblNameByGroupName(String groupName);
    public List getCelueByPrefix(String prefix,String userGroup);
    public boolean updateStrategyInfo(gtao_phone_celue celue,String group);
    public boolean saveStrategyInfo(gtao_phone_celue celue,String group);
    public List getAllGroup();
    public boolean newUserGroup(gtao_phone_group group);
    public boolean createCelueTbl(String tblName);
    public boolean deleteUserGroup(String userGroup);
    public boolean deleteStrategyInfo(String prefix,String group);
    public boolean delInfoFromUser(int id,String longNum);
    public List getCallHistoryByMonth(String month,String today);
    public List getCallHistory(String month,String phoneNum,String callType);
    public List getCallHistoryByDate(String fromDate,String toDate,String longNum);
    public List getCallHistoryById(String id,String startTime);
    public List getUserInfoFromRadius(String userid);
    public boolean initFreeNum(String phoneNum,String tbl);
    public boolean createAccount(gtao_phone_view view,gtao_Phone_bc_sale sale,String tbl,String longNum);
    public String getUserAddress(String userid);
    public List getUserCalledDetail(String longNum,String shortNum,String month,String startTime,String endTime);
    public String getMonthConversation(String month,String longNum,String shortNum,int attribute);
    public List getCelueBean(String groupTbl,short CallAttribute);
    public boolean changePass(String newpass,String uid);
    public boolean checkPassword(String uid,String password);
    public boolean addUser(BillSys_User user);
    public boolean removeUser(BillSys_User user);
    public List getUserInfoByNum(String longNum);
    public boolean updateUserStatus(String status,int id);
    public List getOverTimeUser(String tbl,String userClass);
}
