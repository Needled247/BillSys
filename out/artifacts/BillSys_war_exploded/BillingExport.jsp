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
            <a href="#">打印派工单</a>
        </li>
    </ul>
</div>
<div class="row-fluid sortable ui-sortable">
<div class="box span12" style="">
<div class="box-header well" data-original-title="">
    <h2><i class="icon-edit"></i>打印派工单</h2>
</div>
<div class="box-content">
    <form class="form-horizontal">
    <fieldset>
        <div class="control-group">
            <label class="control-label" for="longNum">长号码：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="longNum" type="text" style="width: 15%">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="installer">安装人：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="installer" type="text" style="width: 15%">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="installTime">安装时间：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="installTime" type="text" style="width: 15%">
            </div>
        </div>
        <div id="toPrint" hidden>
            <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" >
                <tbody>
                <tr style="text-align: center"><h1>语音业务开通派工单</h1></tr>
                <tr>
                    <td width="25%">用户姓名：</td>
                    <td width="25%"><div id="printName"></div></td>
                    <td width="25%">用户电话：</td>
                    <td width="25%"><div id="printMobile"></div></td>
                </tr>
                <tr>
                    <td width="25%">用户账号：</td>
                    <td width="25%"><div id="printUserid"></div></td>
                    <td width="25%">网关：</td>
                    <td width="25%"><div id="printGate"></div></td>
                </tr>
                <tr>
                    <td width="25%">住址：</td>
                    <td width="25%"><div id="printAddress"></div></td>
                    <td width="25%">MAC地址：</td>
                    <td width="25%"><div id="printMac"></div></td>
                </tr>
                <tr>
                    <td width="25%">长号：</td>
                    <td width="25%"><div id="printLongNum"></div></td>
                    <td width="25%">短号：</td>
                    <td width="25%"><div id="printShortNum"></div></td>
                </tr>
                <tr>
                    <td width="25%">绑定IP：</td>
                    <td width="25%"><div id="printIp"></div></td>
                    <td width="25%">绑定VLAN：</td>
                    <td width="25%"><div id="printVlan"></div></td>
                </tr>
                <tr>
                    <td width="25%">安装负责人：</td>
                    <td width="25%"><div id="printInstaller"></div></td>
                    <td width="25%">安装时间：</td>
                    <td width="25%"><div id="printInsTime"></div></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="form-actions">
            <button type="button" class="btn btn-primary" id="btn_save" onclick="initBilling()">查询</button>
            <button type="button" id="btn_print" class="btn" onclick="PagePrint()">打印</button>
            <button type="button" class="btn btn-success" onclick="window.location.href='newApply.jsp'">
                <i class="icon-chevron-left icon-white"></i>返回</button>
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
<script src="js/jquery.jqprint-0.3.js"></script>
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
<!--js文件-->
<script src="js/printBilling.js"></script>
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
