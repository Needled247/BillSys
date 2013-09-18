package com.bill.tool;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Author:jh
 * Date: 13-7-11
 * Time: 上午10:49
 * SSH远程登陆SHELL服务器。
 */
public class SshTool {
    private static final String HOST_NAME = "10.0.1.32";
    private static final String USER_NAME = "bill";
    private static final String USER_PASS = "billtt#$%lo1";
    private static final int PORT = 8723;

    /**
     * 9500调用创建用户脚本
     * @param longNum
     * @param shortNum
     * @param ipadd
     * @param protocal
     * @param userid
     * @return boolean
     */
    public boolean CreateUser(String longNum,String shortNum,String ipadd,String protocal,String userid){
        boolean flag = false;
        try{
            Connection conn = new Connection(HOST_NAME,PORT);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(USER_NAME, USER_PASS);
            if (isAuthenticated == false)
                throw new IOException("验证失败");
            Session sess = conn.openSession();
            String cmd = "";
            if(protocal.equals("sip")||protocal=="sip"){
                cmd = "/home/to9500/s_opensipuser.sh "+shortNum+" "+longNum+" "+ipadd;
            }
            else if(protocal.equals("248")||protocal=="248"){
                cmd = "/home/to9500/s_open248user.sh "+shortNum+" "+longNum+" "+ipadd+" "+userid;
            }
            System.out.println(cmd);
            sess.execCommand(cmd);
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            int mark = 0;
            while (true)
            {
                String line = br.readLine();
                if (line == null)
                    break;
                if(line.contains("Command executed success")){
                    mark++;
                }
                System.out.println(line);
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
            if(mark>=2){
                flag = true;
            }
            sess.close();
            conn.close();
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
        }
        return flag;
    }

    /**
     * 9500调用删除用户脚本
     * @param longNum
     * @param shortNum
     * @param ipadd
     * @param protocal
     * @param userid
     * @return boolean
     */
    public boolean DelUser(String longNum,String shortNum,String ipadd,String protocal,String userid){
        boolean flag = false;
        try{
            Connection conn = new Connection(HOST_NAME,PORT);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(USER_NAME, USER_PASS);
            if (isAuthenticated == false)
                throw new IOException("Authenticated Fail");
            Session sess = conn.openSession();
            String cmd = "";
            if(protocal.equals("sip")||protocal=="sip"){
                cmd = "/home/to9500/s_delsipuser.sh "+shortNum;
            }
            else if(protocal.equals("248")||protocal=="248"){
                cmd = "/home/to9500/s_del248user.sh "+shortNum+" "+userid;
            }
            System.out.println(cmd);
            sess.execCommand(cmd);
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            int mark = 0;
            while (true)
            {
                String line = br.readLine();
                if (line == null)
                    break;
                if(line.contains("Command executed success")){
                    mark++;
                }
                System.out.println(line);
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
            if(mark>=2){
                flag = true;
            }
            sess.close();
            conn.close();
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
        }
        return flag;
    }

    /**
     * 用户权限管理
     * @param eid
     * @param action
     * @return Boolean
     */
    public boolean userCallCompetenceManage(String eid,String action){
        boolean flag = false;
        try{
            Connection conn = new Connection(HOST_NAME,PORT);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(USER_NAME, USER_PASS);
            if (isAuthenticated == false)
                throw new IOException("Authenticated Fail");
            Session sess = conn.openSession();
            String cmd = "";
            if(action.equals("openLocal")){
                cmd = "/home/to9500/s_onuser_local.sh "+eid;
            }
            else if(action.equals("offLocal")){
                cmd = "/home/to9500/s_offuser_local.sh "+eid;
            }
            else if(action.equals("openLong")){
                cmd = "/home/to9500/s_onuser_ddd.sh "+eid;
            }
            else if(action.equals("offLong")){
                cmd = "/home/to9500/s_offuser_ddd.sh "+eid;
            }
            System.out.println(cmd);
            sess.execCommand(cmd);
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            int mark = 0;
            while (true)
            {
                String line = br.readLine();
                if (line == null)
                    break;
                if(line.contains("Command executed success")){
                    mark++;
                }
                System.out.println(line);
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
            if(mark>=2){
                flag = true;
            }
            sess.close();
            conn.close();
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
        }
        return flag;
    }

    /**
     * 语音业务预开通   “开通第一步”
     * @param longNum
     * @param shortNum
     * @param ipadd
     * @param userid
     * @param protocal
     * @exception IOException
     * @return  boolean
     */
    public boolean pre_Oppening(String longNum,String shortNum,String ipadd,String userid,String protocal){
        boolean flag = false;
        try{
            Connection conn = new Connection(HOST_NAME,PORT);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(USER_NAME, USER_PASS);
            if (isAuthenticated == false)
                throw new IOException("验证失败");
            Session sess = conn.openSession();
            String cmd = "";
            if(protocal.equals("sip")||protocal=="sip"){
                cmd = "/home/to9500/s_opensiponlyinter.sh "+shortNum+" "+longNum+" "+ipadd;
            }
            else if(protocal.equals("248")||protocal=="248"){
                cmd = "/home/to9500/s_open248onlyinter.sh "+shortNum+" "+longNum+" "+ipadd+" "+userid;
            }
            System.out.println(cmd);
            sess.execCommand(cmd);
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            int mark = 0;
            while (true)
            {
                String line = br.readLine();
                if (line == null)
                    break;
                if(line.contains("Command executed success")){
                    mark++;
                }
                System.out.println(line);
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
            if(mark>=2){
                flag = true;
            }
            sess.close();
            conn.close();
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
        }
        return flag;
    }

    /**
     * 语音开通第二步，开通关闭权限
     * @param eid
     * @param action
     * @return boolean
     */
    public boolean pre_OpenningCompetence(String eid,String action){
        boolean flag = false;
        try{
            Connection conn = new Connection(HOST_NAME,PORT);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(USER_NAME, USER_PASS);
            if (isAuthenticated == false)
                throw new IOException("Authenticated Fail");
            Session sess = conn.openSession();
            String cmd = "";
            if(action.equals("open")){
                //cmd = "/home/to9500/s_onuser_local.sh "+eid;         开通长途市话脚本
            }
            else if(action.equals("close")){
                //cmd = "/home/to9500/s_offuser_ddd.sh "+eid;          关闭长途市话脚本
            }
            System.out.println(cmd);
            sess.execCommand(cmd);
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            int mark = 0;
            while (true)
            {
                String line = br.readLine();
                if (line == null)
                    break;
                if(line.contains("Command executed success")){
                    mark++;
                }
                System.out.println(line);
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
            if(mark>=2){
                flag = true;
            }
            sess.close();
            conn.close();
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
        }
        return flag;
    }
}
