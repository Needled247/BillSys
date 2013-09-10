function createAccount(){
    $("#longNumErr").html("");
    $("#userIdErr").html("");
    $("#shortNumErr").html("");
    $("#mobileErr").html("");
    $("#phoneIpErr").html("");
    $("#VlanErr").html("");
    $("#installerErr").html("");
    $("#insTimeErr").html("");
    $("#gateErr").html("");
    var phoneNum = $("#phoneNum").val();
    if(phoneNum==''||phoneNum==null){
        $("#longNumErr").html("号码不能为空...请填写");
        return;
    }
    var userId = $("#userId").val();
    if(userId==''||userId==null){
        $("#userIdErr").html("用户账号不能为空...请填写");
        return;
    }
    var shortNum = $("#shortNum").val();
    if(shortNum==''||shortNum==null){
        $("#shortNumErr").html("短号不能为空...请填写");
        return;
    }
    var mobile = $("#mobile").val();
    if(mobile==''||mobile==null){
        $("#mobileErr").html("手机号码不能为空...请填写");
        return;
    }
    var phoneIp = $("#phoneIp").val();
    if(phoneIp==''||phoneIp==null){
        $("#phoneIpErr").html("绑定IP不能为空...请填写");
        return;
    }
    var vlan = $("#vlan").val();
    if(vlan==''||vlan==null){
        $("#VlanErr").html("Vlan不能为空...请填写");
        return;
    }
    var installer = $("#installer").val();
    if(installer==''||installer==null){
        $("#installerErr").html("安装负责人不能为空...请填写");
        return;
    }
    var installTime = $("#installTime").val();
    if(installTime==''||installTime==null){
        $("#insTimeErr").html("安装时间不能为空...请填写");
        return;
    }
    var gate = $("#gate").val();
    if(gate==''||gate==null){
        $("#gateErr").html("网关不能为空...请填写");
        return;
    }
    var userGroup = $("#userGroup").val();
    if(userGroup==''||userGroup==null){
        $("#userGroupErr").html("用户组不能为空...请填写");
    }
    var Balance = $("#Balance").val();
    var ipadd = $("#ipadd").val();
    var editType = 'create';
    var finalNum = $("#finalNum").val();
    var finalUser = $("#finalUser").val();
    var tbl = $("#tbl").val();
    var protocal = $("#protocal").val();
    var toPrint = $("#toPrint");
    var isPay = $("#isPay").val();
    $("#btn_save").attr("disabled","true");
    $("#btn_print").attr("disabled","true");
    $("#btn_back").attr("disabled","true");
    $.post("DetailEdit",
        {tbl:tbl,
            editType:editType,
            phoneNum:phoneNum,
            userid:userId,
            shortNum:shortNum,
            mobile:mobile,
            phoneIp:phoneIp,
            vlan:vlan,
            installer:installer,
            installTime:installTime,
            ipadd:ipadd,
            finalNum:finalNum,
            finalUser:finalUser,
            userGroup:userGroup,
            Balance:Balance,
            gate:gate,
            protocal:protocal,
            isPay:isPay
        },
        function(data){
        if(data=='success'){
            if(confirm("操作成功！要打印派工单吗？")){
                $("#btn_save").removeAttr("disabled");
                $("#btn_print").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                PagePrint();
            }
            else{
                window.location.href='createAccount.jsp';
            }
        }
        else if(data=='error'){
            alert("操作失败！请联系系统管理员");
        }
        else if(data=='reg_error'){
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