$(function(){
    var type='search';
    $.post("UserGroup",{editType:type},function(data){

        $('#UserGroup').on('click', 'td .btn-warning', function() {
            var param = $(this).closest('tr').find('td:nth-child(2)').html();
            window.location.href='StrategyManage.jsp?group='+param;
        });

        $('#UserGroup').on('click', 'td .btn-danger', function() {
            var param = $(this).closest('tr').find('td:nth-child(2)').html();
            if(confirm("确定要删除这条策略记录？")){
                window.location.href='UserGroup.action?editType=del&userGroup='+param;
            }
            else{
                return;
            }

        });

        $("#UserGroup").dataTable({
            "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
            "bPaginate": true,
            "sProcessing" : "正在读取数据，请稍后...",
            "aaData" :data,
            "aoColumns": [
                { "mData": "ID" },
                { "mData": "用户组编码" },
                { "mData": "用户组" },
                {
                    "mData": null,
                    "sClass": "center",
                    //"sDefaultContent": '<a href="123.jsp" class="editor_edit">编辑</a>'
                    "sDefaultContent": '<a class="btn btn-warning" href="#"><i class="icon-edit icon-white"></i>编辑</a>&nbsp;&nbsp;<a class="btn btn-danger" href="#"><i class="icon-edit icon-white"></i>删除</a>'
                }
            ],
            "bDestroy" : true,
            "sPaginationType": "bootstrap",
            "bAutoWith": true,
            "bSort": true,
            "bLengthChange": false,
            "bStateSave": true,
            "oLanguage": {
                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
                "sSearch": "搜索",
                "sZeroRecords": "对不起，查询不到相关数据！",
                "sEmptyTable": "表中无数据存在！",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "末页"
                }
            }
        } );
    })
});

function newUserGroup(){
    var userGroup = $("#userGroup").val();
    var groupDetail = $("#groupDetail").val();
    var editType = 'new';
    $.post("UserGroup",{editType:editType,userGroup:userGroup,groupDetail:groupDetail},function(data){
        if(data=='success'){
            alert("添加成功！");
            window.location.href="UserGroup.jsp";
        }
        else{
            alert("添加失败！请联系管理员");
        }
    })
}