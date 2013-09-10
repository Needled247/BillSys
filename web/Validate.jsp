<%@ page contentType="text/html;charset=GBK" language="java" %>
<%
    Object uid = session.getAttribute("uid");
    Object lid = session.getAttribute("lid");
    if(uid==null){
        response.sendRedirect("login.jsp");
        return;
    }
    if(lid==null){
        response.sendRedirect("login.jsp");
        return;
    }
    if(!lid.toString().equals("1")){
        response.sendRedirect("login.jsp");
        return;
    }
%>