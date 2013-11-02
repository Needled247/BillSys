package com.bill.dao;

import com.bill.bean.TBL_USERSINFO;
import com.bill.bean.tbl_billInfo;
import com.bill.pojo.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.transform.Transformers;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: 蒋浩
 * Date: 13-5-23
 * Time: 下午4:55
 * 数据库操作类，实现BillSysDAO
 */
public class BillSysDAOImpl implements BillSysDAO {
    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory = configureSessionFactory();

    @Override
    public String loginCheck(BillSys_User user){
        String level = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Query query = session.createSQLQuery("select level from BillSys_User where username='" + user.getUsername() + "' and password='" + user.getPassword() + "'");
            List<BillSys_User> list = query.list();
            session.getTransaction().commit();
            Iterator it = list.iterator();
            while (it.hasNext()){
                level = it.next().toString();
            }
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
        }
        finally {
            session.flush();
            session.clear();
            session.close();
        }
        return level;
    }

    @Override

    /**
     * 对应功能：获取所有新申请数据
     * 返回：List 装载 gtao_phone_view
     */
    public List getApplyList() {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "select * from gtao_phone_view where userId!='' and isHandle='0';" ;
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(gtao_phone_view.class));
        try{
            li = query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return li;
    }

    /**
     * 功能：获取所有付费申请数据
     * @return List
     */
    @Override
    public List getSaleApplyList() {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM gtao_phone_sale WHERE userId!='' AND isHandle='0'";
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(gtao_Phone_bc_sale.class));
        try{
            li = query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return li;
    }

    /**
     * 对应功能：查看申请详细数据
     * @param view
     * @return List 装载 gtao_phone_view
     */
    @Override
    public List getApplyDetail(gtao_phone_view view) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "select * from gtao_phone_view where longNum='"+view.getLongNum()+"' and userId='"+view.getUserId()+"'";
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(gtao_phone_view.class));
        try{
            li = query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return li;
    }

    /**
     * 查看付费申请模块：获得详细信息数据
     * @param sale
     * @return List
     */
    @Override
    public List getSaleApplyDetail(gtao_Phone_bc_sale sale,String tbl) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM "+tbl+" WHERE longNum=? AND userId=?";
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(gtao_Phone_bc_sale.class));
        query.setParameter(0,sale.getLongNum());
        query.setParameter(1,sale.getUserId());
        try{
            li = query.list();
            session.flush();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
            
        }
        return li;
    }

    /**
     * 功能：获取数据库gtao_phone_MIME中ip地址和地区映射
     * @return List 全部数据
     */
    @Override
    public List getAreaMime() {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from gtao_Phone_MIME as mime";
        Query query = session.createQuery(hql);
        try{
            li = query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return li;
    }

    /**
     * 修改申请信息后将数据插入User表
     * @param user
     * @return boolean
     */
    @Override
    public boolean userRegister(gtao_Phone_User user) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
            
        }
        return flag;
    }

    /**
     * 功能：修改编辑后的详细申请信息，返回true修改成功，返回false修改失败。
     * @param view
     * @return  flag
     */
    @Override
    public boolean editApplyDetail(gtao_phone_view view,String tbl_name,String pnum,String user) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "update "+tbl_name+" set longNum='"+view.getLongNum()+"',userId='"
                +view.getUserId()+"',shortNum='"+view.getShortNum()+"',mobile='"
                +view.getMobile()+"',ip='"+view.getIp()+"',vlan='"+view.getVlan()+"',Installer='"
                +view.getInstaller()+"',InstallTime='"+view.getInstallTime()+"',isHandle='1',gate='"
                +view.getGate()+"',type='"+view.getType()+"' where longNum='"+pnum+"' and userId='"+user+"'";
        Query query = session.createSQLQuery(sql);
        try {
            query.executeUpdate();
            session.getTransaction().commit();
            flag = true;
        }
        catch (Exception e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
            
        }
        return flag;
    }

    /**
     * 功能：初始化错误数据
     * @param tbl_name
     * @param pnum
     * @param user
     * @return flag
     */
    @Override
    public boolean initApplyDetail(String tbl_name, String pnum, String user) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "update "+tbl_name+" set userId='',mobile='',ipadd='',isHandle='',Installer='',InstallTime='',type='' where longNum='"+pnum+"' and userId='"+user+"'";
        Query query = session.createSQLQuery(sql);
        try{
            int mark = query.executeUpdate();
            session.getTransaction().commit();
            session.flush();
            if(mark>0){
                flag = true;
            }
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
            
        }
        return flag;
    }

    /**
     * 查看付费申请模块：修改申请数据
     * @param sale
     * @param pnum
     * @param user
     * @return
     */
    @Override
    public boolean editSaleApplyDetail(gtao_Phone_bc_sale sale, String pnum, String user,String tbl) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "update "+tbl+" set longNum='"+sale.getLongNum()+"',userId='"
                +sale.getUserId()+"',shortNum='"+sale.getShortNum()+"',mobile='"+sale.getMobile()+"',money='"
                +sale.getMoney()+"',isPay='"+sale.getIsPay()+"',vlan='"+sale.getVlan()+"',installer='"+sale.getInstaller()
                +"',installTime='"+sale.getInstallTime()+"',ip='"+sale.getIp()+"',isHandle='1',gate='"
                +sale.getGate()+"',type='"+sale.getType()+"' where longNum='"+pnum+"' and userId='"+user+"'";
        Query query = session.createSQLQuery(sql);
        try{
            int i = query.executeUpdate();
            transaction.commit();
            session.flush();
            if(i>0){
                flag = true;
            }
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
            
        }
        return flag;
    }

    /**
     * 查看付费申请模块：初始化错误号码数据
     * @param pnum
     * @param user
     * @return boolean
     */
    @Override
    public boolean initSaleApplyDetail(String pnum, String user,String tbl) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "update "+tbl+" set userId='',mobile='',ipadd='',isHandle='',type='',isPay='',Installer='',InstallTime='' where longNum='"+pnum+"' and userId='"+user+"'";
        Query query = session.createSQLQuery(sql);
        try {
            int mark = query.executeUpdate();
            session.getTransaction().commit();
            session.flush();
            if(mark>0){
                flag = true;
            }
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
            
        }
        return flag;
    }

    /**
     * 功能：获取所有用户数据
     * @return List
     */
    @Override
    public List getAllUser() {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from gtao_Phone_User as user";
        Query query = session.createQuery(hql);
        try{
            li = query.list();
            session.flush();
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return li;
    }

    @Override
    public boolean updateUserInfo(gtao_Phone_User user) {
        boolean flag = false;
        sessionFactory = configureSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try{
            session.update(user);
            session.getTransaction().commit();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
        }
        return flag;
    }

    /**
     * 号码管理模块：按地区查询数据
     * @param tbl
     * @param status
     * @return List
     */
    @Override
    public List SearchByArea(String tbl, String status) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "";
        if(status.equals("0")||status=="0"){     //未使用号码
            sql = "select * from "+tbl+" where userId=''";
        }
        else if(status.equals("1")||status=="1"){     //以使用号码
            sql = "select * from "+tbl+" where userId!=''";
        }
        else if(status.equals("2")||status=="2"){   //全部号码
            sql = "select * from "+tbl;
        }
        Query query = null;
        if(tbl.contains("sale")){
            query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(gtao_Phone_bc_sale.class));
        }
        else {
            query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(gtao_phone_view.class));
        }
        li = query.list();
        return li;
    }

    /**
     * 号码管理模块：按号码查询
     * @param phoneNum
     * @return List
     */
    @Override
    public Map SearchByNum(String phoneNum) {
        Map<String,List> map = new HashMap<String, List>();
        List ali = new ArrayList();
        List sli = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM gtao_phone_view WHERE longNum=?";
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(gtao_phone_view.class));
        query.setParameter(0,phoneNum);
        ali = query.list();
        session.clear();
        String hql = "FROM gtao_Phone_bc_sale AS sale WHERE sale.longNum=?";
        Query query1 = session.createQuery(hql);
        query1.setParameter(0,phoneNum);
        sli = query1.list();
        if(ali.isEmpty()&&sli.isEmpty()){
            map = null;
        }
        if(!ali.isEmpty()){
            map.put("free",ali);
        }
        if(!sli.isEmpty()){
            map.put("sale",sli);
        }
        session.clear();
        session.close();
        return map;
    }

    /**
     * 根据号码得到用户全部信息
     * @param user
     * @return  li
     */
    @Override
    public List getUserByNum(gtao_Phone_User user) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from gtao_Phone_User as user where user.longNum='"+user.getLongNum()+"' and user.userid='"+user.getUserid()+"'";
        Query query = session.createQuery(hql);
        try{
            li = query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return li;
    }

    @Override
    public List getUserById(String userid) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from gtao_Phone_User as user where user.userid=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,userid);
        try{
            li = query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return li;
    }

    /**
     * 号码管理模块：查询号码功能
     * @param tbl
     * @param phoneNum
     * @return List
     */
    @Override
    public List getAreaInfoByNum(String tbl, String phoneNum) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "select * from "+tbl+" where longNum='"+phoneNum+"'";
        Query query = null;
        if(tbl.contains("sale")){
            query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(gtao_Phone_bc_sale.class));
        }
        else {
            query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(gtao_phone_view.class));
        }
        try {
            li = query.list();
            session.flush();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return li;
    }

    /**
     * 号码管理模块：付费号码详细数据取得
     * @param phoneNum
     * @return List
     */
    @Override
    public List getSaleInfoByNum(String phoneNum) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from gtao_Phone_bc_sale as sale where sale.longNum='"+phoneNum+"'";
        Query query = session.createQuery(hql);
        try{
            li = query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return li;
    }

    /**
     * 号码管理模块：修改号码信息功能
     * @param view
     * @param tbl
     * @param pnum
     * @return boolean
     */
    @Override
    public boolean editFreeNumInfo(gtao_phone_view view, String tbl, String pnum) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "update "+tbl+" set longNum='"+view.getLongNum()+"',userId='"+view.getUserId()+
                "',shortNum='"+view.getShortNum()+"',mobile='"+view.getMobile()+"',ipAdd='"+view.getIpAdd()+
                "',vlan='"+view.getVlan()+"',gate='"+view.getGate()+"' where longNum='"+pnum+"'";
        Query query = session.createSQLQuery(sql);
        try {
            if(query.executeUpdate()>0){
                session.getTransaction().commit();
                flag = true;
            }
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
        }
        return flag;
    }

    /**
     * 号码管理模块：修改付费号码数据
     * @param sale
     * @param pnum
     * @return boolean
     */
    @Override
    public boolean editSaleNumInfo(gtao_Phone_bc_sale sale, String pnum) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "update gtao_Phone_bc_sale set longNum='"+sale.getLongNum()+"',userId='"+sale.getUserId()+
                "',shortNum='"+sale.getShortNum()+"',mobile='"+sale.getMobile()+"',ipAdd='"+sale.getIpAdd()+
                "',vlan='"+sale.getVlan()+"',money='"+sale.getMoney()+"',gate='"+sale.getGate()+"' where longNum='"+pnum+"'";
        Query query = session.createSQLQuery(sql);
        try{
            query.executeUpdate();
            session.getTransaction().commit();
            flag = true;
        }
        catch (HibernateException e){
            flag = false;
        }
        finally {
            session.clear();
            session.close();
        }
        return flag;
    }

    /**
     * 号码管理模块：删除号码功能
     * @param phoneNum
     * @param tbl
     * @return boolean
     */
    @Override
    public boolean delFreeNumInfo(String phoneNum, String tbl) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "delete from "+tbl+" where longNum='"+phoneNum+"'";
        Query query = session.createSQLQuery(sql);
        try {
            query.executeUpdate();
            session.getTransaction().commit();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
        }
        return flag;
    }

    /**
     * 号码管理模块：删除付费号码功能
     * @param phoneNum
     * @return
     */
    @Override
    public boolean delSaleNumInfo(String phoneNum) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "delete from gtao_Phone_bc_sale where longNum='"+phoneNum+"'";
        Query query = session.createSQLQuery(sql);
        try {
            query.executeUpdate();
            session.getTransaction().commit();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
        }
        return flag;
    }

    /**
     * 将Excel一行的数据导入数据库
     * @param sale
     * @param view
     * @param tbl
     * @return 导入数量 int
     */
    @Override
    public int excel2db(gtao_Phone_bc_sale sale, gtao_phone_view view, String tbl) {
        int insNum = 0;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "";
        if(!(this.checkRepeat(sale,view,tbl))){
            return 0;
        }
            if(tbl.equals("gtao_Phone_bc_sale")||tbl=="gtao_Phone_bc_sale"){
                sql = "insert into "+tbl+"(longNum,shortNum,ip,money,vlan,gate) values (?,?,?,?,?,?)";
                Query query = session.createSQLQuery(sql);
                query.setParameter(0,sale.getLongNum());
                query.setParameter(1,sale.getShortNum());
                query.setParameter(2,sale.getIp());
                query.setParameter(3,sale.getMoney());
                query.setParameter(4,sale.getVlan());
                query.setParameter(5,sale.getGate());
                insNum = query.executeUpdate();
                session.getTransaction().commit();
                session.flush();
                session.clear();
            }
            else {
                sql = "insert into "+tbl+"(longNum,shortNum,ip,vlan,gate) values (?,?,?,?,?)";
                Query query = session.createSQLQuery(sql);
                query.setParameter(0,view.getLongNum());
                query.setParameter(1,view.getShortNum());
                query.setParameter(2,view.getIp());
                query.setParameter(3,view.getVlan());
                query.setParameter(4,view.getGate());
                insNum = query.executeUpdate();
                session.getTransaction().commit();
                session.flush();
                session.clear();
            }
        session.clear();
        session.close();
        return insNum;
    }

    /**
     * select * from gtao_phone_profile
     * @return List
     */
    @Override
    public List getAllFeeProfile() {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from gtao_phone_profile";
        Query query = session.createQuery(hql);
        li = query.list();
        session.clear();
        session.close();
        
        return li;
    }

    /**
     * 获取计费策略数据
     * @param tbl
     * @return List
     */
    @Override
    public List getAllCelue(String tbl) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "select * from "+tbl;
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(gtao_phone_celue.class));
        li = query.list();
        session.clear();
        session.close();
        
        return li;
    }

    /**
     * 根据组策略Id获取对应策略表名
     * @param groupId
     * @return String
     */
    @Override
    public String getTblNameByGroupId(String groupId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from gtao_phone_group as group where group.userGroup=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,groupId);
        List li = new ArrayList();
        li = query.list();
        Iterator it = li.iterator();
        gtao_phone_group group = new gtao_phone_group();
        String tblName = "";
        while (it.hasNext()){
            group = (gtao_phone_group)it.next();
            tblName = group.getCelueTbl();
        }
        session.clear();
        session.close();
        
        return tblName;
    }

    @Override
    public String getTblNameByGroupName(String groupName) {
        String tblName = "";
        List li = new ArrayList();
        gtao_phone_group group = new gtao_phone_group();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from gtao_phone_group as group where group.groupDetail=?";
        Query query = session.createQuery(hql);
        query.setParameter(0,groupName);
        try{
            li = query.list();
            session.flush();
            Iterator it = li.iterator();
            while (it.hasNext()){
                group = (gtao_phone_group)it.next();
                tblName = group.getCelueTbl();
            }
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return tblName;
    }

    /**
     * 按呼叫前缀查找计费策略信息
     * @param prefix
     * @return list
     */
    @Override
    public List getCelueByPrefix(String prefix,String userGroup) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String tbl = this.getTblNameByGroupId(userGroup);
        String sql = "SELECT * FROM "+tbl+" WHERE SPREFIX=?";
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(gtao_phone_celue.class));
        query.setParameter(0,prefix);
        li = query.list();
        session.clear();
        session.close();
        
        return li;
    }

    /**
     * 修改计费策略方法
     * @param celue
     * @return boolean
     */
    @Override
    public boolean updateStrategyInfo(gtao_phone_celue celue,String group) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String tbl = this.getTblNameByGroupId(group);
        try {
            String sql = "UPDATE "+tbl+" SET SPROFILE=?,SPREFIX=?,RATESTIME1=?,RATES1=?," +
                    "RATESTIME2=?,RATES2=?,RATESTIME3=?,RATES3=?,OTHERFEE=?," +
                    "SPECIALTIMEBEGIN1=?,SPECIALTIMEEND1=?,SPECIALTIMEBEGIN2=?," +
                    "SPECIALTIMEEND2=?,SPECIALTIMEBEGIN3=?,SPECIALTIMEEND3=?," +
                    "SPECIALTIMEFEE1=?,SPECIALTIMEFEE2=?,SPECIALTIMEFEE3=? WHERE ID=?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0,celue.getSPROFILE());
            query.setParameter(1,celue.getSPREFIX());
            query.setParameter(2,celue.getRATESTIME1());
            query.setParameter(3,celue.getRATES1());
            query.setParameter(4,celue.getRATESTIME2());
            query.setParameter(5,celue.getRATES2());
            query.setParameter(6,celue.getRATESTIME3());
            query.setParameter(7,celue.getRATES3());
            query.setParameter(8,celue.getOTHERFEE());
            query.setParameter(9,celue.getSPECIALTIMEBEGIN1());
            query.setParameter(10,celue.getSPECIALTIMEEND1());
            query.setParameter(11,celue.getSPECIALTIMEBEGIN2());
            query.setParameter(12,celue.getSPECIALTIMEEND2());
            query.setParameter(13,celue.getSPECIALTIMEBEGIN3());
            query.setParameter(14,celue.getSPECIALTIMEEND3());
            query.setParameter(15,celue.getSPECIALTIMEFEE1());
            query.setParameter(16,celue.getSPECIALTIMEFEE2());
            query.setParameter(17,celue.getSPECIALTIMEFEE3());
            query.setParameter(18,celue.getID());
            query.executeUpdate();
            session.getTransaction().commit();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
            
        }
        return flag;
    }

    /**
     * 添加计费策略方法
     * @return boolean
     */
    @Override
    public boolean saveStrategyInfo(gtao_phone_celue celue,String group) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String tbl = this.getTblNameByGroupId(group);
        try {
            String sql = "INSERT INTO "+tbl+"(SPROFILE,SPREFIX,RATESTIME1,RATES1,RATESTIME2,RATES2,RATESTIME3,RATES3," +
                    "OTHERFEE,SPECIALTIMEBEGIN1,SPECIALTIMEEND1,SPECIALTIMEBEGIN2,SPECIALTIMEEND2," +
                    "SPECIALTIMEBEGIN3,SPECIALTIMEEND3,SPECIALTIMEFEE1,SPECIALTIMEFEE2,SPECIALTIMEFEE3)VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0,celue.getSPROFILE());
            query.setParameter(1,celue.getSPREFIX());
            query.setParameter(2,celue.getRATESTIME1());
            query.setParameter(3,celue.getRATES1());
            query.setParameter(4,celue.getRATESTIME2());
            query.setParameter(5,celue.getRATES2());
            query.setParameter(6,celue.getRATESTIME3());
            query.setParameter(7,celue.getRATES3());
            query.setParameter(8,celue.getOTHERFEE());
            query.setParameter(9,celue.getSPECIALTIMEBEGIN1());
            query.setParameter(10,celue.getSPECIALTIMEEND1());
            query.setParameter(11,celue.getSPECIALTIMEBEGIN2());
            query.setParameter(12,celue.getSPECIALTIMEEND2());
            query.setParameter(13,celue.getSPECIALTIMEBEGIN3());
            query.setParameter(14,celue.getSPECIALTIMEEND3());
            query.setParameter(15,celue.getSPECIALTIMEFEE1());
            query.setParameter(16,celue.getSPECIALTIMEFEE2());
            query.setParameter(17,celue.getSPECIALTIMEFEE3());
            query.executeUpdate();
            session.getTransaction().commit();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally{
            session.clear();
            session.close();
            
        }
        return flag;
    }

    /**
     * 获取所有组策略方法
     * @return List
     */
    @Override
    public List getAllGroup() {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from gtao_phone_group";
        Query query = session.createQuery(hql);
        li = query.list();
        session.clear();
        session.close();
        
        return li;
    }

    /**
     * 向用户组表中插入新数据
     * @param group
     * @return boolean
     */
    @Override
    public boolean newUserGroup(gtao_phone_group group) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try{
            session.save(group);
            session.getTransaction().commit();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
            
        }
        return flag;
    }

    /**
     * 建立新组策略表
     * @param tblName
     * @return boolean
     */
    @Override
    public boolean createCelueTbl(String tblName) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "CREATE TABLE [dbo].["+tblName+"] (" +
                "[ID] int NOT NULL IDENTITY(1,1) PRIMARY KEY," +
                "[SPROFILE] varchar(20) NULL DEFAULT '' ," +
                "[SPREFIX] varchar(10) NULL DEFAULT '' ," +
                "[RATESTIME1] varchar(200) NULL DEFAULT '' ," +
                "[RATES1] varchar(20) NULL DEFAULT '' ," +
                "[RATESTIME2] varchar(200) NULL DEFAULT '' ," +
                "[RATES2] varchar(20) NULL DEFAULT '' ," +
                "[RATESTIME3] varchar(200) NULL DEFAULT '' ," +
                "[RATES3] varchar(20) NULL DEFAULT '' ," +
                "[OTHERFEE] varchar(20) NULL DEFAULT '' ," +
                "[SPECIALTIMEBEGIN1] varchar(20) NULL DEFAULT '' ," +
                "[SPECIALTIMEEND1] varchar(20) NULL DEFAULT '' ," +
                "[SPECIALTIMEBEGIN2] varchar(20) NULL DEFAULT '' ," +
                "[SPECIALTIMEEND2] varchar(20) NULL DEFAULT '' ," +
                "[SPECIALTIMEBEGIN3] varchar(20) NULL DEFAULT '' ," +
                "[SPECIALTIMEEND3] varchar(20) NULL DEFAULT '' ," +
                "[SPECIALTIMEFEE1] varchar(20) NULL DEFAULT '' ," +
                "[SPECIALTIMEFEE2] varchar(20) NULL DEFAULT '' ," +
                "[SPECIALTIMEFEE3] varchar(20) NULL DEFAULT '' " +
                ")";
        Query query = session.createSQLQuery(sql);
        try {
            query.executeUpdate();
            session.getTransaction().commit();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
            
        }
        return flag;
    }

    /**
     * 删除用户组策略
     * @param userGroup
     * @return boolean
     */
    @Override
    public boolean deleteUserGroup(String userGroup) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String dropTbl = this.getTblNameByGroupId(userGroup);
        String sql = "DELETE FROM gtao_phone_group WHERE userGroup=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0,userGroup);
        try {
            if(this.deleteGroupTable(dropTbl)){
                query.executeUpdate();
                session.getTransaction().commit();
                flag = true;
            }
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
            
        }
        return flag;
    }

    /**
     * 删除详细计费策略
     * @param prefix
     * @param group
     * @return boolean
     */
    @Override
    public boolean deleteStrategyInfo(String prefix, String group) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String tbl = this.getTblNameByGroupId(group);
        String sql = "DELETE FROM "+tbl+" WHERE SPREFIX=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0,prefix);
        try {
            query.executeUpdate();
            session.getTransaction().commit();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
            
        }
        return flag;
    }

    /**
     * 删除一条用户记录
     * @param id
     * @param longNum
     * @return boolean
     */
    @Override
    public boolean delInfoFromUser(int id, String longNum) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        gtao_Phone_User user = new gtao_Phone_User();
        user.setId(id);
        user.setLongNum(longNum);
        try {
            session.delete(user);
            session.getTransaction().commit();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
            
        }
        return flag;
    }

    @Override
    public List getCallHistoryByMonth(String month,String today) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM tbl_billInfo"+month+" WHERE StartTime BETWEEN '"+today+" 00:00:00.000' AND '"+today+" 23:59:59.999'";
        try {
            Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(tbl_billInfo.class));
            li = query.list();
            session.flush();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
            
        }
        return li;
    }

    /**
     * 多条件查询通话记录
     * @param month
     * @param phoneNum
     * @param callType
     * @return List
     */
    @Override
    public List getCallHistory(String month, String phoneNum, String callType) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "";
        if(callType.equals("null")||callType=="null"){
            if(!(phoneNum.equals("0")||phoneNum=="0")){
                sql = "SELECT * FROM tbl_billInfo"+month+
                        " WHERE ucCallerNumber='"+phoneNum+"' OR ucCalledNumber='"+phoneNum+"'";
            }
            else {
                sql = "SELECT * FROM tbl_billInfo"+month;
            }
        }
        else {
            if(!(phoneNum.equals("0")||phoneNum=="0")){
                sql = "SELECT * FROM tbl_billInfo"+month+
                        " WHERE (ucCallerNumber='"+phoneNum+"' OR ucCalledNumber='"+phoneNum+"') AND ucCallAttribute='"+callType+"'";
            }
            else {
                sql = "SELECT * FROM tbl_billInfo"+month+
                        " WHERE ucCallAttribute='"+callType+"'";
            }

        }
        try {
            Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(tbl_billInfo.class));
            li = query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
            
        }
        return li;
    }

    /**
     * 按时间段、电话号码查询通话记录
     * @param fromDate
     * @param toDate
     * @param longNum
     * @return
     */
    @Override
    public List getCallHistoryByDate(String fromDate, String toDate, String longNum) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "";
        //如果电话号码为空
        if(longNum==null||longNum.equals("")){
            sql = "SELECT * FROM gtao_phone_bill WHERE StartTime BETWEEN '"+fromDate+" 00:00:00.000' AND '"+toDate+" 23:59:59.999'";
            Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(tbl_billInfo.class));
            li = query.list();
        }
        //电话不为空
        else {
            sql = "SELECT * FROM gtao_phone_bill WHERE (StartTime BETWEEN '"+fromDate+" 00:00:00.000' AND '"+toDate+" 23:59:59.999') AND (ucCallerNumber=? OR ucCalledNumber=?)";
            Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(tbl_billInfo.class));
            query.setParameter(0,longNum);
            query.setParameter(1,longNum);
            li = query.list();
        }
        session.flush();
        session.clear();
        
        return li;
    }

    /**
     * 按ID获取通话详细信息
     * @param id
     * @return List
     */
    @Override
    public List getCallHistoryById(String id,String startTime) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * FROM tbl_billInfo"+startTime+" WHERE ID=?";
        try {
            Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(tbl_billInfo.class));
            query.setParameter(0,id);
            li = query.list();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.flush();
            session.clear();
            session.close();
            
        }
        return li;
    }

    /**
     * 从Radius中获取用户信息
     * @param userid
     * @return List
     */
    @Override
    public List getUserInfoFromRadius(String userid) {
        List li = new ArrayList();
        sessionFactory = configureOracleSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM TBL_USERSINFO WHERE SUSERNAME=?";
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(TBL_USERSINFO.class));
        query.setParameter(0,userid);
        try {
            li = query.list();
            session.flush();
        }
        catch (Exception e){
            e.printStackTrace();
            sessionFactory = configureSessionFactory();
        }
        finally {
            session.clear();
            session.close();
            sessionFactory = configureSessionFactory();
        }
        return li;
    }

    @Override
    public boolean initFreeNum(String phoneNum, String tbl) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "";
        Query query = null;
        if(tbl.contains("sale")){
            sql = "UPDATE "+tbl+" SET ipAdd='',userId='',isPay='',isHandle='',type='',mobile='',Installer='',InstallTime=''" +
                    "WHERE longNum=?";
            query = session.createSQLQuery(sql);
            query.setParameter(0,phoneNum);
        }
        else {
            sql = "UPDATE "+tbl+" SET ipAdd='',userId='',isHandle='',type='',mobile='',Installer='',InstallTime=''" +
                    "WHERE longNum=?";
            query = session.createSQLQuery(sql);
            query.setParameter(0,phoneNum);
        }
        try{
            query.executeUpdate();
            transaction.commit();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.flush();
            session.clear();
            session.close();
            
        }
        return flag;
    }

    /**
     * 开户
     * @param view
     * @param sale
     * @param tbl
     * @param longNum
     * @return boolean
     */
    @Override
    public boolean createAccount(gtao_phone_view view, gtao_Phone_bc_sale sale, String tbl, String longNum) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "";
        Query query = null;
        if(tbl.contains("sale")){
            sql = "UPDATE "+tbl+" SET userId=?,type=?,mobile=?,Installer=?,InstallTime=?,isPay=? WHERE longNum=?";
            query = session.createSQLQuery(sql);
            query.setParameter(0,sale.getUserId());
            query.setParameter(1,sale.getType());
            query.setParameter(2,sale.getMobile());
            query.setParameter(3,sale.getInstaller());
            query.setParameter(4,sale.getInstallTime());
            query.setParameter(5,sale.getIsPay());
            query.setParameter(6,longNum);
        }
        else{
            sql = "UPDATE "+tbl+" SET userId=?,type=?,mobile=?,Installer=?,InstallTime=? WHERE longNum=?";
            query = session.createSQLQuery(sql);
            query.setParameter(0,view.getUserId());
            query.setParameter(1,view.getType());
            query.setParameter(2,view.getMobile());
            query.setParameter(3,view.getInstaller());
            query.setParameter(4,view.getInstallTime());
            query.setParameter(5,longNum);
        }
        //commit
        try{
            query.executeUpdate();
            transaction.commit();
            session.flush();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            transaction.rollback();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
            
        }
        return flag;
    }

    /**
     * 从128数据库获取用户住址
     * @param userid
     * @return String
     */
    @Override
    public String getUserAddress(String userid) {
        String address = "";
        sessionFactory = configureOracleSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "SELECT SFEEPHONE FROM TBL_USERS WHERE SUSERNAME=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0,userid);
        try{
            List li = new ArrayList();
            li = query.list();
            Iterator it = li.iterator();
            while (it.hasNext()){
                address = it.next().toString();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            sessionFactory = configureSessionFactory();
        }
        finally {
            session.clear();
            session.close();
            sessionFactory = configureSessionFactory();
        }
        return address;
    }

    /**
     * 获取用户通话记录  按月份
     * @param longNum
     * @param shortNum
     * @param month
     * @return List
     */
    @Override
    public List getUserCalledDetail(String longNum, String shortNum, String month,String startTime,String endTime) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "";
        Query query = null;
        //查询全部
        if(month==null&&startTime==null&&endTime==null){
            sql = "SELECT * FROM gtao_phone_bill WHERE (ucCallerNumber=? OR ucCalledNumber=? OR ucCallerNumber=? OR ucCalledNumber=?) ORDER BY StartTime";
            query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(tbl_billInfo.class));
            query.setParameter(0,longNum);
            query.setParameter(1,longNum);
            query.setParameter(2,shortNum);
            query.setParameter(3,shortNum);
        }
        //查询某月
        else if (month!=null&&startTime==null&&endTime==null){
            sql = "SELECT * FROM tbl_billInfo"+month+" WHERE (ucCallerNumber=? OR ucCalledNumber=? OR ucCallerNumber=? OR ucCalledNumber=?) ORDER BY StartTime";
            query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(tbl_billInfo.class));
            query.setParameter(0,longNum);
            query.setParameter(1,longNum);
            query.setParameter(2,shortNum);
            query.setParameter(3,shortNum);
        }
        //时间段查询
        else if(month==null&&startTime!=null&&endTime!=null){
            startTime = startTime + " 00:00:00.000";
            endTime = endTime + " 23:59:59.999";
            sql = "SELECT * FROM gtao_phone_bill WHERE (ucCallerNumber=? OR ucCalledNumber=? OR ucCallerNumber=? OR ucCalledNumber=?) AND (StartTime BETWEEN ? AND ?) ORDER BY StartTime";
            query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(tbl_billInfo.class));
            query.setParameter(0,longNum);
            query.setParameter(1,longNum);
            query.setParameter(2,shortNum);
            query.setParameter(3,shortNum);
            query.setParameter(4,startTime);
            query.setParameter(5,endTime);
        }
        try{
            li = query.list();
            session.flush();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return li;
    }

    /**
     * 获取某号码某月某属性通话总长
     * @param month
     * @param longNum
     * @param shortNum
     * @param attribute
     * @return
     */
    @Override
    public String getMonthConversation(String month, String longNum, String shortNum, int attribute) {
        String convTime = "";
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "";
        Query query = null;
        //attribute==0，不分通话属性
        if(attribute==0){
            sql = "SELECT SUM(dwConversationTime) FROM tbl_billInfo"+month+" WHERE ucCallerNumber=? OR ucCallerNumber=?";
            query = session.createSQLQuery(sql);
            query.setParameter(0,longNum);
            query.setParameter(1,shortNum);
        }
        try{
            li = query.list();
            session.flush();
            Iterator it = li.iterator();
            while (it.hasNext()){
                convTime = it.next()+"";
            }
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return convTime;
    }

    @Override
    public List getCelueBean(String groupTbl, short CallAttribute) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM "+groupTbl+" WHERE SPREFIX=?";
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(gtao_phone_celue.class));
        query.setParameter(0,CallAttribute);
        try {
            li = query.list();
            session.flush();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return li;
    }

    @Override
    public boolean changePass(String newpass, String uid) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "UPDATE BillSys_User SET password=? WHERE username=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0,newpass);
        query.setParameter(1,uid);
        try{
            query.executeUpdate();
            transaction.commit();
            session.flush();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
        }
        return flag;
    }

    @Override
    public boolean checkPassword(String uid, String password) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT COUNT(*) FROM BillSys_User WHERE username=? AND password=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0,uid);
        query.setParameter(1,password);
        List li = new ArrayList();
        try {
            li = query.list();
            session.flush();
            Iterator it = li.iterator();
            while (it.hasNext()){
                String line = it.next().toString();
                if(Integer.parseInt(line)>0){
                    flag = true;
                }
            }
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return flag;
    }

    /**
     * 在用户账号密码表中插入新注册的用户
     * @param user
     * @return
     */
    @Override
    public boolean addUser(BillSys_User user) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.saveOrUpdate(user);
            transaction.commit();
            session.flush();
            flag  = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
        }
        return flag;
    }

    /**
     * 删除用户账号密码
     * @param user
     * @return
     */
    @Override
    public boolean removeUser(BillSys_User user) {
        boolean  flag = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "DELETE FROM BillSys_User WHERE username=? AND password=?";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0,user.getUsername());
        query.setParameter(1,user.getPassword());
        try {
            query.executeUpdate();
            transaction.commit();
            session.flush();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
        }
        return flag;
    }

    @Override
    public List getUserInfoByNum(String longNum) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM gtao_Phone_User WHERE longNum=?";
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(gtao_Phone_User.class));
        query.setParameter(0,longNum);
        try {
            li = query.list();
            session.flush();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return li;
    }

    /**
     * 修改用户状态
     * @param status
     * @param id
     * @return boolean
     */
    @Override
    public boolean updateUserStatus(String status,int id) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        gtao_Phone_User user = new gtao_Phone_User();
        user.setId(id);
        user.setStatus(status);
        try{
            session.update(user);
            transaction.commit();
            session.flush();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            transaction.rollback();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
        }
        return flag;
    }

    /**
     * 查询65开头拨打总数
     * @param tbl
     * @param userClass
     * @return
     */
    @Override
    public List getOverTimeUser(String tbl, String userClass) {
        List li = new ArrayList();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "SELECT ucCallerNumber,SUM(dwConversationTime) FROM "+tbl+" WHERE ucCallerNumber LIKE '65%' GROUP BY ucCallerNumber";
        try{
            Query query = session.createSQLQuery(sql);
            li = query.list();
            session.flush();
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        finally {
            session.clear();
            session.close();
        }
        return li;
    }

    /**
     * 删除组策略表
     * @param tbl
     * @return boolean
     */
    public boolean deleteGroupTable(String tbl){
        boolean flag = false;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "DROP TABLE "+tbl;
        Query query = session.createSQLQuery(sql);
        try {
            query.executeUpdate();
            session.getTransaction().commit();
            flag = true;
        }
        catch (HibernateException e){
            e.printStackTrace();
            flag = false;
        }
        finally {
            session.clear();
            session.close();
        }
        return flag;
    }

    /**
     * 数据库查重
     * @param sale
     * @param view
     * @param tbl
     * @return boolean
     */
    public boolean checkRepeat(gtao_Phone_bc_sale sale, gtao_phone_view view, String tbl){
        boolean flag = true;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "";
        if(tbl.equals("gtao_Phone_bc_sale")||tbl=="gtao_Phone_bc_sale"){
            sql = "select count(*) from "+tbl+" where longNum=? and shortNum=? and ip=? and money=? and vlan=?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0,sale.getLongNum());
            query.setParameter(1,sale.getShortNum());
            query.setParameter(2,sale.getIp());
            query.setParameter(3,sale.getMoney());
            query.setParameter(4,sale.getVlan());
            List li = query.list();
            Iterator it = li.iterator();
            while (it.hasNext()){
                int i = (Integer)it.next();
                if(i>0){
                    flag = false;
                }
            }
        }
        else {
            sql = "select count(*) from "+tbl+" where longNum=? and shortNum=? and ip=? and vlan=?";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0,view.getLongNum());
            query.setParameter(1,view.getShortNum());
            query.setParameter(2,view.getIp());
            query.setParameter(3,view.getVlan());
            List li = query.list();
            Iterator it = li.iterator();
            while (it.hasNext()){
                int i = (Integer)it.next();
                if(i>0){
                    flag = false;
                }
            }
        }
        session.clear();
        session.close();
        
        return flag;
    }

    /**
     * 功能：初始化Hibernate，得到234 HuaWei Sql Server sessionFactory对象。
     * @return sessionFactory
     * @throws HibernateException
     */
    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    /**
     * 功能：初始化Hibernate，得到128 radius oracle 数据库 sessionFactory对象。
     * @return sessionFactory
     * @throws HibernateException
     */
    private static SessionFactory configureOracleSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure("radius.cfg.xml");
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
}
