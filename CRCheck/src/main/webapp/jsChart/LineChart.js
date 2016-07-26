/**
 * Created by lvdechao on 2016/7/20.
 */

var lineChart = echarts
    .init(document.getElementById('lineChart'));


function  getLineChart(id) {
    var times = []
    var method1 = []
    var method2 = []

    $.ajax({
        type: "post",
        async: false, //同步执行
        url: '../lineChart',
        data: {"projectId": id},
        dataType: "json", //返回数据形式为json
        success: function (result) {
            if (result) {
                for (var i = 0; i < result.length; i++) {
                    times.push(result[i].endTime)
                    method1.push(result[i].method1)
                    method2.push(result[i].method2)
                }
            }
        },
        error: function (errorMsg) {
            alert("不好意思，数据加载失败啦!");
            scatterDiagram.hideLoading();
        }
    })

    option = {
        //title: {
        //    text: '预测缺陷数趋势图',
        //    x:'center',
        //    y:'5%'
        //},
        tooltip: {
            trigger: 'axis',
            axisPointer: {

                lineStyle: {
                    type: 'dashed',
                    width: 1
                }
            },
        },
        grid: [{
            //left : '10%',
            //right : '10%',
            top: '5%',
            bottom: '12%'
        }],
        xAxis: {
            data: times
        },
        yAxis: {
            splitLine: {
                show: false
            }
        },
        visualMap: {
            show: false,
            orient: 'horizontal',
            top: '5%',
            right: 'center',
            pieces: [{
                gt: 0,
                lte: 50,
                color: '#096'
            }, {
                gt: 50,
                lte: 100,
                color: '#ffde33'
            }, {
                gt: 100,
                lte: 150,
                color: '#ff9933'
            }, {
                gt: 150,
                lte: 200,
                color: '#cc0033'
            }, {
                gt: 200,
                lte: 250,
                color: '#660099'
            }, {
                gt: 250,
                color: '#7e0023'
            }],
            outOfRange: {
                color: '#999'
            }
        },
        series: [{
            name: '方法一预测缺陷数',
            type: 'line',
            data: method1,
            markLine: {
                silent: true,
                data: [{
                    yAxis: 50
                }, {
                    yAxis: 100
                }, {
                    yAxis: 150
                }, {
                    yAxis: 200
                }, {
                    yAxis: 250
                }]
            }
        }, {
            name: '方法二预测缺陷数',
            type: 'line',
            data: method2
        }]
    }


    lineChart.setOption(option);
}