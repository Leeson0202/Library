// pages/book/book.js

var jsonData = require('../../../utils/data.js');
const app = getApp();
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
        roomList: ["一楼阅览室", '二楼阅览室'],
        roomIdx: 0,

        dates: ["今天", "明天"],
        date: '今天',
        times: [
            "8:00-10:00", "10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00", "18:00-20:00", "20:00-22:00"
        ],
        timeIdx: 0,

        // 设置宽高
        paddingLeft: 40,
        paddingTop: 70,

        // 座位数据
        seatList: [],
        zhuoziList: [],
        selectSeatIndex: null
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        // 初始化数据
        let that = this;
        console.log();
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['school', 'hasSchool', 'hasLogin','baseUrl'],
            actions: ['GetSchool', 'InitData']
        });
        // console.log(options);
        that.setData({
            schoolId: options.schoolId,
            libraryId: options.libraryId,
            seatList: [],
            zhuoziList: []
        })
        // 遍历图书馆 寻找idx
        setTimeout(() => {
            store.school.libraries.forEach((el, idx) => {
                if (el.libraryId === options.libraryId) {
                    that.setData({
                        libraryIdx: idx
                    })
                }
            })
        }, 100);



        // 网络获取数据
        that.queryLibrary(options.libraryId);




        // 本地数据
        console.log("height:", getApp().globalData.screenHeight);
        console.log("bar Height: ", getApp().globalData.statusBarHeight);

        that.setData({
            seatArea: getApp().globalData.screenHeight - getApp().globalData.statusBarHeight * 2 - 185 - 50,
            seatAreaWidth: getApp().globalData.screenWidth - this.data.paddingLeft,
            rpxToPx: getApp().globalData.screenWidth / 750,
        });

        let seatList = []
        jsonData.dataJson.seatList.forEach((element, index) => {
            let item = {}
            item.id = element.id;
            item.name = element.name;
            item.x = element.x;
            item.y = element.y;
            item.direction = element.direction;

            if (element.type == 0) {
                item['src'] = "../../../resources/images/library/yizi-normal.svg"
            } else if (element.type == 1) {
                item['src'] = "../../../resources/images/library/yizi-red.svg"
            }
            // console.log(element,item);
            seatList.push(item)
        })

        this.setData({
            seatList: seatList,
            zhuoziList: jsonData.dataJson.zhuoziList
        })
        // 计算长度
        this.getWidth(jsonData.dataJson);
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
                console.log("library: ", data.data);
                if (data.code != 200) {
                    wx.showToast({
                        title: data.err,
                        icon: "none"
                    })
                    return
                }
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
                    that.queryRoom(data.data.libraryRooms[0].roomId);
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
    queryRoom(roomId) {
        console.log("roomId", roomId);
        let that = this;
        // 请求
        let url = app.globalData.baseUrl + "/room/id/" + roomId;
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
                console.log(data);
                if (data.code != 200) {
                    wx.showToast({
                        title: data.msg,
                        icon: "none"
                    })
                    return;
                }
                if (data.code == 200) {

                }
            },
            fail() {
                wx.showToast({
                    title: '座位获取失败',
                    icon: "none"
                })
            }

        })

    },


    // 椅子点击事件
    handelSelect(e) {
        let id = e.currentTarget.dataset.id;
        let index = null;
        console.log("id:", id);
        // 找到
        this.data.seatList.forEach((element, idx) => {
            if (element.id == id) {
                index = idx;
            }
        })
        console.log("index: ", index);
        if (index == null) return;
        let strHead = 'seatList['
        let strEnd = '].src'
        let Obj = null;
        // 原来如果有
        if (this.data.selectSeatIndex != null) {
            Obj = strHead + this.data.selectSeatIndex + strEnd;
            // 取消选择
            this.setData({
                [Obj]: "../../../resources/images/library/yizi-normal.svg"
            })
            // this.data.seatList[this.data.selectSeatIndex].src = "../../../resources/images/library/yizi-nomal.svg"
            if (this.data.selectSeatIndex === index) {
                // 相同的
                this.setData({
                    selectSeatIndex: null
                })
                return
            }

        }
        // 新的变green
        Obj = strHead + index + strEnd;
        // 取消选择
        this.setData({
            [Obj]: "../../../resources/images/library/yizi-green.svg",
            selectSeatIndex: index
        })
        // console.log(this.data.seatList[index]);
    },
    bindPickerChange: function (e) {
        this.setData({
            roomIdx: e.detail.value
        })
    },
    bindPickerDateChange: function (e) {
        this.setData({
            date: this.data.dates[e.detail.value]
        })
    },
    bindPickerTimeChange: function (e) {
        this.setData({
            timeIdx: e.detail.value
        })
    },

    //计算最大座位数,生成影厅图大小
    getWidth(dataJson) {
        var maxX = 0;
        let seatList = dataJson.seatList;
        let zhuoziList = dataJson.zhuoziList;
        // console.log(seatList.length);
        seatList.forEach(element => {
            let tempX = parseInt(element.x);
            maxX = tempX > maxX ? tempX : maxX;
        });
        zhuoziList.forEach(element => {
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