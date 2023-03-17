// pages/book/book.js

var jsonData = require('../../../utils/data.js');
const app = getApp();
import {
    intercept
} from 'mobx-miniprogram';
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

        schoolId: null,

        libraryId: null,
        libraries: [{
            name: "老图书馆"
        }, {
            name: "数字图书馆"
        }], // [] 记录详细信息
        libraryList: ['老图书馆', '数字图书馆'], // [] 记录名字
        libraryIdx: 1, // 下标

        // 选择的房间
        roomId: null,
        rooms: [{
            name: "请选择"
        }, {
            name: "一楼阅览室"
        }, {
            name: "二楼阅览室"
        }],
        roomList: [],
        roomIdx: 0,

        dates: ["今天", "明天"],
        today: true,
        times: [
            "8:00-10:00", "10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00", "18:00-20:00", "20:00-22:00"
        ],
        timeIdx: 0,

        // 设置宽高
        paddingLeft: 40,
        paddingTop: 70,
        receiveTag: false,

        // 房间信息
        room: null,
        // 座位数据
        seatList: [],
        tableList: [],
        selectLength: 0,
        selectedSeats: {},
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
            actions: ['GetSchool', 'InitData','CheckError']
        });
        // 获取现在的时间 初始化配置
        let hour = new Date().getHours();
        let timeIdx = 1;
        let today = true;
        if (hour >= 22) today = false;
        if (hour < 10 || hour >= 22) {
            timeIdx = 1;
        } else if (hour < 12) {
            timeIdx = 2;
        } else if (hour < 14) {
            timeIdx = 3;
        } else if (hour < 16) {
            timeIdx = 4;
        } else if (hour < 18) {
            timeIdx = 5;
        } else if (hour < 20) {
            timeIdx = 6;
        } else if (hour < 22) {
            timeIdx = 7;
        }
        // 初始化options
        that.setData({
            schoolId: options.schoolId,
            libraryId: options.libraryId,
            seatList: [],
            zhuoziList: [],
            timeIdx: timeIdx - 1,
            today: today
        })
        // 长宽配置
        that.setData({
            seatArea: getApp().globalData.screenHeight - getApp().globalData.statusBarHeight * 2 - 185 - 50,
            seatAreaWidth: getApp().globalData.screenWidth - this.data.paddingLeft,
            rpxToPx: getApp().globalData.screenWidth / 750,
        });
        // 遍历图书馆 寻找idx
        setTimeout(() => {
            store.school.libraries.forEach((el, idx) => {
                if (el.libraryId === options.libraryId) {
                    that.setData({
                        libraryIdx: idx
                    })
                }
            })
        }, 200);



        // 网络获取数据
        // 图书馆数据
        that.queryLibrary(options.libraryId);

    },

    /**
     * 获取图书馆信息
     */
    queryLibrary(libraryId) {
        let that = this
        let url = app.globalData.baseUrl + '/library/id/' + libraryId;
        console.log(url);
        // 获取图书馆
        wx.request({
            url: url,
            method: 'GET',
            success({
                data
            }) {
                // console.log("library: ", data.data);
                if(that.CheckError(data)) return
                if (data.code == 200) {
                    let roomList = []
                    data.data.libraryRooms.forEach(el => {
                        roomList.push(el.name)
                    })
                    that.setData({
                        roomId: data.data.libraryRooms[0].roomId,
                        roomIdx: 0,
                        roomList: roomList,
                        rooms: data.data.libraryRooms
                    })
                    console.log();
                    that.queryRoom(data.data.libraryRooms[0].roomId, that.data.today, that.data.timeIdx);
                }
            },
            fail() {
                wx.showToast({
                    title: '获取图书馆失败',
                    icon: "error"
                })
                return
            }
        })
    },

    /**
     * 获取图书室信息
     */
    queryRoom(roomId, today, idx) {
        wx.showLoading({
            title: '正在加载',
        })
        let that = this;
        idx = parseInt(idx) + 1;
        // 请求
        let url = app.globalData.baseUrl + "/room/id/" + roomId + "?today=" + today + "&idx=" + idx;
        console.log(url);
        wx.request({
            url: url,
            method: "GET",
            header: {
                token: wx.getStorageSync('token'),
            },
            success({
                data
            }) {
                // console.log(data);
                wx.hideLoading();
                if(that.CheckError(data)) return
                if (data.code == 200) {
                    data.data.librarySeats.forEach(el => {
                        el.src = el.red ? '/resources/images/library/yizi-red.svg' : '/resources/images/library/yizi-normal.svg'
                    })
                    // 本地和数据进行对比
                    let timeKey = that.data.today ? "A" : "B"
                    timeKey = timeKey + that.data.timeIdx
                    if (that.data.selectedSeats[timeKey] != undefined && that.data.selectedSeats[timeKey].libraryId == that.data.libraryId && that.data.selectedSeats[timeKey].roomId == that.data.roomId) {
                        // 时间 地点相同
                        data.data.librarySeats.forEach(el => {
                            if (el.seatId == that.data.selectedSeats[timeKey].seatId) {
                                el.src = '/resources/images/library/yizi-green.svg'
                            }
                        })
                    }
                    that.setData({
                        room: data.data,
                        seatList: data.data.librarySeats,
                        tableList: data.data.libraryTables
                    })
                    that.getWidth(data.data);

                }
            },
            fail() {
                wx.hideLoading()
                wx.showToast({
                    title: '座位获取失败',
                    icon: "none"
                })
            }

        })

    },


    // 椅子点击事件
    handelSelect(e) {
        let that = this;
        let seatId = e.currentTarget.dataset.seatId;
        let index = null;
        // 找到
        this.data.seatList.forEach((element, idx) => {
            if (element.seatId == seatId && !element.red) {
                index = idx;
            }
        })
        if (index == null) return;
        let strHead = 'seatList['
        let strEnd = '].src'
        let Obj = null;
        // 时间对比
        let timeKey = that.data.today ? "A" : "B"; //今天是A 明天是B
        timeKey = timeKey + that.data.timeIdx; // 选择的时间
        // console.log("timeKey", timeKey);
        // console.log("含有该时间？", that.data.selectedSeats.hasOwnProperty(timeKey));
        if (that.data.selectedSeats.hasOwnProperty(timeKey)) { // 有就替换
            // 判断位置是否一样
            // console.log("原数据具体信息：", that.data.selectedSeats[timeKey]);
            if (that.data.selectedSeats[timeKey].libraryId == that.data.libraryId && that.data.selectedSeats[timeKey].roomId == that.data.roomId) { // 同一时间同一地点（当前页面）
                // console.log("同一时间同一地点（当前页面）");
                let oIndex = null
                let nIndex = null
                that.data.seatList.forEach((el, idx) => {
                    if (el.seatId == seatId) {
                        nIndex = idx;
                    }
                    if (el.seatId == that.data.selectedSeats[timeKey].seatId) {
                        oIndex = idx;
                    }
                })
                // console.log("oIdx nIdx:", oIndex, nIndex);
                //先取消
                let iname = "seatList[" + oIndex + "].src"
                that.setData({
                    [iname]: '/resources/images/library/yizi-normal.svg',
                })
                // 不相同则显示
                if (oIndex != nIndex && that.data.seatList[nIndex].red != true) {
                    let item = {
                        libraryId: that.data.libraryId,
                        libraryName: that.data.libraryList[that.data.libraryIdx],
                        roomId: that.data.roomId,
                        roomName: that.data.rooms[that.data.roomIdx].name,
                        seatId: seatId,
                        seatName: that.data.seatList[nIndex].name,
                        today: that.data.today,
                        timeIdx: that.data.timeIdx
                    }
                    iname = "selectedSeats." + timeKey;
                    let iiname = "seatList[" + nIndex + "].src"
                    that.setData({
                        [iname]: item,
                        [iiname]: '/resources/images/library/yizi-green.svg'
                    })
                } else {
                    // 相同 清楚记录
                    let selectedSeats = that.data.selectedSeats
                    delete selectedSeats[timeKey]
                    that.setData({
                        selectedSeats: selectedSeats
                    })
                }
            }
        } else {
            // 没有这个时间的 直接加入
            let nIndex = null;
            that.data.seatList.forEach((el, idx) => {
                if (el.seatId == seatId && el.red != true) {
                    nIndex = idx
                }
            })
            // console.log(nIndex);
            if (nIndex == null) return;

            let selecteditem = {
                libraryId: that.data.libraryId,
                libraryName: that.data.libraries[that.data.libraryIdx].name,
                roomId: that.data.roomId,
                roomName: that.data.rooms[that.data.roomIdx].name,
                seatId: seatId,
                seatName: that.data.seatList[nIndex].name,
                today: that.data.today,
                timeIdx: that.data.timeIdx,
            }
            let iname = 'selectedSeats.' + timeKey;
            let iiname = 'seatList[' + nIndex + '].src'
            that.setData({
                [iname]: selecteditem,
                [iiname]: '/resources/images/library/yizi-green.svg'
            })
        }
        that.setData({
            selectLength: Object.keys(that.data.selectedSeats).length
        })

    },
    // 选择事件
    bindPickerChange: function (e) {
        this.setData({
            roomId: this.data.roomList[e.detail.value],
            roomIdx: e.detail.value
        })
        this.queryRoom(this.data.rooms[this.data.roomIdx].roomId, this.data.today, this.data.timeIdx)
    },
    bindPickerDateChange: function (e) {
        this.setData({
            today: e.detail.value == 0 ? true : false
        })
        this.queryRoom(this.data.rooms[this.data.roomIdx].roomId, this.data.today, this.data.timeIdx)
    },
    bindPickerTimeChange: function (e) {
        this.setData({
            timeIdx: e.detail.value
        })
        this.queryRoom(this.data.rooms[this.data.roomIdx].roomId, this.data.today, this.data.timeIdx)
    },
    // 切换结果
    checkReceive() {
        this.setData({
            receiveTag: !this.data.receiveTag
        })
    },
    // 提交预约
    handleSubmit() {
        if (!this.data.receiveTag) {
            this.setData({
                receiveTag: true
            })
            return
        }
    },

    //计算最大座位数,生成影厅图大小
    getWidth(room) {
        var maxX = 0;
        let seatList = room.librarySeats;
        let tableList = room.libraryTables;
        // console.log(seatList.length);
        seatList.forEach(element => {
            let tempX = parseInt(element.x);
            maxX = tempX > maxX ? tempX : maxX;
        });
        tableList.forEach(element => {
            let tempX = parseInt(element.x);
            maxX = tempX > maxX ? tempX : maxX;
        })
        let height = this.data.seatAreaWidth / maxX
        // console.log(maxX, this.data.seatAreaWidth, height);
        this.setData({
            seatScaleHeight: height
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
        this.storeBindings.destroyStoreBindings()
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