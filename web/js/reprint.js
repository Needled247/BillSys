function reprint(){
    var longNum = $("#longNum").val();
    var account = $("#account").val();
    window.location.href='rePrint.jsp?longNum='+longNum+'&account='+account;
}