$(function () {
    $.ajax({
        type:'get',
        url:'/display/init_firstpage',
        async:false,
        dataType:'json',
        success:function (data) {
            for (var i=0;i<data.length;i++){
                var aid = "ainfo"+(i+1);
                var priceid = "p"+(i+1)+"text";
                document.getElementById(aid).innerHTML = data[i].name;
                document.getElementById(aid).title = data[i].name;
                var pricearray = data[i].price;
                document.getElementById(priceid).innerHTML = pricearray.split(",",1);
            }
        },
        error:function () {
            alert("error");
        }
    })
});
function deliverTitle(title) {
    $.ajax({
        type:'post',
        url:'/display/set_visit_title',
        async:false,
        dataType:'json',
        data:{
            title:title
        },
        success:function (data) {

        },
        error:function () {
            alert("error");
        }
    });
}