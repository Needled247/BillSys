package com.bill.dao;

import com.bill.bean.TBL_USERSINFO;
import com.bill.bean.tbl_billInfo;
import com.bill.pojo.*;
import org.hibernate.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: 蒋浩
 * Date: 13-5-23
 * Time: 下午4:55
 * 数据库操作类，实现BillSysDAO
 */
public class BillSysDAOImpl implements BillSysDAO {
    private JdbcTemplate jdbcTemplateBmu;
    private JdbcTemplate jdbcTemplateRadius;

    public void setJdbcTemplateBmu(JdbcTemplate jdbcTemplateBmu) {
        this.jdbcTemplateBmu = jdbcTemplateBmu;
    }

    public void setJdbcTemplateRadius(JdbcTemplate jdbcTemplateRadius) {
        this.jdbcTemplateRadius = jdbcTemplateRadius;
    }

    @Override
    
    public String loginCheck(final BillSys_User user){
        String level = null;
        final List li = new ArrayList();
        String sql = "select level from BillSys_User where username=? and password=?";
        final List list = new ArrayList();
         jdbcTemplateBmu.query(sql, new PreparedStatementSetter() {
             @Override
             public void setValues(PreparedStatement preparedStatement) throws SQLException {
                 preparedStatement.setString(1, user.getUsername());
                 preparedStatement.setString(2, user.getPassword());
             }
         }, new RowCallbackHandler() {
             @Override
             public void processRow(ResultSet rs) throws SQLException {
                li.add(rs.getInt("level"));
             }
         });
        Iterator it = li.iterator();
        while (it.hasNext()){
            level = it.next().toString();
        }
        return level;
    }

    /**
     * 对应功能：获取所有新申请数据
     * 返回：List 装载 gtao_phone_view
     */
    @Override
    public List getApplyList() {
        final List li = new ArrayList();
        String sql = "select * from gtao_phone_view where userId!='' and isHandle='0';" ;
        jdbcTemplateBmu.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_phone_view bean = new gtao_phone_view();
                bean.setId(rs.getInt("id"));
                bean.setLongNum(rs.getString("longNum"));
                bean.setShortNum(rs.getString("shortNum"));
                bean.setIpAdd(rs.getString("ipAdd"));
                bean.setUserId(rs.getString("userId"));
                bean.setHandle(rs.getString("isHandle"));
                bean.setUpTime(rs.getDate("upTime"));
                bean.setType(rs.getString("type"));
                bean.setMobile(rs.getString("mobile"));
                bean.setIp(rs.getString("ip"));
                bean.setVlan(rs.getString("vlan"));
                bean.setInstaller(rs.getString("Installer"));
                bean.setInstallTime(rs.getString("InstallTime"));
                bean.setTbl(rs.getString("tbl"));
                bean.setGate(rs.getString("gate"));
                li.add(bean);
            }
        });
        return li;
    }

    /**
     * 功能：获取所有付费申请数据
     * @return List
     */
    @Override
    
    public List getSaleApplyList() {
        final List li = new ArrayList();
        String sql = "SELECT * FROM gtao_phone_sale WHERE userId!='' AND isHandle='0'";
        jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                gtao_Phone_bc_sale bean = new gtao_Phone_bc_sale();
                bean.setId(resultSet.getInt("id"));
                bean.setLongNum(resultSet.getString("longNum"));
                bean.setShortNum(resultSet.getString("shortNum"));
                bean.setIpAdd(resultSet.getString("ipadd"));
                bean.setUserId(resultSet.getString("userid"));
                bean.setIsHandle(resultSet.getString("ishandle"));
                bean.setUpTime(resultSet.getDate("uptime"));
                bean.setType(resultSet.getString("type"));
                bean.setMobile(resultSet.getString("mobile"));
                bean.setIp(resultSet.getString("ip"));
                bean.setMoney(resultSet.getString("money"));
                bean.setInstaller(resultSet.getString("Installer"));
                bean.setVlan(resultSet.getString("vlan"));
                bean.setInstallTime(resultSet.getString("InstallTime"));
                bean.setIsPay(resultSet.getString("ispay"));
                bean.setOrdId(resultSet.getString("ordid"));
                bean.setGate(resultSet.getString("gate"));
                bean.setTbl(resultSet.getString("tbl"));
                li.add(bean);
            }
        });
        return li;
    }

    /**
     * 对应功能：查看申请详细数据
     * @param view
     * @return List 装载 gtao_phone_view
     */
    @Override
    
    public List getApplyDetail(gtao_phone_view view) {
        final List li = new ArrayList();
        String sql = "select * from gtao_phone_view where longNum='"+view.getLongNum()+"'";
        jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_phone_view bean = new gtao_phone_view();
                bean.setId(rs.getInt("id"));
                bean.setLongNum(rs.getString("longNum"));
                bean.setShortNum(rs.getString("shortNum"));
                bean.setIpAdd(rs.getString("ipAdd"));
                bean.setUserId(rs.getString("userId"));
                bean.setHandle(rs.getString("isHandle"));
                bean.setUpTime(rs.getDate("upTime"));
                bean.setType(rs.getString("type"));
                bean.setMobile(rs.getString("mobile"));
                bean.setIp(rs.getString("ip"));
                bean.setVlan(rs.getString("vlan"));
                bean.setInstaller(rs.getString("Installer"));
                bean.setInstallTime(rs.getString("InstallTime"));
                bean.setTbl(rs.getString("tbl"));
                bean.setGate(rs.getString("gate"));
                li.add(bean);
            }
        });
        return li;
    }

    /**
     * 查看付费申请模块：获得详细信息数据
     * @param sale
     * @return List
     */
    @Override
    
    public List getSaleApplyDetail(final gtao_Phone_bc_sale sale,final String tbl) {
        final List li = new ArrayList();
        String sql = "SELECT * FROM "+tbl+" WHERE longNum=? AND userId=?";
        jdbcTemplateBmu.query(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,sale.getLongNum());
                pstmt.setString(2,sale.getUserId());
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                gtao_Phone_bc_sale bean = new gtao_Phone_bc_sale();
                bean.setId(resultSet.getInt("id"));
                bean.setLongNum(resultSet.getString("longNum"));
                bean.setShortNum(resultSet.getString("shortNum"));
                bean.setIpAdd(resultSet.getString("ipadd"));
                bean.setUserId(resultSet.getString("userid"));
                bean.setIsHandle(resultSet.getString("ishandle"));
                bean.setUpTime(resultSet.getDate("uptime"));
                bean.setType(resultSet.getString("type"));
                bean.setMobile(resultSet.getString("mobile"));
                bean.setIp(resultSet.getString("ip"));
                bean.setMoney(resultSet.getString("money"));
                bean.setInstaller(resultSet.getString("Installer"));
                bean.setVlan(resultSet.getString("vlan"));
                bean.setInstallTime(resultSet.getString("InstallTime"));
                bean.setIsPay(resultSet.getString("ispay"));
                bean.setOrdId(resultSet.getString("ordid"));
                bean.setGate(resultSet.getString("gate"));
                bean.setTbl(resultSet.getString("tbl"));
                li.add(bean);
            }
        });
        return li;
    }

    /**
     * 功能：获取数据库gtao_phone_MIME中ip地址和地区映射
     * @return List 全部数据
     */
    @Override
    
    public List<gtao_Phone_MIME> getAreaMime() {
        final List<gtao_Phone_MIME> li = new ArrayList<gtao_Phone_MIME>();
        String sql = "select * from gtao_Phone_MIME";
        jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_Phone_MIME bean = new gtao_Phone_MIME();
                bean.setId(rs.getInt("id"));
                bean.setDepartment(rs.getString("department"));
                bean.setIpregex(rs.getString("ipregex"));
                li.add(bean);
            }
        });
        return li;
    }

    /**
     * 修改申请信息后将数据插入User表
     * @param user
     * @return boolean
     */
    @Override
    
    public boolean userRegister(final gtao_Phone_User user) {
        String sql =
        "INSERT INTO gtao_Phone_User" +
        "(userid,mobile,phoneIp,vlan,longNum,shortNum,itime,lastUpd," +
        "Tactics,status,email,balance,stored,MaturityTime,tbl,gate,protocal)" +
        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int uNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,user.getUserid());
                pstmt.setString(2,user.getMobile());
                pstmt.setString(3,user.getPhoneIp());
                pstmt.setString(4,user.getVlan());
                pstmt.setString(5,user.getLongNum());
                pstmt.setString(6,user.getShortNum());
                pstmt.setString(7,user.getItime());
                pstmt.setString(8,user.getLastUpd());
                pstmt.setString(9,user.getTactics());
                pstmt.setString(10,user.getStatus());
                pstmt.setString(11,user.getEmail());
                pstmt.setString(12,user.getBalance());
                pstmt.setString(13,user.getStored());
                pstmt.setString(14,user.getMaturityTime());
                pstmt.setString(15,user.getTbl());
                pstmt.setString(16,user.getGate());
                pstmt.setString(17,user.getProtocal());
            }
        });
        if (uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 功能：修改编辑后的详细申请信息，返回true修改成功，返回false修改失败。
     * @param view
     * @return  flag
     */
    @Override
    
    public boolean editApplyDetail(final gtao_phone_view view,final String tbl_name,final String pnum,final String user) {
        String sql =
        "update "+tbl_name+" set longNum=?,userId=?,shortNum=?,mobile=?,ip=?,vlan=?," +
        "Installer=?,InstallTime=?,isHandle='1',gate=?,type=? where longNum=? and userId=?";
        int uNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,view.getLongNum());
                pstmt.setString(2,view.getUserId());
                pstmt.setString(3,view.getShortNum());
                pstmt.setString(4,view.getMobile());
                pstmt.setString(5,view.getIp());
                pstmt.setString(6,view.getVlan());
                pstmt.setString(7,view.getInstaller());
                pstmt.setString(8,view.getInstallTime());
                pstmt.setString(9,view.getGate());
                pstmt.setString(10,view.getType());
                pstmt.setString(11,pnum);
                pstmt.setString(12,user);
            }
        });
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 功能：初始化错误数据
     * @param tbl_name
     * @param pnum
     * @param user
     * @return flag
     */
    @Override
    
    public boolean initApplyDetail(String tbl_name, final String pnum, final String user) {
        boolean flag = false;
        String sql =
        "update "+tbl_name+" set userId='',mobile='',ipadd='',isHandle='',Installer='',InstallTime='',type=''" +
        " where longNum=? and userId=?";
        int uNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,pnum);
                pstmt.setString(2,user);
            }
        });
        if(uNum>0){
            return true;
        }
        return false;
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
        String sql = "update "+tbl+" set longNum='"+sale.getLongNum()+"',userId='"
                +sale.getUserId()+"',shortNum='"+sale.getShortNum()+"',mobile='"+sale.getMobile()+"',money='"
                +sale.getMoney()+"',isPay='"+sale.getIsPay()+"',vlan='"+sale.getVlan()+"',installer='"+sale.getInstaller()
                +"',installTime='"+sale.getInstallTime()+"',ip='"+sale.getIp()+"',isHandle='1',gate='"
                +sale.getGate()+"',type='"+sale.getType()+"' where longNum='"+pnum+"' and userId='"+user+"'";
        int uNum = jdbcTemplateBmu.update(sql);
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 查看付费申请模块：初始化错误号码数据
     * @param pnum
     * @param user
     * @return boolean
     */
    @Override
    
    public boolean initSaleApplyDetail(String pnum, String user,String tbl) {
        String sql =
        "update "+tbl+" set userId='',mobile='',ipadd='',isHandle='',type='',isPay='',Installer=''," +
        "InstallTime='' where longNum='"+pnum+"' and userId='"+user+"'";
        int uNum = jdbcTemplateBmu.update(sql);
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 功能：获取所有用户数据
     * @return List
     */
    @Override
    
    public List getAllUser() {
        final List li = new ArrayList();
        String sql = "select * from gtao_Phone_User";
        jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_Phone_User bean = new gtao_Phone_User();
                bean.setId(rs.getInt("id"));
                bean.setUserid(rs.getString("userid"));
                bean.setMobile(rs.getString("mobile"));
                bean.setPhoneIp(rs.getString("phoneip"));
                bean.setVlan(rs.getString("vlan"));
                bean.setLongNum(rs.getString("longNum"));
                bean.setShortNum(rs.getString("shortNum"));
                bean.setItime(rs.getString("itime"));
                bean.setLastUpd(rs.getString("lastUpd"));
                bean.setTactics(rs.getString("tactics"));
                bean.setStatus(rs.getString("status"));
                bean.setEmail(rs.getString("email"));
                bean.setBalance(rs.getString("balance"));
                bean.setStored(rs.getString("stored"));
                bean.setMaturityTime(rs.getString("maturitytime"));
                bean.setTbl(rs.getString("tbl"));
                bean.setGate(rs.getString("gate"));
                bean.setProtocal(rs.getString("protocal"));
                li.add(bean);
            }
        });
        return li;
    }

    @Override
    
    public boolean updateUserInfo(final gtao_Phone_User user) {
        boolean flag = false;
        String sql = "UPDATE gtao_Phone_User SET userid=?,mobile=?,phoneIp=?,vlan=?,longNum=?," +
        "shortNum=?,itime=?,lastUpd=?,Tactics=?,status=?,email=?,balance=?,stored=?,MaturityTime=?," +
        "tbl=?,gate=?,protocal=? where id=?";
        int uNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,user.getUserid());
                pstmt.setString(2,user.getMobile());
                pstmt.setString(3,user.getPhoneIp());
                pstmt.setString(4,user.getVlan());
                pstmt.setString(5,user.getLongNum());
                pstmt.setString(6,user.getShortNum());
                pstmt.setString(7,user.getItime());
                pstmt.setString(8,user.getLastUpd());
                pstmt.setString(9,user.getTactics());
                pstmt.setString(10,user.getStatus());
                pstmt.setString(11,user.getEmail());
                pstmt.setString(12,user.getBalance());
                pstmt.setString(13,user.getStored());
                pstmt.setString(14,user.getMaturityTime());
                pstmt.setString(15,user.getTbl());
                pstmt.setString(16,user.getGate());
                pstmt.setString(17,user.getProtocal());
                pstmt.setInt(18,user.getId());
            }
        });
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 号码管理模块：按地区查询数据
     * @param tbl
     * @param status
     * @return List
     */
    @Override
    
    public List SearchByArea(String tbl, String status) {
        final List li = new ArrayList();
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
            //gtao_phone_bc_sale
            jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    gtao_Phone_bc_sale bean = new gtao_Phone_bc_sale();
                    bean.setId(resultSet.getInt("id"));
                    bean.setLongNum(resultSet.getString("longNum"));
                    bean.setShortNum(resultSet.getString("shortNum"));
                    bean.setIpAdd(resultSet.getString("ipadd"));
                    bean.setUserId(resultSet.getString("userid"));
                    bean.setIsHandle(resultSet.getString("ishandle"));
                    bean.setUpTime(resultSet.getDate("uptime"));
                    bean.setType(resultSet.getString("type"));
                    bean.setMobile(resultSet.getString("mobile"));
                    bean.setIp(resultSet.getString("ip"));
                    bean.setMoney(resultSet.getString("money"));
                    bean.setInstaller(resultSet.getString("Installer"));
                    bean.setVlan(resultSet.getString("vlan"));
                    bean.setInstallTime(resultSet.getString("InstallTime"));
                    bean.setIsPay(resultSet.getString("ispay"));
                    bean.setOrdId(resultSet.getString("ordid"));
                    bean.setGate(resultSet.getString("gate"));
                    bean.setTbl(resultSet.getString("tbl"));
                    li.add(bean);
                }
            });
        }
        else {
            //gtao_phone_view
            jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet rs) throws SQLException {
                    gtao_phone_view bean = new gtao_phone_view();
                    bean.setId(rs.getInt("id"));
                    bean.setLongNum(rs.getString("longNum"));
                    bean.setShortNum(rs.getString("shortNum"));
                    bean.setIpAdd(rs.getString("ipAdd"));
                    bean.setUserId(rs.getString("userId"));
                    bean.setHandle(rs.getString("isHandle"));
                    bean.setUpTime(rs.getDate("upTime"));
                    bean.setType(rs.getString("type"));
                    bean.setMobile(rs.getString("mobile"));
                    bean.setIp(rs.getString("ip"));
                    bean.setVlan(rs.getString("vlan"));
                    bean.setInstaller(rs.getString("Installer"));
                    bean.setInstallTime(rs.getString("InstallTime"));
                    bean.setTbl(rs.getString("tbl"));
                    bean.setGate(rs.getString("gate"));
                    li.add(bean);
                }
            });
        }
        return li;
    }

    /**
     * 号码管理模块：按号码查询
     * @param phoneNum
     * @return List
     */
    @Override
    
    public Map SearchByNum(final String phoneNum) {
        Map<String,List> map = new HashMap<String, List>();
        final List ali = new ArrayList();
        final List sli = new ArrayList();
        String sql = "SELECT * FROM gtao_phone_view WHERE longNum=?";
        jdbcTemplateBmu.query(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,phoneNum);
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_phone_view bean = new gtao_phone_view();
                bean.setId(rs.getInt("id"));
                bean.setLongNum(rs.getString("longNum"));
                bean.setShortNum(rs.getString("shortNum"));
                bean.setIpAdd(rs.getString("ipAdd"));
                bean.setUserId(rs.getString("userId"));
                bean.setHandle(rs.getString("isHandle"));
                bean.setUpTime(rs.getDate("upTime"));
                bean.setType(rs.getString("type"));
                bean.setMobile(rs.getString("mobile"));
                bean.setIp(rs.getString("ip"));
                bean.setVlan(rs.getString("vlan"));
                bean.setInstaller(rs.getString("Installer"));
                bean.setInstallTime(rs.getString("InstallTime"));
                bean.setTbl(rs.getString("tbl"));
                bean.setGate(rs.getString("gate"));
                ali.add(bean);
            }
        });
        
        String hql = "SELECT * FROM gtao_Phone_bc_sale WHERE longNum=?";
        jdbcTemplateBmu.query(hql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,phoneNum);
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                gtao_Phone_bc_sale bean = new gtao_Phone_bc_sale();
                bean.setId(resultSet.getInt("id"));
                bean.setLongNum(resultSet.getString("longNum"));
                bean.setShortNum(resultSet.getString("shortNum"));
                bean.setIpAdd(resultSet.getString("ipadd"));
                bean.setUserId(resultSet.getString("userid"));
                bean.setIsHandle(resultSet.getString("ishandle"));
                bean.setUpTime(resultSet.getDate("uptime"));
                bean.setType(resultSet.getString("type"));
                bean.setMobile(resultSet.getString("mobile"));
                bean.setIp(resultSet.getString("ip"));
                bean.setMoney(resultSet.getString("money"));
                bean.setInstaller(resultSet.getString("Installer"));
                bean.setVlan(resultSet.getString("vlan"));
                bean.setInstallTime(resultSet.getString("InstallTime"));
                bean.setIsPay(resultSet.getString("ispay"));
                bean.setOrdId(resultSet.getString("ordid"));
                bean.setGate(resultSet.getString("gate"));
                bean.setTbl(resultSet.getString("tbl"));
                sli.add(bean);
            }
        });
        if(ali.isEmpty()&&sli.isEmpty()){
            map = null;
        }
        if(!ali.isEmpty()){
            map.put("free",ali);
        }
        if(!sli.isEmpty()){
            map.put("sale",sli);
        }
        return map;
    }

    /**
     * 根据号码得到用户全部信息
     * @param user
     * @return  li
     */
    @Override
    
    public List getUserByNum(gtao_Phone_User user) {
        final List li = new ArrayList();
        String hql = "select * from gtao_Phone_User where longNum='"+user.getLongNum()+"' and userid='"+user.getUserid()+"'";
        jdbcTemplateBmu.query(hql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_Phone_User bean = new gtao_Phone_User();
                bean.setId(rs.getInt("id"));
                bean.setUserid(rs.getString("userid"));
                bean.setMobile(rs.getString("mobile"));
                bean.setPhoneIp(rs.getString("phoneip"));
                bean.setVlan(rs.getString("vlan"));
                bean.setLongNum(rs.getString("longNum"));
                bean.setShortNum(rs.getString("shortNum"));
                bean.setItime(rs.getString("itime"));
                bean.setLastUpd(rs.getString("lastUpd"));
                bean.setTactics(rs.getString("tactics"));
                bean.setStatus(rs.getString("status"));
                bean.setEmail(rs.getString("email"));
                bean.setBalance(rs.getString("balance"));
                bean.setStored(rs.getString("stored"));
                bean.setMaturityTime(rs.getString("maturitytime"));
                bean.setTbl(rs.getString("tbl"));
                bean.setGate(rs.getString("gate"));
                bean.setProtocal(rs.getString("protocal"));
                li.add(bean);
            }
        });
        return li;
    }

    @Override
    
    public List getUserById(final String userid) {
        final List li = new ArrayList();
        String sql = "select * from gtao_Phone_User where userid=?";
        jdbcTemplateBmu.query(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,userid);
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_Phone_User bean = new gtao_Phone_User();
                bean.setId(rs.getInt("id"));
                bean.setUserid(rs.getString("userid"));
                bean.setMobile(rs.getString("mobile"));
                bean.setPhoneIp(rs.getString("phoneip"));
                bean.setVlan(rs.getString("vlan"));
                bean.setLongNum(rs.getString("longNum"));
                bean.setShortNum(rs.getString("shortNum"));
                bean.setItime(rs.getString("itime"));
                bean.setLastUpd(rs.getString("lastUpd"));
                bean.setTactics(rs.getString("tactics"));
                bean.setStatus(rs.getString("status"));
                bean.setEmail(rs.getString("email"));
                bean.setBalance(rs.getString("balance"));
                bean.setStored(rs.getString("stored"));
                bean.setMaturityTime(rs.getString("maturitytime"));
                bean.setTbl(rs.getString("tbl"));
                bean.setGate(rs.getString("gate"));
                bean.setProtocal(rs.getString("protocal"));
                li.add(bean);
            }
        });
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
        final List li = new ArrayList();
        String sql = "select * from "+tbl+" where longNum='"+phoneNum+"'";
        if(tbl.contains("sale")){
            //gtao_Phone_bc_sale.class
            jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    gtao_Phone_bc_sale bean = new gtao_Phone_bc_sale();
                    bean.setId(resultSet.getInt("id"));
                    bean.setLongNum(resultSet.getString("longNum"));
                    bean.setShortNum(resultSet.getString("shortNum"));
                    bean.setIpAdd(resultSet.getString("ipadd"));
                    bean.setUserId(resultSet.getString("userid"));
                    bean.setIsHandle(resultSet.getString("ishandle"));
                    bean.setUpTime(resultSet.getDate("uptime"));
                    bean.setType(resultSet.getString("type"));
                    bean.setMobile(resultSet.getString("mobile"));
                    bean.setIp(resultSet.getString("ip"));
                    bean.setMoney(resultSet.getString("money"));
                    bean.setInstaller(resultSet.getString("Installer"));
                    bean.setVlan(resultSet.getString("vlan"));
                    bean.setInstallTime(resultSet.getString("InstallTime"));
                    bean.setIsPay(resultSet.getString("ispay"));
                    bean.setOrdId(resultSet.getString("ordid"));
                    bean.setGate(resultSet.getString("gate"));
                    bean.setTbl(resultSet.getString("tbl"));
                    li.add(bean);
                }
            });
        }
        else {
            //gtao_phone_view.class
            jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet rs) throws SQLException {
                    gtao_phone_view bean = new gtao_phone_view();
                    bean.setId(rs.getInt("id"));
                    bean.setLongNum(rs.getString("longNum"));
                    bean.setShortNum(rs.getString("shortNum"));
                    bean.setIpAdd(rs.getString("ipAdd"));
                    bean.setUserId(rs.getString("userId"));
                    bean.setHandle(rs.getString("isHandle"));
                    bean.setUpTime(rs.getDate("upTime"));
                    bean.setType(rs.getString("type"));
                    bean.setMobile(rs.getString("mobile"));
                    bean.setIp(rs.getString("ip"));
                    bean.setVlan(rs.getString("vlan"));
                    bean.setInstaller(rs.getString("Installer"));
                    bean.setInstallTime(rs.getString("InstallTime"));
                    bean.setTbl(rs.getString("tbl"));
                    bean.setGate(rs.getString("gate"));
                    li.add(bean);
                }
            });
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
        final List li = new ArrayList();
        String sql = "select * from gtao_Phone_bc_sale where longNum='"+phoneNum+"'";
        jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                gtao_Phone_bc_sale bean = new gtao_Phone_bc_sale();
                bean.setId(resultSet.getInt("id"));
                bean.setLongNum(resultSet.getString("longNum"));
                bean.setShortNum(resultSet.getString("shortNum"));
                bean.setIpAdd(resultSet.getString("ipadd"));
                bean.setUserId(resultSet.getString("userid"));
                bean.setIsHandle(resultSet.getString("ishandle"));
                bean.setUpTime(resultSet.getDate("uptime"));
                bean.setType(resultSet.getString("type"));
                bean.setMobile(resultSet.getString("mobile"));
                bean.setIp(resultSet.getString("ip"));
                bean.setMoney(resultSet.getString("money"));
                bean.setInstaller(resultSet.getString("Installer"));
                bean.setVlan(resultSet.getString("vlan"));
                bean.setInstallTime(resultSet.getString("InstallTime"));
                bean.setIsPay(resultSet.getString("ispay"));
                bean.setOrdId(resultSet.getString("ordid"));
                bean.setGate(resultSet.getString("gate"));
                bean.setTbl(resultSet.getString("tbl"));
                li.add(bean);
            }
        });
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
    
    public boolean editFreeNumInfo(final gtao_phone_view view, String tbl, String pnum) {
        String sql =
        "update "+tbl+" set longNum='"+view.getLongNum()+"',userId='"+view.getUserId()+
        "',shortNum='"+view.getShortNum()+"',mobile='"+view.getMobile()+"',ipAdd='"+view.getIpAdd()+
        "',vlan='"+view.getVlan()+"',gate='"+view.getGate()+"' where longNum='"+pnum+"'";
        int uNum = jdbcTemplateBmu.update(sql);
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 号码管理模块：修改付费号码数据
     * @param sale
     * @param pnum
     * @return boolean
     */
    @Override
    
    public boolean editSaleNumInfo(gtao_Phone_bc_sale sale, String pnum) {
        String sql =
        "update gtao_Phone_bc_sale set longNum='"+sale.getLongNum()+"',userId='"+sale.getUserId()+
        "',shortNum='"+sale.getShortNum()+"',mobile='"+sale.getMobile()+"',ipAdd='"+sale.getIpAdd()+
        "',vlan='"+sale.getVlan()+"',money='"+sale.getMoney()+"',gate='"+sale.getGate()+"' where longNum='"+pnum+"'";
        int uNum = jdbcTemplateBmu.update(sql);
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 号码管理模块：删除号码功能
     * @param phoneNum
     * @param tbl
     * @return boolean
     */
    @Override
    
    public boolean delFreeNumInfo(String phoneNum, String tbl) {
        String sql = "delete from "+tbl+" where longNum='"+phoneNum+"'";
        int uNum = jdbcTemplateBmu.update(sql);
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 号码管理模块：删除付费号码功能
     * @param phoneNum
     * @return
     */
    @Override
    
    public boolean delSaleNumInfo(String phoneNum) {
        String sql = "delete from gtao_Phone_bc_sale where longNum='"+phoneNum+"'";
        int uNum = jdbcTemplateBmu.update(sql);
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 将Excel一行的数据导入数据库
     * @param sale
     * @param view
     * @param tbl
     * @return 导入数量 int
     */
    @Override
    
    public int excel2db(final gtao_Phone_bc_sale sale, final gtao_phone_view view, String tbl) {
        int insNum = 0;
        String sql = "";
        if(!(this.checkRepeat(sale,view,tbl))){
            return 0;
        }
            if(tbl.equals("gtao_Phone_bc_sale")||tbl=="gtao_Phone_bc_sale"){
                sql = "insert into "+tbl+"(longNum,shortNum,ip,money,vlan,gate) values (?,?,?,?,?,?)";
                insNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement pstmt) throws SQLException {
                        pstmt.setString(1,sale.getLongNum());
                        pstmt.setString(2,sale.getShortNum());
                        pstmt.setString(3,sale.getIp());
                        pstmt.setString(4,sale.getMoney());
                        pstmt.setString(5,sale.getVlan());
                        pstmt.setString(6,sale.getGate());
                    }
                });
            }
            else {
                sql = "insert into "+tbl+"(longNum,shortNum,ip,vlan,gate) values (?,?,?,?,?)";
                insNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement pstmt) throws SQLException {
                        pstmt.setString(1,view.getLongNum());
                        pstmt.setString(2,view.getShortNum());
                        pstmt.setString(3,view.getIp());
                        pstmt.setString(4,view.getVlan());
                        pstmt.setString(5,view.getGate());
                    }
                });
            }
        return insNum;
    }

    /**
     * select * from gtao_phone_profile
     * @return List
     */
    @Override
    
    public List getAllFeeProfile() {
        final List li = new ArrayList();
        String sql = "select * from gtao_phone_profile";
        jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_phone_profile bean = new gtao_phone_profile();
                bean.setID(rs.getInt("ID"));
                bean.setFEEPROFILE(rs.getString("FEEPROFILE"));
                li.add(bean);
            }
        });
        return li;
    }

    /**
     * 获取计费策略数据
     * @param tbl
     * @return List
     */
    @Override
    
    public List getAllCelue(String tbl) {
        final List li = new ArrayList();
        String sql = "select * from "+tbl;
        jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_phone_celue bean = new gtao_phone_celue();
                bean.setID(rs.getInt("ID"));
                bean.setSPROFILE(rs.getString("SPROFILE"));
                bean.setSPREFIX(rs.getString("SPREFIX"));
                bean.setRATESTIME1(rs.getString("RATESTIME1"));
                bean.setRATES1(rs.getString("RATES1"));
                bean.setRATESTIME2(rs.getString("RATESTIME2"));
                bean.setRATES2(rs.getString("RATES2"));
                bean.setRATESTIME3(rs.getString("RATESTIME3"));
                bean.setRATES3(rs.getString("RATES3"));
                bean.setOTHERFEE(rs.getString("OTHERFEE"));
                bean.setSPECIALTIMEBEGIN1(rs.getString("SPECIALTIMEBEGIN1"));
                bean.setSPECIALTIMEEND1(rs.getString("SPECIALTIMEEND1"));
                bean.setSPECIALTIMEBEGIN2(rs.getString("SPECIALTIMEBEGIN2"));
                bean.setSPECIALTIMEEND2(rs.getString("SPECIALTIMEEND2"));
                bean.setSPECIALTIMEBEGIN3(rs.getString("SPECIALTIMEBEGIN3"));
                bean.setSPECIALTIMEEND3(rs.getString("SPECIALTIMEEND3"));
                bean.setSPECIALTIMEFEE1(rs.getString("SPECIALTIMEFEE1"));
                bean.setSPECIALTIMEFEE2(rs.getString("SPECIALTIMEFEE2"));
                bean.setSPECIALTIMEFEE3(rs.getString("SPECIALTIMEFEE3"));
                li.add(bean);
            }
        });
        return li;
    }

    /**
     * 根据组策略Id获取对应策略表名
     * @param groupId
     * @return String
     */
    @Override
    
    public String getTblNameByGroupId(final String groupId) {
        final List li = new ArrayList();
        String sql = "select * from gtao_phone_group where userGroup=?";
        jdbcTemplateBmu.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,groupId);
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_phone_group bean = new gtao_phone_group();
                bean.setId(rs.getInt("id"));
                bean.setUserGroup(rs.getString("userGroup"));
                bean.setCelueTbl(rs.getString("celueTbl"));
                bean.setGroupDetail(rs.getString("groupDetail"));
                li.add(bean);
            }
        });
        Iterator it = li.iterator();
        gtao_phone_group group = new gtao_phone_group();
        String tblName = "";
        while (it.hasNext()){
            group = (gtao_phone_group)it.next();
            tblName = group.getCelueTbl();
        }
        return tblName;
    }
     ///////////////////////////////////////////TODO
    @Override
    
    public String getTblNameByGroupName(final String groupName) {
        String tblName = "";
        final List li = new ArrayList();
        String sql = "select * from gtao_phone_group where groupDetail=?";
        jdbcTemplateBmu.query(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,groupName);
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_phone_group bean = new gtao_phone_group();
                bean.setId(rs.getInt("id"));
                bean.setUserGroup(rs.getString("userGroup"));
                bean.setCelueTbl(rs.getString("celueTbl"));
                bean.setGroupDetail(rs.getString("groupDetail"));
                li.add(bean);
            }
        });
        Iterator it = li.iterator();
        while (it.hasNext()){
            gtao_phone_group group = (gtao_phone_group)it.next();
            tblName = group.getCelueTbl();
        }
        return tblName;
    }

    /**
     * 按呼叫前缀查找计费策略信息
     * @param prefix
     * @return list
     */
    @Override
    
    public List getCelueByPrefix(final String prefix,String userGroup) {
        final List li = new ArrayList();
        String tbl = this.getTblNameByGroupId(userGroup);
        String sql = "SELECT * FROM "+tbl+" WHERE SPREFIX=?";
        jdbcTemplateBmu.query(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,prefix);
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_phone_celue bean = new gtao_phone_celue();
                bean.setID(rs.getInt("ID"));
                bean.setSPROFILE(rs.getString("SPROFILE"));
                bean.setSPREFIX(rs.getString("SPREFIX"));
                bean.setRATESTIME1(rs.getString("RATESTIME1"));
                bean.setRATES1(rs.getString("RATES1"));
                bean.setRATESTIME2(rs.getString("RATESTIME2"));
                bean.setRATES2(rs.getString("RATES2"));
                bean.setRATESTIME3(rs.getString("RATESTIME3"));
                bean.setRATES3(rs.getString("RATES3"));
                bean.setOTHERFEE(rs.getString("OTHERFEE"));
                bean.setSPECIALTIMEBEGIN1(rs.getString("SPECIALTIMEBEGIN1"));
                bean.setSPECIALTIMEEND1(rs.getString("SPECIALTIMEEND1"));
                bean.setSPECIALTIMEBEGIN2(rs.getString("SPECIALTIMEBEGIN2"));
                bean.setSPECIALTIMEEND2(rs.getString("SPECIALTIMEEND2"));
                bean.setSPECIALTIMEBEGIN3(rs.getString("SPECIALTIMEBEGIN3"));
                bean.setSPECIALTIMEEND3(rs.getString("SPECIALTIMEEND3"));
                bean.setSPECIALTIMEFEE1(rs.getString("SPECIALTIMEFEE1"));
                bean.setSPECIALTIMEFEE2(rs.getString("SPECIALTIMEFEE2"));
                bean.setSPECIALTIMEFEE3(rs.getString("SPECIALTIMEFEE3"));
                li.add(bean);
            }
        });
        return li;
    }

    /**
     * 修改计费策略方法
     * @param celue
     * @return boolean
     */
    @Override
    
    public boolean updateStrategyInfo(final gtao_phone_celue celue,String group) {
        boolean flag = false;
        String tbl = this.getTblNameByGroupId(group);
            String sql =
            "UPDATE "+tbl+" SET SPROFILE=?,SPREFIX=?,RATESTIME1=?,RATES1=?," +
            "RATESTIME2=?,RATES2=?,RATESTIME3=?,RATES3=?,OTHERFEE=?," +
            "SPECIALTIMEBEGIN1=?,SPECIALTIMEEND1=?,SPECIALTIMEBEGIN2=?," +
            "SPECIALTIMEEND2=?,SPECIALTIMEBEGIN3=?,SPECIALTIMEEND3=?," +
            "SPECIALTIMEFEE1=?,SPECIALTIMEFEE2=?,SPECIALTIMEFEE3=? WHERE ID=?";
            int uNum = jdbcTemplateBmu.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement pstmt) throws SQLException {
                    pstmt.setString(1,celue.getSPROFILE());
                    pstmt.setString(2,celue.getSPREFIX());
                    pstmt.setString(3,celue.getRATESTIME1());
                    pstmt.setString(4,celue.getRATES1());
                    pstmt.setString(5,celue.getRATESTIME2());
                    pstmt.setString(6,celue.getRATES2());
                    pstmt.setString(7,celue.getRATESTIME3());
                    pstmt.setString(8,celue.getRATES3());
                    pstmt.setString(9,celue.getOTHERFEE());
                    pstmt.setString(10,celue.getSPECIALTIMEBEGIN1());
                    pstmt.setString(11,celue.getSPECIALTIMEEND1());
                    pstmt.setString(12,celue.getSPECIALTIMEBEGIN2());
                    pstmt.setString(13,celue.getSPECIALTIMEEND2());
                    pstmt.setString(14,celue.getSPECIALTIMEBEGIN3());
                    pstmt.setString(15,celue.getSPECIALTIMEEND3());
                    pstmt.setString(16,celue.getSPECIALTIMEFEE1());
                    pstmt.setString(17,celue.getSPECIALTIMEFEE2());
                    pstmt.setString(18,celue.getSPECIALTIMEFEE3());
                    pstmt.setInt(19, celue.getID());
                }
            });
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 添加计费策略方法
     * @return boolean
     */
    @Override
    
    public boolean saveStrategyInfo(final gtao_phone_celue celue,String group) {
        boolean flag = false;
        String tbl = this.getTblNameByGroupId(group);
            String sql =
            "INSERT INTO "+tbl+"(SPROFILE,SPREFIX,RATESTIME1,RATES1,RATESTIME2,RATES2,RATESTIME3,RATES3," +
            "OTHERFEE,SPECIALTIMEBEGIN1,SPECIALTIMEEND1,SPECIALTIMEBEGIN2,SPECIALTIMEEND2," +
            "SPECIALTIMEBEGIN3,SPECIALTIMEEND3,SPECIALTIMEFEE1,SPECIALTIMEFEE2,SPECIALTIMEFEE3)VALUES " +
            "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            int uNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement pstmt) throws SQLException {
                    pstmt.setString(1,celue.getSPROFILE());
                    pstmt.setString(2,celue.getSPREFIX());
                    pstmt.setString(3,celue.getRATESTIME1());
                    pstmt.setString(4,celue.getRATES1());
                    pstmt.setString(5,celue.getRATESTIME2());
                    pstmt.setString(6,celue.getRATES2());
                    pstmt.setString(7,celue.getRATESTIME3());
                    pstmt.setString(8,celue.getRATES3());
                    pstmt.setString(9,celue.getOTHERFEE());
                    pstmt.setString(10,celue.getSPECIALTIMEBEGIN1());
                    pstmt.setString(11,celue.getSPECIALTIMEEND1());
                    pstmt.setString(12,celue.getSPECIALTIMEBEGIN2());
                    pstmt.setString(13,celue.getSPECIALTIMEEND2());
                    pstmt.setString(14,celue.getSPECIALTIMEBEGIN3());
                    pstmt.setString(15,celue.getSPECIALTIMEEND3());
                    pstmt.setString(16,celue.getSPECIALTIMEFEE1());
                    pstmt.setString(17,celue.getSPECIALTIMEFEE2());
                    pstmt.setString(18, celue.getSPECIALTIMEFEE3());
                }
            });
            if(uNum>0){
                return true;
            }
        return false;
    }

    /**
     * 获取所有组策略方法
     * @return List
     */
    @Override
    
    public List getAllGroup() {
        final List li = new ArrayList();
        String hql = "select * from gtao_phone_group";
        jdbcTemplateBmu.query(hql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_phone_group bean = new gtao_phone_group();
                bean.setId(rs.getInt("id"));
                bean.setUserGroup(rs.getString("userGroup"));
                bean.setCelueTbl(rs.getString("celueTbl"));
                bean.setGroupDetail(rs.getString("groupDetail"));
                li.add(bean);
            }
        });
        return li;
    }

    /**
     * 向用户组表中插入新数据
     * @param group
     * @return boolean
     */
    @Override
    
    public boolean newUserGroup(final gtao_phone_group group) {
        String sql = "INSERT INTO gtao_phone_group(userGroup,celueTbl,groupDetail)VALUES(?,?,?)";
        int uNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,group.getUserGroup());
                pstmt.setString(2,group.getCelueTbl());
                pstmt.setString(3,group.getGroupDetail());
            }
        });
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 建立新组策略表
     * @param tblName
     * @return boolean
     */
    @Override
    
    public boolean createCelueTbl(String tblName) {
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
        int uNum = jdbcTemplateBmu.update(sql);
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 删除用户组策略
     * @param userGroup
     * @return boolean
     */
    @Override
    
    public boolean deleteUserGroup(final String userGroup) {
        boolean flag = false;
        int uNum = 0;
        String dropTbl = this.getTblNameByGroupId(userGroup);
        String sql = "DELETE FROM gtao_phone_group WHERE userGroup=?";
        if(this.deleteGroupTable(dropTbl)){
            uNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement pstmt) throws SQLException {
                    pstmt.setString(1,userGroup);
                }
            });
        }

        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 删除详细计费策略
     * @param prefix
     * @param group
     * @return boolean
     */
    @Override
    
    public boolean deleteStrategyInfo(final String prefix, String group) {
        String tbl = this.getTblNameByGroupId(group);
        String sql = "DELETE FROM "+tbl+" WHERE SPREFIX=?";
        int uNum = jdbcTemplateBmu.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, prefix);
            }
        });
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 删除一条用户记录
     * @param id
     * @param longNum
     * @return boolean
     */
    @Override
    
    public boolean delInfoFromUser(final int id, final String longNum) {
        boolean flag = false;
        String sql = "DELETE FROM gtao_Phone_User WHERE id=? AND longNum=?";
        int uNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1,id);
                pstmt.setString(2,longNum);
            }
        });
        if(uNum>0){
            return true;
        }
        return false;
    }

    @Override
    
    public List getCallHistoryByMonth(String month,String today) {
        final List li = new ArrayList();
        String sql =
        "SELECT * FROM tbl_billInfo"+month+" WHERE StartTime BETWEEN '"+today+" 00:00:00.000' AND " +
        "'"+today+" 23:59:59.999'";
        jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                tbl_billInfo bean = new tbl_billInfo();
                bean.setID(rs.getInt("ID"));
                bean.setSid(rs.getInt("Sid"));
                bean.setUcFree(rs.getShort("ucFree"));
                bean.setUcChargeParty(rs.getShort("ucChargeParty"));
                bean.setStartTime(rs.getDate("StartTime"));
                bean.setEndTime(rs.getDate("EndTime"));
                bean.setDwConversationTime(rs.getInt("dwConversationTime"));
                bean.setUcCallerAddressNature(rs.getShort("ucCallerAddressNature"));
                bean.setUcCallerNumber(rs.getString("ucCallerNumber"));
                bean.setUcCallerDepartment(rs.getString("ucCallerDepartment"));
                bean.setUcCallerName(rs.getString("ucCallerName"));
                bean.setDwCallerID(rs.getString("dwCallerId"));
                bean.setUcCalledAddressNature(rs.getShort("ucCalledAddressNature"));
                bean.setUcCalledNumber(rs.getString("ucCalledNumber"));
                bean.setUcOrgCalledNumber(rs.getString("ucOrgCalledNumber"));
                bean.setwTrunkGroupIn(rs.getInt("wTrunkGroupIn"));
                bean.setDwTrunkCircuitIn(rs.getInt("dwTrunkCircuitIn"));
                bean.setwTrunkGroupOut(rs.getInt("wTrunkGroupOut"));
                bean.setDwTrunkCircuitOut(rs.getInt("dwTrunkCircuitOut"));
                bean.setUcCallerProtocol(rs.getShort("ucCallerProtocol"));
                bean.setUcCalledProtocol(rs.getShort("ucCalledProtocol"));
                bean.setUcCallerSignalling(rs.getShort("ucCallerSignalling"));
                bean.setUcCalledSignalling(rs.getShort("ucCalledSignalling"));
                bean.setwCallerMGId(rs.getInt("wCallerMGId"));
                bean.setwCalledMGId(rs.getInt("wCalledMGId"));
                bean.setUcCallerCategory(rs.getShort("ucCallerCategory"));
                bean.setUcCallType(rs.getShort("ucCallType"));
                bean.setUcCallAttribute(rs.getShort("ucCallAttribute"));
                bean.setDwCallerRTPIPAddress(rs.getInt("dwCallerRTPIPAddress"));
                bean.setDwCalledRTPIPAddress(rs.getInt("dwCalledRTPIPAddress"));
                bean.setfCallFee(rs.getDouble("fCallFee"));
                bean.setDwCallID(rs.getInt("dwCallID"));
                bean.setUcOrientation(rs.getInt("ucOrientation"));
                bean.setUcCallFlag(rs.getShort("ucCallFlag"));
                bean.setUcBillAttribute(rs.getShort("ucBillAttribute"));
                bean.setUcUnicallType(rs.getShort("ucUnicallType"));
                bean.setUcUnicallRealCallerNumber(rs.getString("ucUnicallRealCallerNumber"));
                bean.setUcUnicallRealCalledNumber(rs.getString("ucUnicallRealCalledNumber"));
                bean.setSubPbxID(rs.getInt("subPbxID"));
                bean.setNeIP(rs.getString("NeIP"));
                bean.setDomainID(rs.getInt("domainID"));
                bean.setRestrictFlag(rs.getShort("restrictFlag"));
                bean.setRestrictId(rs.getString("restrictID"));
                bean.setAreaCode(rs.getString("areaCode"));
                li.add(bean);
            }
        });
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
        final List li = new ArrayList();
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
        jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                tbl_billInfo bean = new tbl_billInfo();
                bean.setID(rs.getInt("ID"));
                bean.setSid(rs.getInt("Sid"));
                bean.setUcFree(rs.getShort("ucFree"));
                bean.setUcChargeParty(rs.getShort("ucChargeParty"));
                bean.setStartTime(rs.getDate("StartTime"));
                bean.setEndTime(rs.getDate("EndTime"));
                bean.setDwConversationTime(rs.getInt("dwConversationTime"));
                bean.setUcCallerAddressNature(rs.getShort("ucCallerAddressNature"));
                bean.setUcCallerNumber(rs.getString("ucCallerNumber"));
                bean.setUcCallerDepartment(rs.getString("ucCallerDepartment"));
                bean.setUcCallerName(rs.getString("ucCallerName"));
                bean.setDwCallerID(rs.getString("dwCallerId"));
                bean.setUcCalledAddressNature(rs.getShort("ucCalledAddressNature"));
                bean.setUcCalledNumber(rs.getString("ucCalledNumber"));
                bean.setUcOrgCalledNumber(rs.getString("ucOrgCalledNumber"));
                bean.setwTrunkGroupIn(rs.getInt("wTrunkGroupIn"));
                bean.setDwTrunkCircuitIn(rs.getInt("dwTrunkCircuitIn"));
                bean.setwTrunkGroupOut(rs.getInt("wTrunkGroupOut"));
                bean.setDwTrunkCircuitOut(rs.getInt("dwTrunkCircuitOut"));
                bean.setUcCallerProtocol(rs.getShort("ucCallerProtocol"));
                bean.setUcCalledProtocol(rs.getShort("ucCalledProtocol"));
                bean.setUcCallerSignalling(rs.getShort("ucCallerSignalling"));
                bean.setUcCalledSignalling(rs.getShort("ucCalledSignalling"));
                bean.setwCallerMGId(rs.getInt("wCallerMGId"));
                bean.setwCalledMGId(rs.getInt("wCalledMGId"));
                bean.setUcCallerCategory(rs.getShort("ucCallerCategory"));
                bean.setUcCallType(rs.getShort("ucCallType"));
                bean.setUcCallAttribute(rs.getShort("ucCallAttribute"));
                bean.setDwCallerRTPIPAddress(rs.getInt("dwCallerRTPIPAddress"));
                bean.setDwCalledRTPIPAddress(rs.getInt("dwCalledRTPIPAddress"));
                bean.setfCallFee(rs.getDouble("fCallFee"));
                bean.setDwCallID(rs.getInt("dwCallID"));
                bean.setUcOrientation(rs.getInt("ucOrientation"));
                bean.setUcCallFlag(rs.getShort("ucCallFlag"));
                bean.setUcBillAttribute(rs.getShort("ucBillAttribute"));
                bean.setUcUnicallType(rs.getShort("ucUnicallType"));
                bean.setUcUnicallRealCallerNumber(rs.getString("ucUnicallRealCallerNumber"));
                bean.setUcUnicallRealCalledNumber(rs.getString("ucUnicallRealCalledNumber"));
                bean.setSubPbxID(rs.getInt("subPbxID"));
                bean.setNeIP(rs.getString("NeIP"));
                bean.setDomainID(rs.getInt("domainID"));
                bean.setRestrictFlag(rs.getShort("restrictFlag"));
                bean.setRestrictId(rs.getString("restrictID"));
                bean.setAreaCode(rs.getString("areaCode"));
                li.add(bean);
            }
        });
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
        final List li = new ArrayList();
        String sql = "";
        //如果电话号码为空
        if(longNum==null||longNum.equals("")){
            sql = "SELECT * FROM gtao_phone_bill WHERE StartTime BETWEEN '"+fromDate+" 00:00:00.000' AND '"+toDate+" 23:59:59.999'";
        }
        //电话不为空
        else {
            sql = "SELECT * FROM gtao_phone_bill WHERE (StartTime BETWEEN '"+fromDate+" 00:00:00.000' AND '"+toDate+" 23:59:59.999') AND (ucCallerNumber='"+longNum+"' OR ucCalledNumber='"+longNum+"')";
        }
        jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                tbl_billInfo bean = new tbl_billInfo();
                bean.setID(rs.getInt("ID"));
                bean.setSid(rs.getInt("Sid"));
                bean.setUcFree(rs.getShort("ucFree"));
                bean.setUcChargeParty(rs.getShort("ucChargeParty"));
                bean.setStartTime(rs.getDate("StartTime"));
                bean.setEndTime(rs.getDate("EndTime"));
                bean.setDwConversationTime(rs.getInt("dwConversationTime"));
                bean.setUcCallerAddressNature(rs.getShort("ucCallerAddressNature"));
                bean.setUcCallerNumber(rs.getString("ucCallerNumber"));
                bean.setUcCallerDepartment(rs.getString("ucCallerDepartment"));
                bean.setUcCallerName(rs.getString("ucCallerName"));
                bean.setDwCallerID(rs.getString("dwCallerId"));
                bean.setUcCalledAddressNature(rs.getShort("ucCalledAddressNature"));
                bean.setUcCalledNumber(rs.getString("ucCalledNumber"));
                bean.setUcOrgCalledNumber(rs.getString("ucOrgCalledNumber"));
                bean.setwTrunkGroupIn(rs.getInt("wTrunkGroupIn"));
                bean.setDwTrunkCircuitIn(rs.getInt("dwTrunkCircuitIn"));
                bean.setwTrunkGroupOut(rs.getInt("wTrunkGroupOut"));
                bean.setDwTrunkCircuitOut(rs.getInt("dwTrunkCircuitOut"));
                bean.setUcCallerProtocol(rs.getShort("ucCallerProtocol"));
                bean.setUcCalledProtocol(rs.getShort("ucCalledProtocol"));
                bean.setUcCallerSignalling(rs.getShort("ucCallerSignalling"));
                bean.setUcCalledSignalling(rs.getShort("ucCalledSignalling"));
                bean.setwCallerMGId(rs.getInt("wCallerMGId"));
                bean.setwCalledMGId(rs.getInt("wCalledMGId"));
                bean.setUcCallerCategory(rs.getShort("ucCallerCategory"));
                bean.setUcCallType(rs.getShort("ucCallType"));
                bean.setUcCallAttribute(rs.getShort("ucCallAttribute"));
                bean.setDwCallerRTPIPAddress(rs.getInt("dwCallerRTPIPAddress"));
                bean.setDwCalledRTPIPAddress(rs.getInt("dwCalledRTPIPAddress"));
                bean.setfCallFee(rs.getDouble("fCallFee"));
                bean.setDwCallID(rs.getInt("dwCallID"));
                bean.setUcOrientation(rs.getInt("ucOrientation"));
                bean.setUcCallFlag(rs.getShort("ucCallFlag"));
                bean.setUcBillAttribute(rs.getShort("ucBillAttribute"));
                bean.setUcUnicallType(rs.getShort("ucUnicallType"));
                bean.setUcUnicallRealCallerNumber(rs.getString("ucUnicallRealCallerNumber"));
                bean.setUcUnicallRealCalledNumber(rs.getString("ucUnicallRealCalledNumber"));
                bean.setSubPbxID(rs.getInt("subPbxID"));
                bean.setNeIP(rs.getString("NeIP"));
                bean.setDomainID(rs.getInt("domainID"));
                bean.setRestrictFlag(rs.getShort("restrictFlag"));
                bean.setRestrictId(rs.getString("restrictID"));
                bean.setAreaCode(rs.getString("areaCode"));
                li.add(bean);
            }
        });
        return li;
    }

    /**
     * 按ID获取通话详细信息
     * @param id
     * @return List
     */
    @Override
    
    public List getCallHistoryById(String id,String startTime) {
        final List li = new ArrayList();
        String sql = "SELECT * FROM tbl_billInfo"+startTime+" WHERE ID="+id;
        jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                tbl_billInfo bean = new tbl_billInfo();
                bean.setID(rs.getInt("ID"));
                bean.setSid(rs.getInt("Sid"));
                bean.setUcFree(rs.getShort("ucFree"));
                bean.setUcChargeParty(rs.getShort("ucChargeParty"));
                bean.setStartTime(rs.getDate("StartTime"));
                bean.setEndTime(rs.getDate("EndTime"));
                bean.setDwConversationTime(rs.getInt("dwConversationTime"));
                bean.setUcCallerAddressNature(rs.getShort("ucCallerAddressNature"));
                bean.setUcCallerNumber(rs.getString("ucCallerNumber"));
                bean.setUcCallerDepartment(rs.getString("ucCallerDepartment"));
                bean.setUcCallerName(rs.getString("ucCallerName"));
                bean.setDwCallerID(rs.getString("dwCallerId"));
                bean.setUcCalledAddressNature(rs.getShort("ucCalledAddressNature"));
                bean.setUcCalledNumber(rs.getString("ucCalledNumber"));
                bean.setUcOrgCalledNumber(rs.getString("ucOrgCalledNumber"));
                bean.setwTrunkGroupIn(rs.getInt("wTrunkGroupIn"));
                bean.setDwTrunkCircuitIn(rs.getInt("dwTrunkCircuitIn"));
                bean.setwTrunkGroupOut(rs.getInt("wTrunkGroupOut"));
                bean.setDwTrunkCircuitOut(rs.getInt("dwTrunkCircuitOut"));
                bean.setUcCallerProtocol(rs.getShort("ucCallerProtocol"));
                bean.setUcCalledProtocol(rs.getShort("ucCalledProtocol"));
                bean.setUcCallerSignalling(rs.getShort("ucCallerSignalling"));
                bean.setUcCalledSignalling(rs.getShort("ucCalledSignalling"));
                bean.setwCallerMGId(rs.getInt("wCallerMGId"));
                bean.setwCalledMGId(rs.getInt("wCalledMGId"));
                bean.setUcCallerCategory(rs.getShort("ucCallerCategory"));
                bean.setUcCallType(rs.getShort("ucCallType"));
                bean.setUcCallAttribute(rs.getShort("ucCallAttribute"));
                bean.setDwCallerRTPIPAddress(rs.getInt("dwCallerRTPIPAddress"));
                bean.setDwCalledRTPIPAddress(rs.getInt("dwCalledRTPIPAddress"));
                bean.setfCallFee(rs.getDouble("fCallFee"));
                bean.setDwCallID(rs.getInt("dwCallID"));
                bean.setUcOrientation(rs.getInt("ucOrientation"));
                bean.setUcCallFlag(rs.getShort("ucCallFlag"));
                bean.setUcBillAttribute(rs.getShort("ucBillAttribute"));
                bean.setUcUnicallType(rs.getShort("ucUnicallType"));
                bean.setUcUnicallRealCallerNumber(rs.getString("ucUnicallRealCallerNumber"));
                bean.setUcUnicallRealCalledNumber(rs.getString("ucUnicallRealCalledNumber"));
                bean.setSubPbxID(rs.getInt("subPbxID"));
                bean.setNeIP(rs.getString("NeIP"));
                bean.setDomainID(rs.getInt("domainID"));
                bean.setRestrictFlag(rs.getShort("restrictFlag"));
                bean.setRestrictId(rs.getString("restrictID"));
                bean.setAreaCode(rs.getString("areaCode"));
                li.add(bean);
            }
        });
        return li;
    }

    /**
     * 从Radius中获取用户信息
     * @param userid
     * @return List
     */
    @Override
    
    public List getUserInfoFromRadius(final String userid) {
        final List li = new ArrayList();
        String sql = "SELECT * FROM TBL_USERSINFO WHERE SUSERNAME=?";
        jdbcTemplateRadius.query(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,userid);
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                TBL_USERSINFO bean = new TBL_USERSINFO();
                bean.setSUSERNAME(rs.getString("SUSERNAME"));
                bean.setSREALNAME(rs.getString("SREALNAME"));
                bean.setISEX(rs.getBigDecimal("ISEX"));
                bean.setSBIRTHDAY(rs.getString("SBIRTHDAY"));
                bean.setICERTTYPE(rs.getBigDecimal("ICERTTYPE"));
                bean.setSCERTNO(rs.getString("SCERTNO"));
                bean.setSEMAIL(rs.getString("SEMAIL"));
                bean.setSADDRESS(rs.getString("SADDRESS"));
                bean.setSPOSTCODE(rs.getString("SPOSTCODE"));
                bean.setSTELE(rs.getString("STELE"));
                bean.setSFAX(rs.getString("SFAX"));
                bean.setSCOMPANY(rs.getString("SCOMPANY"));
                bean.setIJOB(rs.getBigDecimal("IJOB"));
                li.add(bean);
            }
        });
        return li;
    }

    @Override
    
    public boolean initFreeNum(String phoneNum, String tbl) {
        boolean flag = false;
        String sql = "";
        Query query = null;
        if(tbl.contains("sale")){
            sql = "UPDATE "+tbl+" SET ipAdd='',userId='',isPay='',isHandle='',type='',mobile='',Installer='',InstallTime=''" +
                    "WHERE longNum='"+phoneNum+"'";
        }
        else {
            sql = "UPDATE "+tbl+" SET ipAdd='',userId='',isHandle='',type='',mobile='',Installer='',InstallTime=''" +
                    "WHERE longNum='"+phoneNum+"'";
        }
        int uNum = jdbcTemplateBmu.update(sql);
        if(uNum>0){
            return true;
        }
        return false;
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
    
    public boolean createAccount(final gtao_phone_view view, final gtao_Phone_bc_sale sale, String tbl, final String longNum) {
        int uNum = 0;
        String sql = "";
        if(tbl.contains("sale")){
            sql =
            "UPDATE "+tbl+" SET userId=?,type=?,mobile=?,Installer=?,InstallTime=?,isPay=? " +
            "WHERE longNum=?";
            uNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement pstmt) throws SQLException {
                    pstmt.setString(1,sale.getUserId());
                    pstmt.setString(2, sale.getType());
                    pstmt.setString(3, sale.getMobile());
                    pstmt.setString(4, sale.getInstaller());
                    pstmt.setString(5, sale.getInstallTime());
                    pstmt.setString(6, sale.getIsPay());
                    pstmt.setString(7, longNum);
                }
            });
        }
        else{
            sql =
            "UPDATE "+tbl+" SET userId=?,type=?,mobile=?,Installer=?,InstallTime=? WHERE longNum=?";
            uNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement pstmt) throws SQLException {
                    pstmt.setString(1,view.getUserId());
                    pstmt.setString(2, view.getType());
                    pstmt.setString(3, view.getMobile());
                    pstmt.setString(4, view.getInstaller());
                    pstmt.setString(5, view.getInstallTime());
                    pstmt.setString(6, longNum);
                }
            });
        }
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 从128数据库获取用户住址
     * @param userid
     * @return String
     */
    @Override
    
    public String getUserAddress(final String userid) {
        String address = "";
        final List li = new ArrayList();
        String sql = "SELECT SFEEPHONE FROM TBL_USERS WHERE SUSERNAME=?";
        jdbcTemplateRadius.query(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,userid);
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                String fp = rs.getString("SFEEPHONE");
                li.add(fp);
            }
        });
        Iterator it = li.iterator();
        while (it.hasNext()){
            address = it.next().toString();
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
    
    public List getUserCalledDetail(final String longNum, final String shortNum, final String month, String startTime, String endTime) {
        final List li = new ArrayList();
        String sql = "";
        //查询全部
        if(month==null&&startTime==null&&endTime==null){
            sql =
            "SELECT * FROM gtao_phone_bill WHERE " +
            "(ucCallerNumber='"+longNum+"' OR ucCalledNumber='"+longNum+"' OR ucCallerNumber='"+shortNum+"' OR ucCalledNumber='"+shortNum+"') " +
            "ORDER BY StartTime";
        }
        //查询某月
        else if (month!=null&&startTime==null&&endTime==null){
            sql = "SELECT * FROM tbl_billInfo"+month+" WHERE " +
            "(ucCallerNumber='"+longNum+"' OR ucCalledNumber='"+longNum+"' OR ucCallerNumber='"+shortNum+"' OR ucCalledNumber='"+shortNum+"') " +
            "ORDER BY StartTime";
        }
        //时间段查询
        else if(month==null&&startTime!=null&&endTime!=null){
            startTime = startTime + " 00:00:00.000";
            endTime = endTime + " 23:59:59.999";
            sql =
            "SELECT * FROM gtao_phone_bill WHERE " +
            "(ucCallerNumber='"+longNum+"' OR ucCalledNumber='"+longNum+"' OR ucCallerNumber='"+shortNum+"' OR ucCalledNumber='"+shortNum+"') " +
            "AND (StartTime BETWEEN '"+startTime+"' AND '"+endTime+"') ORDER BY StartTime";

        }
        jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                tbl_billInfo bean = new tbl_billInfo();
                bean.setID(rs.getInt("ID"));
                bean.setSid(rs.getInt("Sid"));
                bean.setUcFree(rs.getShort("ucFree"));
                bean.setUcChargeParty(rs.getShort("ucChargeParty"));
                bean.setStartTime(rs.getDate("StartTime"));
                bean.setEndTime(rs.getDate("EndTime"));
                bean.setDwConversationTime(rs.getInt("dwConversationTime"));
                bean.setUcCallerAddressNature(rs.getShort("ucCallerAddressNature"));
                bean.setUcCallerNumber(rs.getString("ucCallerNumber"));
                bean.setUcCallerDepartment(rs.getString("ucCallerDepartment"));
                bean.setUcCallerName(rs.getString("ucCallerName"));
                bean.setDwCallerID(rs.getString("dwCallerId"));
                bean.setUcCalledAddressNature(rs.getShort("ucCalledAddressNature"));
                bean.setUcCalledNumber(rs.getString("ucCalledNumber"));
                bean.setUcOrgCalledNumber(rs.getString("ucOrgCalledNumber"));
                bean.setwTrunkGroupIn(rs.getInt("wTrunkGroupIn"));
                bean.setDwTrunkCircuitIn(rs.getInt("dwTrunkCircuitIn"));
                bean.setwTrunkGroupOut(rs.getInt("wTrunkGroupOut"));
                bean.setDwTrunkCircuitOut(rs.getInt("dwTrunkCircuitOut"));
                bean.setUcCallerProtocol(rs.getShort("ucCallerProtocol"));
                bean.setUcCalledProtocol(rs.getShort("ucCalledProtocol"));
                bean.setUcCallerSignalling(rs.getShort("ucCallerSignalling"));
                bean.setUcCalledSignalling(rs.getShort("ucCalledSignalling"));
                bean.setwCallerMGId(rs.getInt("wCallerMGId"));
                bean.setwCalledMGId(rs.getInt("wCalledMGId"));
                bean.setUcCallerCategory(rs.getShort("ucCallerCategory"));
                bean.setUcCallType(rs.getShort("ucCallType"));
                bean.setUcCallAttribute(rs.getShort("ucCallAttribute"));
                bean.setDwCallerRTPIPAddress(rs.getInt("dwCallerRTPIPAddress"));
                bean.setDwCalledRTPIPAddress(rs.getInt("dwCalledRTPIPAddress"));
                bean.setfCallFee(rs.getDouble("fCallFee"));
                bean.setDwCallID(rs.getInt("dwCallID"));
                bean.setUcOrientation(rs.getInt("ucOrientation"));
                bean.setUcCallFlag(rs.getShort("ucCallFlag"));
                bean.setUcBillAttribute(rs.getShort("ucBillAttribute"));
                bean.setUcUnicallType(rs.getShort("ucUnicallType"));
                bean.setUcUnicallRealCallerNumber(rs.getString("ucUnicallRealCallerNumber"));
                bean.setUcUnicallRealCalledNumber(rs.getString("ucUnicallRealCalledNumber"));
                bean.setSubPbxID(rs.getInt("subPbxID"));
                bean.setNeIP(rs.getString("NeIP"));
                bean.setDomainID(rs.getInt("domainID"));
                bean.setRestrictFlag(rs.getShort("restrictFlag"));
                bean.setRestrictId(rs.getString("restrictID"));
                bean.setAreaCode(rs.getString("areaCode"));
                li.add(bean);
            }
        });
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
        String sql = "";
        //attribute==0，不分通话属性
        if(attribute==0){
            sql =
            "SELECT SUM(dwConversationTime) FROM tbl_billInfo"+month+" " +
            "WHERE ucCallerNumber=? OR ucCallerNumber=?";
            Object[] para = {longNum,shortNum};
            convTime = jdbcTemplateBmu.queryForInt(sql,para)+"";
        }
        return convTime;
    }

    @Override
    public List getCelueBean(String groupTbl, final short CallAttribute) {
        final List li = new ArrayList();
        String sql = "SELECT * FROM "+groupTbl+" WHERE SPREFIX=?";
        jdbcTemplateBmu.query(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setShort(1, CallAttribute);
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_phone_celue bean = new gtao_phone_celue();
                bean.setID(rs.getInt("ID"));
                bean.setSPROFILE(rs.getString("SPROFILE"));
                bean.setSPREFIX(rs.getString("SPREFIX"));
                bean.setRATESTIME1(rs.getString("RATESTIME1"));
                bean.setRATES1(rs.getString("RATES1"));
                bean.setRATESTIME2(rs.getString("RATESTIME2"));
                bean.setRATES2(rs.getString("RATES2"));
                bean.setRATESTIME3(rs.getString("RATESTIME3"));
                bean.setRATES3(rs.getString("RATES3"));
                bean.setOTHERFEE(rs.getString("OTHERFEE"));
                bean.setSPECIALTIMEBEGIN1(rs.getString("SPECIALTIMEBEGIN1"));
                bean.setSPECIALTIMEEND1(rs.getString("SPECIALTIMEEND1"));
                bean.setSPECIALTIMEBEGIN2(rs.getString("SPECIALTIMEBEGIN2"));
                bean.setSPECIALTIMEEND2(rs.getString("SPECIALTIMEEND2"));
                bean.setSPECIALTIMEBEGIN3(rs.getString("SPECIALTIMEBEGIN3"));
                bean.setSPECIALTIMEEND3(rs.getString("SPECIALTIMEEND3"));
                bean.setSPECIALTIMEFEE1(rs.getString("SPECIALTIMEFEE1"));
                bean.setSPECIALTIMEFEE2(rs.getString("SPECIALTIMEFEE2"));
                bean.setSPECIALTIMEFEE3(rs.getString("SPECIALTIMEFEE3"));
                li.add(bean);
            }
        });
        return li;
    }

    @Override
    
    public boolean changePass(String newpass, String uid) {
        boolean flag = false;
        String sql = "UPDATE BillSys_User SET password='"+newpass+"' WHERE username='"+uid+"'";
        int uNum = jdbcTemplateBmu.update(sql);
        if(uNum>0){
            return true;
        }
        return false;
    }

    @Override
    
    public boolean checkPassword(String uid, String password) {
        boolean flag = false;
        String sql = "SELECT COUNT(*) FROM BillSys_User WHERE username=? AND password=?";
        Object[] param = {uid,password};
        int count = jdbcTemplateBmu.queryForInt(sql, param);
        if(count >0 ){
            return true;
        }
        return false;
    }

    /**
     * 在用户账号密码表中插入新注册的用户
     * @param user
     * @return
     */
    @Override
    
    public boolean addUser(final BillSys_User user) {
        String sql = "INSERT INTO BillSys_User(username,password,level)VALUES(?,?,?)";
        int uNum = jdbcTemplateBmu.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.setInt(3, user.getLevel());
            }
        });
        if(uNum>0){
            return true;
        }
        return false;
    }

    /**
     * 删除用户账号密码
     * @param user
     * @return
     */
    @Override
    
    public boolean removeUser(final BillSys_User user) {
        String sql = "DELETE FROM BillSys_User WHERE username=? AND password=?";
        int uNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,user.getUsername());
                pstmt.setString(2, user.getPassword());
            }
        });
        if(uNum>0){
            return true;
        }
        return false;
    }

    @Override
    
    public List getUserInfoByNum(final String longNum) {
        final List li = new ArrayList();
        String sql = "SELECT * FROM gtao_Phone_User WHERE longNum=?";
        jdbcTemplateBmu.query(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,longNum);
            }
        },new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_Phone_User bean = new gtao_Phone_User();
                bean.setId(rs.getInt("id"));
                bean.setUserid(rs.getString("userid"));
                bean.setMobile(rs.getString("mobile"));
                bean.setPhoneIp(rs.getString("phoneip"));
                bean.setVlan(rs.getString("vlan"));
                bean.setLongNum(rs.getString("longNum"));
                bean.setShortNum(rs.getString("shortNum"));
                bean.setItime(rs.getString("itime"));
                bean.setLastUpd(rs.getString("lastUpd"));
                bean.setTactics(rs.getString("tactics"));
                bean.setStatus(rs.getString("status"));
                bean.setEmail(rs.getString("email"));
                bean.setBalance(rs.getString("balance"));
                bean.setStored(rs.getString("stored"));
                bean.setMaturityTime(rs.getString("maturitytime"));
                bean.setTbl(rs.getString("tbl"));
                bean.setGate(rs.getString("gate"));
                bean.setProtocal(rs.getString("protocal"));
                li.add(bean);
            }
        });
        return li;
    }

    /**
     * 修改用户状态
     * @param status
     * @param id
     * @return boolean
     */
    @Override
    
    public boolean updateUserStatus(final String status,final int id) {
        String sql = "UPDATE gtao_Phone_User SET status=? WHERE ID=?";
        int uNum = jdbcTemplateBmu.update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1,status);
                pstmt.setInt(2,id);
            }
        });
        if(uNum>0){
            return true;
        }
        return false;
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
        String sql = "SELECT ucCallerNumber,SUM(dwConversationTime) FROM "+tbl+" t,gtao_Phone_User u WHERE t.ucCallerNumber=u.shortNum GROUP BY ucCallerNumber";
        /////////////////TODO
        return li;
    }

    /*
    全部用户分页查询         WHERE userid LIKE '%"+key+"%' OR longNum LIKE '%"+key+"%'
     */
    @Override
    public List viewUserPage(final int start, final int length,final String key) {
        final List li = new ArrayList();
        String sql = "";
        if(!key.equals("")){
            sql = "SELECT TOP "+length+" * FROM (SELECT " +
            "ROW_NUMBER() OVER (ORDER BY ID) AS RowNumber,* " +
            "FROM gtao_Phone_User WHERE userid LIKE '%"+key+"%' OR longNum LIKE '%"+key+"%') _myResults " +
            "WHERE RowNumber >"+start;
        }
        else{
            sql =
                    "SELECT TOP "+length+" * FROM (SELECT " +
                    "ROW_NUMBER() OVER (ORDER BY ID) AS RowNumber,* " +
                    "FROM gtao_Phone_User) _myResults " +
                    "WHERE RowNumber >"+start;
        }
        jdbcTemplateBmu.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                gtao_Phone_User bean = new gtao_Phone_User();
                bean.setId(rs.getInt("id"));
                bean.setUserid(rs.getString("userid"));
                bean.setMobile(rs.getString("mobile"));
                bean.setPhoneIp(rs.getString("phoneip"));
                bean.setVlan(rs.getString("vlan"));
                bean.setLongNum(rs.getString("longNum"));
                bean.setShortNum(rs.getString("shortNum"));
                bean.setItime(rs.getString("itime"));
                bean.setLastUpd(rs.getString("lastUpd"));
                bean.setTactics(rs.getString("tactics"));
                bean.setStatus(rs.getString("status"));
                bean.setEmail(rs.getString("email"));
                bean.setBalance(rs.getString("balance"));
                bean.setStored(rs.getString("stored"));
                bean.setMaturityTime(rs.getString("maturitytime"));
                bean.setTbl(rs.getString("tbl"));
                bean.setGate(rs.getString("gate"));
                bean.setProtocal(rs.getString("protocal"));
                li.add(bean);
            }
        });
        return li;
    }

    /*
    获取用户总数（分页用）WHERE userid LIKE '%"+key+"%' OR longNum LIKE '%"+key+"%'
     */
    @Override
    public int getUserCount(String key) {
        String sql = "SELECT COUNT(*) FROM gtao_Phone_User";
        if(!key.equals("")){
            sql += " WHERE userid LIKE '%"+key+"%' OR longNum LIKE '%"+key+"%'";
        }
        int count = jdbcTemplateBmu.queryForInt(sql);
        return count;
    }

    /**
     * 删除组策略表
     * @param tbl
     * @return boolean
     */
    public boolean deleteGroupTable(String tbl){
        String sql = "DROP TABLE "+tbl;
        int uNum = jdbcTemplateBmu.update(sql);
        if(uNum>0){
            return true;
        }
        return false;
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
        String sql = "";
        if(tbl.equals("gtao_Phone_bc_sale")||tbl=="gtao_Phone_bc_sale"){
            sql = "select count(*) from "+tbl+" where longNum=? and shortNum=? and ip=? and money=? and vlan=?";
            Object[] param = {sale.getLongNum(),sale.getShortNum(),sale.getIp(),sale.getMoney(),sale.getVlan()};
            int count = jdbcTemplateBmu.queryForInt(sql,param);
            if(count>0){
                flag = false;
            }
        }
        else {
            sql = "select count(*) from "+tbl+" where longNum=? and shortNum=? and ip=? and vlan=?";
            Object[] param = {view.getLongNum(),view.getShortNum(),view.getIp(),view.getVlan()};
            int count = jdbcTemplateBmu.queryForInt(sql,param);
            if(count >0){
                flag = false;
            }
        }
        return flag;
    }
}
