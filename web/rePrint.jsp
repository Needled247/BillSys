<%@ page import="com.bill.dao.BillSysDAOImpl" %>
<%@ page import="com.bill.pojo.gtao_phone_group" %>
<%@ page import="com.bill.pojo.gtao_phone_view" %>
<%@ page import="com.bill.tool.BillSysTool" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@include file="Validate.jsp"%>
<%@ page import="com.bill.bean.TBL_USERSINFO" %>
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
            <a href="newApply.jsp">新申请</a><span class="divider">/</span>
        </li>
        <li>
            <a href="#">查看申请详细</a>
        </li>
    </ul>
</div>
<%
    String longNum = request.getParameter("longNum");
    String userId = request.getParameter("account");
    gtao_phone_view view = new gtao_phone_view();
    view.setLongNum(longNum);
    view.setUserId(userId);
    List li = new ArrayList();
    li = new BillSysDAOImpl().getApplyDetail(view);
    Iterator it = li.iterator();
    String longNumText="",userIdText="",mobileText="",shortNumText="",AreaText="",IpText="",tbl="",
    installer="",installtime="",vlan="",gate="";
    String TimeText = null;
    gtao_phone_view viewText = null;
    BillSysTool tool = new BillSysTool();
    String newTime = "";
    while (it.hasNext()){
        viewText = (gtao_phone_view)it.next();
        longNumText = tool.NullStrFormatter(viewText.getLongNum());
        userIdText = tool.NullStrFormatter(viewText.getUserId());
        mobileText = tool.NullStrFormatter(viewText.getMobile());
        shortNumText = tool.NullStrFormatter(viewText.getShortNum());
        AreaText = tool.NullStrFormatter(viewText.getIpAdd());
        TimeText = viewText.getUpTime().toString();
        newTime = TimeText.substring(0,TimeText.indexOf(":",1)-3);
        IpText = tool.NullStrFormatter(viewText.getIp());
        vlan = tool.NullStrFormatter(viewText.getVlan());
        installer = tool.NullStrFormatter(viewText.getInstaller());
        installtime = tool.NullStrFormatter(viewText.getInstallTime());
        tbl = tool.NullStrFormatter(viewText.getTbl());
        gate = viewText.getGate();
    }
    //获取用户信息
    List userInfoList = new BillSysDAOImpl().getUserInfoFromRadius(userId);
    TBL_USERSINFO userinfo = null;
    Iterator userInfoIter = userInfoList.iterator();
    String address = "",username="",userid="",usercertno="",usermac="",userphone="";
    while (userInfoIter.hasNext()){
        userinfo = (TBL_USERSINFO)userInfoIter.next();
        if(userinfo.getSADDRESS()!=null){
            address = new String(userinfo.getSADDRESS().getBytes("ISO-8859-1"),"GBK");
        }
        if(userinfo.getSREALNAME()!=null){
            username = new String(userinfo.getSREALNAME().getBytes("ISO-8859-1"),"GBK");
        }
        userid = userinfo.getSUSERNAME();
        usercertno = userinfo.getSCERTNO();
        usermac = userinfo.getSPOSTCODE();
        if(userinfo.getSTELE()!=null){
            userphone = userinfo.getSTELE().replaceAll(";","");
        }
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
        <legend>详细申请信息</legend>
        <table class="table table-bordered table-striped" style="width: 600px">
            <tbody>
            <tr>
                <td><h3>用户姓名：</h3></td>
                <td><%=username%></td>
                <td><h3>电话：</h3></td>
                <td><%=userphone%></td>
            </tr>
            <tr>
                <td><h3>用户账号：</h3></td>
                <td><%=userid%></td>
                <td><h3>身份证号：</h3></td>
                <td><%=usercertno%></td>
            </tr>
            <tr>
                <td><h3>住址：</h3></td>
                <td><%=address%></td>
                <td><h3>MAC地址：</h3></td>
                <td><%=usermac%></td>
            </tr>
            </tbody></table>
        <div class="control-group">
            <label class="control-label" for="phoneNum">号码：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="phoneNum" type="text" value="<%=longNumText%>">
                <input type="hidden" id="finalNum" value="<%=longNumText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="userId">用户账号：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="userId" type="text" value="<%=userIdText%>">
                <input type="hidden" id="finalUser" value="<%=userIdText%>">
            </div>
        </div>
        <input type="hidden" id="ipadd" value="<%=AreaText%>">
        <div class="control-group">
            <label class="control-label" for="shortNum">短号：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="shortNum" type="text" value="<%=shortNumText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="mobile">手机号码：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="mobile" type="text" value="<%=mobileText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="time">申请时间：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="time" type="text" value="<%=newTime%>" disabled>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="phoneIp">绑定IP：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="phoneIp" type="text" value="<%=IpText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="vlan">绑定Vlan：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="vlan" type="text" value="<%=vlan%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="gate">网关：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="gate" type="text" value="<%=gate%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="protocal">协议：</label>
            <div class="controls">
                <select id="protocal">
                    <option value="0" selected>--请选择--</option>
                    <option value="sip">SIP</option>
                    <option value="248">248</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="installer">安装负责人：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="installer" type="text" value="<%=installer%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="installTime">安装时间：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="installTime" type="text" value="<%=installtime%>">
                <input type="hidden" id="tbl" value="<%=tbl%>">
            </div>
        </div>
        <%
            List groupList = new ArrayList();
            groupList = new BillSysDAOImpl().getAllGroup();
            Iterator groupIterator = groupList.iterator();
            gtao_phone_group group = new gtao_phone_group();
        %>
        <div class="control-group">
            <label class="control-label" for="userGroup">用户组：</label>
            <div class="controls">
                <select id="userGroup">
                    <%
                        while (groupIterator.hasNext()){
                            group = (gtao_phone_group)groupIterator.next();
                    %>
                    <option value="<%=group.getGroupDetail()%>"><%=group.getGroupDetail()%></option>
                    <%
                        }
                    %>
                </select>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="Balance">赠送时间：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="Balance" type="text" placeholder="请填写数字，不赠送请为空">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="opentime">开通时间：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="opentime" type="text">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="endtime">到期时间：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="endtime" type="text">
            </div>
        </div>
        <div class="alert alert-error" id="alert_block" hidden>
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>注意！</strong> <div id="alert"></div>
        </div>

        <div id="toPrint" hidden>
            <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" >
                <tbody>
                <tr style="text-align: center"><h1>语音业务开通派工单</h1></tr>
                <tr>
                    <td width="25%">用户姓名：</td>
                    <td width="25%"><div id="printName"><%=username%></div></td>
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
                    <td width="25%"><div id="printAddress"><%=address%></div></td>
                    <td width="25%">MAC地址：</td>
                    <td width="25%"><div id="printMac"><%=usermac%></div></td>
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
            <button type="button" id="btn_print" class="btn" onclick="PagePrint()">打印</button>
            <button type="button" class="btn btn-success" onclick="window.location.href='rePrintPre.jsp'">
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
        $("#opentime").datepicker();
        $("#endtime").datepicker();
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
<script src="js/editApplyInfo.js"></script>
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
