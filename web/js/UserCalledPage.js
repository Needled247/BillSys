var UserCalledTbl;
$(function(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var thisMonth;
    if(month<10){
        thisMonth = year + '0' + month;
    }
    else{
        thisMonth = year +''+ month;
    }
    $("#month").attr("value",month+'��');
    $("#month").val('0'+month);
    $("#year").attr("value",year);
    $("#year").val(year);
    var longNum = $("#longNum").val();
    var shortNum = $("#shortNum").val();
    var userid = $("#userid").val();
    var userGroup = $("#userGroup").val();
    var balance = $("#balance").val();
    var type = 'init';
    $.post("UserCallAction",{type:type,userid:userid,longNum:longNum,shortNum:shortNum,month:thisMonth,userGroup:userGroup,balance:balance},function(data){
        UserCalledTbl = $("#UserCalledTbl").dataTable({
            "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
            "bPaginate": true,
            "sProcessing" : "���ڶ�ȡ���ݣ����Ժ�...",
            "aaData" :data,
            "aoColumns": [
                { "mData": "����" },
                { "mData": "����" },
                { "mData": "����", "sClass": "center" },
                { "mData": "��ʼʱ��", "sClass": "center" },
                { "mData": "����ʱ��", "sClass": "center" },
                { "mData": "ͨ��ʱ��", "sClass": "center" },
                { "mData": "����", "sClass": "center" }
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
        })
    })
});

function getUserCalledByMonth(){
    var type = 'month';
    var year = $("#year").val();
    var month = $("#month").val();
    var date = year+month;
    var longNum = $("#longNum").val();
    var shortNum = $("#shortNum").val();
    var userid = $("#userid").val();
    var userGroup = $("#userGroup").val();
    var balance = $("#balance").val();
    $.post("UserCallAction",{type:type,month:date,longNum:longNum,shortNum:shortNum,userid:userid,userGroup:userGroup,balance:balance},function(data){
        UserCalledTbl.fnClearTable();
        UserCalledTbl.fnAddData(data);
        UserCalledTbl.fnDraw(data);
    })
}

function getUserCalledByTime(){
    var startTime = $("#datefrom").val();
    var endTime = $("#dateto").val();
    var longNum = $("#longNum").val();
    var shortNum = $("#shortNum").val();
    var userid = $("#userid").val();
    var userGroup = $("#userGroup").val();
    var balance = $("#balance").val();
    var type = 'time';
    $.post("UserCallAction",{type:type,longNum:longNum,shortNum:shortNum,userid:userid,startTime:startTime,endTime:endTime,userGroup:userGroup,balance:balance},function(data){
        UserCalledTbl.fnClearTable();
        UserCalledTbl.fnAddData(data);
        UserCalledTbl.fnDraw(data);
    })
}

function getThisMonthCalled(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var thisMonth;
    if(month<10){
        thisMonth = year + '0' + month;
    }
    else{
        thisMonth = year +''+ month;
    }
    $("#month").attr("value",month+'��');
    $("#month").val('0'+month);
    $("#year").attr("value",year);
    $("#year").val(year);
    var longNum = $("#longNum").val();
    var shortNum = $("#shortNum").val();
    var userid = $("#userid").val();
    var userGroup = $("#userGroup").val();
    var balance = $("#balance").val();
    var type = 'init';
    $.post("UserCallAction",{type:type,userid:userid,longNum:longNum,shortNum:shortNum,month:thisMonth,userGroup:userGroup,balance:balance},function(data){
        UserCalledTbl.fnClearTable();
        UserCalledTbl.fnAddData(data);
        UserCalledTbl.fnDraw(data);
    })
}

function getAllCalled(){
    var type = 'all';
    var longNum = $("#longNum").val();
    var shortNum = $("#shortNum").val();
    var userid = $("#userid").val();
    var userGroup = $("#userGroup").val();
    var balance = $("#balance").val();
    $.post("UserCallAction",{type:type,userid:userid,longNum:longNum,shortNum:shortNum,userGroup:userGroup,balance:balance},function(data){
        UserCalledTbl.fnClearTable();
        UserCalledTbl.fnAddData(data);
        UserCalledTbl.fnDraw(data);
    })
}
