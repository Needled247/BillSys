<%@page language="java" pageEncoding="GBK" %>
<div class="span2 main-menu-span">
    <div class="well nav-collapse sidebar-nav" id="menu">
        <ul class="nav nav-tabs nav-stacked main-menu">
            <li class="nav-header hidden-tablet">�û�����</li>
            <li><a class="ajax-link" href="createAccount.jsp"><i class="icon-home"></i><span class="hidden-tablet"> ����</span></a></li>
            <li><a class="ajax-link" href="NumManage.jsp"><i class="icon-bookmark"></i><span class="hidden-tablet"> �������</span></a></li>
            <li><a class="ajax-link" href="newApply.jsp"><i class="icon-search"></i><span class="hidden-tablet"> �鿴������</span></a></li>
            <li><a class="ajax-link" href="newSaleApply.jsp"><i class="icon-shopping-cart"></i><span class="hidden-tablet"> �鿴��������</span></a></li>
            <li><a class="ajax-link" href="ViewUser.jsp"><i class="icon-eye-open"></i><span class="hidden-tablet"> ��ѯ�û�</span></a></li>
            <li><a class="ajax-link" href="CallHistory.jsp"><i class="icon-comment"></i><span class="hidden-tablet"> ��ѯͨ����¼</span></a></li>
            <!--li><a class="ajax-link" href="BusinessChange.jsp"><i class="icon-edit"></i><span class="hidden-tablet"> ҵ����</span></a></li-->
            <!--li><a class="ajax-link" href="Monthly.jsp"><i class="icon-font"></i><span class="hidden-tablet"> �½Ṥ��</span></a></li-->
            <li class="nav-header hidden-tablet">����</li>
            <li><a class="ajax-link" href="UserGroup.jsp"><i class="icon-align-justify"></i><span class="hidden-tablet">�ƷѲ���</span></a></li>
            <li><a class="ajax-link" href="#"><i class="icon-repeat"></i><span class="hidden-tablet">���ݱ���</span></a></li>
            <li><a class="ajax-link" href="rePrintPre.jsp"><i class="icon-th"></i><span class="hidden-tablet">�����ɹ���</span></a></li>
            <li class="nav-header hidden-tablet">ϵͳ</li>
            <li><a class="ajax-link" href="#"><i class="icon-folder-open"></i><span class="hidden-tablet"> ����Ա����</span></a></li>
            <li><a class="ajax-link" href="ChangePass.jsp"><i class="icon-folder-open"></i><span class="hidden-tablet"> �޸�����</span></a></li>
            <li><a class="ajax-link" href="logout.jsp"><i class="icon-folder-open"></i><span class="hidden-tablet"> ע����¼</span></a></li>
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