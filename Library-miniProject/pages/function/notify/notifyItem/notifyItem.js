// pages/notifyItem/notifyItem.js
import api from '../../../../utils/api'
Page({

    /**
     * 页面的初始数据
     */
    data: {
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
        let that = this
        api.queryNotification(id).then(data => {
            data.data.time = new Date(data.data.date).toLocaleString().slice(0, 9)
            that.setData({
                notifyText: data.data
            })
        })

    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        if (options.id !== undefined) {
            // console.log(options.id);
            this.getNotifyTextById(options.id);
        }
    },


    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {

    },



})