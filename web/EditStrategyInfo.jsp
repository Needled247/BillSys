<%@ page import="com.bill.dao.BillSysDAOImpl" %>
<%@ page import="com.bill.pojo.gtao_phone_celue" %>
<%@ page import="com.bill.pojo.gtao_phone_profile" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
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
            <a href="#">设置</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="StrategyManage.jsp">计费策略管理</a><span class="divider">/</span>
        </li>
        <li>
            <a href="#">编辑计费策略</a>
        </li>
    </ul>
</div>
<%
    String prefix = request.getParameter("prefix");
    String userGroup = request.getParameter("group");
    int id = 0;
    String sprofile="",sprefix="",ratestime1="",ratestime2="",ratestime3="",rates1="",rates2="",rates3="",otherfee="",
            specialtimebegin1="",specialtimeend1="",specialtimebegin2="",specialtimeend2="",specialtimebegin3="",
            specialtimeend3="",specialtimefee1="",specialtimefee2="",specialtimefee3="";
    List li = new ArrayList();
    if(prefix!=null){
        li = new BillSysDAOImpl().getCelueByPrefix(prefix,userGroup);
    }
    Iterator it = li.iterator();
    gtao_phone_celue celue = new gtao_phone_celue();
    while (it.hasNext()){
        celue = (gtao_phone_celue)it.next();
        id = celue.getID();
        sprofile = celue.getSPROFILE();
        sprefix = celue.getSPREFIX();
        ratestime1 = celue.getRATESTIME1();
        ratestime2 = celue.getRATESTIME2();
        ratestime3 = celue.getRATESTIME3();
        rates1 = celue.getRATES1();
        rates2 = celue.getRATES2();
        rates3 = celue.getRATES3();
        otherfee = celue.getOTHERFEE();
        specialtimebegin1 = celue.getSPECIALTIMEBEGIN1();
        specialtimebegin2 = celue.getSPECIALTIMEBEGIN2();
        specialtimebegin3 = celue.getSPECIALTIMEBEGIN3();
        specialtimeend1 = celue.getSPECIALTIMEEND1();
        specialtimeend2 = celue.getSPECIALTIMEEND2();
        specialtimeend3 = celue.getSPECIALTIMEEND3();
        specialtimefee1 = celue.getSPECIALTIMEFEE1();
        specialtimefee2 = celue.getSPECIALTIMEFEE2();
        specialtimefee3 = celue.getSPECIALTIMEFEE3();
    }
    List list = new BillSysDAOImpl().getAllFeeProfile();
%>
<div class="row-fluid sortable ui-sortable">
<div class="box span12" style="">
<div class="box-header well" data-original-title="">
    <h2><i class="icon-edit"></i>编辑信息</h2>
</div>
<div class="box-content">
    <form class="form-horizontal">
    <fieldset>
        <legend>详细策略信息</legend>
        <div class="control-group">
            <label class="control-label" for="prefix">呼叫前缀：</label>
            <div class="controls">
                <input type="hidden" id="id" value="<%=id%>">
                <input class="input-xlarge focused" id="prefix" type="text" value="<%=sprefix%>">
                <input type="hidden" id="finalPrefix" value="<%=sprefix%>">
                <input type="hidden" id="group" value="<%=userGroup%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="profile">呼叫属性：</label>
            <div class="controls">
                <select id="profile">
                    <%
                        Iterator it2 = list.iterator();
                        gtao_phone_profile gprofile = new gtao_phone_profile();
                        while(it2.hasNext()){
                            gprofile = (gtao_phone_profile)it2.next();
                            String profileText = gprofile.getFEEPROFILE();
                            if(profileText.equals(sprofile)||profileText==sprofile){
                    %>
                    <option value="<%=profileText%>" selected><%=profileText%></option>
                    <%
                            }
                        else {
                    %>
                    <option value="<%=profileText%>"><%=profileText%></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="ratestime1">费率时长1（秒）：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="ratestime1" type="text" value="<%=ratestime1%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="rates1">费率1（元）：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="rates1" type="text" value="<%=rates1%>">
            </div>
        </div>
        <hr>
        <div class="control-group">
            <label class="control-label" for="ratestime2">费率时长2（秒）：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="ratestime2" type="text" value="<%=ratestime2%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="rates2">费率2（元）：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="rates2" type="text" value="<%=rates2%>">
            </div>
        </div>
        <hr>
        <div class="control-group">
            <label class="control-label" for="ratestime3">费率时长3（秒）：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="ratestime3" type="text" value="<%=ratestime3%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="rates3">费率3（元）：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="rates3" type="text" value="<%=rates3%>">
            </div>
        </div>
        <hr>
        <div class="control-group">
            <label class="control-label" for="otherfee">附加收费：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="otherfee" type="text" value="<%=otherfee%>">
            </div>
        </div>
        <legend>特殊时段计费</legend>
        <div class="control-group">
            <label class="control-label" for="specialtime1">时段1：</label>
            <div class="controls">
                <select id="specialtime1" style="width: 60px">
                    <option value="<%=specialtimebegin1%>" hidden><%=specialtimebegin1%></option>
                    <option value="">空</option>
                    <option value="0">00</option>
                    <option value="1">01</option>
                    <option value="2">02</option>
                    <option value="3">03</option>
                    <option value="4">04</option>
                    <option value="5">05</option>
                    <option value="6">06</option>
                    <option value="7">07</option>
                    <option value="8">08</option>
                    <option value="9">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                </select>&nbsp;至&nbsp;
                <select id="specialtimeend1" style="width: 60px">
                    <option value="<%=specialtimeend1%>" hidden><%=specialtimeend1%></option>
                    <option value="">空</option>
                    <option value="0">00</option>
                    <option value="1">01</option>
                    <option value="2">02</option>
                    <option value="3">03</option>
                    <option value="4">04</option>
                    <option value="5">05</option>
                    <option value="6">06</option>
                    <option value="7">07</option>
                    <option value="8">08</option>
                    <option value="9">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                    <option value="23">24</option>
                </select>点
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="specialfee1">费率1：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="specialfee1" type="text" value="<%=specialtimefee1%>">
            </div>
        </div>
        <hr>
        <div class="control-group">
            <label class="control-label" for="specialtime2">时段2：</label>
            <div class="controls">
                <select id="specialtime2" style="width: 60px">
                    <option value="<%=specialtimebegin2%>" hidden><%=specialtimebegin2%></option>
                    <option value="">空</option>
                    <option value="0">00</option>
                    <option value="1">01</option>
                    <option value="2">02</option>
                    <option value="3">03</option>
                    <option value="4">04</option>
                    <option value="5">05</option>
                    <option value="6">06</option>
                    <option value="7">07</option>
                    <option value="8">08</option>
                    <option value="9">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                </select>&nbsp;至&nbsp;
                <select id="specialtimeend2" style="width: 60px">
                    <option value="<%=specialtimeend2%>" hidden><%=specialtimeend2%></option>
                    <option value="">空</option>
                    <option value="0">00</option>
                    <option value="1">01</option>
                    <option value="2">02</option>
                    <option value="3">03</option>
                    <option value="4">04</option>
                    <option value="5">05</option>
                    <option value="6">06</option>
                    <option value="7">07</option>
                    <option value="8">08</option>
                    <option value="9">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                    <option value="23">24</option>
                </select>点
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="specialfee2">费率2：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="specialfee2" type="text" value="<%=specialtimefee2%>">
            </div>
        </div>
        <hr>
        <div class="control-group">
            <label class="control-label" for="specialtime3">时段3：</label>
            <div class="controls">
                <select id="specialtime3" style="width: 60px">
                    <option value="<%=specialtimebegin3%>" hidden><%=specialtimebegin3%></option>
                    <option value="">空</option>
                    <option value="0">00</option>
                    <option value="1">01</option>
                    <option value="2">02</option>
                    <option value="3">03</option>
                    <option value="4">04</option>
                    <option value="5">05</option>
                    <option value="6">06</option>
                    <option value="7">07</option>
                    <option value="8">08</option>
                    <option value="9">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                </select>&nbsp;至&nbsp;
                <select id="specialtimeend3" style="width: 60px">
                    <option value="<%=specialtimeend3%>" hidden><%=specialtimeend3%></option>
                    <option value="">空</option>
                    <option value="0">00</option>
                    <option value="1">01</option>
                    <option value="2">02</option>
                    <option value="3">03</option>
                    <option value="4">04</option>
                    <option value="5">05</option>
                    <option value="6">06</option>
                    <option value="7">07</option>
                    <option value="8">08</option>
                    <option value="9">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                    <option value="23">24</option>
                </select>点
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="specialfee3">费率3：</label>
            <div class="controls">
                <input class="input-xlarge focused" id="specialfee3" type="text" value="<%=specialtimefee3%>">
            </div>
        </div>
        <div class="alert alert-error" id="alert_block" hidden>
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>注意！</strong> <div id="alert"></div>
        </div>
        <div class="form-actions">
            <button type="button" class="btn btn-primary" onclick="SaveStrategyInfo()">保存修改</button>
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
<script src="js/EditStrategyInfo.js"></script>
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
