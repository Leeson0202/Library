// pages/center/learnCount/learnCount.js

// 引入ecahrt.js
import * as echarts from '../../../ec-canvas/echarts';

// 获取应用实例
const app = getApp()

Page({

    /**
     * 页面的初始数据
     */
    data: {
        checked: 1,
        options: [{
            xAxis: {
                type: 'category',
                data: ['一', '二', '三', '四', '五', '六', '七'],axisTick: {
                    alignWithLabel: true
                  }
            },
            yAxis: {
                type: 'value',
                name: '学习时间',
                position: 'left',
                alignTicks: true,
                axisLine: {
                    show: true
                },
                axisLabel: {
                    formatter: '{value}h'
                }
            },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            series: [
                {
                    type: 'bar',
                    label: {
                        show: true,
                        position: 'inside'
                    },
                    data: [10, 12, 6, 8, 7, 14, 2],
                    type: 'bar',
                    showBackground: true,
                    backgroundStyle: {
                        color: 'rgba(180, 180, 180, 0.1)'
                    }
                }
            ]
        }, {
            xAxis: {
                data: ['8', '10', '12', '14', '16', '18', '20']
            },
            yAxis: {
                type: 'value',
                name: '学习时间',
                position: 'left',
                alignTicks: true,
                axisLine: {
                    show: true
                },
                axisLabel: {
                    formatter: '{value}min'
                }
            },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            series: [

                {
                    type: 'bar',
                    label: {
                        show: true,
                        position: 'inside'
                    },
                    data: [100, 80, 60, 90, 120, 110, 30],
                    type: 'bar',
                    showBackground: true,
                    backgroundStyle: {
                        color: 'rgba(180, 180, 180, 0.1)'
                    }
                }
            ]
        }],
        ec: {
            lazyLoad: true
        }

    },

    handleChange(e) {
        if (e.currentTarget.dataset.check == this.data.checked) return;
        this.setData({
            checked: e.currentTarget.dataset.check
        })
        // this.barComponent = this.selectComponent("#mychart-count");
        this.init(this.barComponent);

    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

    },
    //初始化图标
    init: function (component, type) {
        component.init((canvas, width, height, dpr) => {
            const chart = echarts.init(canvas, null, {
                width: width,
                height: height,
                devicePixelRatio: dpr
            });
            chart.clear();
            this.setOption(chart)
            return chart;
        });
    },
    setOption(chart, type) {
        let that = this
        var xdata = this.data.dateList
        var ydata = this.data.vodList
        var data = this.data.data
        chart.setOption(that.data.options[that.data.checked])
    },


    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        this.barComponent = this.selectComponent("#mychart-count");
        this.init(this.barComponent);

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

    }
})