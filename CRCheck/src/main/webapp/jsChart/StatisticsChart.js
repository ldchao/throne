/**
 * Created by lvdechao on 2016/7/20.
 */


var statisticsChart = echarts
    .init(document.getElementById('statisticsChart'));

//依次为左坐标轴颜色，右坐标轴颜色
var colors = ['#2F5F91', '#C43E3E'];
//依次为未审批缺陷、正确缺陷、错误缺陷、评审效率颜色
var itemColors = ['#DDDEE2', '#7FB9E3','#E17963', '#5483B3'];

option = {
    //title: {
    //    text: '个人评审统计结果',
    //    x:'center',
    //    y:'3%'
    //},
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data:['未审批缺陷', '正确缺陷','错误缺陷','评审效率'],
        top:'2%'
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
    grid :  {
        //left : '10%',
        //right : '10%',
        top:'20%',
// 			height : '36%'
        bottom:'10%'
    } ,
    calculable : true,
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
    xAxis : [
        {
            type : 'category',
            data : ['user1','user2','user3','user4','user5','user6','user7']
        }
    ],
    series : [
        {
            name:'未审批缺陷',
            type:'bar',
            stack: '总量', itemStyle : {
            normal: {
                color:itemColors[0],
                label : {
                    show: true,
                    position: 'inside',
                    textStyle: {
                        color: '#6B6F78'
                    }


                }
            }
        },
            data:[320, 302, 301, 334, 390, 330, 320]
        },
        {
            name:'正确缺陷',
            type:'bar',
            stack: '总量', itemStyle : {
            normal: {
                color:itemColors[1],
                label : {
                    show: true,
                    position: 'inside'

                }
            }
        },
            data:[120, 132, 101, 134, 90, 230, 210]
        },
        {
            name:'错误缺陷',
            type:'bar',
            stack: '总量',
            itemStyle : {
                normal: {
                    color:itemColors[2],
                    label : {
                        show: true,
                        position: 'inside'

                    }
                }
            },
            data:[220, 182, 191, 234, 290, 330, 310]
        },
        {
            name:'评审效率',
            type:'line',
            yAxisIndex: 1,
            itemStyle : {
                normal: {
                    color:itemColors[3],
                }
            },
            data:[2, 1, 5, 4, 2, 3, 3]
        }
    ]
};

statisticsChart.setOption(option);