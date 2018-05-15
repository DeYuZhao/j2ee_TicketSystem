$(function () {
   $.ajax({
       type:'get',
       url:'/member/load_member_info',
       async:false,
       dataType:'json',
       success:function (data) {
           document.getElementById("form-username").innerHTML = data.email;
           if (data.membership == 1){
               document.getElementById("membership").innerHTML = "会员";
               document.getElementById("delete-membership").style.backgroundColor = "rgb(15,120,159)";
               document.getElementById("delete-membership").style.cursor = "pointer";
           } else {
               document.getElementById("membership").innerHTML = "非会员";
               document.getElementById("delete-membership").style.backgroundColor = "white";
               document.getElementById("delete-membership").style.cursor = "auto";
           }
           document.getElementById("memberlevel-div").innerHTML = data.memberlevel;
           document.getElementById("memberpoint-div").innerHTML = data.integration;
           document.getElementById("coupon-div1-num-s1").innerHTML = data.benifitA;
           document.getElementById("coupon-div1-num-s2").innerHTML = data.benifitB;
           document.getElementById("coupon-div1-num-s3").innerHTML = data.benifitC;
       },
       error:function () {
           alert("error");
       }
   })
});
function cancelMembership() {
    var con = confirm("撤销会员将会失去一切有关会员的优惠，并且无法恢复，请问是否确认撤销？");
    if (con == true){
        $.ajax({
            type:'get',
            url:'/member/cancel_membership',
            async:false,
            dataType:'json',
            success:function (data) {
                alert(data.information);
                if (data.information == "撤销成功") {
                    document.getElementById("delete-membership").style.backgroundColor = "white";
                    document.getElementById("delete-membership").style.cursor = "auto";
                    document.getElementById("membership").innerHTML = "非会员";
                }
            },
            error:function () {
                alert("error")
            }
        });


    }
}
function convertBenifit() {
    var kind = document.getElementById("form_coupon").value;
    if (kind == "coupon-0"){
        alert("请选择兑换优惠券类型");
    }else{
        var number;
        if (kind == "coupon-1"){
            number = 2000;
        }else if (kind == "coupon-2"){
            number = 5000;
        }else {
            number = 8000;
        }
        $.ajax({
            type:'post',
            url:'/member/convert_benifits',
            async:false,
            dataType:'json',
            data:{
                number:number
            },
            success:function (data) {
                alert(data.information);
            },
            error:function () {
                alert("error");
            }
        })
    }
}