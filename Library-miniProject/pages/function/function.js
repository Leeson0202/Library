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
        // receiveList: [{
        //     receiveId: "dahaius",
        //     timeIdx: 0,
        //     date: "3月18日",
        //     libraryName: "数字图书馆",
        //     roomName: "一楼阅览室",
        //     seatName: "A1001",
        //     status: 1,
        //     online: 1
        // }, {
        //     receiveId: "dahaius",
        //     timeIdx: 1,
        //     date: "3月18日",
        //     libraryName: "数字图书馆",
        //     roomName: "一楼阅览室",
        //     seatName: "A1001",
        //     status: 0
        // }, {
        //     receiveId: "dahaius",
        //     timeIdx: 2,
        //     date: "3月18日",
        //     libraryName: "数字图书馆",
        //     roomName: "一楼阅览室",
        //     seatName: "A1001",
        //     status: 0
        // }, {
        //     receiveId: "dahaius",
        //     timeIdx: 3,
        //     date: "3月18日",
        //     libraryName: "数字图书馆",
        //     roomName: "一楼阅览室",
        //     seatName: "A1001",
        //     status: 0
        // }, {
        //     receiveId: "dahaius",
        //     timeIdx: 4,
        //     date: "3月18日",
        //     libraryName: "数字图书馆",
        //     roomName: "一楼阅览室",
        //     seatName: "A1001",
        //     status: 0
        // }, ]

    },
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
    downUp() {
        wx.navigateTo({
            url: '/pages/function/downUp/downUp',
        })

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
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        let that = this
        setTimeout(that.getSchedule, 500)


    },
    getSchedule() {
        let that = this
        api.receiveSchedule().then((data) => {
            if (store.CheckError(data)) return;
            that.setData({
                receiveList: data.data
            })
            console.log(data.data);
        })

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
        this.storeBindings.destroyStoreBindings()
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