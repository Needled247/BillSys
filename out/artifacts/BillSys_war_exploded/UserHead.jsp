<%@page language="java" pageEncoding="GBK" %>
<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="http://www.gtao.com"><span>语音计费系统</span></a>

            <!-- theme selector starts -->
                <!--none-->
            <!-- theme selector ends -->
            <!-- user dropdown starts -->
            <div class="btn-group pull-right" >
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="icon-user"></i><span class="hidden-phone"><%=session.getAttribute("uid")%></span>
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="logout.jsp">注销</a></li>
                </ul>
            </div>
            <!-- user dropdown ends -->
            <div class="top-nav nav-collapse">
                <ul class="nav">
                    <li><a href="#"></a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>