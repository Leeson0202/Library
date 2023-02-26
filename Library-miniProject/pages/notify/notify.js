// pages/notify/notify.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        index: 0,
        notifyTexts: [{
                "id": 1,
                "time": "2022/09/13",
                "title": "国庆节放假通知",
                "context": "劳动节放假通知，本馆将于10.1正式放假5天，放假时间为10.1-10.5，10.6正常开馆",
                "view": 10
            },
            {
                "id": 3,
                "time": "2022/07/08",
                "title": "图书馆暑假开馆通知",
                "context": "劳动节放假通知，本馆将于10.1正式放假5天，放假时间为10.1-10.5，10.6正常开馆",
                "view": 10
            },
            {
                "id": 4,
                "time": "2022/04/27",
                "title": "劳动节放假通知",
                "context": "劳动节放假通知，本馆将于10.1正式放假5天，放假时间为10.1-10.5，10.6正常开馆",
                "view": 10
            },
            {
                "id": 5,
                "time": "2022/01/13",
                "title": "寒假放假通知",
                "context": "劳动节放假通知，本馆将于10.1正式放假5天，放假时间为10.1-10.5，10.6正常开馆",
                "view": 10
            },
        ]
    },
    // 处理点击事件，跳转notifyItem 并带上id
    notifyItemHandel(e) {
        wx.navigateTo({
            url: '/pages/notifyItem/notifyItem?id=' + this.data.notifyTexts[e.currentTarget.dataset.index].id
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

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