var historyTable;
$(function(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    $("#month").attr("value",month+'��');
    $("#month").val('0'+month);
    $("#year").attr("value",year);
    $("#year").val(year);
    year = $("#year").val();
    month = $("#month").val();
    var type = 'init';
    $.post("CallHistory",{year:year,month:month,type:type},function(data){

        $('#CallHistoryTbl').on('click', 'td .btn-warning', function() {
            var id = $(this).closest('tr').find('td:first').html();
            var startTime = $(this).closest('tr').find('td:nth-child(5)').html();
            window.location.href='ViewHistoryDetail.jsp?sid='+id+'&startTime='+startTime;
        });

        historyTable = $("#CallHistoryTbl").dataTable({
            "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
            "bPaginate": true,
            "sProcessing" : "���ڶ�ȡ���ݣ����Ժ�...",
            "aaData" :data,
            "aoColumns": [
                { "mData": "ID" },
                { "mData": "���к���" },
                { "mData": "���к���" },
                { "mData": "�Ự����", "sClass": "center" },
                { "mData": "��ʼʱ��", "sClass": "center" },
                { "mData": "����ʱ��", "sClass": "center" },
                { "mData": "ͨ��ʱ�����룩", "sClass": "center" },
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
            "bLengthChange": true,
            "bStateSave": true,
            "oLanguage": {
                "sInfo": "��ǰ��ʾ _START_ �� _END_ ������ _TOTAL_ ����¼",
                "sLengthMenu": "ÿҳ��ʾ _MENU_ ����¼",
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

function getHistoryByMonth(){
    var year = $("#year").val();
    var month = $("#month").val();
    var phoneNum = $("#longNum").val();
    var callType = $("#callType").val();
    var type = 'search';
    if(phoneNum==''){
        phoneNum = '0';
    }
    $.post("CallHistory",{year:year,month:month,phoneNum:phoneNum,callType:callType,type:type},function(data){
        historyTable.fnClearTable();
        historyTable.fnAddData(data);
        historyTable.fnDraw(data);
    })
}

function getHistoryByDate(){
    var datefrom = $("#datefrom").val();
    var dateto = $("#dateto").val();
    var longNum2 = $("#longNum2").val();
    var type='dateSearch';
    $.post("CallHistory",{type:type,datefrom:datefrom,dateto:dateto,longNum2:longNum2},function(data){
        historyTable.fnClearTable();
        historyTable.fnAddData(data);
        historyTable.fnDraw(data);
    })
}