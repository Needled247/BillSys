function Area2Tbl(tbl){
    if(tbl.match(/[0-9]+/)>0){
        area = tbl;
    }
    var area = '';
    if(tbl=='������'){
        area = 'gtao_Phone_bc';
    }
    else if(tbl=='�ϳ�'){
        area = 'gtao_Phone_nc';
    }
    else if(tbl=='����'){
        area = 'gtao_Phone_qt';
    }
    else if(tbl=='����'){
        area = 'gtao_Phone_lx';
    }
    else if(tbl=='����'){
        area = 'gtao_Phone_sh';
    }
    else if(tbl=='����'){
        area = 'gtao_Phone_zy';
    }
    else if(tbl=='����'){
        area = 'gtao_Phone_ky';
    }
    else if(tbl=='����Է'){
        area = 'gtao_Phone_xyy';
    }
    else if(tbl=='�����꣨���ѣ�'){
        area = 'gtao_Phone_bc_sale';
    }
    else if(tbl=='�ϳ������ѣ�'){
        area = 'gtao_Phone_nc_sale';
    }
    else if(tbl=='���������ѣ�'){
        area = 'gtao_Phone_qt_sale';
    }
    else if(tbl=='���磨���ѣ�'){
        area = 'gtao_Phone_lx_sale';
    }
    else if(tbl=='���������ѣ�'){
        area = 'gtao_Phone_sh_sale';
    }
    else if(tbl=='���������ѣ�'){
        area = 'gtao_Phone_zy_sale';
    }
    else if(tbl=='���������ѣ�'){
        area = 'gtao_Phone_ky_sale';
    }
    else if(tbl=='����Է�����ѣ�'){
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
            "sProcessing" : "���ڶ�ȡ���ݣ����Ժ�...",
            "aaData" :data,
            "aoColumns": [
                { "mData": "����" },
                { "mData": "IP" },
                { "mData": "Vlan" },
                { "mData": "����", "sClass": "center" },
                { "mData": "�۸�", "sClass": "center" },
                { "mData": "����", "sClass": "center" },
                {
                    "mData": null,
                    "sClass": "center",
                    "sDefaultContent": '<a class="btn btn-warning" href="#"><i class="icon-edit icon-white"></i>�༭</a>'
                }
            ],
            "bDestroy" : true,
            "sPaginationType": "bootstrap",
            "bAutoWith": true,
            "bSort": true,
            "bLengthChange": false,
            "bStateSave": false,
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
            "sProcessing" : "���ڶ�ȡ���ݣ����Ժ�...",
            "aaData" :data,
            "aoColumns": [
                { "mData": "����" },
                { "mData": "IP" },
                { "mData": "Vlan" },
                { "mData": "����", "sClass": "center" },
                { "mData": "�۸�", "sClass": "center" },
                { "mData": "����", "sClass": "center" },
                {
                    "mData": null,
                    "sClass": "center",
                    "sDefaultContent": '<a class="btn btn-warning" href="#"><i class="icon-edit icon-white"></i>����</a>'
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
            "sProcessing" : "���ڶ�ȡ���ݣ����Ժ�...",
            "bProcessing":false,
            "aaData" :data,
            "aoColumns": [
                { "mData": "����" },
                { "mData": "IP" },
                { "mData": "Vlan" },
                { "mData": "����", "sClass": "center" },
                { "mData": "�۸�", "sClass": "center" },
                { "mData": "����", "sClass": "center" },
                {
                    "mData": null,
                    "sClass": "center",
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
            "sProcessing" : "���ڶ�ȡ���ݣ����Ժ�...",
            "bProcessing":false,
            "aaData" :data,
            "aoColumns": [
                { "mData": "����" },
                { "mData": "IP" },
                { "mData": "Vlan" },
                { "mData": "����", "sClass": "center" },
                { "mData": "�۸�", "sClass": "center" },
                { "mData": "����", "sClass": "center" },
                {
                    "mData": null,
                    "sClass": "center",
                    "sDefaultContent": '<a class="btn btn-warning" href="#"><i class="icon-edit icon-white"></i>����</a>'
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
            alert('�޸�ʧ�ܣ�������...');
        }
    })
}

function initNum(){
    if(confirm("��ȷ��Ҫ��ʼ�����������\r\n��ʾ���˲����Ὣ����ָ��ɳ�ʼ״̬������а��û������󶨡�")){
        var phoneNum = $("#finalNum").val();
        var tblName = $("#tblName").val();
        var EditType = 'freeInit';
        $.post("EditNumInfo",{phoneNum:phoneNum,tblName:tblName,EditType:EditType},function(data){
            if(data=='init_success'){
                window.location.href='eSuccess.jsp';
            }
            else{
                alert('��ʼ��ʧ�ܣ�������...');
            }
        })
    }
    else{
        return;
    }
}

function delFreeNumInfo(){
    if(confirm("��ȷ��Ҫ����ɾ��������룿��ע�⣺�˲������ɻָ���"))
    {
        var phoneNum = $("#phoneNum").val();
        var tblName = $("#tblName").val();
        var EditType='freeDelete';
        $.post("EditNumInfo",{phoneNum:phoneNum,tblName:tblName,EditType:EditType},function(data){
            if(data=='success'){
                window.location.href="eSuccess.jsp";
            }
            else{
                alert('ɾ��ʧ�ܣ�������...');
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
            alert('�޸�ʧ�ܣ�������...');
        }
    })
}

function initSaleNumInfo(){
    if(confirm("��ȷ��Ҫɾ��������룿"))
    {
        var phoneNum = $("#phoneNum").val();
        var EditType='saleDelete';
        $.post("EditNumInfo",{phoneNum:phoneNum,EditType:EditType},function(data){
            if(data=='success'){
                window.location.href="eSuccess.jsp";
            }
            else{
                alert('ɾ��ʧ�ܣ�������...');
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
        alert("��ѡ���ϴ��ļ�!");
    }
    $("#uploadForm").submit();
}