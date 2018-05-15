var judgeEmail = false;
function login() {
    if (judgeEmail) {
        var email = document.getElementById("form-username").value;
        var password = document.getElementById("form-password").value;
        var code = document.getElementById("form-code").value;
        $.ajax({
            type: 'post',
            url: 'executeLogin',
            async: false,
            data: {
                email: email,
                password: password,
                code: code
            },
            success: function (data) {
                alert(data.information);
                if (data.information == "登录成功") {
                    document.getElementById("logintojsp").href = data.href;
                }else{
                    alert("登录失败");
                }
            }
        })
    }else{
        alert("请先获取验证码");
    }

}
function sendCode() {
    var email = document.getElementById("form-username").value;
    $.ajax({
        type:'post',
        url:'sendLoginMail',
        async:false,
        data:{
            email: email
        },
        dataType:'json',
        success:function (data) {
            alert(data.information);
            judgeEmail = true;
        },
        error:function (data) {
            alert("发送失败");
        }
    })
}