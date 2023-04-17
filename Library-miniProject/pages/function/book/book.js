// pages/book/book.js
const app = getApp();
import {
    createStoreBindings
} from 'mobx-miniprogram-bindings'
import {
    store
} from '../../../store/store'
import api from '../../../utils/api'


Page({

    /**
     * 页面的初始数据
     */
    data: {

        schoolId: null,

        libraryId: null,
        libraries: [], // [] 记录详细信息
        libraryList: [], // [] 记录名字
        libraryIdx: 0, // 下标

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
        // 成功标准
        success: false
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
        // console.log(getApp().globalData.screenHeight, getApp().globalData.statusBarHeight, getApp().globalData.screenWidth);
        // 长宽配置
        that.setData({
            seatArea: getApp().globalData.screenHeight - getApp().globalData.statusBarHeight * 2 - 200 - 55,
            seatAreaWidth: getApp().globalData.screenWidth - this.data.paddingLeft,
            rpxToPx: getApp().globalData.screenWidth / 750,
        });
        // 遍历图书馆 寻找idx
        setTimeout(() => {
            store.school.libraries.forEach((el, idx) => {
                if (el.libraryId === options.libraryId) {
                    console.log("libraryIdx setting:", idx);
                    that.setData({
                        libraryIdx: idx
                    })
                }
            })
        }, 200);
    },


    /**
     * 获取图书馆信息
     */
    queryLibrary(libraryId) {
        let that = this
        api.queryLibrary(libraryId).then((data) => {
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
                that.queryRoom(data.data.libraryRooms[0].roomId, that.data.today, that.data.timeIdx);
            }
        });

    },

    /**
     * 获取图书室信息
     */
    queryRoom(roomId, today, idx) {
        wx.showLoading({
            title: '正在加载',
        })
        let that = this;
        idx = parseInt(idx);
        api.queryRoom(roomId, today, idx).then((data) => {
            // console.log(data);
            if (data.data == null) return;
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
                        libraryName: store.school.libraries[that.data.libraryIdx].name,
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
                libraryName: store.school.libraries[that.data.libraryIdx].name,
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
            roomId: this.data.rooms[e.detail.value].roomId,
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
        let that = this;
        if (!this.data.receiveTag || that.data.selectLength == 0) {
            this.setData({
                receiveTag: true
            })
            return
        }
        if (that.data.selectedSeats.length == 0) return;
        wx.showLoading({
            title: '正在预约',
        })
        api.receive(Object.values(that.data.selectedSeats)).then((data) => {
            console.log(data);
            if (data.code == 200) {
                that.setData({
                    success: true
                })
            }
        })
    },
    successBack() {
        wx.switchTab({
            url: '/pages/function/function',
        })
    },
    // 删除预约
    deleteSelected(e) {
        let that = this
        console.log(e.currentTarget.dataset.index);
        let selectedSeats = this.data.selectedSeats;
        console.log(selectedSeats[e.currentTarget.dataset.index])
        let selectedSeat = selectedSeats[e.currentTarget.dataset.index];
        let today = e.currentTarget.dataset.index[0] == 'A';
        let idx = parseInt(e.currentTarget.dataset.index[1])
        console.log(today, idx, selectedSeat.roomId == this.data.roomId && today == this.data.today && idx == this.data.timeIdx);

        if (selectedSeat.roomId == this.data.roomId && today == this.data.today && idx == this.data.timeIdx) {
            // 时间 位置都相同
            this.data.seatList.forEach((e, index) => {
                if (e.seatId == selectedSeat.seatId) {
                    console.log(index, e);
                    let s = "seatList[" + index + "].src"
                    that.setData({
                        [s]: '/resources/images/library/yizi-normal.svg'
                    })
                }
            })
        }

        delete selectedSeats[e.currentTarget.dataset.index]
        this.setData({
            selectedSeats: selectedSeats,
            selectLength: that.data.selectLength - 1
        })

    },

    //计算最大座位数,生成影厅图大小
    getWidth(room) {
        let that = this;
        var maxX = 0;
        var maxY = 0;
        let seatList = room.librarySeats;
        let tableList = room.libraryTables;
        // console.log(seatList.length);
        seatList.forEach(el => {
            let tempX = parseInt(el.x);
            let tempY = parseInt(el.y);
            maxX = tempX > maxX ? tempX : maxX;
            maxY = tempY > maxY ? tempY : maxY;
        });
        tableList.forEach(el => {
            let tempX = parseInt(el.x) + parseInt(el.direction);
            let tempY = parseInt(el.y) + 1 - parseInt(el.direction);
            maxX = tempX > maxX ? tempX : maxX;
            maxY = tempY > maxY ? tempY : maxY;
        })
        // 先按照宽
        let height = this.data.seatAreaWidth / (maxX + 1)
        // 如果高度不够
        let hh = height * (maxY + 1);
        if (hh > this.data.seatArea - this.data.paddingTop) {
            console.log("大了");
            // 按照高度计算
            height = (this.data.seatArea - this.data.paddingTop) / (maxY + 1)
            // 并计算 左右内边距之和
            that.setData({
                paddingLeft: getApp().globalData.screenWidth - height * (maxX + 1)
            })

        }
        console.log(height);
        // console.log(maxX, this.data.seatAreaWidth, height);
        this.setData({
            seatScaleHeight: height
        })
    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        let that = this;
        setTimeout(() => {
            that.queryLibrary(that.data.libraryId);
        }, 500);
        this.queryLibrary(this.data.libraryId);
    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {
        this.storeBindings.destroyStoreBindings()
    },



    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {
        this.queryRoom(this.data.roomId, this.data.today, this.data.timeIdx)

    },


})