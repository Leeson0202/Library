// pages/notify/notify.js
import {
    store
} from '../../../store/store'
import api from '../../../utils/api'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        index: 0,
        notifyTexts: []
    },
    // 处理点击事件，跳转notifyItem 并带上id
    notifyItemHandel(e) {
        wx.navigateTo({
            url: './notifyItem/notifyItem?id=' + this.data.notifyTexts[e.currentTarget.dataset.index].notificationId
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
        setTimeout(() => this.getNotifications(), 50)

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {
        this.getNotifications();

    },

    // 获取通知
    getNotifications() {
        let that = this
        if (!store.hasSchool || !store.hasSchool) return;
        let form = {
            schoolId: store.school.schoolId,
            page: 0,
            size: 2
        }
        api.queryNotifications(form).then(data => {
            console.log(data);
            if (data.data.content.length == 0) return;
            data.data.content.forEach(el => {
                el.time = new Date(el.date).toLocaleString().slice(0, 9);
            })
            that.setData({
                notifyTexts: data.data.content
            })
        })
    },
})