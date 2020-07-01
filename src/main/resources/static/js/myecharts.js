(function() {
    //实例化对象
    var myChart = echarts.init(document.getElementById('echar'));
    //配置项和数据
    var option = {
        color: ["#2f89cf"],
        tooltip: {
            //轴线
            trigger: 'axis',
            // 坐标轴提示器 shadow阴影 line 线
            axisPointer: {
                type: "shadow"
            }
        },
        /* 网格 */
        grid: {
            left: "0%",
            top: "10px",
            right: "0%",
            bottom: "4%",
            containLabel: true /* 防止标签溢出 */
        },
        xAxis: [{
            type: 'category',
            data: ['研发组', '测试组', '旅游组', 'UI组', '管理组', ],

            axisTick: {
                //坐标轴对起居中
                alignWithLable: true,
            },
            axisLabel: {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: "12"
                }
            },
            axisLine: {
                show: true
            }
        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: "12"
                }
            },
            axisLine: {
                show: true
            },
            splitLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)"
                }
            }
        }],
        series: [{
            data: [25, 75, 60, 80, 110, ],
            type: 'bar',
            barWidth: "35%",
        }]
    };
    //配置项给实例化对象
    myChart.setOption(option);
    window.addEventListener("resize", function() {
        myChart.resize();
    });
})()