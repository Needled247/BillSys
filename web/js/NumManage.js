function Area2Tbl(tbl){
    if(tbl.match(/[0-9]+/)>0){
        area = tbl;
    }
    var area = '';
    if(tbl=='长辛店'){
        area = 'gtao_Phone_bc';
    }
    else if(tbl=='南厂'){
        area = 'gtao_Phone_nc';
    }
    else if(tbl=='青塔'){
        area = 'gtao_Phone_qt';
    }
    else if(tbl=='良乡'){
        area = 'gtao_Phone_lx';
    }
    else if(tbl=='三环'){
        area = 'gtao_Phone_sh';
    }
    else if(tbl=='正阳'){
        area = 'gtao_Phone_zy';
    }
    else if(tbl=='开阳'){
        area = 'gtao_Phone_ky';
    }
    else if(tbl=='晓月苑'){
        area = 'gtao_Phone_xyy';
    }
    else if(tbl=='长辛店（付费）'){
        area = 'gtao_Phone_bc_sale';
    }
    else if(tbl=='南厂（付费）'){
        area = 'gtao_Phone_nc_sale';
    }
    else if(tbl=='青塔（付费）'){
        area = 'gtao_Phone_qt_sale';
    }
    else if(tbl=='良乡（付费）'){
        area = 'gtao_Phone_lx_sale';
    }
    else if(tbl=='三环（付费）'){
        area = 'gtao_Phone_sh_sale';
    }
    else if(tbl=='正阳（付费）'){
        area = 'gtao_Phone_zy_sale';
    }
    else if(tbl=='开阳（付费）'){
        area = 'gtao_Phone_ky_sale';
    }
    else if(tbl=='晓月苑（付费）'){
        area = 'gtao_Phone_xyy_sale';
    }
    return area;
}

function SearchByArea(){
    var area = $("#Area").val();
    var type = 'area';
    var status = $("#status").val();
    $.post("NumManage",{area:area,type:type,status:status},function(data){

        $('#NumManageTbl').on('click', 'td .btn-warning', function() {
            var para1 = $(this).closest('tr').find('td:first').html();
            var area = $(this).closest('tr').find('td:nth-child(4)').html();
            var para2 = $(this).closest('tr').find('td:nth-child(5)').html();
            if(para2.match(/[0-9]+/)>0){
                window.location.href='EditSaleNum.jsp?pNum='+para1+'&tbl='+Area2Tbl(area);
            }
            else{
                window.location.href='EditFreeNum.jsp?pNum='+para1+'&tbl='+Area2Tbl(area);
            }
        });

        $("#NumManageTbl").dataTable({
            "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
            "bPaginate": true,
            "sProcessing" : "正在读取数据，请稍后...",
            "aaData" :data,
            "aoColumns": [
                { "mData": "号码" },
                { "mData": "IP" },
                { "mData": "Vlan" },
                { "mData": "地区", "sClass": "center" },
                { "mData": "价格", "sClass": "center" },
                { "mData": "网关", "sClass": "center" },
                {
                    "mData": null,
                    "sClass": "center",
                    "sDefaultContent": '<a class="btn btn-warning" href="#"><i class="icon-edit icon-white"></i>编辑</a>'
                }
            ],
            "bDestroy" : true,
            "sPaginationType": "bootstrap",
            "bAutoWith": true,
            "bSort": true,
            "bLengthChange": false,
            "bStateSave": false,
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
}

function SearchByAreaNoUse(){
    var area = $("#Area").val();
    var type = 'area';
    var status = '0';
    $.post("NumManage",{area:area,type:type,status:status},function(data){

        $('#NumManageTbl').on('click', 'td .btn-warning', function() {
            var para1 = $(this).closest('tr').find('td:first').html();
            var para2 = $(this).closest('tr').find('td:nth-child(4)').html();
            window.location.href='CreateApply.jsp?pNum='+para1+'&tbl='+Area2Tbl(para2);
        });

        $("#NumManageTbl").dataTable({
            "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
            "bPaginate": true,
            "sProcessing" : "正在读取数据，请稍后...",
            "aaData" :data,
            "aoColumns": [
                { "mData": "号码" },
                { "mData": "IP" },
                { "mData": "Vlan" },
                { "mData": "地区", "sClass": "center" },
                { "mData": "价格", "sClass": "center" },
                { "mData": "网关", "sClass": "center" },
                {
                    "mData": null,
                    "sClass": "center",
                    "sDefaultContent": '<a class="btn btn-warning" href="#"><i class="icon-edit icon-white"></i>开户</a>'
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
}

function SearchByNum(){
    var phoneNum = $("#phoneNum").val();
    var type = 'num';
    $.post("NumManage",{phoneNum:phoneNum,type:type},function(data){
        $('#NumManageTbl').on('click', 'td .btn-warning', function() {
            var para1 = $(this).closest('tr').find('td:first').html();
            var para2 = $(this).closest('tr').find('td:nth-child(4)').html();
            if(para2.match(/[0-9]+/)>0){
                window.location.href='EditSaleNum.jsp?pNum='+para1;
            }
            else{
                window.location.href='EditFreeNum.jsp?pNum='+para1+'&tbl='+Area2Tbl(para2);
            }
        });

        $("#NumManageTbl").dataTable({
            "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
            "bPaginate": true,
            "sProcessing" : "正在读取数据，请稍后...",
            "bProcessing":false,
            "aaData" :data,
            "aoColumns": [
                { "mData": "号码" },
                { "mData": "IP" },
                { "mData": "Vlan" },
                { "mData": "地区", "sClass": "center" },
                { "mData": "价格", "sClass": "center" },
                { "mData": "网关", "sClass": "center" },
                {
                    "mData": null,
                    "sClass": "center",
                    "sDefaultContent": '<a class="btn btn-warning" href="#"><i class="icon-edit icon-white"></i>查看</a>'
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
}

function SearchByNumNoUse(){
    var phoneNum = $("#phoneNum").val();
    var type = 'num';
    $.post("NumManage",{phoneNum:phoneNum,type:type},function(data){
        $('#NumManageTbl').on('click', 'td .btn-warning', function() {
            var para1 = $(this).closest('tr').find('td:first').html();
            var para2 = $(this).closest('tr').find('td:nth-child(4)').html();
            window.location.href='CreateApply.jsp?pNum='+para1+'&tbl='+Area2Tbl(para2);
        });

        $("#NumManageTbl").dataTable({
            "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
            "bPaginate": true,
            "sProcessing" : "正在读取数据，请稍后...",
            "bProcessing":false,
            "aaData" :data,
            "aoColumns": [
                { "mData": "号码" },
                { "mData": "IP" },
                { "mData": "Vlan" },
                { "mData": "地区", "sClass": "center" },
                { "mData": "价格", "sClass": "center" },
                { "mData": "网关", "sClass": "center" },
                {
                    "mData": null,
                    "sClass": "center",
                    "sDefaultContent": '<a class="btn btn-warning" href="#"><i class="icon-edit icon-white"></i>开户</a>'
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
}

function EditFreeNumInfo(){
    var phoneNum = $("#phoneNum").val();
    var userId = $("#userId").val();
    var shortNum = $("#shortNum").val();
    var mobile = $("#mobile").val();
    var phoneIp = $("#phoneIp").val();
    var vlan = $("#vlan").val();
    var tblName = $("#tblName").val();
    var finalNum = $("#finalNum").val();
    var gate = $("#gate").val();
    var EditType = 'freeUpdate';
    $.post("EditNumInfo",{phoneNum:phoneNum,userId:userId,shortNum:shortNum,mobile:mobile,phoneIp:phoneIp,vlan:vlan,tblName:tblName,gate:gate,EditType:EditType,finalNum:finalNum},function(data){
        if(data=='success'){
            window.location.href="eSuccess.jsp";
        }
        else{
            alert('修改失败！请重试...');
        }
    })
}

function initNum(){
    if(confirm("您确定要初始化这个号码吗？\r\n提示：此操作会将号码恢复成初始状态，如果有绑定用户则解除绑定。")){
        var phoneNum = $("#finalNum").val();
        var tblName = $("#tblName").val();
        var EditType = 'freeInit';
        $.post("EditNumInfo",{phoneNum:phoneNum,tblName:tblName,EditType:EditType},function(data){
            if(data=='init_success'){
                window.location.href='eSuccess.jsp';
            }
            else{
                alert('初始化失败！请重试...');
            }
        })
    }
    else{
        return;
    }
}

function delFreeNumInfo(){
    if(confirm("您确定要彻底删除这个号码？（注意：此操作不可恢复）"))
    {
        var phoneNum = $("#phoneNum").val();
        var tblName = $("#tblName").val();
        var EditType='freeDelete';
        $.post("EditNumInfo",{phoneNum:phoneNum,tblName:tblName,EditType:EditType},function(data){
            if(data=='success'){
                window.location.href="eSuccess.jsp";
            }
            else{
                alert('删除失败！请重试...');
            }
        })
    }
    else
    {
        return false;
    }
}



function EditSaleNumInfo(){
    var phoneNum = $("#phoneNum").val();
    var userId = $("#userId").val();
    var shortNum = $("#shortNum").val();
    var mobile = $("#mobile").val();
    var phoneIp = $("#phoneIp").val();
    var vlan = $("#vlan").val();
    var tblName = $("#tblName").val();
    var finalNum = $("#finalNum").val();
    var money = $("#money").val();
    var gate = $("#gate").val();
    var EditType = 'saleUpdate';
    $.post("EditNumInfo",{phoneNum:phoneNum,gate:gate,userId:userId,shortNum:shortNum,mobile:mobile,phoneIp:phoneIp,vlan:vlan,tblName:tblName,EditType:EditType,finalNum:finalNum,money:money},function(data){
        if(data=='success'){
            window.location.href="eSuccess.jsp";
        }
        else{
            alert('修改失败！请重试...');
        }
    })
}

function initSaleNumInfo(){
    if(confirm("您确定要删除这个号码？"))
    {
        var phoneNum = $("#phoneNum").val();
        var EditType='saleDelete';
        $.post("EditNumInfo",{phoneNum:phoneNum,EditType:EditType},function(data){
            if(data=='success'){
                window.location.href="eSuccess.jsp";
            }
            else{
                alert('删除失败！请重试...');
            }
        })
    }
    else
    {
        return false;
    }
}

function upload(){
    var file = $("#fileInput").val();
    if(file==""||file==null){
        alert("请选择上传文件!");
    }
    $("#uploadForm").submit();
}