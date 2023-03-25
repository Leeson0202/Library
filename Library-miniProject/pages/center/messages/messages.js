// pages/center/messages/messages.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        index: 0,
        tag: false,
        messages: [{
            id: "davka",
            title: "违规通知",
            msg: "您与2023-03-23 8:00 没有按时入座，您的信用值减5分。请注意，\n信用值低于90分，即日起一周内不能预约图书馆。\n信用值低于80分，一个月不能预约图书馆。",
            readed: false,
            time: "2023-02-23 8:14"
        }, {
            id: "davka1",
            title: "预约通知",
            msg: "您已预约2月23日 8:00-10:00 在数字图书馆的座位，请按时入座。",
            readed: false,
            time: "2023-02-22 8:14"
        }, {
            id: "davka2",
            title: "预约通知",
            msg: "您已预约2月23日 8:00-10:00 在数字图书馆的座位，请按时入座。",
            readed: false,
            time: "2023-02-23 8:14"
        }, ]

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

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    look(e) {
        let index = e.currentTarget.dataset.index
        let name = "messages[" + index + "].readed"
        this.setData({
            tag: !this.data.tag,
            [name]: true,
            index: index
        })
    },
    back() {
        this.setData({
            tag: !this.data.tag
        })

    }
})