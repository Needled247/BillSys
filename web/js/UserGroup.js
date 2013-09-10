$(function(){
    var type='search';
    $.post("UserGroup",{editType:type},function(data){

        $('#UserGroup').on('click', 'td .btn-warning', function() {
            var param = $(this).closest('tr').find('td:nth-child(2)').html();
            window.location.href='StrategyManage.jsp?group='+param;
        });

        $('#UserGroup').on('click', 'td .btn-danger', function() {
            var param = $(this).closest('tr').find('td:nth-child(2)').html();
            if(confirm("ȷ��Ҫɾ���������Լ�¼��")){
                window.location.href='UserGroup.action?editType=del&userGroup='+param;
            }
            else{
                return;
            }

        });

        $("#UserGroup").dataTable({
            "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
            "bPaginate": true,
            "sProcessing" : "���ڶ�ȡ���ݣ����Ժ�...",
            "aaData" :data,
            "aoColumns": [
                { "mData": "ID" },
                { "mData": "�û������" },
                { "mData": "�û���" },
                {
                    "mData": null,
                    "sClass": "center",
                    //"sDefaultContent": '<a href="123.jsp" class="editor_edit">�༭</a>'
                    "sDefaultContent": '<a class="btn btn-warning" href="#"><i class="icon-edit icon-white"></i>�༭</a>&nbsp;&nbsp;<a class="btn btn-danger" href="#"><i class="icon-edit icon-white"></i>ɾ��</a>'
                }
            ],
            "bDestroy" : true,
            "sPaginationType": "bootstrap",
            "bAutoWith": true,
            "bSort": true,
            "bLengthChange": false,
            "bStateSave": true,
            "oLanguage": {
                "sInfo": "��ǰ��ʾ _START_ �� _END_ ������ _TOTAL_ ����¼",
                "sInfoFiltered": "���ݱ��й�Ϊ _MAX_ ����¼",
                "sSearch": "����",
                "sZeroRecords": "�Բ��𣬲�ѯ����������ݣ�",
                "sEmptyTable": "���������ݴ��ڣ�",
                "oPaginate": {
                    "sFirst": "��ҳ",
                    "sPrevious": "��һҳ",
                    "sNext": "��һҳ",
                    "sLast": "ĩҳ"
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
            alert("��ӳɹ���");
            window.location.href="UserGroup.jsp";
        }
        else{
            alert("���ʧ�ܣ�����ϵ����Ա");
        }
    })
}