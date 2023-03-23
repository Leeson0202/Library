// pages/function/function.js
//先引入
import api from '../../utils/api';
import {
    createStoreBindings
} from 'mobx-miniprogram-bindings'
import {
    store
} from '../../store/store'

Page({

    /**
     * 页面的初始数据
     */
    data: {
        receiveList: [],
        status: 0,
        online: 0,


    },


    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['userInfo', 'hasUserInfo', 'hasLogin', 'hasSchool'],
            actions: ['HasLogin', 'HasSchool', 'CheckError']
        });
    },


    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        let that = this
        setTimeout(that.getSchedule, 500)

    },


    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {
        this.storeBindings.destroyStoreBindings()
    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {
        this.getSchedule();
    },
    // 获取我的计划
    getSchedule() {
        let that = this
        api.receiveSchedule().then((data) => {
            // 判断是否为空
            if (data.data == null || data.data.length == 0) return;
            // 开始判断状态 status
            let beginDate = new Date(data.data[0].receiveDate);
            let endDate = new Date(data.data[0].receiveDate);
            beginDate.setHours(data.data[0].timeIdx * 2 + 8);
            endDate.setHours(data.data[0].timeIdx * 2 + 10);
            let now = new Date();
            let status = 0;
            if (now >= beginDate && now <= endDate) {
                status = 1
            }

            that.setData({
                receiveList: data.data,
                status: status
            })
        })

    },
    // 点击预约事件
    bookHandel(e) {
        // console.log(e.currentTarget.dataset.tag);
        let tag = e.currentTarget.dataset.tag;
        // 判断是否登陆
        this.HasLogin();
        if (!this.HasSchool()) return;
        let url = "/pages/function/" + tag + "/" + tag;

        console.log(url);
        if (url !== "") {
            wx.navigateTo({
                url: url
            })
        }

    },
    // 入座或离座按钮
    downUp() {
        wx.navigateTo({
            url: '/pages/function/downUp/downUp',
        })

    },


})