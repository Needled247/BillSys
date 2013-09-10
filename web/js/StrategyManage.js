$(function(){
    var type='search';
    var group = $("#group").val();
    $.post("StrategyManage",{type:type,userGroup:group},function(data){

        $('#SManage').on('click', 'td .btn-warning', function() {
            var param = $(this).closest('tr').find('td:nth-child(2)').html();
            window.location.href='EditStrategyInfo.jsp?prefix='+param+'&group='+group;
        });

        $('#SManage').on('click', 'td .btn-danger', function() {
            var param = $(this).closest('tr').find('td:nth-child(2)').html();
            window.location.href='StrategyManage.action?type=del&prefix='+param+'&userGroup='+group;
        });

        $("#SManage").dataTable({
            "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
            "bPaginate": true,
            "sProcessing" : "���ڶ�ȡ���ݣ����Ժ�...",
            "aaData" :data,
            "aoColumns": [
                { "mData": "ID" },
                { "mData": "����ǰ׺" },
                { "mData": "��������" },
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



function newStrategyManage(){
    var profile = $("#profile").val();
    var prefix = $("#prefix").val();
    var userGroup = $("#userGroup").val();
    var ratestime1 = $("#ratestime1").val();
    var rates1 = $("#rates1").val();
    var ratestime2 = $("#ratestime2").val();
    var rates2 = $("#rates2").val();
    var ratestime3 = $("#ratestime3").val();
    var rates3 = $("#rates3").val();
    var otherfee = $("#otherfee").val();
    var specialtime1 = $("#specialtime1").val();
    var specialtimeend1 = $("#specialtimeend1").val();
    var specialtime2 = $("#specialtime2").val();
    var specialtimeend2 = $("#specialtimeend2").val();
    var specialtime3 = $("#specialtime3").val();
    var specialtimeend3 = $("#specialtimeend3").val();
    var specialfee1 = $("#specialfee1").val();
    var specialfee2 = $("#specialfee2").val();
    var specialfee3 = $("#specialfee3").val();
    var type = 'save';
    $.post("StrategyManage",{type:type,userGroup:userGroup,prefix:prefix,profile:profile,ratestime1:ratestime1,rates1:rates1,
            ratestime2:ratestime2,rates2:rates2,ratestime3:ratestime3,rates3:rates3,otherfee:otherfee,
            specialtime1:specialtime1,specialtimeend1:specialtimeend1,specialtime2:specialtime2,specialtimeend2:specialtimeend2,
            specialtime3:specialtime3,specialtimeend3:specialtimeend3,specialfee1:specialfee1,specialfee2:specialfee2,specialfee3:specialfee3},
        function(data){
            if(data=='success'){
                window.location.href='StraSucc.jsp';
            }
            else{
                alert("����ʧ�ܣ�����ϵ����Ա");
            }
        });
}