function changePass(){
    var oldpass = $("#oldpass").val();
    var newpass1 = $("#newpass1").val();
    var newpass2 = $("#newpass2").val();
    var uid = $("#uid").val();
    var alertBlock = $("#alert");
    if(oldpass==''){
        $("#alert_block").show();
        alertBlock.html("");
        alertBlock.html("旧密码不能为空！");
        return;
    }
    if(newpass1.length<5||newpass1.length>12){
        $("#alert_block").show();
        alertBlock.html("");
        alertBlock.html("新密码长度为5-12位");
        return;
    }
    if(newpass2.length<5||newpass2.length>12){
        $("#alert_block").show();
        alertBlock.html("");
        alertBlock.html("新密码长度为5-12位");
        return;
    }
    if(newpass1==''){
        $("#alert_block").show();
        alertBlock.html("");
        alertBlock.html("新密码不能为空！");
        return;
    }
    if(newpass2==''){
        $("#alert_block").show();
        alertBlock.html("");
        alertBlock.html("确认密码不能为空！");
        return;
    }
    if(newpass1!=newpass2){
        $("#alert_block").show();
        alertBlock.html("");
        alertBlock.html("新密码两次输入不一致！");
        return;
    }

    $.post("ChangePassAction",{oldpass:oldpass,newpass1:newpass1,newpass2:newpass2,uid:uid},function(data){
        if(data=='success'){
            alert("修改成功！");
            window.location.href='UserCallHistory.jsp';
        }
        else if(data=='ck_err'){
            $("#alert_block").show();
            alertBlock.html("");
            alertBlock.html("旧密码错误！");
        }
        else if(data=='error'){
            alert('修改失败！请重试');
        }
    })
}