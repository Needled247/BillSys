$(function(){
    $.post("BillingPrintAction",null,function(data){
        $('#UserGroup').on('click', 'td .btn-warning', function() {
            var param = $(this).closest('tr').find('td:nth-child(2)').html();
            window.location.href='StrategyManage.jsp?group='+param;
        });

        $("#BillPrint").dataTable({
            "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
            "bPaginate": true,
            "sProcessing" : "���ڶ�ȡ���ݣ����Ժ�...",
            "aaData" :data,
            "aoColumns": [
                { "mData": "ID" },
                { "mData": "����" },
                { "mData": "�û��˺�" },
                { "mData": "����ʱ��" },
                { "mData": "����Ӫҵ��" },
                {
                    "mData": null,
                    "sClass": "center",
                    //"sDefaultContent": '<a href="123.jsp" class="editor_edit">�༭</a>'
                    "sDefaultContent": '<a class="btn btn-warning" href="#"><i class="icon-edit icon-white"></i>�鿴</a>'
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