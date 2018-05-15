var page=1;//当前页
var pagesize=10;//每页数据条数
$(function () {
    initPage();
});
function initPage() {
    $("#pagination-demo-v1_1").remove();
    $("#pagination-on").append('<ul id="pagination-demo-v1_1" class="pagination-sm"></ul>');
    $.ajax({
        type:'post',
        url:'/venue/get_planinfo_byaddress',
        async:false,
        data:{
            start: 0,
            size: 10
        },
        dataType:'json',
        success:function (data) {
            var total = data.total;//总数据条数
            var pagecount=Math.floor(total/pagesize)+(total%pagesize!=0?1:0);//总页数
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
                    getInfoList(0,page*10);//数据查询列表
                }
            });
        },
        error:function () {
            alert("error");
        }
    });
}
function getInfoList(start,size) {
    var checkIdArray = [];
    var sellIdArray = [];
    var planArray = [];
    $.ajax({
        type:'post',
        url:'/venue/get_planinfo_byaddress',
        dataType:'json',
        async:false,
        data:{
            start:start,
            size:size
        },
        success:function (data) {
            var tab = document.getElementById("list-table") ;
            var length = tab.rows.length;
            for (var m=1; m < length; m++){
                tab.deleteRow(1);
            }
            var total = data.total;//总数据条数
            var temp = total-(page-1)*10;
            var num = temp<10?temp:10;
            var table = document.getElementById("list-table");
            for (var i=0;i<num;i++){
                if (checkDate(data.planEntities[i+(page-1)*10]['date'])) {
                    var row = table.insertRow();
                    var cell1 = row.insertCell();
                    var cell2 = row.insertCell();
                    var cell3 = row.insertCell();
                    //这里是数据添加
                    cell1.innerHTML = data.planEntities[i + (page - 1) * 10]['name'];
                    var link = document.createElement("a");
                    link.id = "a" + i;
                    checkIdArray.push("a" + i);
                    link.innerHTML = "检票";
                    link.style.cursor = "pointer";
                    cell2.appendChild(link);
                    var link2 = document.createElement("a");
                    link2.id = "a2" + i;
                    sellIdArray.push("a2" + i);
                    link2.innerHTML = "售票";
                    link2.style.cursor = "pointer";
                    cell3.appendChild(link2);
                    planArray.push(data.planEntities[i + (page - 1) * 10]['name']);
                }
            }
        },
        error:function () {
            alert("error");
        }
    });
    for (var i=0;i<checkIdArray.length;i++){
        chi(i);
        sell(i);
    }
    function chi(m) {
        var e = document.getElementById(checkIdArray[m]);
        e.onclick = function () {
            checkTicket(checkIdArray[m],planArray[m]);
        }
    }
    function sell(m) {
        var e = document.getElementById(sellIdArray[m]);
        e.onclick = function () {
            sellTicket(sellIdArray[m],planArray[m]);
        }
    }
}
function checkTicket(id,planname) {
    alert(planname)
    $.ajax({
        type:'post',
        url:'/venue/set_planname',
        async:false,
        dataType:'json',
        data:{
            planName:planname
        },
        success:function (data) {
            document.getElementById(id).href = "/venue/venuecheckticket";
        },
        error:function () {
            alert("error");
        }
    });
}
function sellTicket(id,planname) {
    // alert(planname)
    $.ajax({
        type:'post',
        url:'/venue/set_planname',
        async:false,
        dataType:'json',
        data:{
            planName:planname
        },
        success:function (data) {
            document.getElementById(id).href = "/venue/venuesellticket";
        },
        error:function () {
            alert("error");
        }
    });
}
function checkDate(date) {
    var now = new Date();
    var nowMonth = now.getMonth()+1;
    var nowDay = now.getDate();
    var nowHour = now.getHours();
    var endMonth = parseInt(date.charAt(5))*10+parseInt(date.charAt(6));
    var endDay = parseInt(date.charAt(8))*10+parseInt(date.charAt(9));
    var endHour = parseInt(date.charAt(11))*10+parseInt(date.charAt(12));
    if (nowMonth == endMonth && nowDay == endDay && nowHour<endHour-1){
        return true;
    }
    return false;
}
