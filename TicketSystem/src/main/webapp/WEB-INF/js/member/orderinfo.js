var page=1;//当前页
var pagesize=4;//每页数据条数
var state = 0;//0为等待检票，1为等待配票，2为已经检票，3为失效订单，4为退订
var stateTextArray = ["等待检票","等待配票","已经检票","失效订单","已退订"];
$(document).ready(function () {
    //需要在页面初始化时处理配票订单和失效订单
    document.getElementById("whole-order").style.borderBottom = "2px solid rgb(15,120,159)";
    document.getElementById("wait-match").style.borderBottom = "2px solid #ffffff";
    document.getElementById("prepare-ticket").style.borderBottom = "2px solid #ffffff";
    document.getElementById("outdate-ticket").style.borderBottom = "2px solid #ffffff";
    document.getElementById("return-ticket").style.borderBottom = "2px solid #ffffff";
    dealOrder();
    initPage();
});
function dealOrder() {
    $.ajax({
        type: 'get',
        url: '/member/deal_order',
        async: false,
        dataType: 'json',
        success: function (data) {

        },
        error: function () {
            alert("error");
        }
    });
}
function initPage() {
    $("#pagination-demo-v1_1").remove();
    $("#pagination-on").append('<ul id="pagination-demo-v1_1" class="pagination-sm"></ul>');
    $.ajax({
        type:'post',
        url:'/member/get_checked_tickets',
        async:false,
        data:{
            start: 0,
            size: 4,
            state:state
        },
        dataType:'json',
        success:function (data) {
            var total = data.total;//总数据条数
            var pagecount=0;
            if (total%pagesize == 0){
                pagecount = parseInt(total/pagesize);
            }else {
                pagecount = parseInt(total/pagesize)+1;
            }
            pagecount=(pagecount==0?1:pagecount);
            $("#pagination-demo-v1_1").twbsPagination({
                totalPages: pagecount,
                visiblePages: pagecount<3?pagecount:3,
                version: '1.1',
                first:"首页",
                prev:"上一页",
                next:"下一页",
                last:"末页",
                loop:false,
                href:"javascript:void(0)",
                onPageClick:function(event,index){
                    page = index;
                    getInfoList(0,page*4);//数据查询列表
                }
            });
        },
        error:function () {
            alert("error");
        }
    });
}
function getInfoList(start,size) {
    var returnIdArray = [];
    var orderIdArray = [];
    $.ajax({
        type: 'post',
        url: '/member/get_checked_tickets',
        dataType: 'json',
        async: false,
        data: {
            start: start,
            size: size,
            state: state
        },
        success: function (data) {
            var orderList = data.orderEntityList;
            var timeMap = data.planDateMap;
            var addressMap = data.planAddressMap;
            var end = (orderList.length>page*4?page*4:orderList.length);
            for (var i=(page-1)*4;i<end;i++){
                var divNumber = i-(page-1)*4;
                document.getElementsByClassName("imgclass")[divNumber].src = "../../images/member/orderlogo.png";
                var time = document.getElementsByClassName("order-time")[divNumber];
                var address = document.getElementsByClassName("order-address")[divNumber];
                var number = document.getElementsByClassName("order-num")[divNumber];
                var payfortime = document.getElementsByClassName("order-total")[divNumber];
                var orderstate = document.getElementsByClassName("order-state")[divNumber];
                var returnstate = document.getElementsByClassName("order-return")[divNumber];
                time.innerHTML = timeMap[orderList[i].name];
                address.innerHTML = addressMap[orderList[i].name];
                var seatInfoArray = (orderList[i].seatinfo).split(",");
                var seatInfo = "";
                for (var j=0;j<seatInfoArray.length-1;j++){
                    var row = seatInfoArray[j].split("_")[0];
                    var column = seatInfoArray[j].split("_")[1];
                    seatInfo += row+"排"+column+"座 ";
                }
                number.innerHTML = seatInfo+"  合计:"+orderList[i].totalprice+"元";
                payfortime.innerHTML = "下单时间:"+orderList[i].date;
                orderstate.innerHTML = getOrderState(orderList[i].orderstate);
                if (orderList[i].orderstate == "WAIT_CHECK_TICKET" || orderList[i].orderstate == "WAIT_ALLOCATE_TICKET") {
                    returnstate.innerHTML = "退票";
                    returnstate.id = "returnId"+i;
                    returnIdArray.push("returnId"+i);
                    orderIdArray.push(orderList[i].id);
                    orderstate.style.backgroundColor = "rgb(15,120,159)";
                    returnstate.style.backgroundColor = "rgb(15,120,159)";
                }else {
                    orderstate.style.backgroundColor = "rgb(15,120,159)";
                    returnstate.style.backgroundColor = "white";
                }

            }
            for (var i=end;i<page*4;i++){
                var divNumber = i-(page-1)*4;
                document.getElementsByClassName("imgclass")[divNumber].src = "../../images/member/blank.png";
                var time = document.getElementsByClassName("order-time")[divNumber];
                var address = document.getElementsByClassName("order-address")[divNumber];
                var number = document.getElementsByClassName("order-num")[divNumber];
                var payfortime = document.getElementsByClassName("order-total")[divNumber];
                var state = document.getElementsByClassName("order-state")[divNumber];
                var returnstate = document.getElementsByClassName("order-return")[divNumber];
                time.innerHTML = "";
                address.innerHTML = "";
                number.innerHTML = "";
                payfortime.innerHTML = "";
                state.innerHTML = "";
                returnstate.innerHTML = "";
                state.style.backgroundColor = "white";
                returnstate.style.backgroundColor = "white";
            }
        },
        error: function () {
            alert("error");
        }
    });
    for (var i=0;i<returnIdArray.length;i++){
        chi(i);
    }
    function chi(m) {
        var e = document.getElementById(returnIdArray[m]);
        e.onclick = function () {
            dealReturn(orderIdArray[m]);
        }
    }
}
function dealReturn(orderId) {
    $.ajax({
        type: 'get',
        url: '/member/return_ticket',
        async: false,
        dataType: 'json',
        data:{
          orderId:orderId
        },
        success: function (data) {
            alert(data.information);
            location.reload();
        },
        error: function () {
            alert("error");
        }
    });
}
function state0click() {
    document.getElementById("whole-order").style.borderBottom = "2px solid rgb(15,120,159)";
    document.getElementById("wait-match").style.borderBottom = "2px solid #ffffff";
    document.getElementById("prepare-ticket").style.borderBottom = "2px solid #ffffff";
    document.getElementById("outdate-ticket").style.borderBottom = "2px solid #ffffff";
    document.getElementById("return-ticket").style.borderBottom = "2px solid #ffffff";
    state = 0;
    initPage();
}
function state1click() {
    document.getElementById("whole-order").style.borderBottom = "2px solid #ffffff";
    document.getElementById("wait-match").style.borderBottom = "2px solid rgb(15,120,159)";
    document.getElementById("prepare-ticket").style.borderBottom = "2px solid #ffffff";
    document.getElementById("outdate-ticket").style.borderBottom = "2px solid #ffffff";
    document.getElementById("return-ticket").style.borderBottom = "2px solid #ffffff";
    state = 1;
    initPage();
}
function state2click() {
    document.getElementById("whole-order").style.borderBottom = "2px solid #ffffff";
    document.getElementById("wait-match").style.borderBottom = "2px solid #ffffff";
    document.getElementById("prepare-ticket").style.borderBottom = "2px solid rgb(15,120,159)";
    document.getElementById("outdate-ticket").style.borderBottom = "2px solid #ffffff";
    document.getElementById("return-ticket").style.borderBottom = "2px solid #ffffff";
    state = 2;
    initPage();

}
function state3click() {
    document.getElementById("whole-order").style.borderBottom = "2px solid #ffffff";
    document.getElementById("wait-match").style.borderBottom = "2px solid #ffffff";
    document.getElementById("prepare-ticket").style.borderBottom = "2px solid #ffffff";
    document.getElementById("outdate-ticket").style.borderBottom = "2px solid rgb(15,120,159)";
    document.getElementById("return-ticket").style.borderBottom = "2px solid #ffffff";
    state = 3;
    initPage();
}
function state4click() {
    document.getElementById("whole-order").style.borderBottom = "2px solid #ffffff";
    document.getElementById("wait-match").style.borderBottom = "2px solid #ffffff";
    document.getElementById("prepare-ticket").style.borderBottom = "2px solid #ffffff";
    document.getElementById("outdate-ticket").style.borderBottom = "2px solid #ffffff";
    document.getElementById("return-ticket").style.borderBottom = "2px solid rgb(15,120,159)";
    state = 4;
    initPage();
}
function getOrderState(orderState) {
    switch (orderState){
        case "WAIT_CHECK_TICKET":return "等待检票";break;
        case "WAIT_ALLOCATE_TICKET":return "等待配票";break;
        case "HAVE_CHECKED":return "已经检票";break;
        case "OUT_DATE_TICKET":return "失效订单";break;
        case "RETURN_TICKET":return "已退订";break;
    }
}