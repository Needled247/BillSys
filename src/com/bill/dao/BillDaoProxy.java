package com.bill.dao;

import com.bill.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * Created by HP on 14-3-7.
 */
public class BillDaoProxy implements BillSysDAO {
    private BillSysDAO billSysDAO;

    public void setBillSysDAO(BillSysDAO billSysDAO) {
        this.billSysDAO = billSysDAO;
    }

    @Override
    
    public String loginCheck(BillSys_User user) {
        return billSysDAO.loginCheck(user);
    }

    @Override
    
    public List getApplyList() {
        return billSysDAO.getApplyList();
    }

    @Override
    
    public List getSaleApplyList() {
        return billSysDAO.getSaleApplyList();
    }

    @Override
    
    public List getApplyDetail(gtao_phone_view view) {
        return billSysDAO.getApplyDetail(view);
    }

    @Override
    
    public List getSaleApplyDetail(gtao_Phone_bc_sale sale, String tbl) {
        return billSysDAO.getSaleApplyDetail(sale,tbl);
    }

    @Override
    
    public List getAreaMime() {
        return billSysDAO.getAreaMime();
    }

    @Override
    
    public boolean userRegister(gtao_Phone_User user) {
        return billSysDAO.userRegister(user);
    }

    @Override
    
    public boolean editApplyDetail(gtao_phone_view view, String tbl_name, String pnum, String user) {
        return billSysDAO.editApplyDetail(view,tbl_name,pnum,user);
    }

    @Override
    
    public boolean initApplyDetail(String tbl_name, String pnum, String user) {
        return billSysDAO.initApplyDetail(tbl_name,pnum,user);
    }

    @Override
    
    public boolean editSaleApplyDetail(gtao_Phone_bc_sale sale, String pnum, String user, String tbl) {
        return billSysDAO.editSaleApplyDetail(sale, pnum, user, tbl);
    }

    @Override
    
    public boolean initSaleApplyDetail(String pnum, String user, String tbl) {
        return billSysDAO.initSaleApplyDetail(pnum,user,tbl);
    }

    @Override
    
    public List getAllUser() {
        return billSysDAO.getAllUser();
    }

    @Override
    
    public boolean updateUserInfo(gtao_Phone_User user) {
        return billSysDAO.updateUserInfo(user);
    }

    @Override
    
    public List SearchByArea(String tbl, String status) {
        return billSysDAO.SearchByArea(tbl,status);
    }

    @Override
    
    public Map SearchByNum(String phoneNum) {
        return billSysDAO.SearchByNum(phoneNum);
    }

    @Override
    
    public List getUserByNum(gtao_Phone_User user) {
        return billSysDAO.getUserByNum(user);
    }

    @Override
    
    public List getUserById(String userid) {
        return billSysDAO.getUserById(userid);
    }

    @Override
    
    public List getAreaInfoByNum(String tbl, String phoneNum) {
        return billSysDAO.getAreaInfoByNum(tbl,phoneNum);
    }

    @Override
    
    public List getSaleInfoByNum(String phoneNum) {
        return billSysDAO.getSaleInfoByNum(phoneNum);
    }

    @Override
    
    public boolean editFreeNumInfo(gtao_phone_view view, String tbl, String pnum) {
        return billSysDAO.editFreeNumInfo(view,tbl,pnum);
    }

    @Override
    
    public boolean editSaleNumInfo(gtao_Phone_bc_sale sale, String pnum) {
        return billSysDAO.editSaleNumInfo(sale,pnum);
    }

    @Override
    
    public boolean delFreeNumInfo(String phoneNum, String tbl) {
        return billSysDAO.delFreeNumInfo(phoneNum,tbl);
    }

    @Override
    
    public boolean delSaleNumInfo(String phoneNum) {
        return billSysDAO.delSaleNumInfo(phoneNum);
    }

    @Override
    
    public int excel2db(gtao_Phone_bc_sale sale, gtao_phone_view view, String tbl) {
        return billSysDAO.excel2db(sale,view,tbl);
    }

    @Override
    
    public List getAllFeeProfile() {
        return billSysDAO.getAllFeeProfile();
    }

    @Override
    
    public List getAllCelue(String tbl) {
        return billSysDAO.getAllCelue(tbl);
    }

    @Override
    
    public String getTblNameByGroupId(String groupId) {
        return billSysDAO.getTblNameByGroupId(groupId);
    }

    @Override
    
    public String getTblNameByGroupName(String groupName) {
        return billSysDAO.getTblNameByGroupName(groupName);
    }

    @Override
    
    public List getCelueByPrefix(String prefix, String userGroup) {
        return billSysDAO.getCelueByPrefix(prefix,userGroup);
    }

    @Override
    
    public boolean updateStrategyInfo(gtao_phone_celue celue, String group) {
        return billSysDAO.updateStrategyInfo(celue,group);
    }

    @Override
    
    public boolean saveStrategyInfo(gtao_phone_celue celue, String group) {
        return billSysDAO.saveStrategyInfo(celue,group);
    }

    @Override
    
    public List getAllGroup() {
        return billSysDAO.getAllGroup();
    }

    @Override
    
    public boolean newUserGroup(gtao_phone_group group) {
        return billSysDAO.newUserGroup(group);
    }

    @Override
    
    public boolean createCelueTbl(String tblName) {
        return billSysDAO.createCelueTbl(tblName);
    }

    @Override
    
    public boolean deleteUserGroup(String userGroup) {
        return billSysDAO.deleteUserGroup(userGroup);
    }

    @Override
    
    public boolean deleteStrategyInfo(String prefix, String group) {
        return billSysDAO.deleteStrategyInfo(prefix,group);
    }

    @Override
    
    public boolean delInfoFromUser(int id, String longNum) {
        return billSysDAO.delInfoFromUser(id,longNum);
    }

    @Override
    
    public List getCallHistoryByMonth(String month, String today) {
        return billSysDAO.getCallHistoryByMonth(month,today);
    }

    @Override
    
    public List getCallHistory(String month, String phoneNum, String callType) {
        return billSysDAO.getCallHistory(month,phoneNum,callType);
    }

    @Override
    
    public List getCallHistoryByDate(String fromDate, String toDate, String longNum) {
        return billSysDAO.getCallHistoryByDate(fromDate,toDate,longNum);
    }

    @Override
    
    public List getCallHistoryById(String id, String startTime) {
        return billSysDAO.getCallHistoryById(id,startTime);
    }

    @Override
    
    public List getUserInfoFromRadius(String userid) {
        return billSysDAO.getUserInfoFromRadius(userid);
    }

    @Override
    
    public boolean initFreeNum(String phoneNum, String tbl) {
        return billSysDAO.initFreeNum(phoneNum,tbl);
    }

    @Override
    
    public boolean createAccount(gtao_phone_view view, gtao_Phone_bc_sale sale, String tbl, String longNum) {
        return billSysDAO.createAccount(view,sale,tbl,longNum);
    }

    @Override
    
    public String getUserAddress(String userid) {
        return billSysDAO.getUserAddress(userid);
    }

    @Override
    
    public List getUserCalledDetail(String longNum, String shortNum, String month, String startTime, String endTime) {
        return billSysDAO.getUserCalledDetail(longNum,shortNum,month,startTime,endTime);
    }

    @Override
    
    public String getMonthConversation(String month, String longNum, String shortNum, int attribute) {
        return billSysDAO.getMonthConversation(month,longNum,shortNum,attribute);
    }

    @Override
    
    public List getCelueBean(String groupTbl, short CallAttribute) {
        return billSysDAO.getCelueBean(groupTbl,CallAttribute);
    }

    @Override
    
    public boolean changePass(String newpass, String uid) {
        return billSysDAO.changePass(newpass,uid);
    }

    @Override
    
    public boolean checkPassword(String uid, String password) {
        return billSysDAO.checkPassword(uid,password);
    }

    @Override
    
    public boolean addUser(BillSys_User user) {
        return billSysDAO.addUser(user);
    }

    @Override
    
    public boolean removeUser(BillSys_User user) {
        return billSysDAO.removeUser(user);
    }

    @Override
    
    public List getUserInfoByNum(String longNum) {
        return billSysDAO.getUserInfoByNum(longNum);
    }

    @Override
    
    public boolean updateUserStatus(String status, int id) {
        return billSysDAO.updateUserStatus(status,id);
    }

    @Override
    
    public List getOverTimeUser(String tbl, String userClass) {
        return billSysDAO.getOverTimeUser(tbl,userClass);
    }

    @Override
    public List viewUserPage(int start, int length,String key) {
        return billSysDAO.viewUserPage(start,length,key);
    }

    @Override
    public int getUserCount(String key) {
        return billSysDAO.getUserCount(key);
    }
}
