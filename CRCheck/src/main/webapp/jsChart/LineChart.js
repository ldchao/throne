/**
 * Created by lvdechao on 2016/7/20.
 */

var lineChart = echarts
    .init(document.getElementById('lineChart'));
option = {
    //title: {
    //    text: '预测缺陷数趋势图',
    //    x:'center',
    //    y:'5%'
    //},
    tooltip: {
        trigger: 'axis',
        axisPointer:{

            lineStyle: {
                type : 'dashed',
                width : 1
            }
        },
    },
    //grid : [ {
// 			left : '10%',
// 			right : '15%',
//        top:'20%',
// 			height : '90%'
//    }],
    xAxis: {
        data: ['2014-06-01','2014-06-02','2014-06-03','2014-06-04','2014-06-05','2014-06-06']
    },
    yAxis: {
        splitLine: {
            show: false
        }
    },
    visualMap: {
        orient: 'horizontal',
        top: '12%',
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
        data: [50,100,120,130,180,300],
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
    },{
        name: '方法二预测缺陷数',
        type: 'line',
        data: [25,60,110,150,230,280],
    }]
}


lineChart.setOption(option);