// pages/center/feedback/feedback.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        text: "",
        textNum: 0

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

    },


    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        this.setData({
            text: "",
            textNum: 0
        })

    },

    inputBind(e) {
        this.setData({
            text: e.detail.text,
            textNum: e.detail.text.length
        })
    },
    submit() {
        if (this.data.text.length == 0) return;
        wx.showToast({
            title: '提交成功',
        })
        setTimeout(() => {
            wx.switchTab({
                url: '/pages/center/center',
            })
        }, 1000);
    }
})