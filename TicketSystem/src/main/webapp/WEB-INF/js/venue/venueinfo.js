$(function () {
   $.ajax({
      type:'get',
      url:'/venue/get_venueinfo',
      async:false,
      dataType:'json',
      success:function (data) {
          document.getElementById("form-address").value = data.address;
          document.getElementById("form-seatinfo1").value = data.partitionnum;
          document.getElementById("form-seatinfo2").value = data.rows;
          document.getElementById("form-seatinfo3").value = data.columns;
      },
      error:function () {
          alert("error");
      }
   });
});
function askToChangeInfo() {
    var email = "default";
    var password = "default";
    var address = document.getElementById("form-address").value;
    var partitionnum = parseInt(document.getElementById("form-seatinfo1").value);
    var rows = parseInt(document.getElementById("form-seatinfo2").value);
    var columns = parseInt(document.getElementById("form-seatinfo3").value);
    var venue = {email:email,password:password,address:address,partitionnum:partitionnum,rows:rows,columns:columns};
    $.ajax({
        type: 'post',
        url: '/venue/ask_to_changeinfo',
        async: false,
        contentType:'application/json',
        dataType: 'json',
        data:JSON.stringify(venue),
        success: function (data) {
            alert(data.information);
        },
        error: function () {
            alert("errror");
        }
    });
}