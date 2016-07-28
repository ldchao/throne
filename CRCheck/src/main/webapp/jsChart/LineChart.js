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
        legend: {
            top: '2%',
            data:['Method1','Method2'],
            selectedMode: 'single'
        },
        grid: [{
            //left : '10%',
            //right : '10%',
            top: '8%',
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
            //right: '',
            pieces: [{
                gt: 0,
                lte: 20,
                color: '#096'
            }, {
                gt: 20,
                lte: 40,
                color: '#ffde33'
            }, {
                gt: 40,
                lte: 60,
                color: '#ff9933'
            }, {
                gt: 60,
                lte: 80,
                color: '#cc0033'
            }, {
                gt: 80,
                lte: 100,
                color: '#660099'
            }, {
                gt: 100,
                color: '#7e0023'
            }],
            outOfRange: {
                color: '#999'
            }
        },
        series: [{
            name: 'Method1',
            type: 'line',
            data: method1,
            markLine: {
                silent: true,
                data: [{
                    yAxis: 20
                }, {
                    yAxis: 40
                }, {
                    yAxis: 60
                }, {
                    yAxis: 80
                }, {
                    yAxis: 100
                }]
            }
        }, {
            name: 'Method2',
            type: 'line',
            data: method2,
            markLine: {
                silent: true,
                data: [{
                    yAxis: 20
                }, {
                    yAxis: 40
                }, {
                    yAxis: 60
                }, {
                    yAxis: 80
                }, {
                    yAxis: 100
                }]
            }
        }]
    }


    lineChart.setOption(option);
}