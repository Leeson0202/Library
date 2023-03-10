// pages/book/book.js

var jsonData = require('../../../utils/data.js');
const app = getApp();


Page({

    /**
     * 页面的初始数据
     */
    data: {

        schoolId: null,
        school: null,
        libraryId: null,
        libraryIdx: 0, // 下标
        libraries: null,
        // 选择的房间
        array: ['美国', '中国', '巴西', '日本'],
        roomIdx: 0,
        index: 0,

        // 设置宽高
        paddingLeft: 15,
        paddingTop: 160,

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
        console.log(options);
        that.setData({
            schoolId: options.schoolId,
            libraryId: options.libraryId,
            seatList: [],
            zhuoziList: []
        })

        // 网络获取数据
        // 获取学校信息
        wx.request({
            url: app.globalData.baseUrl + '/school/' + that.data.schoolId,
            method: "GET",
            success({
                data
            }) {
                if (data.code != 200) {
                    wx.showToast({
                        title: data.err,
                        icon: "none"
                    })
                    return
                }
                if (data.code == 200) {
                    that.setData({
                        school: data.data
                    })
                }

            }
        })
        // 获取图书馆🏫
        wx.request({
            url: app.globalData.baseUrl + '/library/school?schoolId=dcajhbadhcavacda',
            method: 'GET',
            success({
                data
            }) {
                console.log(data);
                if (data.code != 200) {
                    wx.showToast({
                        title: data.err,
                        icon: "none"
                    })
                    return
                }
                if (data.code == 200) {
                    that.setData({
                        libraries: data.data
                    })
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

        that.setData({
            seatArea: getApp().globalData.screenHeight - getApp().globalData.statusBarHeight - (500 * getApp().globalData.screenWidth / 750),
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

    // 点击事件
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