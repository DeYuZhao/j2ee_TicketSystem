/**
 * Created by Administrator on 2018/1/28.
 */
var judgeEmail = false;
function sendCode() {
    var email = document.getElementById("form-username").value;
    $.ajax({
        type:'post',
        url:'sendMail',
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
function register() {
    if (judgeEmail) {
        var email = document.getElementById("form-username").value;
        var password = document.getElementById("form-password").value;
        var confirmpassword = document.getElementById("form-confirm-password").value;
        var code = document.getElementById("form-code").value;
        $.ajax({
            type:'post',
            url:'executeRegister',
            async:false,
            data:{
                email: email,
                password: password,
                confirmpassword: confirmpassword,
                code: code
            },
            success:function (data) {
                alert(data.information);
                if (data.href != null){
                    document.getElementById("registertojsp").href = data.href;
                }
            }
        })
    }else{
        alert("请先获取验证码");
    }

}
