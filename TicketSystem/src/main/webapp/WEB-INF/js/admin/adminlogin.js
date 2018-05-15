function login() {
    var username = document.getElementById('form-username').value;
    var password = document.getElementById('form-password').value;
    $.ajax({
        type:'post',
        url:'/admin/login',
        async:false,
        dataType:'json',
        data:{
            username: username,
            password: password
        },
        success:function (data) {
            if (data.loginState){
                alert("登录成功")
                document.getElementById("logintojsp").href = "/admin/adminregisterlist";
            }else{
                alert("登录失败")
            }
        },
        error:function (data) {
            alert("error")
        }

    })
}