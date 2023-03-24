// pages/function/fastBook/fastBook.jsconst app = getApp();
import {
    createStoreBindings
} from 'mobx-miniprogram-bindings'
import {
    store
} from '../../../store/store'
import api from '../../../utils/api';

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
        libraryIdx: 0,
        libraryList: [],
        libraries: [],
        // 图书室
        roomIdx: 0,
        roomList: ["请选择"],
        rooms: [],

        // 座位号
        seatIdx: 0,
        seatList: ["请选择"],
        seats: []
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        // 初始化数据
        let that = this;
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['hasSchool', 'hasLogin', 'baseUrl'],
            actions: ['GetSchool', 'InitData', 'CheckError']
        });


    },


    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        let that = this
        if (store.school == null) store.GetSchool();
        setTimeout(that.init, 200)

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
        this.init();

    },
    init() {
        let that = this
        // console.log(store.school);
        // 获取学校的信息
        api.schoolSimple(store.school.schoolId).then(data => {
            let libraries = data.data.librarySimpleList
            let libraryList = ["请选择"]
            libraries.forEach((el, index) => {
                libraryList.push(el.name)
            })
            that.setData({
                schoolSimple: data.data,
                libraryList: libraryList,
                libraries: libraries
            })
        })
        setTimeout(that.getReceiveFast, 200);


    },
    getReceiveFast() {
        let that = this
        wx.showLoading({
            title: '正在加载',
        })
        api.getReceiveFast().then(data => {

            console.log(data);
            let libraryIdx = 0,
                roomIdx = 0,
                seatIdx = 0;
            that.data.libraries.forEach((el, idx) => {
                if (el.libraryId == data.data.libraryId) {
                    libraryIdx = idx + 1
                }
            })
            let rooms = that.data.libraries[libraryIdx - 1].roomSimpleList;
            let roomList = ["请选择"]
            rooms.forEach((el, idx) => {
                roomList.push(el.name);
                if (el.roomId == data.data.roomId) {
                    roomIdx = idx + 1;
                }
            })
            let seats = rooms[roomIdx - 1].seatSimpleList
            let seatList = ["请选择"]
            seats.forEach((el, idx) => {
                seatList.push(el.name)
                if (el.seatId == data.data.seatId) {
                    seatIdx = idx + 1;
                }
            })
            that.setData({
                libraryIdx: libraryIdx,
                rooms: rooms,
                roomList: roomList,
                roomIdx: roomIdx,
                seats: seats,
                seatList: seatList,
                seatIdx: seatIdx,
                switchChecked: data.data.open == 1 ? true : false
            })
        })
    },

    // pickers----------
    bindPickerLibraryChange: function (e) {
        console.log("picker library " + e.detail.value);
        let that = this
        let libraryIdx = e.detail.value;
        if (that.data.libraryIdx == libraryIdx) return;
        if (libraryIdx == 0) {
            that.dataInit("room")
            that.dataInit("seat");
            that.setData({
                libraryIdx: 0
            })
            return;
        }
        let rooms = that.data.libraries[libraryIdx - 1].roomSimpleList
        let roomList = ["请选择"]
        rooms.forEach(el => {
            roomList.push(el.name)
        })
        this.setData({
            libraryIdx: libraryIdx,
            rooms: rooms,
            roomList: roomList,
            roomIdx: 0,
            seats: [],
            seatList: ["请选择"],
            seatIdx: 0
        })
    },
    bindPickerRoomChange: function (e) {
        console.log("picker room " + e.detail.value);
        let that = this
        let roomIdx = e.detail.value;
        if (that.data.roomIdx == roomIdx) return;
        if (roomIdx == 0) {
            that.dataInit("seat");
            that.setData({
                roomIdx: 0
            })
            return;
        }
        let seats = that.data.rooms[roomIdx - 1].seatSimpleList
        let seatList = ["请选择"]
        seats.forEach(el => {
            seatList.push(el.name)
        })
        this.setData({
            roomIdx: e.detail.value,
            seats: seats,
            seatList: seatList,
            seatIdx: 0
        })
    },
    bindPickerSeatChange: function (e) {
        this.setData({
            seatIdx: e.detail.value
        })
    },
    // open开关
    handleSwithChecked: function (e) {
        this.setData({
            switchChecked: e.detail.value
        })
        if (e.detail.value == false) {
            api.closeReceiveFast().then((data) => {
                if (data.code == 200) {
                    wx.showToast({
                        title: '关闭成功',
                        icon: "none"
                    })
                }
            })
        }

    },
    // 根据名字清楚
    dataInit(name) {
        let List = name + "List"
        let s = name + "s"
        let idx = name + "Idx"
        this.setData({
            [List]: ["请选择"],
            [s]: [],
            [idx]: 0
        })
    },
    // 修改保存
    onsubmit() {
        let data = {
            schoolId: this.data.schoolSimple.schoolId,
            libraryId: this.data.libraries[this.data.libraryIdx - 1].libraryId,
            roomId: this.data.rooms[this.data.roomIdx - 1].roomId,
            seatId: this.data.seats[this.data.seatIdx - 1].seatId,
            open: this.data.switchChecked ? 1 : 0
        }
        console.log(data);
        api.updateReceiveFast(data).then(data => {
            console.log(data);
            if (data.code == 200) {
                wx.showToast({
                    title: '保存成功',
                    icon: "none"
                })
            }
        })
    }
})