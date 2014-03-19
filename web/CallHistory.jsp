<%@ page import="com.bill.pojo.gtao_phone_profile" %>
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
<!-- 顶部显示 -->
<%@include file="head.jsp"%>
<div class="container-fluid">
<div class="row-fluid">
<!--左侧菜单-->
<%@include file="menu.jsp"%>
<div id="content" class="span10">
<!-- 主容器部分 -->
<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">用户管理</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="#">查询通话记录</a>
        </li>
    </ul>
</div>

<div class="row-fluid">
    <div class="box span12">
        <div class="box-header well">
            <h2><i class="icon-info-sign"></i> 查询条件</h2>
            <div class="box-icon">
                <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
            </div>
        </div>
        <div class="box-content">
            <div class="control-group">
                <div class="controls">
                    <div class="input-append">
                        <h3>单月通话记录查询</h3>
                        <hr>
                        年份：
                        <select id="year" style="width: 100px">
                            <option value="2012">2012年</option>
                            <option value="2013">2013年</option>
                            <option value="2014" selected>2014年</option>
                        </select>&nbsp;
                        月份：
                        <select id="month" style="width: 80px">
                            <option value="01">1月</option>
                            <option value="02">2月</option>
                            <option value="03">3月</option>
                            <option value="04">4月</option>
                            <option value="05">5月</option>
                            <option value="06">6月</option>
                            <option value="07">7月</option>
                            <option value="08">8月</option>
                            <option value="09">9月</option>
                            <option value="10">10月</option>
                            <option value="11">11月</option>
                            <option value="12">12月</option>
                        </select>&nbsp;呼叫属性：
                        <select style="width: 100px" id="callType">
                            <option value="null">无</option>
                            <%
                                ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
                                BillSysDAO billSysDAO = ctx.getBean("billService",BillSysDAO.class);
                                List li = billSysDAO.getAllFeeProfile();
                                gtao_phone_profile profile = new gtao_phone_profile();
                                Iterator it = li.iterator();
                                while(it.hasNext()){
                                    profile = (gtao_phone_profile)it.next();
                            %>
                                <option value="<%=profile.getID()%>"><%=profile.getFEEPROFILE()%></option>
                            <%
                                }
                            %>
                        </select>&nbsp;&nbsp;号码：&nbsp;
                        <input placeholder="长号码" size="8" type="text" id="longNum" style="width: 150px">
                        <button class="btn" type="button" onclick="getHistoryByMonth()">查询</button>
                        <button class="btn" type="button" onclick="window.open('exportCallHistory.jsp')">导出</button>
                        <hr>
                        <h3>按时间段查询</h3>
                        <hr>
                       起始时间：<input type="text" class="input-small"  id="datefrom">
                        结束时间：<input type="text" class="input-small"  id="dateto">
                        &nbsp;号码：&nbsp;
                        <input placeholder="长号码" size="8" type="text" id="longNum2" style="width: 150px">
                        <button class="btn" type="button" onclick="getHistoryByDate()">查询</button>
                        <button class="btn" type="button" onclick="window.open('exportDateHistory.jsp')">导出</button>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
    <!-- 表格部分 -->
<div class="row-fluid">
  <div class="box span12">
    <div class="box-content">
        <div id="CallHistoryTblBlock" class="dataTables_wrapper" role="grid">
            <table class="table table-striped table-bordered bootstrap-datatable datatable dataTable" id="CallHistoryTbl" aria-describedby="DataTables_Table_0_info" style="width: 100%">
                <thead>
                <tr role="row">
                    <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-sort="ascending">ID</th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">主叫号码</th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">被叫号码</th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">会话属性</th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" style="width: 15%">开始时间</th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" style="width: 15%">结束时间</th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" style="width: 6%">通话时长（秒）</th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">操作</th>
                </tr>
                </thead>
                <tbody role="alert" aria-live="polite" aria-relevant="all">
                    <!--JSON填充部分-->
                </tbody>
            </table>
        </div>
        </div>
      </div>
    </div>
</div>
</div>
<hr>
<!--页面底部-->
<footer>
    <p class="pull-left">&copy; <a href="http://www.gtao.com" target="_blank">GuanTao High Tech co.,ltd</a> 2013</p>
</footer>
</div>
<!-- JS文件
================================================== -->
<!-- jQuery -->
<script src="js/jquery-1.7.2.min.js"></script>
<!-- jQuery UI -->
<script src="js/jquery-ui-1.8.21.custom.min.js"></script>
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
<script src="js/CallHistory.js"></script>

<script type="text/javascript">
    $(function(){
        $("#datefrom").datepicker();
        $("#dateto").datepicker();
    });
</script>
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
