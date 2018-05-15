var reservedArray = [];//预定
var returnArray = [];//退订
var consumption = [];//消费
var reservedMax;
var reservedMin;
var returnMax;
var returnMin;
var consumptionMax;
var consumptionMin;
var countMax;
var countMin;
$.ajax({
    type: 'get',
    url: '/member/get_statisticsinfo',
    async: false,
    dataType: 'json',
    success: function (data) {
        for (var i=0;i<12;i++){
            reservedArray.push(parseInt(data.reservedNumList[i]));
            returnArray.push(parseInt(data.returnNumList[i]));
            consumption.push(parseInt(data.consumptionList[i]));
        }
        reservedMax = parseInt(data.reservedNumList[0]);
        reservedMin = parseInt(data.reservedNumList[0]);
        returnMax = parseInt(data.returnNumList[0]);
        returnMin = parseInt(data.returnNumList[0]);
        consumptionMax = parseInt(data.consumptionList[0]);
        consumptionMin = parseInt(data.consumptionList[0]);
        for (var i=1;i<12;i++){
            var reservedNow = parseInt(data.reservedNumList[i]);
            var returnNow = parseInt(data.returnNumList[i]);
            var consumptionNow = parseInt(data.consumptionList[i]);
            if (reservedNow>=reservedMax) reservedMax = reservedNow;
            else if (reservedNow<=reservedMin) reservedMin = reservedNow;
            if (returnNow>=returnMax) returnMax = returnNow;
            else if (returnNow<=reservedMin) returnMax = returnNow;
            if (consumptionNow>=consumptionMax) consumptionMax = consumptionNow;
            else if (consumptionNow<=consumptionMin) consumptionMin = consumptionNow;
        }
        countMin = reservedMin>returnMin?returnMin:reservedMin;
        countMax = reservedMax>returnMax?reservedMax:returnMax;
        countMax += parseInt(countMax*0.1)+1;
        consumptionMax = parseInt(consumptionMax*1.1);
        var myChart = echarts.init(document.getElementById('basicinfo-content-bottom-graph'));
        option = {
            title : {
                text: '用户订票数据统计'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                data:['预定','退订','消费']
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '场次',
                    min: countMin,
                    max: countMax,
                    interval: parseInt((countMax-countMin)/12)+1,
                    axisLabel: {
                        formatter: '{value} 次'
                    }
                },
                {
                    type: 'value',
                    name: '金额',
                    min: consumptionMin,
                    max: consumptionMax,
                    interval: parseInt((consumptionMax-consumptionMin)/12),
                    axisLabel: {
                        formatter: '{value} 元'
                    }
                }
            ],
            series: [
                {
                    name:'预定',
                    type:'bar',
                    data:reservedArray
                },
                {
                    name:'退订',
                    type:'bar',
                    data:returnArray
                },
                {
                    name:'消费',
                    type:'line',
                    yAxisIndex: 1,
                    data:consumption
                }
            ]
        };
        myChart.setOption(option);
    },
    error: function () {
        alert("error");
    }
});
$.ajax({
   type:'get',
   url:'/member/get_current_user',
   async:false,
   dataType:'json',
   success:function (data) {
       document.getElementById("basicinfo-content-top-info").innerHTML = "账户："+data.email;
   },
   error:function () {
       alert("error");
   }
});