var page=1;//当前页
var pagesize=10;//每页数据条数
$(document).ready(function () {
    initPage();
});
function initPage() {
    $("#pagination-demo-v1_1").remove();
    $("#pagination-on").append('<ul id="pagination-demo-v1_1" class="pagination-sm"></ul>');
    $.ajax({
        type:'post',
        url:'/admin/get_settle_accountpage',
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
    var namearray = [];
    var pricearray = [];
    var linkidarray = [];
    $.ajax({
        type:'post',
        url:'/admin/get_settle_accountpage',
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
                var row = table.insertRow();
                var cell1 = row.insertCell();
                var cell2 = row.insertCell();
                var cell3 = row.insertCell();
                var cell5 = row.insertCell();
                var cell5id = "binfoid"+i;
                cell5.id = cell5id;
                namearray.push(data.showinfoList[i+(page-1)*10]['name']);
                //这里是数据添加
                cell1.innerHTML = data.showinfoList[i+(page-1)*10]['address'];
                cell2.innerHTML = data.showinfoList[i+(page-1)*10]['name'];
                cell3.innerHTML = "¥"+data.showinfoList[i+(page-1)*10]['price'];
                pricearray.push(parseInt(data.showinfoList[i+(page-1)*10]['price']));
                var link = document.createElement("a");
                link.id = "a"+i;
                link.innerHTML = "结算";
                linkidarray.push("a"+i);
                cell5.appendChild(link);
            }
        },
        error:function () {
            alert("error");
        }
    });
    for (var i=0;i<linkidarray.length;i++){
        chi(i);
    }
    function chi(m) {
        var e = document.getElementById(linkidarray[m]);
        e.onclick = function () {
            settleAccount(namearray[m],pricearray[m],linkidarray[m]);
        }
    }
}
function settleAccount(name,price,linkid) {
    $.ajax({
        type:'post',
        url:'/admin/settle_account',
        async:false,
        dateType:'json',
        data:{
            name:name,
            price:price
        },
        success:function (data) {
            alert(data.information);
            document.getElementById(linkid).href = "";
        },
        error:function () {
            alert("error");
        }
    })
}