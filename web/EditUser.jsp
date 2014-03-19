<%@ page import="com.bill.bean.TBL_USERSINFO" %>
<%@ page import="com.bill.pojo.gtao_Phone_User" %>
<%@ page import="com.bill.pojo.gtao_phone_group" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bill.tool.BillSysTool" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="com.bill.dao.BillSysDAO" %>
<%@include file="Validate.jsp"%>
<%@page language="java" pageEncoding="GBK" %>
<%@taglib prefix="s" uri="/struts-tags" %>
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
            <a href="index.jsp">�û�����</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="newApply.jsp">�¸�������</a><span class="divider">/</span>
        </li>
        <li>
            <a href="#">�鿴������ϸ</a>
        </li>
    </ul>
</div>
<%
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    BillSysDAO billSysDAO = ctx.getBean("billService",BillSysDAO.class);
    String longNum = request.getParameter("phoneNum");
    int id = 0;
    String userid = request.getParameter("userid");
    String userIdText="",mobileText="",phoneIpText="",vlanText="",longNumText="",
            shortNumText="",itimeText="",lastUptText="",TacticsText="",statusText="",balanceText="",
            storedText="",MaturityTime="",tbl="",gate="",protocal="",opentime="";
    List li = new ArrayList();
    gtao_Phone_User user = new gtao_Phone_User();
    user.setLongNum(longNum);
    user.setUserid(userid);
    li = billSysDAO.getUserByNum(user);
    Iterator it = li.iterator();
    gtao_Phone_User userTemp = null;
    BillSysTool tool = new BillSysTool();
    while (it.hasNext()){
        userTemp = (gtao_Phone_User)it.next();
        id = userTemp.getId();
        userIdText = userTemp.getUserid();
        mobileText = userTemp.getMobile();
        phoneIpText = userTemp.getPhoneIp();
        vlanText = userTemp.getVlan();
        longNumText = userTemp.getLongNum();
        shortNumText = userTemp.getShortNum();
        itimeText = userTemp.getItime();
        lastUptText = userTemp.getLastUpd();
        TacticsText = userTemp.getTactics();
        statusText = userTemp.getStatus();
        balanceText = tool.NullStrFormatter(userTemp.getBalance());
        storedText = tool.NullStrFormatter(userTemp.getStored());
        MaturityTime = tool.NullStrFormatter(userTemp.getMaturityTime());
        opentime = tool.NullStrFormatter(userTemp.getEmail());
        tbl = userTemp.getTbl();
        gate =userTemp.getGate();
        protocal = userTemp.getProtocal();
    }

    //��ȡ�û���Ϣ
    List userInfoList = billSysDAO.getUserInfoFromRadius(userIdText);
    TBL_USERSINFO userinfo = null;
    Iterator userInfoIter = userInfoList.iterator();
    String address = "",username="",tuserid="",usercertno="",usermac="",userphone="";
    while (userInfoIter.hasNext()){
        userinfo = (TBL_USERSINFO)userInfoIter.next();
        if(userinfo.getSREALNAME()!=null){
            username = new String(userinfo.getSREALNAME().getBytes("ISO-8859-1"),"GBK");
        }
        tuserid = userinfo.getSUSERNAME();
        usercertno = userinfo.getSCERTNO();
        usermac = userinfo.getSPOSTCODE();
        if(userinfo.getSTELE()!=null){
            userphone = userinfo.getSTELE().replaceAll(";","");
        }
    }
    address = new String(billSysDAO.getUserAddress(userIdText).getBytes("ISO-8859-1"),"GBK");
%>
<div class="row-fluid sortable ui-sortable">
<div class="box span12" style="">
<div class="box-header well" data-original-title="">
    <h2><i class="icon-edit"></i>�༭��Ϣ</h2>
</div>
<div class="box-content">
    <form class="form-horizontal">
    <fieldset>
        <legend>��ϸ������Ϣ</legend>

        <table class="table table-bordered table-striped" style="width: 600px">
            <tbody>
            <tr>
                <td><h3>�û�������</h3></td>
                <td><%=username%></td>
                <td><h3>�绰��</h3></td>
                <td><%=userphone%></td>
            </tr>
            <tr>
                <td><h3>�û��˺ţ�</h3></td>
                <td><%=tuserid%></td>
                <td><h3>���֤�ţ�</h3></td>
                <td><%=usercertno%></td>
            </tr>
            <tr>
                <td><h3>סַ��</h3></td>
                <td><%=address%></td>
                <td><h3>MAC��ַ��</h3></td>
                <td><%=usermac%></td>
            </tr>
            </tbody></table>

        <div class="control-group">
            <label class="control-label" for="phoneNum">���룺</label>
            <div class="controls">
                <input class="input-xlarge focused" id="phoneNum" type="text" value="<%=longNumText%>">
                <input type="hidden" id="finalNum" value="<%=longNumText%>">
                <input type="hidden" id="id" value="<%=id%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="userId">�û��˺ţ�</label>
            <div class="controls">
                <input class="input-xlarge focused" id="userId" type="text" value="<%=userIdText%>">
                <input type="hidden" id="finalUser" value="<%=userIdText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="shortNum">�̺ţ�</label>
            <div class="controls">
                <input class="input-xlarge focused" id="shortNum" type="text" value="<%=shortNumText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="mobile">�ֻ����룺</label>
            <div class="controls">
                <input class="input-xlarge focused" id="mobile" type="text" value="<%=mobileText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="time">��ͨʱ�䣺</label>
            <div class="controls">
                <input class="input-xlarge focused" id="time" type="text" value="<%=itimeText%>" disabled>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="ipadd">IP��</label>
            <div class="controls">
                <input class="input-xlarge focused" id="ipadd" type="text" value="<%=phoneIpText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="vlan">Vlan��</label>
            <div class="controls">
                    <input class="input-xlarge focused" id="vlan" type="text" value="<%=vlanText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="gate">���أ�</label>
            <div class="controls">
                <input class="input-xlarge focused" id="gate" type="text" value="<%=gate%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="protocal">Э�飺</label>
            <div class="controls">
                <input class="input-xlarge focused" id="protocal" type="text" disabled value="<%=protocal%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="tactics">�û��飺</label>
            <div class="controls">
                <%
                    List groupList = new ArrayList();
                    groupList = billSysDAO.getAllGroup();
                    gtao_phone_group group = new gtao_phone_group();
                    Iterator groupIterator = groupList.iterator();
                %>
                <select id="tactics">
                    <%
                        while (groupIterator.hasNext()){
                            group = (gtao_phone_group)groupIterator.next();
                            if(group.getUserGroup().equals(TacticsText)||TacticsText==group.getGroupDetail()){
                    %>
                    <option value="<%=group.getGroupDetail()%>" selected><%=group.getGroupDetail()%></option>
                    <%
                    } else {
                    %>
                    <option value="<%=group.getGroupDetail()%>"><%=group.getGroupDetail()%></option>
                    <%
                            } }
                    %>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="status">״̬��</label>
            <div class="controls">
                <select id="status">
                    <option value="<%=statusText%>" selected hidden><%=statusText%></option>
                    <option value="�ѿ�ͨ">�ѿ�ͨ</option>
                    <option value="δ��ͨ">δ��ͨ</option>
                    <option value="ͣ��">ͣ��</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="balance">����ʱ�䣺</label>
            <div class="controls">
                <input class="input-xlarge focused" id="balance" type="text" value="<%=balanceText%>" disabled>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="stored">Ԥ�棺</label>
            <div class="controls">
                <input class="input-xlarge focused" id="stored" type="text" value="<%=storedText%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="opentime">��ͨʱ�䣺</label>
            <div class="controls">
                <input class="input-xlarge focused" id="opentime" type="text" value="<%=opentime%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="mTime">����ʱ�䣺</label>
            <div class="controls">
                <input class="input-xlarge focused" id="mTime" type="text" value="<%=MaturityTime%>">
                <input type="hidden" value="<%=tbl%>" id="tbl">
            </div>
        </div>
        <legend>ҵ������</legend>
        <div id="option">
            <p>�л�ҵ��
                <button type="button" class="btn" onclick="openLocal()" id="btn_openLocal" data-rel="popover" data-content="��ͨ�ú����л�Ȩ�ޣ������û���������쳣ʱʹ�ã�" title="��ʾ">��ͨ�л�ҵ��</button>
                <button type="button" class="btn  btn-danger" onclick="offLocal()" id="btn_closeLocal" data-rel="popover" data-content="�رոú����л�Ȩ�ޣ������û���������쳣ʱʹ�ã�" title="��ʾ">�ر��л�ҵ��</button>
            </p>
            <p>��;ҵ��
                <button type="button" class="btn" onclick="openLong()" id="btn_openLong" data-rel="popover" data-content="��ͨ�ú��볤;Ȩ�ޣ������û���������쳣ʱʹ�ã�" title="��ʾ">��ͨ��;ҵ��</button>
                <button type="button" class="btn  btn-danger" onclick="offLong()" id="btn_closeLong" data-rel="popover" data-content="�رոú��볤;Ȩ�ޣ������û���������쳣ʱʹ�ã�" title="��ʾ">�رճ�;ҵ��</button>
            </p>
        </div>
        <div class="form-actions">
            <button type="button" class="btn btn-primary" onclick="editUser()" id="btn_save">�����޸�</button>
            <button type="button" class="btn btn-danger" onclick="initUserInfo()" id="btn_del">ɾ���û�</button>
            <button type="button" class="btn btn-success" onclick="window.location.href='ViewUser.jsp'" id="btn_back">
                <i class="icon-chevron-left icon-white"></i>����</button>
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
        $("#mTime").datepicker();
        $("#opentime").datepicker();
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
<!-- chart libraries end -->
<script src="js/EditUser.js"></script>
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
