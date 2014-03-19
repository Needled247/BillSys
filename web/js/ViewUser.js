function delUser(){
    var id = $("#id").val();
    var longNum = $("#longNum").val();
    var userid = $("#userid").val();
    var tbl = $("#tbl").val();
    var type='delete';
    if(confirm("确定要删除这个用户吗？"))
    {
        $.post("EditUser",{id:id,tbl:tbl,phoneNum:longNum,type:type,userId:userid},function(data){
            if(data=='success'){
                alert('删除成功！号码已被初始化');
                location.reload();
            }
            else{
                alert('删除失败！请联系管理员');
            }
        })
    }
    else
    {
        return;
    }
}


$(document).ready( function(){
    $('#viewUser').on('click', 'td .btn-success', function() {
        var userid = $(this).closest('tr').find('td:nth-child(2)').html();
        var longNum = $(this).closest('tr').find('td:nth-child(3)').html();
        window.location.href='EditUser.jsp?phoneNum='+longNum+'&userid='+userid;
    });

    $('#viewUser').on('click', 'td .btn-warning', function() {
        var userid = $(this).closest('tr').find('td:nth-child(2)').html();
        var longNum = $(this).closest('tr').find('td:nth-child(3)').html();
        window.location.href='UserCalledPage.jsp?userid='+userid+'&longNum='+longNum;
    });

    $("#viewUser").dataTable({
        "sPaginationType" : "bootstrap",
        "iDisplayLength" : 10,
        "sAjaxDataProp" : "aaData",
        "bDestroy" : true,
        "bSort": false,
        "bLengthChange": false,
        "bStateSave": false,
        "bProcessing" : true,
        "bServerSide" : true,
        "sAjaxSource" : "ViewUserAction",
        "aoColumns" : [
            {"mData" : "ID"},
            {"mData" : "用户账号"},
            {"mData" : "号码"},
            {"mData" : "用户组"},
            {"mData" : "开户时间"},
            {"mData" : "所属营业厅"},
            {
                "mData": null,
                "sClass": "center",
                "sDefaultContent": '<a class="btn btn-success" href="#"><i class="icon-edit icon-green"></i>查看/编辑</a>&nbsp;<a class="btn btn-warning" href="#"><i class="icon-search icon-orange"></i>通话明细</a>'
            }
        ],
        "oLanguage" : {
            "sProcessing" : "正在加载中......",
            "sLengthMenu" : "每页显示 _MENU_ 条记录",
            "sZeroRecords" : "没有数据！",
            "sEmptyTable" : "表中无数据存在！",
            "sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
            "sInfoEmpty" : "显示0到0条记录",
            "sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
            "sSearch" : "搜索",
            "oPaginate" : {
                "sFirst" : "首页",
                "sPrevious" : "上一页",
                "sNext" : "下一页",
                "sLast" : "末页"
            }
        }
    });
});