<%@ page import="com.bill.dao.BillSysDAOImpl" %>
<%@ page import="com.bill.pojo.gtao_Phone_User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@include file="UserValidate.jsp"%>
<%@page language="java" pageEncoding="GBK" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="GBK">
    <title>�����Ʒѹ���ϵͳ</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- ��ʽ�� -->
    <link id="bs-css" href="css/bootstrap-cerulean.css" rel="stylesheet">
    <style type="text/css">
        body {padding-bottom: 40px;}
        .sidebar-nav {padding: 9px 0;}
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
    <!-- HTML5����IE -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">
</head>

<body>
<!-- ������ʾ -->
<%@include file="UserHead.jsp"%>
<div class="container-fluid">
    <div class="row-fluid">
        <!--���˵�-->
        <%@include file="UserMenu.jsp"%>
        <div id="content" class="span10">
            <!-- ���������� -->
            <div>
                <ul class="breadcrumb">
                    <li>
                        <a href="#">�ҵ��˻�</a> <span class="divider">/</span>
                    </li>
                    <li>�鿴ͨ����¼</li>
                </ul>
            </div>
            <!--��ȡ��Ϣ-->
            <%
                List li = new ArrayList();
                gtao_Phone_User user = null;
                String username = session.getAttribute("uid").toString();
                li = new BillSysDAOImpl().getUserById(username);
                Iterator it = li.iterator();
                String userid="",mobile="",longNum="",shortNum="",userGroup="",balance="";
                while (it.hasNext()){
                    user = (gtao_Phone_User)it.next();
                    userid = user.getUserid();
                    mobile = user.getMobile();
                    longNum = user.getLongNum();
                    shortNum = user.getShortNum();
                    userGroup = user.getTactics();
                    balance = user.getBalance();
                }
            %>
            <!--hidden����-->
            <input type="hidden" id="userid" value="<%=userid%>">
            <input type="hidden" id="longNum" value="<%=longNum%>">
            <input type="hidden" id="shortNum" value="<%=shortNum%>">
            <input type="hidden" id="userGroup" value="<%=userGroup%>">
            <input type="hidden" id="balance" value="<%=balance%>">
            <!--�û���Ϣ����-->
            <div class="row-fluid">
                <div class="box span12">
                    <div class="box-header well">
                        <h2><i class="icon-info-sign"></i> �û���Ϣ�������ťչ����</h2>
                        <div class="box-icon">
                            <a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-down"></i></a>
                        </div>
                    </div>
                    <div class="box-content" style="display: none">
                        <div class="control-group">
                            <div class="controls">
                                <div class="input-append">
                                    <table class="table table-bordered table-striped" style="width: 60%">
                                        <tbody>
                                        <tr>
                                            <td><h3>�û��˺ţ�</h3></td>
                                            <td><%=userid%></td>
                                            <td><h3>�ֻ���</h3></td>
                                            <td><%=mobile%></td>
                                        </tr>
                                        <tr>
                                            <td><h3>���ţ�</h3></td>
                                            <td><%=longNum%></td>
                                            <td><h3>�̺ţ�</h3></td>
                                            <td><%=shortNum%></td>
                                        </tr>
                                        <tr>
                                            <td><h3>�û��飺</h3></td>
                                            <td><%=userGroup%></td>
                                            <td><h3>���ʱ����</h3></td>
                                            <td><%=balance%></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
            <!--��ѯ����-->
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
                                    <h3>���²�ѯ��</h3>
                                    ��ݣ�
                                    <select id="year" style="width: 100px">
                                        <option value="2012">2012��</option>
                                        <option value="2013">2013��</option>
                                    </select>&nbsp;
                                    �·ݣ�
                                    <select id="month" style="width: 80px">
                                        <option value="01">1��</option>
                                        <option value="02">2��</option>
                                        <option value="03">3��</option>
                                        <option value="04">4��</option>
                                        <option value="05">5��</option>
                                        <option value="06">6��</option>
                                        <option value="07">7��</option>
                                        <option value="08">8��</option>
                                        <option value="09">9��</option>
                                        <option value="10">10��</option>
                                        <option value="11">11��</option>
                                        <option value="12">12��</option>
                                    </select>&nbsp;
                                    <button class="btn" type="button" onclick="getUserCalledByMonth()">��ѯ</button>
                                    <button type="button" class="btn btn-success" onclick="getThisMonthCalled()"  data-rel="popover" data-content="�鿴�����µ�ͨ����¼��" title="��ʾ">����</button>
                                    <button type="button" class="btn btn-warning" onclick="getAllCalled()"  data-rel="popover" data-content="�鿴��ȫ����ͨ����¼��" title="��ʾ">ȫ��</button>
                                </div>
                                <hr>
                                <div class="input-append">
                                    <h3>ʱ��β�ѯ��</h3>
                                    ��ʼʱ�䣺<input type="text" class="input-small"  id="datefrom" placeholder="�������...">
                                    ����ʱ�䣺<input type="text" class="input-small"  id="dateto" placeholder="�������...">
                                    <button class="btn" type="button" onclick="getUserCalledByTime()">��ѯ</button>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>

            <!--��ѯ���-->
            <div class="row-fluid">
                <div class="box span12">
                    <div class="box-content">
                        <div id="UserCalledTblBlock" class="dataTables_wrapper" role="grid">
                            <table class="table table-striped table-bordered bootstrap-datatable datatable dataTable" id="UserCalledTbl" aria-describedby="DataTables_Table_0_info" style="width: 100%">
                                <thead>
                                <tr role="row">
                                    <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" aria-sort="ascending">����</th>
                                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">����</th>
                                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">����</th>
                                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">��ʼʱ��</th>
                                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">����ʱ��</th>
                                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1">ʱ��</th>
                                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1" colspan="1" style="width: 15%">����</th>
                                </tr>
                                </thead>
                                <tbody role="alert" aria-live="polite" aria-relevant="all">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr>
    <!--ҳ��ײ�-->
    <footer>
        <p class="pull-left">&copy; <a href="http://www.gtao.com" target="_blank">GuanTao High Tech co.,ltd</a> 2013</p>
    </footer>
</div>
<!-- JS�ļ�
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
<!--ajax�ļ�-->
<script src="js/UserCalledPage.js"></script>
<!-- library for creating tabs -->
<script src="js/date_zhcn.js"></script>
<script type="text/javascript">
    $(function(){
        $("#datefrom").datepicker();
        $("#dateto").datepicker();
    })
</script>
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
