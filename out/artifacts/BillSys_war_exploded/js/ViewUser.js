function delUser(){
    var id = $("#id").val();
    var longNum = $("#longNum").val();
    var userid = $("#userid").val();
    var tbl = $("#tbl").val();
    var type='delete';
    if(confirm("确定要删除这个用户吗？"))
    {
        $.post("EditUser",{id:id,tbl:tbl,phoneNum:longNum,type:type,userId:userid},function(data){
            if(data=='success'){
                alert('删除成功！号码已被初始化');
                location.reload();
            }
            else{
                alert('删除失败！请联系管理员');
            }
        })
    }
    else
    {
        return;
    }
}