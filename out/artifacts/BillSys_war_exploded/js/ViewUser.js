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