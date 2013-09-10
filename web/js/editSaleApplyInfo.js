function editSaleApplyInfo(){
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
        $("#alert").html("短号码不能为空...请填写");
        return;
    }
    var mobile = $("#mobile").val();
    if(mobile==''||mobile==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("手机号不能为空...请填写");
        return;
    }
    var money = $("#money").val();
    if(money==''||money==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("金额不能为空...请填写");
        return;
    }
    var pay = $("#pay").val();
    if(pay==''||pay==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("是否付款不能为空...请填写");
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
    var phoneIp = $("#phoneIp").val();
    if(phoneIp==''||phoneIp==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("绑定IP不能为空...请填写");
        return;
    }

    var userGroup = $("#userGroup").val();
    if(userGroup==''||userGroup==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("用户组不能为空...请填写");
        return;
    }
    var protocal = $("#protocal").val();
    var Balance = $("#Balance").val();
    var editType = 'update';
    var finalNum = $("#finalNum").val();
    var finalUser = $("#finalUser").val();
    var gate = $("#gate").val();
    var tbl = $("#tbl").val();
    $("#btn_save").attr('disabled',"true");
    $("#btn_print").attr('disabled',"true");
    $("#btn_init").attr('disabled',"true");
    $("#btn_back").attr('disabled',"true");
    $.post("SaleDetailEdit",{editType:editType,phoneNum:phoneNum,userid:userId,shortNum:shortNum,mobile:mobile,phoneIp:phoneIp,vlan:vlan,installer:installer,installTime:installTime,finalNum:finalNum,finalUser:finalUser,money:money,pay:pay,userGroup:userGroup,Balance:Balance,protocal:protocal,tbl:tbl,gate:gate},function(data){        if(data=='update_success'){
            $("#btn_save").removeAttr("disabled");
            $("#btn_print").removeAttr("disabled");
            $("#btn_init").removeAttr("disabled");
            $("#btn_back").removeAttr("disabled");
            if(confirm("操作成功！要打印派工单吗？")){
                PagePrint();
            }
            else{
                window.location.href='newApply.jsp';
            }
        }
        else if(data=='error'){
            alert("操作失败！请联系系统管理员");
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
    toPrint.jqprint({ operaSupport: true });
}

function initSaleApplyInfo(){
    var editType = 'init';
    var finalNum = $("#finalNum").val();
    var finalUser = $("#finalUser").val();
    var tbl = $("#tbl").val();
    $.post("SaleDetailEdit",{editType:editType,finalNum:finalNum,finalUser:finalUser,tbl:tbl},function(data){
        if(data=='init_success'){
            alert("初始化成功！");
            window.location.href="newSaleApply.jsp";
        }
        else if(data=='error'){
            alert("操作失败！请重试或联系系统管理员");
        }
    })
}