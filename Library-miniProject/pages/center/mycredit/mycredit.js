// pages/center/mycredit/mycredit.js

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
        records: [{
            id: "idauckba",
            date: "2023-02-24 8:14",
            libraryName: "数字图书馆",
            roomName: "一楼阅览室",
            seatName: "A1001",
            record: 5,
            msg: "迟到超过10分钟"
        }, {
            id: "idauckba",
            date: "2023-02-24 8:14",
            libraryName: "数字图书馆",
            roomName: "一楼阅览室",
            seatName: "A1001",
            record: 5,
            msg: "迟到超过10分钟"
        }]

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['userInfo', 'hasUserInfo', 'hasLogin'],
            actions: ['GetStorage', 'GetUserInfo']
        });

    },


    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {

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


})