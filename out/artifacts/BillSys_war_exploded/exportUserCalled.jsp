<%@ page contentType="application/vnd.ms-excel" language="java" pageEncoding="GBK"%>
<%@ page import="com.bill.tool.BillSysTool"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String datestr = sdf.format(d);
    response.setHeader("Content-Disposition","attachment;filename="+datestr+".xls");//ָ�����ص��ļ���
    response.setContentType("application/vnd.ms-excel");
    List li = (List)session.getAttribute("userCalledList");
    BillSysTool tool = new BillSysTool();
    tool.getUserCalledExcel("download.xls",response.getOutputStream(),li);
    session.removeAttribute("userCalledList");
%>