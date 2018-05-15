var price = 0; //电影票价
var num = 0;//选座个数
var seatInfo = [];//座位信息
var priceArray;//各区价格
var soldSeat = [];//初始已经被选掉的座位
var selectedSeat = [];//当前用户选中的座位
$(document).ready(function() {
    $.ajax({
        type:'get',
        url:'/member/init_choose_seatinfo',
        async:false,
        dataType:'json',
        success:function (data) {
            document.getElementById("nameid").innerHTML = data.name;
            document.getElementById("timeid").innerHTML = data.date;
            document.getElementById("addressid").innerHTML = data.address;
            var priceString = data.price;
            priceArray = priceString.split(",");
            var seatString = data.seatInfo;
            var array = seatString.split(",");
            for (var i=0;i<array.length-1;i++){
                seatInfo.push(array[i]);
            }
            var soldString = data.checkSeat;
            if (soldString != "") {
                var soldArray = soldString.split(",");
                for (var i = 0; i < soldArray.length - 1; i++) {
                    soldSeat.push(soldArray[i]);
                }
            }
        },
        error:function () {
            alert("error");
        }

    });
    var $cart = $('#seats_chose'), //座位区
        $tickects_num = $('#tickects_num'); //票数
    var sc = $('#seat_area').seatCharts({
        map: seatInfo,
        naming: {//设置行列等信息
            top: false, //不显示顶部横坐标（行）
            getLabel: function(character, row, column) { //返回座位信息
                return column;
            }
        },
        legend: {//定义图例
            node: $('#legend'),
            items: [
                ['c', 'available', '带检票'],
                ['c', 'unavailable', '已检票']
            ]
        },
        click: function() {
            if (this.status() == 'available') { //若为可选座状态，添加座位
                if (num<20) {
                    num++;
                    $('<li>' + (this.settings.row + 1) + '排' + this.settings.label + '座</li>')
                        .attr('id', 'cart-item-' + this.settings.id)
                        .data('seatId', this.settings.id)
                        .appendTo($cart);
                    selectedSeat.push((this.settings.row+1)+"_"+this.settings.label);
                    var pnum = priceArray.length;
                    for (var i=1;i<=pnum;i++){
                        if (this.settings.row>=(seatInfo.length/pnum*(i-1)) && this.settings.row<(seatInfo.length/pnum*(i+1))){
                            price = parseInt(priceArray[pnum-i]);
                        }
                    }
                    alert("当前区价格为"+price+"元");
                    $tickects_num.text(sc.find('selected').length + 1); //统计选票数量
                    // var totalprice = parseInt(document.getElementById("total_price").innerHTML);
                    // $total_price.text(totalprice+price);
                    return 'selected';
                }else{
                    alert("一次性最多检票20张");
                    return 'available';
                }

            } else if (this.status() == 'selected') {
                //若为选中状态
                var nowseat = (this.settings.row+1)+"_"+this.settings.label;
                var index = 0;
                for (var i=0;i<selectedSeat.length;i++){
                    if (selectedSeat[i] == nowseat){
                        index = i;
                        break;
                    }
                }
                selectedSeat.splice(index,1);
                var pnum = priceArray.length;
                for (var i=1;i<=pnum;i++){
                    if (this.settings.row>=(seatInfo.length/pnum*(i-1)) && this.settings.row<(seatInfo.length/pnum*i)){
                        price = parseInt(priceArray[pnum-i]);
                    }
                }
                $tickects_num.text(sc.find('selected').length - 1);//更新票数量
                // var totalprice = parseInt(document.getElementById("total_price").innerHTML);
                // $total_price.text(totalprice-price);
                $('#cart-item-' + this.settings.id).remove();//删除已预订座位
                num--;
                return 'available';
            } else if (this.status() == 'unavailable') { //若为已售出状态
                return 'unavailable';
            } else {
                return this.style();
            }
        }
    });
    // document.getElementsByClassName("seatCharts-cell").style.width = 750/(seatInfo[0].length)-10;
    // document.getElementsByClassName("seatCharts-cell").style.height = "25px";
    //设置已售出的座位
    sc.get(soldSeat).status('unavailable');
    // sc.get(['1_3', '1_4', '4_4', '4_5', '4_6', '4_7', '4_8']).status('unavailable');
});
function getSelectedSeat() {
    var totalPrice = 0;
    alert(selectedSeat);
    if (selectedSeat.length != 0) {
        $.ajax({
            type: 'post',
            url: '/venue/deal_scene_sell',
            async: false,
            dateType: 'json',
            data: {
                chooseSeat:selectedSeat,
                totalPrice:totalPrice,
                type:0
            },
            traditional:true,
            success: function (data) {
                alert(data.information);
                document.getElementById("sureid").href = "";
            },
            error: function () {
                alert("error");
            }
        });
    }else {
        alert("请选择座位")
    }
}