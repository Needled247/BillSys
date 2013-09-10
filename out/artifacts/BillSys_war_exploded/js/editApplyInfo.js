
function editApplyInfo(){
    var protocal = $("#protocal").val();
    if(protocal=='0'){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("协议不能为空...请填写()");
        return;
    }
    var phoneNum = $("#phoneNum").val();
    if(phoneNum==''||phoneNum==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("号码不能为空...请填写");
        return;
    }
    var userId = $("#userId").val();
    if(userId==''||userId==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("用户账号不能为空...请填写");
        return;
    }
    var shortNum = $("#shortNum").val();
    if(shortNum==''||shortNum==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("短号不能为空...请填写");
        return;
    }
    var mobile = $("#mobile").val();
    if(mobile==''||mobile==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("手机号码不能为空...请填写");
        return;
    }
    var phoneIp = $("#phoneIp").val();
    if(phoneIp==''||phoneIp==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("绑定IP不能为空...请填写");
        return;
    }
    var vlan = $("#vlan").val();
    if(vlan==''||vlan==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("Vlan不能为空...请填写");
        return;
    }
    var installer = $("#installer").val();
    if(installer==''||installer==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("安装负责人不能为空...请填写");
        return;
    }
    var installTime = $("#installTime").val();
    if(installTime==''||installTime==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("安装时间不能为空...请填写");
        return;
    }

    var userGroup = $("#userGroup").val();
    if(userGroup==''||userGroup==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("用户组不能为空...请填写");
        return;
    }
    var gate = $("#gate").val();
    if(gate==''||gate==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("网关不能为空...请填写");
        return;
    }


    var Balance = $("#Balance").val();
    var ipadd = $("#ipadd").val();
    var editType = 'update';
    var finalNum = $("#finalNum").val();
    var finalUser = $("#finalUser").val();
    var tbl = $("#tbl").val();
    var toPrint = $("#toPrint");
    $("#btn_save").attr('disabled',"true");
    $("#btn_print").attr('disabled',"true");
    $("#btn_init").attr('disabled',"true");
    $.post("DetailEdit",{tbl:tbl,editType:editType,phoneNum:phoneNum,userid:userId,shortNum:shortNum,mobile:mobile,phoneIp:phoneIp,vlan:vlan,installer:installer,installTime:installTime,ipadd:ipadd,finalNum:finalNum,finalUser:finalUser,userGroup:userGroup,Balance:Balance,gate:gate,protocal:protocal},function(data){
        if(data=='update_success'){
            $("#btn_save").removeAttr("disabled");
            $("#btn_print").removeAttr("disabled");
            $("#btn_init").removeAttr("disabled");
            if(confirm("操作成功！要打印派工单吗？")){
                PagePrint();
            }
            else{
                window.location.href='newApply.jsp';
            }
        }
        else if(data=='error'){
            $("#btn_save").removeAttr("disabled");
            $("#btn_print").removeAttr("disabled");
            $("#btn_init").removeAttr("disabled");
            alert("操作失败！请联系系统管理员");
        }
        else if(data=='reg_error'){
            $("#btn_save").removeAttr("disabled");
            $("#btn_print").removeAttr("disabled");
            $("#btn_init").removeAttr("disabled");
            alert("注册失败！");
        }
    })
}

function PagePrint(){
    $("#printMobile").html($("#mobile").val());
    $("#printUserid").html($("#userId").val());
    $("#printLongNum").html($("#phoneNum").val());
    $("#printShortNum").html($("#shortNum").val());
    $("#printIp").html($("#phoneIp").val());
    $("#printVlan").html($("#vlan").val());
    $("#printInstaller").html($("#installer").val());
    $("#printInsTime").html($("#installTime").val());
    $("#printGate").html($("#gate").val());
    var toPrint = $("#toPrint");
    toPrint.removeAttr("hidden");
    toPrint.slideDown();
    toPrint.jqprint({ operaSupport: true });
}


function initApplyInfo(){
    var ipadd = $("#ipadd").val();
    var editType = 'init';
    var finalNum = $("#finalNum").val();
    var finalUser = $("#finalUser").val();
    if(confirm("您确定要删除这个号码？"))
    {
        $.post("DetailEdit",{editType:editType,finalNum:finalNum,finalUser:finalUser,ipadd:ipadd},function(data){
            if(data=='init_success'){
                window.location.href='Success.jsp';
            }
            else if(data=='error'){
                alert("操作失败！请重试或联系系统管理员");
            }
        })
    }
    else {
        return false;
    }
}