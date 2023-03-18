// pages/function/bookRule/bookRule.js
import {
    createStoreBindings
} from 'mobx-miniprogram-bindings'
import {
    store
} from '../../../store/store'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        rules: []

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        // 初始化数据
        let that = this;
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['school', 'hasSchool', 'hasLogin', 'baseUrl'],
            actions: ['GetSchool', 'InitData', 'CheckError']
        });
        setTimeout(this.getRules, 500);


    },
    getRules() {

        this.setData({
            rules: this.data.school.schoolRule.context.split("\n")
        })
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