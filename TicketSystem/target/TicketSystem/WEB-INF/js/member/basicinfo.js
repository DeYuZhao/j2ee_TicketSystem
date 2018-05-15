$(function () {
    $.ajax({
        type:'get',
        url:'/member/load_basic_info',
        async:false,
        dataType:'json',
        success:function (data) {
            setLoadInfo(data);
        },
        error:function (data) {
            alert("error");
        }
    });
})
function setLoadInfo(data) {
    var nickname = document.getElementById('form-username');
    var man = document.getElementById('man');
    var woman = document.getElementById('woman');
    var birthday = document.getElementById('form_birthday');
    var marriage = document.getElementById('form_marriage');
    var education = document.getElementById('form_education');
    var bussiness = document.getElementById('form_bussiness');
    var income = document.getElementById('form_income');
    var balance1 = document.getElementById("form_balance");
    var address = document.getElementById('form_address');
    nickname.value = data.nickname;
    if (data.sex == "man"){
        man.checked = true;
        woman.checked = false;
    }else{
        man.checked = false;
        woman.checked = true;
    }
    birthday.value = data.birthday;
    if (data.marriage == "") {
        marriage.value = "marriage-0";
    }else{
        marriage.value = data.marriage;
    }
    if (data.education == "") {
        education.value = "education-0";
    }else{
        education.value = data.education;
    }
    if (data.bussiness == "") {
        bussiness.value = "bussiness-0";
    }else{
        bussiness.value = data.bussiness;
    }
    if (data.income == "") {
        income.value = "income-0";
    }else{
        income.value = data.income;
    }
    balance1.innerHTML = data.balance;
    address.value = data.address;
}
function saveInfo() {
    var nickname = document.getElementById('form-username').value;
    var sex;
    var man = document.getElementById('man');
    if (man.checked == true){
        sex = "man";
    }
    var woman = document.getElementById('woman');
    if (woman.checked == true){
        sex = "woman";
    }
    var birthday = document.getElementById('form_birthday').value;
    var marriage = document.getElementById('form_marriage').value;
    var education = document.getElementById('form_education').value;
    var bussiness = document.getElementById('form_bussiness').value;
    var income = document.getElementById('form_income').value;
    var balance = document.getElementById('form_balance').innerHTML;
    var address = document.getElementById('form_address').value;
    var basicinfo = {nickname:nickname,sex:sex,birthday:birthday,marriage:marriage,education:education,
                     bussiness:bussiness,income:income,address:address,balance:parseInt(balance)};
    $.ajax({
        type:'post',
        url:'/member/save_basic_info',
        async:false,
        contentType:'application/json',
        dataType:'json',
        data:JSON.stringify(basicinfo),
        success:function (data) {
            alert(data.information)
        },
        error:function (data) {
            alert("error");
        }
    });
}