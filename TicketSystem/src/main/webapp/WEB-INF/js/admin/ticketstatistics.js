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
        url:'/admin/get_venue_page',
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
    var addressarray = [];
    var idarray = [];
    var linkidarray = [];
    $.ajax({
        type:'post',
        url:'/admin/get_venue_page',
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
                cell1.className = "address-info";
                var cell5 = row.insertCell();
                cell5.className = "button-info";
                var cell5id = "binfoid"+i;
                cell5.id = cell5id;
                idarray.push(cell5id);
                addressarray.push(data.venueEntityList[i+(page-1)*10]['address']);
                //这里是数据添加
                cell1.innerHTML = data.venueEntityList[i+(page-1)*10]['address'];
                var link = document.createElement("a");
                link.id = "a"+i;
                link.innerHTML = "查询统计信息";
                linkidarray.push("a"+i);
                cell5.appendChild(link);

            }
        },
        error:function () {
            alert("error");
        }
    });
    for (var i=0;i<idarray.length;i++){
        chi(i);
    }
    function chi(m) {
        var e = document.getElementById(linkidarray[m]);
        e.style.cursor = "pointer";
        e.onclick = function () {
            checkVenueInfo(addressarray[m],linkidarray[m]);
        }
    }
}
function checkVenueInfo(address,linkid) {
    $.ajax({
        type:'post',
        url:'/admin/set_venue_address',
        async:false,
        dateType:'json',
        data:{
            address:address
        },
        success:function (data) {
            document.getElementById(linkid).href = "/admin/onevenuestatistics";
        },
        error:function () {
            alert("error");
        }
    })
}