<%@ page import="com.bill.dao.BillSysDAOImpl" %>
<%@ page import="com.bill.pojo.gtao_Phone_bc_sale" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="com.bill.dao.BillSysDAO" %>
<%@include file="Validate.jsp"%>
<%@page language="java" pageEncoding="GBK" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="GBK">
    <title>语音计费管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- The styles -->
    <link id="bs-css" href="css/bootstrap-cerulean.css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }
    </style>

    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/charisma-app.css" rel="stylesheet">
    <link href="css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
    <link href='css/fullcalendar.css' rel='stylesheet'>
    <link href='css/fullcalendar.print.css' rel='stylesheet'  media='print'>
    <link href='css/chosen.css' rel='stylesheet'>
    <link href='css/uniform.default.css' rel='stylesheet'>
    <link href='css/colorbox.css' rel='stylesheet'>
    <link href='css/jquery.cleditor.css' rel='stylesheet'>
    <link href='css/jquery.noty.css' rel='stylesheet'>
    <link href='css/noty_theme_default.css' rel='stylesheet'>
    <link href='css/elfinder.min.css' rel='stylesheet'>
    <link href='css/elfinder.theme.css' rel='stylesheet'>
    <link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='css/opa-icons.css' rel='stylesheet'>
    <link href='css/uploadify.css' rel='stylesheet'>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">
</head>
<body>
<!-- topbar starts -->
<%@include file="head.jsp"%>
<!-- topbar ends -->
<div class="container-fluid">
<div class="row-fluid">

<!-- left menu starts -->
<%@include file="menu.jsp"%>
<!-- left menu ends -->
<div id="content" class="span10">
<!-- content starts -->
<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">用户管理</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="NumManage.jsp">号码管理</a><span class="divider">/</span>
        </li>
        <li>
            <a href="#">查看详细</a>
        </li>
    </ul>
</div>
<%
    String longNum = request.getParameter("pNum");
    String tbl = request.getParameter("tbl");
    String userIdText="",mobileText="",phoneIpText="",vlanText="",longNumText="",
            shortNumText="",itimeText="",money="",isHandle="",ipText="",HandleText="";
    List li = new ArrayList();
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    BillSysDAO billSysDAO = ctx.getBean("billService",BillSysDAO.class);
    li = billSysDAO.getAreaInfoByNum(tbl,longNum);
    Iterator it = li.iterator();
    gtao_Phone_bc_sale sale = null;
    while (it.hasNext()){
        sale = (gtao_Phone_bc_sale)it.next();
        userIdText = sale.getUserId();
        mobileText = sale.getMobile();
        phoneIpText = sale.getIp();
        vlanText = sale.getVlan();
        longNumText = sale.getLongNum();
        shortNumText = sale.getShortNum();
        itimeText = sale.getUpTime().toString();
        money = sale.getMoney();
        isHandle = sale.getHandle();
    }
%>
<div class="row-fluid sortable ui-sortable">
<div class="box span12" style="">
<div class="box-header well" data-original-title="">
    <h2><i class="icon-edit"></i>编辑信息</h2>
</div>
<div class="box-content">
    <form class="form-horizontal">
    <fieldset>
        <legend>详细号码信息</legend>
        <div class="control-group">
            <label class="control-label" for="phoneNum">号码：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="phoneNum" type="text" value="<%=longNumText%>">
                <input type="hidden" id="finalNum" value="<%=longNum%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="userId">用户账号：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="userId" type="text" value="<%=userIdText%>" disabled>
                <input type="hidden" id="finalUser" value="<%=userIdText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="shortNum">短号：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="shortNum" type="text" value="<%=shortNumText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="mobile">手机号码：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="mobile" type="text" value="<%=mobileText%>" disabled>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="phoneIp">IP地址：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="phoneIp" type="text" value="<%=phoneIpText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="vlan">绑定Vlan：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="vlan" type="text" value="<%=vlanText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="money">价格</label>
            <div class="controls">
                <input class="input-xlarge focused" id="money" type="text" value="<%=money%>">
                <!--hidden-->
                <input type="hidden" id="tblName" value="<%=tbl%>">
            </div>
        </div>
        <div class="form-actions">
            <button type="button" class="btn btn-primary" onclick="EditSaleNumInfo()">保存修改</button>
            <button type="button" class="btn btn-warning" onclick="initNum()">号码初始化</button>
            <button type="button" class="btn btn-danger" onclick="return initSaleNumInfo()">删除号码</button>
        </div>
    </fieldset>
    </form>
</div>
</div><!--/span-->

</div>

<!-- content ends -->
</div><!--/#content.span10-->
</div><!--/fluid-row-->
<hr>

<footer>
    <p class="pull-left">&copy; <a href="http://www.gtao.com" target="_blank">GuanTao High Tech co.,ltd</a> 2013</p>
</footer>

</div><!--/.fluid-container-->

<!-- external javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<!-- jQuery -->
<script src="js/jquery-1.7.2.min.js"></script>
<!-- jQuery UI -->
<script src="js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript">
    $(function(){
        $("#installTime").datepicker();
    });
</script>
<!-- transition / effect library -->
<script src="js/bootstrap-transition.js"></script>
<!-- alert enhancer library -->
<script src="js/bootstrap-alert.js"></script>
<!-- modal / dialog library -->
<script src="js/bootstrap-modal.js"></script>
<!-- custom dropdown library -->
<script src="js/bootstrap-dropdown.js"></script>
<!-- scrolspy library -->
<script src="js/bootstrap-scrollspy.js"></script>
<!-- library for creating tabs -->
<script src="js/date_zhcn.js"></script>
<script src="js/bootstrap-tab.js"></script>
<!-- library for advanced tooltip -->
<script src="js/bootstrap-tooltip.js"></script>
<!-- popover effect library -->
<script src="js/bootstrap-popover.js"></script>
<!-- button enhancer library -->
<script src="js/bootstrap-button.js"></script>
<!-- accordion library (optional, not used in demo) -->
<script src="js/bootstrap-collapse.js"></script>
<!-- carousel slideshow library (optional, not used in demo) -->
<script src="js/bootstrap-carousel.js"></script>
<!-- autocomplete library -->
<script src="js/bootstrap-typeahead.js"></script>
<!-- tour library -->
<script src="js/bootstrap-tour.js"></script>
<!-- library for cookie management -->
<script src="js/jquery.cookie.js"></script>
<!-- calander plugin -->
<script src='js/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='js/jquery.dataTables.min.js'></script>
<!-- chart libraries start -->
<script src="js/excanvas.js"></script>
<script src="js/jquery.flot.min.js"></script>
<script src="js/jquery.flot.pie.min.js"></script>
<script src="js/jquery.flot.stack.js"></script>
<script src="js/jquery.flot.resize.min.js"></script>
<!-- chart libraries end -->
<script src="js/NumManage.js"></script>
<!-- select or dropdown enhancer -->
<script src="js/jquery.chosen.min.js"></script>
<!-- checkbox, radio, and file input styler -->
<script src="js/jquery.uniform.min.js"></script>
<!-- plugin for gallery image view -->
<script src="js/jquery.colorbox.min.js"></script>
<!-- rich text editor library -->
<script src="js/jquery.cleditor.min.js"></script>
<!-- notification plugin -->
<script src="js/jquery.noty.js"></script>
<!-- file manager library -->
<script src="js/jquery.elfinder.min.js"></script>
<!-- star rating plugin -->
<script src="js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="js/charisma.js"></script>
</body>
</html>
