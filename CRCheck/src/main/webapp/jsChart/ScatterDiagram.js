/**
 * Created by lvdechao on 2016/7/20.
 *
 * 发现缺陷详情散点图
 */


var scatterDiagram = echarts
    .init(document.getElementById('scatterDiagram'));

//$.ajax({
//    type : "get",
//    async : false, //同步执行
//    url : '../GetStockInflowPieChart',
//    dataType : "json", //返回数据形式为json
//    success : function(result) {
//        if (result) {
//            for (var i = 0; i < result.length; i++) {
//                kinds.push(result[i].name);
//                gaps.push(result[i].gap);
//            }
//        }
//    },
//    error : function(errorMsg) {
//        alert("不好意思，资金流向差柱状图数据加载失败啦!");
//        myChart.hideLoading();
//    }
//})

option = {
    title : {
        text: '缺陷发现详情',
        subtext: '缺陷总数：6  评审人数：5',
        x:50
    },
    grid: {
        left: '3%',
        right: '7%',
        bottom: '3%',
        containLabel: true
    },
    tooltip : {
        showDelay : 0,
        formatter : function (params) {
            if (params.value.length > 1) {
                return params.seriesName + ' :<br/>'
                    + '发现者：'+params.value[0] + '<br/>'
                    + '缺陷位置：'+params.value[1] + '<br/>'
                    + '缺陷类型：空指针';
            }
            else {
                return params.seriesName + ' :<br/>'
                    + '发现者：'+params.name + '<br/>'
                    + '缺陷位置：'+params.value + '<br/>'
                    + '缺陷类型：空指针';
            }
        },
    },
    toolbox: {
        feature: {
            dataZoom: {},
            brush: {
                type: ['rect', 'polygon', 'clear']
            }
        }
    },
    brush: {
    },
    legend: {
        data: ['发现缺陷'],
        left: 'center'
    },
    xAxis : [
        {
            data : ['张三','李四','王五','马六','罗七'],
            splitLine: {
                show: true,
                lineStyle: {
                    type: 'dashed'
                }
            }
        }
    ],
    yAxis : [
        {
            // type : 'value',
            // scale:true,
            data : [1,2,3,4,5,6],
            axisLabel : {
                formatter: '缺陷{value}'
            },
            splitLine: {
                show:'true',
                lineStyle: {
                    type: 'dashed'
                }
            }
        }
    ],
    series : [
        {
            name:'缺陷发现详情',
            type:'scatter',
            data: [["张三", 0],["张三", 1],["张三", 2],
                ["李四", 2],["李四", 4], ["王五", 3],
                ["王五", 0], ["马六", 4], ["罗七", 2],["罗七", 5]
            ],
            symbolSize: 20,
            // label: {
            //   emphasis: {
            //       show: true,
            //       formatter: "",
            //       position: 'top'
            //   }
            // },
            itemStyle: {
                normal: {
                    shadowBlur: 10,
                    shadowColor: 'rgba(25, 100, 150, 0.5)',
                    shadowOffsetY: 5,
                    color: new echarts.graphic.RadialGradient(0.4, 0.3, 1, [{
                        offset: 0,
                        color: 'rgb(129, 227, 238)'
                    }, {
                        offset: 1,
                        color: 'rgb(25, 183, 207)'
                    }])
                }
            }
        },

    ]
};


scatterDiagram.setOption(option);