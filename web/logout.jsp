<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.removeAttribute("uid");
    session.removeAttribute("lid");
    response.sendRedirect("login.jsp");
%>