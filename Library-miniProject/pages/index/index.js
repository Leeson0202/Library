// index.js
import api from '../../utils/api'
// 引入ecahrt.js
import * as echarts from '../../ec-canvas/echarts';

// 获取应用实例
const app = getApp()

let chart = null


function initChart(canvas, width, height, dpr) {
    //获取像素比
    const getPixelRatio = () => {
        let pixelRatio = 0
        wx.getSystemInfo({
            success: function (res) {
                pixelRatio = res.pixelRatio
            },
            fail: function () {
                pixelRatio = 0
            }
        })
        return pixelRatio
    }
    // console.log(pixelRatio)
    dpr = getPixelRatio()

    chart = echarts.init(canvas, null, {
        width: width,
        height: height,
        devicPixelRatio: dpr
    });
    canvas.setChart(chart)
    let option = getOption() // 这里是echarts的配置信息
    chart.setOption(option)
}

function getOption() {
    return {
        title: {
            text: '图书馆使用情况',
            // subtext: "图书馆使用情况",
            left: 'center'
        },
        legend: {
            top: 'bottom'
        },
        series: [{
            name: 'Nightingale Chart',
            type: 'pie',
            radius: [10, 100],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
                borderRadius: 4
            },
            data: [{
                    value: 40,
                    name: '男生'
                },
                {
                    value: 38,
                    name: '女生'
                },
                {
                    value: 32,
                    name: '其他'
                }
            ]
        }]
    };
}

Page({
    data: {
        notifyText: "劳动节放假通知，本馆将于5.1正式放假三天，放假时间为5.1-5.3，5.4正常开馆",
        ec: {
            onInit: initChart
        }

    },
    notifyHandel() {
        wx.navigateTo({
            url: './notify/notify',
        })
    },
    onLoad() {},
    onReady() {},
})