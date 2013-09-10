function editUser(){
    var phoneNum = $("#phoneNum").val();
    var userId = $("#userId").val();
    var shortNum = $("#shortNum").val();
    var mobile = $("#mobile").val();
    var time = $("#time").val();
    var ipadd = $("#ipadd").val();
    var vlan = $("#vlan").val();
    var tactics = $("#tactics").val();
    var status = $("#status").val();
    var balance = $("#balance").val();
    var stored = $("#stored").val();
    var mtime = $("#mTime").val();
    var startTime = $("#time").val();
    var protocal = $("#protocal").val();
    var id = $("#id").val();
    var gate = $("#gate").val();
    var tbl = $("#tbl").val();

    var type='update'
    $.post("EditUser",{type:type,id:id,phoneNum:phoneNum,userId:userId,shortNum:shortNum,mobile:mobile,
    time:time,ipadd:ipadd,vlan:vlan,tactics:tactics,status:status,balance:balance,stored:stored,mtime:mtime,
        time:startTime,protocal:protocal,gate:gate,tbl:tbl},function(data){
        if(data=='success'){
            alert('修改成功！');
            window.location.href='ViewUser.jsp';
        }
        else{
            alert('修改失败，请联系管理员。');
        }
    })
}


function initUserInfo(){
    var phoneNum = $("#phoneNum").val();
    var userId = $("#userId").val();
    var shortNum = $("#shortNum").val();
    var mobile = $("#mobile").val();
    var time = $("#time").val();
    var ipadd = $("#ipadd").val();
    var vlan = $("#vlan").val();
    var tactics = $("#tactics").val();
    var status = $("#status").val();
    var balance = $("#balance").val();
    var stored = $("#stored").val();
    var mtime = $("#mTime").val();
    var startTime = $("#time").val();
    var protocal = $("#protocal").val();
    var id = $("#id").val();
    var gate = $("#gate").val();
    var tbl = $("#tbl").val();
    var type='delete';
    var tbl = $("#tbl").val();
    if(confirm('确定要删除此用户吗？\n\r注意：删除此用户后这个号码会返回未使用号码池，并在设备上解除注册。')){
        $("#btn_save").attr("disabled","true");
        $("#btn_del").attr("disabled","true");
        $("#btn_back").attr("disabled","true");
        $("#btn_openLocal").attr("disabled","true");
        $("#btn_closeLocal").attr("disabled","true");
        $("#btn_openLong").attr("disabled","true");
        $("#btn_closeLong").attr("disabled","true");
        $.post("EditUser",{type:type,id:id,phoneNum:phoneNum,userId:userId,shortNum:shortNum,mobile:mobile,
            time:time,ipadd:ipadd,vlan:vlan,tactics:tactics,status:status,balance:balance,stored:stored,mtime:mtime,time:startTime,protocal:protocal,tbl:tbl},function(data){
            if(data == 'success'){
                window.location.href='uSuccess.jsp';
            }
            else if(data == 'fail'){
                alert('删除失败！请重试...');
                $("#btn_save").removeAttr("disabled");
                $("#btn_del").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                $("#btn_openLocal").removeAttr("disabled");
                $("#btn_closeLocal").removeAttr("disabled");
                $("#btn_openLong").removeAttr("disabled");
                $("#btn_closeLong").removeAttr("disabled");
            }
        });
    }
    else{
        return;
    }
}

function openLocal(){
    var shortNum = $("#shortNum").val();
    var type = 'openLocal';
    if(confirm('要给'+shortNum+'号码开通本地通话业务吗？')){
        $("#btn_save").attr("disabled","true");
        $("#btn_del").attr("disabled","true");
        $("#btn_back").attr("disabled","true");
        $("#btn_openLocal").attr("disabled","true");
        $("#btn_closeLocal").attr("disabled","true");
        $("#btn_openLong").attr("disabled","true");
        $("#btn_closeLong").attr("disabled","true");
        $.post("EditUser",{shortNum:shortNum,type:type},function(data){
            if(data=='openLocalSuccess'){
                alert('号码本地通话业务开通成功！');
                $("#btn_save").removeAttr("disabled");
                $("#btn_del").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                $("#btn_openLocal").removeAttr("disabled");
                $("#btn_closeLocal").removeAttr("disabled");
                $("#btn_openLong").removeAttr("disabled");
                $("#btn_closeLong").removeAttr("disabled");
            }
            else{
                alert('开通失败！该号码可能已经开通此项业务。');
                $("#btn_save").removeAttr("disabled");
                $("#btn_del").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                $("#btn_openLocal").removeAttr("disabled");
                $("#btn_closeLocal").removeAttr("disabled");
                $("#btn_openLong").removeAttr("disabled");
                $("#btn_closeLong").removeAttr("disabled");
            }
        });
    }
    else{
        return;
    }
}

function offLocal(){
    var shortNum = $("#shortNum").val();
    var type='offLocal'
    if(confirm('要给'+shortNum+'号码关闭本地通话业务吗？')){
        $("#btn_save").attr("disabled","true");
        $("#btn_del").attr("disabled","true");
        $("#btn_back").attr("disabled","true");
        $("#btn_openLocal").attr("disabled","true");
        $("#btn_closeLocal").attr("disabled","true");
        $("#btn_openLong").attr("disabled","true");
        $("#btn_closeLong").attr("disabled","true");
        $.post("EditUser",{shortNum:shortNum,type:type},function(data){
            if(data=='offLocalSuccess'){
                alert('号码本地通话业务关闭成功！');
                $("#btn_save").removeAttr("disabled");
                $("#btn_del").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                $("#btn_openLocal").removeAttr("disabled");
                $("#btn_closeLocal").removeAttr("disabled");
                $("#btn_openLong").removeAttr("disabled");
                $("#btn_closeLong").removeAttr("disabled");
            }
            else{
                alert('关闭失败！请重试。');
                $("#btn_save").removeAttr("disabled");
                $("#btn_del").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                $("#btn_openLocal").removeAttr("disabled");
                $("#btn_closeLocal").removeAttr("disabled");
                $("#btn_openLong").removeAttr("disabled");
                $("#btn_closeLong").removeAttr("disabled");
            }
        });
    }
    else{
        return;
    }
}

function openLong(){
    var shortNum = $("#shortNum").val();
    var type='openLong';
    if(confirm('要给'+shortNum+'号码开通长途通话业务吗？')){
        $("#btn_save").attr("disabled","true");
        $("#btn_del").attr("disabled","true");
        $("#btn_back").attr("disabled","true");
        $("#btn_openLocal").attr("disabled","true");
        $("#btn_closeLocal").attr("disabled","true");
        $("#btn_openLong").attr("disabled","true");
        $("#btn_closeLong").attr("disabled","true");
        $.post("EditUser",{shortNum:shortNum,type:type},function(data){
            if(data=='openLongSuccess'){
                alert('号码长途通话业务开通成功！');
                $("#btn_save").removeAttr("disabled");
                $("#btn_del").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                $("#btn_openLocal").removeAttr("disabled");
                $("#btn_closeLocal").removeAttr("disabled");
                $("#btn_openLong").removeAttr("disabled");
                $("#btn_closeLong").removeAttr("disabled");
            }
            else{
                alert('开通失败！该号码可能已经开通此项业务。');
                $("#btn_save").removeAttr("disabled");
                $("#btn_del").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                $("#btn_openLocal").removeAttr("disabled");
                $("#btn_closeLocal").removeAttr("disabled");
                $("#btn_openLong").removeAttr("disabled");
                $("#btn_closeLong").removeAttr("disabled");
            }
        });
    }
    else{
        return;
    }
}

function offLong(){
    var shortNum = $("#shortNum").val();
    var type = 'offLong';
    if(confirm('要给'+shortNum+'号码关闭长途通话业务吗？')){
        $("#btn_save").attr("disabled","true");
        $("#btn_del").attr("disabled","true");
        $("#btn_back").attr("disabled","true");
        $("#btn_openLocal").attr("disabled","true");
        $("#btn_closeLocal").attr("disabled","true");
        $("#btn_openLong").attr("disabled","true");
        $("#btn_closeLong").attr("disabled","true");
        $.post("EditUser",{shortNum:shortNum,type:type},function(data){
            if(data=='offLongSuccess'){
                alert('号码长途通话业务关闭成功！');
                $("#btn_save").removeAttr("disabled");
                $("#btn_del").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                $("#btn_openLocal").removeAttr("disabled");
                $("#btn_closeLocal").removeAttr("disabled");
                $("#btn_openLong").removeAttr("disabled");
                $("#btn_closeLong").removeAttr("disabled");
            }
            else{
                alert('关闭失败！请重试。');
                $("#btn_save").removeAttr("disabled");
                $("#btn_del").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                $("#btn_openLocal").removeAttr("disabled");
                $("#btn_closeLocal").removeAttr("disabled");
                $("#btn_openLong").removeAttr("disabled");
                $("#btn_closeLong").removeAttr("disabled");
            }
        });
    }
    else{
        return;
    }
}