function editSaleApplyInfo(){
    var phoneNum = $("#phoneNum").val();
    if(phoneNum==''||phoneNum==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("���벻��Ϊ��...����д");
        return;
    }
    var userId = $("#userId").val();
    if(userId==''||userId==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("�û��˺Ų���Ϊ��...����д");
        return;
    }
    var shortNum = $("#shortNum").val();
    if(shortNum==''||shortNum==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("�̺��벻��Ϊ��...����д");
        return;
    }
    var mobile = $("#mobile").val();
    if(mobile==''||mobile==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("�ֻ��Ų���Ϊ��...����д");
        return;
    }
    var money = $("#money").val();
    if(money==''||money==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("����Ϊ��...����д");
        return;
    }
    var pay = $("#pay").val();
    if(pay==''||pay==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("�Ƿ񸶿��Ϊ��...����д");
        return;
    }
    var vlan = $("#vlan").val();
    if(vlan==''||vlan==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("Vlan����Ϊ��...����д");
        return;
    }
    var installer = $("#installer").val();
    if(installer==''||installer==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("��װ�����˲���Ϊ��...����д");
        return;
    }
    var installTime = $("#installTime").val();
    if(installTime==''||installTime==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("��װʱ�䲻��Ϊ��...����д");
        return;
    }
    var phoneIp = $("#phoneIp").val();
    if(phoneIp==''||phoneIp==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("��IP����Ϊ��...����д");
        return;
    }

    var userGroup = $("#userGroup").val();
    if(userGroup==''||userGroup==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("�û��鲻��Ϊ��...����д");
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
            if(confirm("�����ɹ���Ҫ��ӡ�ɹ�����")){
                PagePrint();
            }
            else{
                window.location.href='newApply.jsp';
            }
        }
        else if(data=='error'){
            alert("����ʧ�ܣ�����ϵϵͳ����Ա");
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
            alert("��ʼ���ɹ���");
            window.location.href="newSaleApply.jsp";
        }
        else if(data=='error'){
            alert("����ʧ�ܣ������Ի���ϵϵͳ����Ա");
        }
    })
}