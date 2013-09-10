<%@page language="java" pageEncoding="GBK" %>
<%@include file="Validate.jsp"%>
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
            <div class="btn-group pull-right theme-container" >
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="icon-tint"></i><span class="hidden-phone"> 更换主题</span>
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu" id="themes">
                    <li><a data-value="classic" href="#"><i class="icon-blank"></i> Classic</a></li>
                    <li><a data-value="cerulean" href="#"><i class="icon-blank"></i> Cerulean</a></li>
                    <li><a data-value="cyborg" href="#"><i class="icon-blank"></i> Cyborg</a></li>
                    <li><a data-value="redy" href="#"><i class="icon-blank"></i> Redy</a></li>
                    <li><a data-value="journal" href="#"><i class="icon-blank"></i> Journal</a></li>
                    <li><a data-value="simplex" href="#"><i class="icon-blank"></i> Simplex</a></li>
                    <li><a data-value="slate" href="#"><i class="icon-blank"></i> Slate</a></li>
                    <li><a data-value="spacelab" href="#"><i class="icon-blank"></i> Spacelab</a></li>
                    <li><a data-value="united" href="#"><i class="icon-blank"></i> United</a></li>
                </ul>
            </div>
            <!-- theme selector ends -->

            <!-- user dropdown starts -->
            <div class="btn-group pull-right" >
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="icon-user"></i><span class="hidden-phone"> admin</span>
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">编辑</a></li>
                    <li class="divider"></li>
                    <li><a href="login.html">注销</a></li>
                </ul>
            </div>
            <!-- user dropdown ends -->
            <div class="top-nav nav-collapse">
                <ul class="nav">
                    <li><a href="#"></a></li>
                    <li>
                        <form class="navbar-search pull-left">
                            <input placeholder="查询内容..." class="search-query span2" name="query" type="text">
                        </form>
                    </li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>
<!-- topbar ends -->
<div class="container-fluid">
<div class="row-fluid">

<!-- left menu starts -->
<div class="span2 main-menu-span">
    <div class="well nav-collapse sidebar-nav">
        <ul class="nav nav-tabs nav-stacked main-menu">
            <li class="nav-header hidden-tablet">用户管理</li>
            <li><a class="ajax-link" href="index.jsp"><i class="icon-home"></i><span class="hidden-tablet"> 首页</span></a></li>
            <li><a class="ajax-link" href="#"><i class="icon-bookmark"></i><span class="hidden-tablet"> 号码管理</span></a></li>
            <li><a class="ajax-link" href="#"><i class="icon-search"></i><span class="hidden-tablet"> 查看新申请</span></a></li>
            <li><a class="ajax-link" href="newSaleApply.jsp"><i class="icon-shopping-cart"></i><span class="hidden-tablet"> 查看付费申请</span></a></li>
            <li><a class="ajax-link" href="index.html"><i class="icon-eye-open"></i><span class="hidden-tablet"> 查询用户</span></a></li>
            <li><a class="ajax-link" href="ui.html"><i class="icon-comment"></i><span class="hidden-tablet"> 查询通话记录</span></a></li>
            <li><a class="ajax-link" href="form.html"><i class="icon-edit"></i><span class="hidden-tablet"> 业务变更</span></a></li>
            <li><a class="ajax-link" href="chart.html"><i class="icon-list-alt"></i><span class="hidden-tablet"> 修改密码</span></a></li>
            <li><a class="ajax-link" href="typography.html"><i class="icon-font"></i><span class="hidden-tablet"> 话单导出</span></a></li>
            <li class="nav-header hidden-tablet">设置</li>
            <li><a class="ajax-link" href="table.html"><i class="icon-align-justify"></i><span class="hidden-tablet">计费策略</span></a></li>
            <li><a class="ajax-link" href="calendar.html"><i class="icon-repeat"></i><span class="hidden-tablet">数据备份</span></a></li>
            <li><a class="ajax-link" href="grid.html"><i class="icon-th"></i><span class="hidden-tablet">系统日志</span></a></li>
            <li><a class="ajax-link" href="file-manager.html"><i class="icon-folder-open"></i><span class="hidden-tablet"> 日志导出</span></a></li>
            <li class="nav-header hidden-tablet">系统</li>
            <li><a class="ajax-link" href="#"><i class="icon-folder-open"></i><span class="hidden-tablet"> 管理员管理</span></a></li>
            <li><a class="ajax-link" href="#"><i class="icon-folder-open"></i><span class="hidden-tablet"> 修改密码</span></a></li>
            <li><a class="ajax-link" href="#"><i class="icon-folder-open"></i><span class="hidden-tablet"> 注销登录</span></a></li>
        </ul>
    </div><!--/.well -->
</div><!--/span-->
<!-- left menu ends -->
<div id="content" class="span10">
<!-- content starts -->
<div>
    <ul class="breadcrumb">
        <li>
            <a href="index.jsp">用户管理</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="#">新申请</a>
        </li>
    </ul>
</div>

<div class="row-fluid sortable ui-sortable">
<div class="box span12" style="">
<div class="box-header well" data-original-title="">
    <h2><i class="icon-user"></i> 成功</h2>
</div>

<div class="box-content">
    <div id="num">
    <h2>保存成功！3秒后跳转...</h2>
    <h3>如果您的浏览器没有跳转，请点击<a href ="UserGroup.jsp">这里</a></h3>
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
<script type="text/javascript">
    $(document).ready(function() {
        function jump(count) {
            window.setTimeout(function(){
                count--;
                if(count > 0) {
                    $('#num').attr('innerHTML', count);
                    jump(count);
                } else {
                    location.href="UserGroup.jsp";
                }
            }, 1000);
        }
        jump(3);
    });
</script>
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
