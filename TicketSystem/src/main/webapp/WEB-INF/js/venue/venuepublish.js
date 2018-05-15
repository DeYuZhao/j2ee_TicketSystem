$(function () {
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 0,
        minView: 2,
        forceParse: 0
    });
});
function publish() {
    var name = document.getElementById("form-name").value;
    var date = document.getElementById("plan-date").value;
    var price = document.getElementById("form-username").value;
    var type = document.getElementById("form_type").value;
    var description = document.getElementById("form-description").value;
    if (name==""){
        alert("名称不能为空");
    }else if (date==""){
        alert("日期不能为空");
    }else if (price==""){
        alert("价格不能为空");
    }else if (type==""){
        alert("类型不能为空");
    }else if (description==""){
        alert("描述不能为空");
    }else {
        var plan = {name: name, date: date, price: price, type: type, description: description};
        $.ajax({
            type: 'post',
            url: '/venue/publish_venue_plan',
            async: false,
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(plan),
            success: function (data) {
                alert(data.information);
            },
            error: function () {
                alert("error");
            }
        });
    }
}