<%@page language="java" pageEncoding="GBK" %>
<%@include file="Validate.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="GBK">
    <title>�����Ʒѹ���ϵͳ</title>
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
            <a href="#">�û�����</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="#">�½Ṥ��</a>
        </li>
    </ul>
</div>

<div class="row-fluid">
    <div class="box span12">
        <div class="box-header well">
            <h2><i class="icon-info-sign"></i> ��ѯ����</h2>
            <div class="box-icon">
                <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
            </div>
        </div>
        <div class="box-content">
            <div class="control-group">
                <div class="controls">
                    <div class="input-append">
                        ����
                        <select style="width: 15%" id="Area">
                            <option value="all">ȫ��</option>
                            <option value="gtao_Phone_bc">������</option>
                            <option value="gtao_Phone_qt">����</option>
                            <option value="gtao_Phone_lx">����</option>
                            <option value="gtao_Phone_sh">����</option>
                            <option value="gtao_Phone_zy">����</option>
                            <option value="gtao_Phone_ky">����</option>
                            <option value="gtao_Phone_xyy">����Է</option>
                        </select>
                        <select style="width: 15%" id="UserClass">
                            <option value="overTime">��ʱ�û�</option>
                            <option value="notFree">������û�</option>
                        </select>
                        <button class="btn" type="button" onclick="todo()">��ѯ</button>
                    </div>
                    <div class="input-append">
                        �ɱ�ͳ�ƣ�
                        <input placeholder="������" size="8" type="text" id="phoneNum">
                        <button class="btn" type="button" onclick="SearchByNum()">��ʼͳ��</button>
                    </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!-- content ends -->
    <!--zxc-->
<div class="row-fluid">
  <div class="box span12">
    <div class="box-content">
        <div id="NumManageTblBlock" class="dataTables_wrapper" role="grid">
            <table class="table table-striped table-bordered bootstrap-datatable datatable dataTable" id="NumManageTbl" aria-describedby="DataTables_Table_0_info" style="width: 100%">
                <thead>
                <tr role="row">
                    <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-sort="ascending">����</th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">IP</th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">Vlan</th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">����</th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">�۸�</th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">����</th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">����</th>
                </tr>
                </thead>
                <tbody role="alert" aria-live="polite" aria-relevant="all">

                </tbody>
            </table>
        </div>
        </div>
      </div>
    </div>
        <!--zxc-->
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
<%
    String status = request.getParameter("upload");
    if(status!=null){
        if(status.equals("ok")||status=="ok"){
%>
<script type="text/javascript">
    alert("���ݵ���ɹ���");
    window.location.href='NumManage.jsp';
</script>
<%
        }
        else if(status.equals("fail")||status=="fail"){
%>
<script type="text/javascript">
    alert("���ݵ���ʧ�ܣ�");
    window.location.href='NumManage.jsp';
</script>
<%
        }
    }
%>
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
<script src="js/NumManage.js"></script>
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