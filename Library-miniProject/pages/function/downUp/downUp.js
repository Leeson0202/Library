// pages/function/downUp/downUp.js

import drawQrcode from '../../../utils/qr/weapp.qrcode.min.js'
Page({

    /**
     * 页面的初始数据
     */
    data: {

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {},


    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        this.drowQr();
    },
    drowQr() {

        let item = {
            // id: this.data.userInfo.id,
            id: this.data.userInfo == null || this.data.userInfo.id == undefined ? "akba" : this.data.userInfo.id,
            time: new Date().toISOString()
        }
        let text = JSON.stringify(item);
        console.log(text);
        const query = wx.createSelectorQuery()
        query.select('#myQrcode')
            .fields({
                node: true,
                size: true
            })
            .exec((res) => {
                var canvas = res[0].node

                // 调用方法drawQrcode生成二维码
                drawQrcode({
                    canvas: canvas,
                    canvasId: 'myQrcode',
                    width: 360,
                    padding: 30,
                    background: '#ffffff',
                    foreground: '#000000',
                    text: text, // 填写自己的信息
                })

                // 获取临时路径（得到之后，想干嘛就干嘛了）
                wx.canvasToTempFilePath({
                    canvasId: 'myQrcode',
                    canvas: canvas,
                    x: 0,
                    y: 0,
                    width: 400,
                    height: 400,
                    destWidth: 400,
                    destHeight: 400,
                    success(res) {
                        console.log('二维码临时路径：', res.tempFilePath)
                    },
                    fail(res) {
                        console.error(res)
                    }
                })
            })

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

    back() {
        wx.switchTab({
            url: '/pages/function/function',
        })
    }
})