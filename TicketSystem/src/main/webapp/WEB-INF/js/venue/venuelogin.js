function login() {
    var loginCode = document.getElementById("form-username").value;
    var password = document.getElementById("form-password").value;
    $.ajax({
       type:'post',
       url:'/venue/do_venue_login',
       async:false,
       data:{
           logincode:loginCode,
           password:password
       },
        dataType:'json',
       success:function (data) {
            alert(data.information);
            if (data.information == "登录成功"){
                document.getElementById("registertojsp").href = "/venue/venueinfo";
            }
       },
       error:function () {
           alert("error");
       }
    });
}