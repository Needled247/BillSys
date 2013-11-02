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
    $("#month").attr("value",month+'月');
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
            "sProcessing" : "正在读取数据，请稍后...",
            "aaData" :data,
            "aoColumns": [
                { "mData": "主叫" },
                { "mData": "被叫" },
                { "mData": "属性", "sClass": "center" },
                { "mData": "开始时间", "sClass": "center" },
                { "mData": "结束时间", "sClass": "center" },
                { "mData": "通话时长", "sClass": "center" },
                { "mData": "费率", "sClass": "center" }
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
    $("#month").attr("value",month+'月');
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
