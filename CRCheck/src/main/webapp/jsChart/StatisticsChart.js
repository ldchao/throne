/**
 * Created by lvdechao on 2016/7/20.
 */


var statisticsChart = echarts
    .init(document.getElementById('statisticsChart'));

//依次为左坐标轴颜色，右坐标轴颜色
var colors = ['#2F5F91', '#C43E3E'];
//依次为未审批缺陷、正确缺陷、错误缺陷、评审效率颜色
var itemColors = ['#DDDEE2', '#7FB9E3','#E17963', '#5483B3'];

function getStatisticsChart(id) {

    var userIDs = [];
    var unApproveDefectNums = [];
    var correctDefectNums = [];
    var errorDefectNums = [];
    var efficiencys = [];


    $.ajax({
        type: "post",
        async: false, //同步执行
        url: '../statisticsChart',
        data: {"projectId": id},
        dataType: "json", //返回数据形式为json
        success: function (result) {
            if (result) {
                for (var i = 0; i < result.length; i++) {
                    userIDs.push(result[i].userID);
                    unApproveDefectNums.push(result[i].unApproveDefectNum);
                    correctDefectNums.push(result[i].correctDefectNum);
                    errorDefectNums.push(result[i].errorDefectNum);
                    efficiencys.push(result[i].efficiency)
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
        //    text: '个人评审统计结果',
        //    x:'center',
        //    y:'3%'
        //},
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: ['未审批缺陷', '正确缺陷', '错误缺陷', '评审效率'],
            top: '2%'
        },
        //toolbox: {
        //    show : true,
        //    feature : {
        //        mark : {show: true},
        //        dataView : {show: true, readOnly: false},
        //        magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
        //        restore : {show: true},
        //        saveAsImage : {show: true}
        //    }
        //},
        grid: {
            //left : '10%',
            //right : '10%',
            top: '20%',
// 			height : '36%'
            bottom: '10%'
        },
        calculable: true,
        yAxis: [
            {
                type: 'value',
                name: '缺陷数',
                // min: 0,
                // max: 250,
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLabel: {
                    formatter: '{value} '
                }
            },
            {
                type: 'value',
                name: '评审效率',
                // min: 0,
                // max: 250,
                position: 'right',
                // offset: 80,
                axisLine: {
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        xAxis: [
            {
                type: 'category',
                data: userIDs
            }
        ],
        series: [
            {
                name: '未审批缺陷',
                type: 'bar',
                stack: '总量', itemStyle: {
                normal: {
                    color: itemColors[0],
                    label: {
                        show: true,
                        position: 'inside',
                        textStyle: {
                            color: '#6B6F78'
                        }


                    }
                }
            },
                data: unApproveDefectNums
            },
            {
                name: '正确缺陷',
                type: 'bar',
                stack: '总量', itemStyle: {
                normal: {
                    color: itemColors[1],
                    label: {
                        show: true,
                        position: 'inside'

                    }
                }
            },
                data: correctDefectNums
            },
            {
                name: '错误缺陷',
                type: 'bar',
                stack: '总量',
                itemStyle: {
                    normal: {
                        color: itemColors[2],
                        label: {
                            show: true,
                            position: 'inside'

                        }
                    }
                },
                data: errorDefectNums
            },
            {
                name: '评审效率',
                type: 'line',
                yAxisIndex: 1,
                itemStyle: {
                    normal: {
                        color: itemColors[3],
                    }
                },
                data: efficiencys
            }
        ]
    };

    statisticsChart.setOption(option);

}