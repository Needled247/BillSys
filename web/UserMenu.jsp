<%@page language="java" pageEncoding="GBK" %>
<div class="span2 main-menu-span">
    <div class="well nav-collapse sidebar-nav" id="menu">
        <ul class="nav nav-tabs nav-stacked main-menu">
            <li class="nav-header hidden-tablet">我的账户</li>
            <li><a class="ajax-link" href="UserCallHistory.jsp"><i class="icon-comment"></i><span class="hidden-tablet"> 查询通话记录</span></a></li>
            <li class="nav-header hidden-tablet">设置</li>
            <li><a class="ajax-link" href="ChangePass.jsp"><i class="icon-repeat"></i><span class="hidden-tablet"> 修改密码</span></a></li>
            <li><a class="ajax-link" href="logout.jsp"><i class="icon-off"></i><span class="hidden-tablet"> 注销登录</span></a></li>
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