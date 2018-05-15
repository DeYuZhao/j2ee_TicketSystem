$(function () {
    $.ajax({
       type:'get',
       url:'/member/init_payinfo',
       async:false,
       dataType:'json',
       success:function (data) {
           document.getElementById("form-username").value = data.email;
           document.getElementById("priceinfo").innerHTML = data.price;
       },
        error:function () {
            alert("error");
        }
    });
});
var timeLeft = 900;//这里设定的时间是900秒
function countTime(){
    if(timeLeft == 0){//这里就是时间到了之后应该执行的动作了，这里只是弹了一个警告框
        alert("时间到，订单已取消")
        document.getElementById("ulcancel").click();
    }
    var startMinutes = Math.floor(timeLeft/60);
    var startSec = timeLeft-startMinutes*60;
    document.getElementById('hours').innerHTML = "00";
    if (startSec<10 && startMinutes>=10){
        document.getElementById('minutes').innerHTML = startMinutes;
        document.getElementById('seconds').innerHTML = "0" + startSec;
    }else if (startSec<10 && startMinutes<10){
        document.getElementById('minutes').innerHTML = "0" + startMinutes;
        document.getElementById('seconds').innerHTML = "0" + startSec;
    }else if (startSec>=10 && startMinutes>=10){
        document.getElementById('minutes').innerHTML = startMinutes;
        document.getElementById('seconds').innerHTML = startSec;
    }else if (startSec>=10 && startMinutes<10){
        document.getElementById('minutes').innerHTML = "0" + startMinutes;
        document.getElementById('seconds').innerHTML = startSec;
    }
    timeLeft = timeLeft - 1;
    setTimeout('countTime()',1000);
}

// function checkusebenifit() {
//     var benifit = 0;
//     var twenty = document.getElementById("twenty");
//     var sixty = document.getElementById("sixty");
//     var ninety = document.getElementById("ninety");
//     if (twenty.checked == true){
//         benifit = 20;
//     }else if (sixty.checked == true){
//         benifit = 60;
//     }else if (ninety.checked == true){
//         benifit =  90;
//     }
//     document.getElementById("priceinfo").innerHTML = data.price;
//     setTimeout('checkusebenifit()',1000);
// }
function payForTickets() {
    var password = document.getElementById("form-password").value;
    var benifit = 0;
    var twenty = document.getElementById("twenty");
    var sixty = document.getElementById("sixty");
    var ninety = document.getElementById("ninety");
    if (twenty.checked == true){
        benifit = 20;
    }else if (sixty.checked == true){
        benifit = 60;
    }else if (ninety.checked == true){
        benifit =  90;
    }
    $.ajax({
        type:'post',
        url:'/member/do_payfor_tickets',
        async:false,
        dataType:'json',
        data:{
            password:password,
            benifit:benifit
        },
        success:function (data) {
            alert(data.information);
            if (data.information == "支付成功"){
                document.getElementById("logintojsp").href = "/display/showinfo";
            }
        },
        error:function () {
            alert("error");
        }
    })
}