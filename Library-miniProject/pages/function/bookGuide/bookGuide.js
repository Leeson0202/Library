// pages/function/bookGuide/bookGuide.js
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        selected: 2,
        school: {
            "schoolId": "dcajhbadhcavacda",
            "name": "重庆邮电大学",
            "descs": "重庆邮电大学（Chongqing University of Posts and Telecommunications）简称“重邮”，坐落于直辖市——重庆市，是中华人民共和国工业和信息化部与重庆市人民政府共建的教学研究型大学，入选国家“中西部高校基础能力建设工程”、国家“卓越工程师教育培养计划”，是国家“2011计划”核心协同高校、中国政府奖学金来华留学生接收院校、国家大学生文化素质教育基地、国家布点设立并重点建设的四所邮电高校之一，重庆市一流学科建设高校，CDIO工程教育联盟成员单位。",
            "icon": "https://bkimg.cdn.bcebos.com/pic/3c6d55fbb2fb43163d37add525a4462309f7d371?x-bce-process=image/resize,m_lfit,w_536,limit_1",
            "background": "https://bkimg.cdn.bcebos.com/pic/d53f8794a4c27d1ef114acd816d5ad6eddc438ff?x-bce-process=image/resize,m_lfit,w_1280,limit_1",
            "libraries": [{
                "libraryId": "jdgchvauajkavavvbh",
                "schoolId": "dcajhbadhcavacda",
                "name": "老图书馆",
                "descs": "老图书馆",
                "background": "https://ids.cqupt.edu.cn/authserver/cquptDzTheme/static/web/dzimages/bg1.jpg"
            }, {
                "libraryId": "jdgchvauajkuvbh",
                "schoolId": "dcajhbadhcavacda",
                "name": "数字图书馆",
                "descs": "新图书馆",
                "background": "http://ehall.cqupt.edu.cn/new/portal/css/dark/millennium/images/bg/bg.jpg"
            }]
        }

    },
    changeSelect(e) {
        let selected = e.currentTarget.dataset.selected;
        this.setData({
            selected: selected
        })

    },

    handelbook(e) {
        let that = this
        let libraryId = e.currentTarget.dataset.id;
        console.log(libraryId);
        wx.navigateTo({
            url: '/pages/function/book/book?schoolId=' + that.data.school.schoolId + '&libraryId=' + libraryId,

        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        let that = this;
        this.setData({
            baseUrl: app.globalData.baseUrl,
            selected: 2
        })
        //获取学校信息
        wx.request({
            url: this.data.baseUrl + "/school",
            method: "GET",
            header: {
                "token": wx.getStorageSync('token')
            },
            success({
                data
            }) {
                console.log(data);
                if (data.code != 200) {
                    wx.showToast({
                        title: data.err,
                        icon: "none"
                    })
                }
                if (data.code == 200) {
                    that.setData({
                        school: data.data
                    })
                    wx.setStorageSync('school', data.data);
                }
            },
            fail() {
                wx.showToast({
                    title: '获取学校信息失败,请稍后再试',
                    icon: "none"
                })
                return
            }
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