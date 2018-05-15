var pricearray;
// var nowPriceIndex = 0;
var priceIdArray = [];
var nowPrice;
$(function () {
   $.ajax({
       type:'get',
       url:'/display/get_showinfo',
       async:false,
       dataType:'json',
       success:function (data) {
            setInfo(data.name,data.date,data.address,data.price);
       },
       error:function () {
           alert("error");
       }
   });
});
function setInfo(name,date,address,price) {
    document.getElementById("right-first-showname").innerHTML = name;
    document.getElementById("right-second-timeinfo").innerHTML = date;
    document.getElementById("right-third-addressinfo").innerHTML = address;
    pricearray = price.split(",");
    var parentDiv = document.getElementById("right-fourth-ticket");
    for (var i=0;i<pricearray.length;i++){
        var div = document.createElement("div");
        div.className = "right-fourth-ticketinfo";
        div.id = "priceid"+i;
        priceIdArray.push("priceid"+i);
        div.innerHTML = pricearray[i]+"票面";
        if (i == 0) {
            div.style.backgroundColor = "rgb(15,120,159)";
            div.style.color = "white";
            nowPrice = pricearray[0];
        }
        parentDiv.appendChild(div);
    }
    for (var i=0;i<priceIdArray.length;i++){
        t(i);
    }
    function t(m) {
        var e = document.getElementById(priceIdArray[m]);
        e.onclick = function () {
            nowPrice = pricearray[m];
            e.style.backgroundColor = "rgb(15,120,159)";
            e.style.color = "white";
            var nownum = document.getElementById("num_box").innerHTML;
            document.getElementById("totalprice-number").innerHTML = nowPrice*nownum;
            for (var j=0;j<m;j++){
                var other = document.getElementById(priceIdArray[j]);
                other.style.backgroundColor = "white";
                other.style.color = "rgb(15,120,159)";
            }
            for (var j=m+1;j<priceIdArray.length;j++){
                var other = document.getElementById(priceIdArray[j]);
                other.style.backgroundColor = "white";
                other.style.color = "rgb(15,120,159)";
            }
        }
    }
    document.getElementById("totalprice-number").innerHTML = nowPrice;
}
function add() {
    var nownum = document.getElementById("num_box").innerHTML;
    if (nownum<20) {
        nownum++;
        document.getElementById("num_box").innerHTML = nownum;
        document.getElementById("totalprice-number").innerHTML = nownum * nowPrice;
    }else {
        alert("立即购买最多购买20张");
    }
}
function decrease() {
    var nownum = document.getElementById("num_box").innerHTML;
    if (nownum>1) {
        nownum--;
        document.getElementById("num_box").innerHTML = nownum;
        document.getElementById("totalprice-number").innerHTML = nownum * nowPrice;
    }
}
function postPrice() {
    var price = document.getElementById("totalprice-number").innerHTML;
    var seatnum = parseInt(document.getElementById("num_box").innerHTML);
    var seat = ['default'];
    $.ajax({
        type: 'post',
        url: '/member/post_choose_seat',
        async: false,
        dateType: 'json',
        data: {
            chooseSeat:seat,
            seatnum:seatnum,
            totalPrice:price,
            orderType: 0 //1为选座购买，0为立即购买
        },
        traditional:true,
        success: function (data) {
            document.getElementById("randomchooseid").href = "/member/payforticket";
        },
        error: function () {
            alert("error");
        }
    });
}