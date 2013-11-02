<%@page language="java" pageEncoding="GBK" %>
<div class="span2 main-menu-span">
    <div class="well nav-collapse sidebar-nav" id="menu">
        <ul class="nav nav-tabs nav-stacked main-menu">
            <li class="nav-header hidden-tablet">用户管理</li>
            <li><a class="ajax-link" href="createAccount.jsp"><i class="icon-home"></i><span class="hidden-tablet"> 开户</span></a></li>
            <li><a class="ajax-link" href="NumManage.jsp"><i class="icon-bookmark"></i><span class="hidden-tablet"> 号码管理</span></a></li>
            <li><a class="ajax-link" href="newApply.jsp"><i class="icon-search"></i><span class="hidden-tablet"> 查看新申请</span></a></li>
            <li><a class="ajax-link" href="newSaleApply.jsp"><i class="icon-shopping-cart"></i><span class="hidden-tablet"> 查看付费申请</span></a></li>
            <li><a class="ajax-link" href="ViewUser.jsp"><i class="icon-eye-open"></i><span class="hidden-tablet"> 查询用户</span></a></li>
            <li><a class="ajax-link" href="CallHistory.jsp"><i class="icon-comment"></i><span class="hidden-tablet"> 查询通话记录</span></a></li>
            <!--li><a class="ajax-link" href="BusinessChange.jsp"><i class="icon-edit"></i><span class="hidden-tablet"> 业务变更</span></a></li-->
            <!--li><a class="ajax-link" href="Monthly.jsp"><i class="icon-font"></i><span class="hidden-tablet"> 月结工作</span></a></li-->
            <li class="nav-header hidden-tablet">设置</li>
            <li><a class="ajax-link" href="UserGroup.jsp"><i class="icon-align-justify"></i><span class="hidden-tablet">计费策略</span></a></li>
            <li><a class="ajax-link" href="#"><i class="icon-repeat"></i><span class="hidden-tablet">数据备份</span></a></li>
            <li><a class="ajax-link" href="rePrintPre.jsp"><i class="icon-th"></i><span class="hidden-tablet">补打派工单</span></a></li>
            <li class="nav-header hidden-tablet">系统</li>
            <li><a class="ajax-link" href="#"><i class="icon-folder-open"></i><span class="hidden-tablet"> 管理员管理</span></a></li>
            <li><a class="ajax-link" href="ChangePass.jsp"><i class="icon-folder-open"></i><span class="hidden-tablet"> 修改密码</span></a></li>
            <li><a class="ajax-link" href="logout.jsp"><i class="icon-folder-open"></i><span class="hidden-tablet"> 注销登录</span></a></li>
        </ul>
    </div><!--/.well -->
</div>

<!--======================js=========================-->
<script src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
    $('#menu').find('a').each(function(){
        if (this.href == document.location.href || document.location.href.search(this.href) >= 0) {
            $(this).addClass('active'); // this.className = 'active';
        }
    });
</script>