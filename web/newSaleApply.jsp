<%@ page import="com.bill.pojo.gtao_Phone_bc_sale" %>
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
            <a href="index.jsp">用户管理</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="#">新付费申请</a>
        </li>
    </ul>
</div>

<div class="row-fluid sortable ui-sortable">
<div class="box span12" style="">
<div class="box-header well" data-original-title="">
    <h2><i class="icon-user"></i> 查看新付费申请</h2>
</div>
<div class="box-content">
<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper" role="grid">
<table class="table table-striped table-bordered bootstrap-datatable datatable dataTable" id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info" style="width: 100%">
<thead>
<tr role="row">
    <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-sort="ascending">号码</th>
    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">用户账号</th>
    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">手机</th>
    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">申请时间</th>
    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">价格</th>
    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">是否付款</th>
    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" style="width: 20%">操作</th>
</tr>
</thead>
    <%
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BillSysDAO billService = ctx.getBean("billService",BillSysDAO.class);
        List li = billService.getSaleApplyList();
        Iterator it = li.iterator();
        gtao_Phone_bc_sale sale = null;%>
<tbody role="alert" aria-live="polite" aria-relevant="all">
    <%
        int flag = 2;
        String mark = "";
        while (it.hasNext()){
            sale = (gtao_Phone_bc_sale)it.next();
            String newTime = sale.getUpTime().toString();
            String tbl = sale.getTbl();
            if(sale.getIsPay().equals("0")||sale.getIsPay()=="0"){
                mark = "未支付";
            }
            else {
                mark = "已支付";
            }
            if(flag%2==0){
    %>
    <tr class="odd">
    <% }else {
    %>
    <tr class="even">
        <%}%>
        <td class="  sorting_1"><%=sale.getLongNum()%></td>
        <td class="center "><%=sale.getUserId()%></td>
        <td class="center "><%=sale.getMobile()%></td>
        <td class="center "><%=newTime%></td>
        <td class="center "><%=sale.getMoney()%></td>
        <!--span class="label label-warning">warn</span-->
        <td class="center "><%=mark%></td>
        <td class="center ">
            <a class="btn btn-success" href="ViewSaleApply.jsp?pnum=<%=sale.getLongNum()%>&userid=<%=sale.getUserId()%>&mobile=<%=sale.getMobile()%>&tbl=<%=sale.getTbl()%>">
                <i class="icon-zoom-in icon-white"></i>查看/编辑</a>
            <a class="btn btn-danger" href="SaleDetailEdit.action?editType=page_init&finalNum=<%=sale.getLongNum()%>&finalUser=<%=sale.getUserId()%>&tbl=<%=sale.getTbl()%>">
                <i class="icon-trash icon-white"></i>初始化</a>
        </td>
    </tr>
        <%flag++;}%>
</tbody>
</table>
<div class="row-fluid">
    <div class="span12">
        <div class="dataTables_info" id="DataTables_Table_0_info"></div>
    </div>
</div>
</div>
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
        $("#datefrom").datepicker();
        $("#dateto").datepicker();
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
