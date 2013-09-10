function SaveStrategyInfo(){
    var profile = $("#profile").val();
    var prefix = $("#prefix").val();
    var ratestime1 = $("#ratestime1").val();
    var rates1 = $("#rates1").val();
    var ratestime2 = $("#ratestime2").val();
    var rates2 = $("#rates2").val();
    var ratestime3 = $("#ratestime3").val();
    var rates3 = $("#rates3").val();
    var otherfee = $("#otherfee").val();
    var specialtime1 = $("#specialtime1").val();
    var specialtimeend1 = $("#specialtimeend1").val();
    var specialtime2 = $("#specialtime2").val();
    var specialtimeend2 = $("#specialtimeend2").val();
    var specialtime3 = $("#specialtime3").val();
    var specialtimeend3 = $("#specialtimeend3").val();
    var specialfee1 = $("#specialfee1").val();
    var specialfee2 = $("#specialfee2").val();
    var specialfee3 = $("#specialfee3").val();
    var finalPrefix = $("#finalPrefix").val();
    var userGroup = $("#group").val();
    var id = $("#id").val();
    var type = 'update';
    $.post("StrategyManage",{type:type,id:id,userGroup:userGroup,prefix:prefix,profile:profile,finalPrefix:finalPrefix,ratestime1:ratestime1,rates1:rates1,
            ratestime2:ratestime2,rates2:rates2,ratestime3:ratestime3,rates3:rates3,otherfee:otherfee,
            specialtime1:specialtime1,specialtimeend1:specialtimeend1,specialtime2:specialtime2,specialtimeend2:specialtimeend2,
            specialtime3:specialtime3,specialtimeend3:specialtimeend3,specialfee1:specialfee1,specialfee2:specialfee2,specialfee3:specialfee3},
        function(data){
            if(data=='success'){
                window.location.href='StraSucc.jsp';
            }
            else{
                alert("保存失败！请联系管理员");
            }
        });
}