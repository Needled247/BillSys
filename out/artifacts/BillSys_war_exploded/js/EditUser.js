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
            alert('�޸ĳɹ���');
            window.location.href='ViewUser.jsp';
        }
        else{
            alert('�޸�ʧ�ܣ�����ϵ����Ա��');
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
    if(confirm('ȷ��Ҫɾ�����û���\n\rע�⣺ɾ�����û����������᷵��δʹ�ú���أ������豸�Ͻ��ע�ᡣ')){
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
                alert('ɾ��ʧ�ܣ�������...');
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
    if(confirm('Ҫ��'+shortNum+'���뿪ͨ����ͨ��ҵ����')){
        $("#btn_save").attr("disabled","true");
        $("#btn_del").attr("disabled","true");
        $("#btn_back").attr("disabled","true");
        $("#btn_openLocal").attr("disabled","true");
        $("#btn_closeLocal").attr("disabled","true");
        $("#btn_openLong").attr("disabled","true");
        $("#btn_closeLong").attr("disabled","true");
        $.post("EditUser",{shortNum:shortNum,type:type},function(data){
            if(data=='openLocalSuccess'){
                alert('���뱾��ͨ��ҵ��ͨ�ɹ���');
                $("#btn_save").removeAttr("disabled");
                $("#btn_del").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                $("#btn_openLocal").removeAttr("disabled");
                $("#btn_closeLocal").removeAttr("disabled");
                $("#btn_openLong").removeAttr("disabled");
                $("#btn_closeLong").removeAttr("disabled");
            }
            else{
                alert('��ͨʧ�ܣ��ú�������Ѿ���ͨ����ҵ��');
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
    if(confirm('Ҫ��'+shortNum+'����رձ���ͨ��ҵ����')){
        $("#btn_save").attr("disabled","true");
        $("#btn_del").attr("disabled","true");
        $("#btn_back").attr("disabled","true");
        $("#btn_openLocal").attr("disabled","true");
        $("#btn_closeLocal").attr("disabled","true");
        $("#btn_openLong").attr("disabled","true");
        $("#btn_closeLong").attr("disabled","true");
        $.post("EditUser",{shortNum:shortNum,type:type},function(data){
            if(data=='offLocalSuccess'){
                alert('���뱾��ͨ��ҵ��رճɹ���');
                $("#btn_save").removeAttr("disabled");
                $("#btn_del").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                $("#btn_openLocal").removeAttr("disabled");
                $("#btn_closeLocal").removeAttr("disabled");
                $("#btn_openLong").removeAttr("disabled");
                $("#btn_closeLong").removeAttr("disabled");
            }
            else{
                alert('�ر�ʧ�ܣ������ԡ�');
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
    if(confirm('Ҫ��'+shortNum+'���뿪ͨ��;ͨ��ҵ����')){
        $("#btn_save").attr("disabled","true");
        $("#btn_del").attr("disabled","true");
        $("#btn_back").attr("disabled","true");
        $("#btn_openLocal").attr("disabled","true");
        $("#btn_closeLocal").attr("disabled","true");
        $("#btn_openLong").attr("disabled","true");
        $("#btn_closeLong").attr("disabled","true");
        $.post("EditUser",{shortNum:shortNum,type:type},function(data){
            if(data=='openLongSuccess'){
                alert('���볤;ͨ��ҵ��ͨ�ɹ���');
                $("#btn_save").removeAttr("disabled");
                $("#btn_del").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                $("#btn_openLocal").removeAttr("disabled");
                $("#btn_closeLocal").removeAttr("disabled");
                $("#btn_openLong").removeAttr("disabled");
                $("#btn_closeLong").removeAttr("disabled");
            }
            else{
                alert('��ͨʧ�ܣ��ú�������Ѿ���ͨ����ҵ��');
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
    if(confirm('Ҫ��'+shortNum+'����رճ�;ͨ��ҵ����')){
        $("#btn_save").attr("disabled","true");
        $("#btn_del").attr("disabled","true");
        $("#btn_back").attr("disabled","true");
        $("#btn_openLocal").attr("disabled","true");
        $("#btn_closeLocal").attr("disabled","true");
        $("#btn_openLong").attr("disabled","true");
        $("#btn_closeLong").attr("disabled","true");
        $.post("EditUser",{shortNum:shortNum,type:type},function(data){
            if(data=='offLongSuccess'){
                alert('���볤;ͨ��ҵ��رճɹ���');
                $("#btn_save").removeAttr("disabled");
                $("#btn_del").removeAttr("disabled");
                $("#btn_back").removeAttr("disabled");
                $("#btn_openLocal").removeAttr("disabled");
                $("#btn_closeLocal").removeAttr("disabled");
                $("#btn_openLong").removeAttr("disabled");
                $("#btn_closeLong").removeAttr("disabled");
            }
            else{
                alert('�ر�ʧ�ܣ������ԡ�');
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