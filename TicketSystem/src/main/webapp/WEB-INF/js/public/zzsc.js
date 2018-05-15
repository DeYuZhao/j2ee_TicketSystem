var page=1;//当前页
var pagesize=10;//每页数据条数
var state = 0;//0位注册，1为修改
$(document).ready(function () {
    initPage();
});
function initPage() {
    $("#pagination-demo-v1_1").remove();
    $("#pagination-on").append('<ul id="pagination-demo-v1_1" class="pagination-sm"></ul>');
    $.ajax({
       type:'post',
       url:'/admin/get_correspond_pageinfo',
       async:false,
       data:{
         start: 0,
         size: 10,
         state:0
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
        url:'/admin/get_correspond_pageinfo',
        dataType:'json',
        async:false,
        data:{
            start:start,
            size:size,
            state:0
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
                var cell2 = row.insertCell();
                cell2.className = "p-info";
                var cell3 = row.insertCell();
                cell3.className = "r-info";
                var cell4 = row.insertCell();
                cell4.className = "c-info";
                var cell5 = row.insertCell();
                cell5.className = "button-info";
                var cell5id = "binfoid"+i;
                cell5.id = cell5id;
                idarray.push(cell5id);
                addressarray.push(data.waitdealvenueEntityList[i+(page-1)*10]['address']);
                //这里是数据添加
                cell1.innerHTML = data.waitdealvenueEntityList[i+(page-1)*10]['address'];
                cell2.innerHTML = data.waitdealvenueEntityList[i+(page-1)*10]['partitionnum'];
                cell3.innerHTML = data.waitdealvenueEntityList[i+(page-1)*10]['rows'];
                cell4.innerHTML = data.waitdealvenueEntityList[i+(page-1)*10]['columns'];
                var link = document.createElement("a");
                link.id = "a"+i;
                link.innerHTML = "查询";
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
        var e = document.getElementById(idarray[m]);
        e.onclick = function () {
            checkVenueRegisterInfo(addressarray[m],linkidarray[m]);
        }
    }
}
function checkVenueRegisterInfo(address,linkid) {
    $.ajax({
        type:'post',
        url:'/admin/get_venueinfo',
        async:false,
        dateType:'json',
        data:{
            address:address,
            state:0
        },
        success:function (data) {
            document.getElementById(linkid).href = "/admin/admincheckregister";
        },
        error:function () {
            alert("error");
        }
    })
}
function loadCheckVenueInfo() {
    $.ajax({
        type:'post',
        url:'/admin/show_venueinfo',
        async:false,
        dateType:'json',
        data:{
            state:0
        },
        success:function (data) {
            document.getElementById("form-username").value = data.email;
            document.getElementById("form-address").value = data.address;
            document.getElementById("form-seatinfo1").value = data.partitionnum;
            document.getElementById("form-seatinfo2").value = data.rows;
            document.getElementById("form-seatinfo3").value = data.columns;
        },
        error:function () {
            alert("error");
        }
    })

}
function pass() {
    var address = document.getElementById("form-address").value;
    $.ajax({
        type:'post',
        url:'/admin/pass_venue_register',
        async:false,
        data:{
            address:address
        },
        dataType:'json',
        success:function (data) {
            alert(data.information);
        },
        error:function () {
            alert("error");
        }
    });
}
function notPass() {
    var address = document.getElementById("form-address").value;
    $.ajax({
        type:'post',
        url:'/admin/notpass_venue_register',
        async:false,
        data:{
            address:address
        },
        dataType:'json',
        success:function (data) {
            alert(data.information);
        },
        error:function () {
            alert("error");
        }
    });
}