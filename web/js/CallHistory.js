var historyTable;
$(function(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    $("#month").attr("value",month+'月');
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
            "sProcessing" : "正在读取数据，请稍后...",
            "aaData" :data,
            "aoColumns": [
                { "mData": "ID" },
                { "mData": "主叫号码" },
                { "mData": "被叫号码" },
                { "mData": "会话属性", "sClass": "center" },
                { "mData": "开始时间", "sClass": "center" },
                { "mData": "结束时间", "sClass": "center" },
                { "mData": "通话时长（秒）", "sClass": "center" },
                {
                    "mData": null,
                    "sClass": "center",
                    //"sDefaultContent": '<a href="123.jsp" class="editor_edit">编辑</a>'
                    "sDefaultContent": '<a class="btn btn-warning" href="#"><i class="icon-edit icon-white"></i>查看</a>'
                }
            ],
            "bDestroy" : true,
            "sPaginationType": "bootstrap",
            "bAutoWith": true,
            "bSort": true,
            "bLengthChange": true,
            "bStateSave": true,
            "oLanguage": {
                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                "sLengthMenu": "每页显示 _MENU_ 条记录",
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