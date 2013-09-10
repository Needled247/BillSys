function initBilling(){
    var longNum = $("#longNum").val();
    $.getJSON("BillingPrintAction",{longNum:longNum},function(data){
        $("#printName").html("");
        $("#printMobile").html("");
        $("#printUserid").html("");
        $("#printGate").html("");
        $("#printAddress").html("");
        $("#printMac").html("");
        $("#printLongNum").html("");
        $("#printShortNum").html("");
        $("#printIp").html("");
        $("#printVlan").html("");
        $("#printInstaller").html("");
        $("#printInsTime").html("");
        var name='',mobile='',userid='',gate='',address='',mac='',longNum='',shortNum='',ip='',vlan='',installer='',instime='';
        $.each(data.tables,function(i,item){
            name = item.name;
            mobile = item.mobile;
            userid = item.userid;
            gate = item.gate;
            address = item.address;
            mac = item.mac;
            longNum = item.longNum;
            shortNum = item.shortNum;
            ip = item.ip;
            vlan = item.vlan;
            installer = item.installer;
            instime = item.installtime;
        });
        $("#printName").html(name);
        $("#toPrint").show();
    })
}