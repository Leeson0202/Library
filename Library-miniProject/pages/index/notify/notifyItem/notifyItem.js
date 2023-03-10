// pages/notifyItem/notifyItem.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        id: 1,
        tag: false,
        notifyText: {
            "id": 5,
            "time": "2022/01/13",
            "title": "寒假放假通知",
            "context": "劳动节放假通知，本馆将于10.1正式放假5天，放假时间为10.1-10.5，10.6正常开馆。",
            "view": 10
        }

    },
    // 获取  notifyText by id
    getNotifyTextById(id) {

    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

        if (options.id !== undefined) {
            console.log(options.id);
            this.setData({
                id : options.id,
                tag: true
            })
            
        }
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