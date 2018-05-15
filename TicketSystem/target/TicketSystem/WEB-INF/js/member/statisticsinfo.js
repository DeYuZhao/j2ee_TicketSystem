$(function () {
    $.ajax({
        type: 'get',
        url: '/member/get_current_user',
        async: false,
        dataType: 'json',
        success: function (data) {
            document.getElementById("basicinfo-content-top-info").innerHTML = "账户："+data.email;
        },
        error: function () {
            alert("error");
        }
    });
});