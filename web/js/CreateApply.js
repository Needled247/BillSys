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
        $("#longNumErr").html("���벻��Ϊ��...����д");
        return;
    }
    var userId = $("#userId").val();
    if(userId==''||userId==null){
        $("#userIdErr").html("�û��˺Ų���Ϊ��...����д");
        return;
    }
    var shortNum = $("#shortNum").val();
    if(shortNum==''||shortNum==null){
        $("#shortNumErr").html("�̺Ų���Ϊ��...����д");
        return;
    }
    var mobile = $("#mobile").val();
    if(mobile==''||mobile==null){
        $("#mobileErr").html("�ֻ����벻��Ϊ��...����д");
        return;
    }
    var phoneIp = $("#phoneIp").val();
    if(phoneIp==''||phoneIp==null){
        $("#phoneIpErr").html("��IP����Ϊ��...����д");
        return;
    }
    var vlan = $("#vlan").val();
    if(vlan==''||vlan==null){
        $("#VlanErr").html("Vlan����Ϊ��...����д");
        return;
    }
    var installer = $("#installer").val();
    if(installer==''||installer==null){
        $("#installerErr").html("��װ�����˲���Ϊ��...����д");
        return;
    }
    var installTime = $("#installTime").val();
    if(installTime==''||installTime==null){
        $("#insTimeErr").html("��װʱ�䲻��Ϊ��...����д");
        return;
    }
    var gate = $("#gate").val();
    if(gate==''||gate==null){
        $("#gateErr").html("���ز���Ϊ��...����д");
        return;
    }
    var userGroup = $("#userGroup").val();
    if(userGroup==''||userGroup==null){
        $("#userGroupErr").html("�û��鲻��Ϊ��...����д");
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
            if(confirm("�����ɹ���Ҫ��ӡ�ɹ�����")){
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
            alert("����ʧ�ܣ�����ϵϵͳ����Ա");
        }
        else if(data=='reg_error'){
            alert("ע��ʧ�ܣ�");
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
    if(confirm("��ȷ��Ҫɾ��������룿"))
    {
        $.post("DetailEdit",{editType:editType,finalNum:finalNum,finalUser:finalUser,ipadd:ipadd},function(data){
            if(data=='init_success'){
                window.location.href='Success.jsp';
            }
            else if(data=='error'){
                alert("����ʧ�ܣ������Ի���ϵϵͳ����Ա");
            }
        })
    }
    else {
        return false;
    }
}