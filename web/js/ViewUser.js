function delUser(){
    var id = $("#id").val();
    var longNum = $("#longNum").val();
    var userid = $("#userid").val();
    var tbl = $("#tbl").val();
    var type='delete';
    if(confirm("ȷ��Ҫɾ������û���"))
    {
        $.post("EditUser",{id:id,tbl:tbl,phoneNum:longNum,type:type,userId:userid},function(data){
            if(data=='success'){
                alert('ɾ���ɹ��������ѱ���ʼ��');
                location.reload();
            }
            else{
                alert('ɾ��ʧ�ܣ�����ϵ����Ա');
            }
        })
    }
    else
    {
        return;
    }
}


$(document).ready( function(){
    $('#viewUser').on('click', 'td .btn-success', function() {
        var userid = $(this).closest('tr').find('td:nth-child(2)').html();
        var longNum = $(this).closest('tr').find('td:nth-child(3)').html();
        window.location.href='EditUser.jsp?phoneNum='+longNum+'&userid='+userid;
    });

    $('#viewUser').on('click', 'td .btn-warning', function() {
        var userid = $(this).closest('tr').find('td:nth-child(2)').html();
        var longNum = $(this).closest('tr').find('td:nth-child(3)').html();
        window.location.href='UserCalledPage.jsp?userid='+userid+'&longNum='+longNum;
    });

    $("#viewUser").dataTable({
        "sPaginationType" : "bootstrap",
        "iDisplayLength" : 10,
        "sAjaxDataProp" : "aaData",
        "bDestroy" : true,
        "bSort": false,
        "bLengthChange": false,
        "bStateSave": false,
        "bProcessing" : true,
        "bServerSide" : true,
        "sAjaxSource" : "ViewUserAction",
        "aoColumns" : [
            {"mData" : "ID"},
            {"mData" : "�û��˺�"},
            {"mData" : "����"},
            {"mData" : "�û���"},
            {"mData" : "����ʱ��"},
            {"mData" : "����Ӫҵ��"},
            {
                "mData": null,
                "sClass": "center",
                "sDefaultContent": '<a class="btn btn-success" href="#"><i class="icon-edit icon-green"></i>�鿴/�༭</a>&nbsp;<a class="btn btn-warning" href="#"><i class="icon-search icon-orange"></i>ͨ����ϸ</a>'
            }
        ],
        "oLanguage" : {
            "sProcessing" : "���ڼ�����......",
            "sLengthMenu" : "ÿҳ��ʾ _MENU_ ����¼",
            "sZeroRecords" : "û�����ݣ�",
            "sEmptyTable" : "���������ݴ��ڣ�",
            "sInfo" : "��ǰ��ʾ _START_ �� _END_ ������ _TOTAL_ ����¼",
            "sInfoEmpty" : "��ʾ0��0����¼",
            "sInfoFiltered" : "���ݱ��й�Ϊ _MAX_ ����¼",
            "sSearch" : "����",
            "oPaginate" : {
                "sFirst" : "��ҳ",
                "sPrevious" : "��һҳ",
                "sNext" : "��һҳ",
                "sLast" : "ĩҳ"
            }
        }
    });
});