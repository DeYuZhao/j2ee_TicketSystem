$(function () {
    $.ajax({
        type:'get',
        url:'/member/get_current_user',
        async:false,
        dataType:'json',
        success:function (data) {
            document.getElementById("form-username").innerHTML = data.email;
            document.getElementById("form-seatinfo1").value = "0";
            document.getElementById("form-seatinfo2").value = "0";
            document.getElementById("form-seatinfo3").value = "0";
        },
        error:function () {
            alert("error");
        }
    })
});
function venueRegister() {
    var email = document.getElementById("form-username").value;
    var password = document.getElementById("form-password").value;
    var confirm_password = document.getElementById("form-confirm-password").value;
    var address = document.getElementById("form-address").value;
    var partitionnum = parseInt(document.getElementById("form-seatinfo1").value);
    var rows = parseInt(document.getElementById("form-seatinfo2").value);
    var columns = parseInt(document.getElementById("form-seatinfo3").value);
    var check = checknum(partitionnum,rows,columns);
    if (password.length<6){
        alert("密码长度不得小于6位");
    }else if(password != confirm_password){
        alert("两次密码输入不一致");
    }else if (address == ""){
        alert("地址不得为空");
    }else if (partitionnum==0 || rows == 0 || columns == 0){
        alert("请选择座位数");
    } else {
            var venue = {email:email, password:password, address:address, partitionnum:partitionnum, rows:rows, columns:columns};
                $.ajax({
                type:'post',
                url:'/venue/do_venue_register',
                async:false,
                contentType:'application/json',
                dataType:'json',
                data:JSON.stringify(venue),
                success:function (data) {
                    alert(data.information);
                },
                error:function () {
                    alert("error");
                }

            })
        }
}
function checknum(num1,num2,num3) {
    var result = [0,0,0];//表示输入数字是否符合,0表示数字格式错误,1表示正确,2表示过大
    if (num1 == 1 || num1 == 2 || num1 == 3 || num1 == 4 || num1 == 5 || num1 == 6){
        result[0] = 1;
    }else if (num1 > 6){
        result[0] = 2;
    }
    if (num2 == 1 || num2 == 2 || num2 == 3 || num2 == 4 || num2 == 5 || num2 == 6 ||
        num2 == 7 || num2 == 8 || num2 == 9 || num2 == 10){
        result[1] = 1;
    }else if (num2 > 10){
        result[1] = 2;
    }
    if (num3 == 1 || num3 == 2 || num3 == 3 || num3 == 4 || num3 == 5 || num3 == 6 ||
        num3 == 7 || num3 == 8 || num3 == 9 || num3 == 10 || num3 == 11 || num3 == 12 ||
        num3 == 13 || num3 == 14 || num3 == 15){
        result[2] = 1;
    }else if (num3 > 15){
        result[2] = 2;
    }
    return result;
}