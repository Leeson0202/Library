// pages/function/fastBook/fastBook.jsconst app = getApp();
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
        fastBook: {

        },
        // 开关
        switchChecked: false,
        // 图书馆
        LibraryIdx: 1,
        LibraryList: ["请选择", "老图书馆", "数字图书馆"],
        Libraries: [],
        // 图书室
        roomIdx: 0,
        roomList: ["请选择", "一楼阅览室", "二楼阅览室"],
        rooms: [],

        // 座位号
        SeatIdx: 0,
        SeatList: ["请选择", "A1001", "A1002", ],
        Seats: []
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

    },
    bindPickerLibraryChange: function (e) {
        this.setData({
            LibraryIdx: e.detail.value
        })
        // this.queryRoom(this.data.rooms[this.data.roomIdx].roomId, this.data.today, this.data.timeIdx)
    },
    bindPickerRoomChange: function (e) {
        this.setData({
            roomIdx: e.detail.value
        })
        // this.queryRoom(this.data.rooms[this.data.roomIdx].roomId, this.data.today, this.data.timeIdx)
    },
    bindPickerSeatChange: function (e) {
        this.setData({
            SeatIdx: e.detail.value
        })
        // this.queryRoom(this.data.rooms[this.data.roomIdx].roomId, this.data.today, this.data.timeIdx)
    },
    handleSwithChecked: function (e) {
        this.setData({
            switchChecked: e.detail.value
        })
        // this.queryRoom(this.data.rooms[this.data.roomIdx].roomId, this.data.today, this.data.timeIdx)
    },
})