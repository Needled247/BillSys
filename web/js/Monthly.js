function monthly(){
    var year = $("#monthlyYear").val();
    var month = $("#monthlyMonth").val();
    var userClass = $("#UserClass").val();
    var type = "out";
    $.post("MonthlyAction",{year:year,month:month,userClass:userClass,type:type},function(data){
        //TODO
    })
}

function cost(){
    var year = $("#year").val();
    var month = $("#month").val();
    var type = "cost";
    $.post("MonthlyAction",{year:year,month:month,type:type},function(data){
        //TODO
    })
}