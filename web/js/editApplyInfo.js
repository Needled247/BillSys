
function editApplyInfo(){
    var protocal = $("#protocal").val();
    if(protocal=='0'){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("Э�鲻��Ϊ��...����д()");
        return;
    }
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
        $("#alert").html("�̺Ų���Ϊ��...����д");
        return;
    }
    var mobile = $("#mobile").val();
    if(mobile==''||mobile==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("�ֻ����벻��Ϊ��...����д");
        return;
    }
    var phoneIp = $("#phoneIp").val();
    if(phoneIp==''||phoneIp==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("��IP����Ϊ��...����д");
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

    var userGroup = $("#userGroup").val();
    if(userGroup==''||userGroup==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("�û��鲻��Ϊ��...����д");
        return;
    }
    var gate = $("#gate").val();
    if(gate==''||gate==null){
        $("#alert_block").show();
        $("#alert").html("");
        $("#alert").html("���ز���Ϊ��...����д");
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
            if(confirm("�����ɹ���Ҫ��ӡ�ɹ�����")){
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
            alert("����ʧ�ܣ�����ϵϵͳ����Ա");
        }
        else if(data=='reg_error'){
            $("#btn_save").removeAttr("disabled");
            $("#btn_print").removeAttr("disabled");
            $("#btn_init").removeAttr("disabled");
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