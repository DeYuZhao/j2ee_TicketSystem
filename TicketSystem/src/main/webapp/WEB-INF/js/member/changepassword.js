$(function () {
    $.ajax({
        type:'get',
        url:'/member/get_current_user',
        async:false,
        dataType:'json',
        success:function (data) {
            $("#form-username").val(data.email);
        },
        error:function () {
            alert("error");
        }
    })
});
function changepassword() {
    var old_password = $("#form-oldpassword").val();
    var new_password = $("#form-newpassword").val();
    var confirm_password = $("#form-confirmnewpassword").val();
    $.ajax({
        type:'post',
        url:'/member/judge_password',
        async:false,
        dataType:'json',
        data:{
            oldpwd: old_password,
            newpwd: new_password,
            cfmpwd: confirm_password
        },
        success:function (data) {
            alert(data.information);
        },
        error:function () {
            alert("error");
        }
    });
}