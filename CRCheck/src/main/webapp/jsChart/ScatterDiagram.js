/**
 * Created by lvdechao on 2016/7/20.
 *
 * 发现缺陷详情散点图
 */


var scatterDiagram = echarts
    .init(document.getElementById('scatterDiagram'));

function showScatterDiagram(id) {

    var defects=[];
    var users=[];
    var datas=[];
    var defectsNum=[];

    $.ajax({
        type: "post",
        async: false, //同步执行
        url: '../scatterDiagram',
        data: {"projectId": id},
        dataType: "json", //返回数据形式为json
        success: function (result) {
            if (result) {
                for (var i = 0; i < result.defectList.length; i++) {
                    defectsNum.push(i);
                    var defectItems=[];
                    defectItems.push(result.defectList[i].fileType);
                    defectItems.push(result.defectList[i].path);
                    defectItems.push(result.defectList[i].lineNum);
                    defectItems.push(result.defectList[i].type);
                    defectItems.push(result.defectList[i].description);
                    defects.push(defectItems);
                }
                for (var i = 0; i < result.userList.length; i++) {
                    users.push(result.userList[i]);
                }
                for (var i = 0; i < result.userAndDefectsList.length; i++) {
                    var userAndDefectsItems=[];
                    userAndDefectsItems.push(result.userAndDefectsList[i].userId);
                    userAndDefectsItems.push(result.userAndDefectsList[i].defectNum);
                    datas.push(userAndDefectsItems);
                }
            }
        },
        error: function (errorMsg) {
            alert("不好意思，数据加载失败啦!");
            scatterDiagram.hideLoading();
        }
    })

    option = {
        title: {
            text: '缺陷发现详情',
            subtext: '缺陷总数：6  评审人数：5',
            x: 50
        },
        grid: {
            left: '3%',
            right: '7%',
            bottom: '8%',
            containLabel: true
        },
        tooltip: {
            showDelay: 0,
            formatter: function (params) {
                if (params.value.length > 1) {
                    var index = params.value[1]
                    return params.seriesName + ' :<br/>'
                        + '发现者：' + params.value[0] + '<br/>'
                        + '所在文档类型：' + defects[index][0] + '<br/>'
                        + '所在文档位置：' + defects[index][1] + '<br/>'
                        + '缺陷位置：' + defects[index][2] + '<br/>'
                        + '缺陷类型：' + defects[index][3] + '<br/>'
                        + '缺陷详情：'+defects[index][4];
                }
                else {
                    var index = params.value
                    return params.seriesName + ' :<br/>'
                        + '发现者：' + params.name + '<br/>'
                        + '所在文档类型：' + defects[index][0] + '<br/>'
                        + '所在文档位置：' + defects[index][1] + '<br/>'
                        + '缺陷位置：' + defects[index][2] + '<br/>'
                        + '缺陷类型：' + defects[index][3] + '<br/>'
                        + '缺陷详情：'+defects[index][4];
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

        dataZoom: [{
            show: true,
            dataZoomIndex: 0,
            realtime: true,
            // orient: 'vertical',   // 'horizontal'
            // x: 0,
            // y: 36,
            // width: 400,
            height: 20,
            // backgroundColor: 'rgba(221,160,221,0.5)',
            // dataBackgroundColor: 'rgba(138,43,226,0.5)',
            // fillerColor: 'rgba(38,143,26,0.6)',
            // handleColor: 'rgba(128,43,16,0.8)',
            // xAxisIndex:[],
            //yAxisIndex:[],
            start: 0,
            end: 100
        }, {
            show: true,
            dataZoomIndex: 1,
            realtime: true,
            orient: 'vertical',
            // x: 0,
            // y: 36,
            width: 20,
            // height: 20,
            // backgroundColor: 'rgba(221,160,221,0.5)',
            // dataBackgroundColor: 'rgba(138,43,226,0.5)',
            // fillerColor: 'rgba(38,143,26,0.6)',
            // handleColor: 'rgba(128,43,16,0.8)',
            // xAxisIndex:[],
            //yAxisIndex:[],
            start: 0,
            end: 100
        }],
        legend: {
            data: ['发现缺陷'],
            left: 'center'
        },
        xAxis: [
            {
                data:users,
                splitLine: {
                    show: true,
                    lineStyle: {
                        type: 'dashed'
                    }
                }
            }
        ],
        yAxis: [
            {
                // type : 'value',
                // scale:true,
                data: defectsNum,
                axisLabel: {
                    formatter: '缺陷{value}'
                },
                splitLine: {
                    show: 'true',
                    lineStyle: {
                        type: 'dashed'
                    }
                }
            }
        ],
        series: [
            {
                name: '缺陷发现详情',
                type: 'scatter',
                data: datas,
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
}