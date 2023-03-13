//先引入
import {
    createStoreBindings
} from 'mobx-miniprogram-bindings'
import {
    store
} from '../../../store/store'
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        avatarUrl: "",
        nickName: "",
        age: "",
        gender: "",
        country: "",
        sign: ""


    },
    // 获取用户信息
    getUserInfo(e) {
        if (this.data.hasUserInfo == undefined || this.data.hasUserInfo === false) {
            this.GetUserInfo();
        }
    },

    // 处理学校
    bindSchool(){
        if (!this.data.hasSchool) {
            wx.navigateTo({
              url: '/pages/center/cquptLogin/cquptLogin',
            })
        }else{
            wx.showModal({
                title: '提示',
                content: '切换学校',
                success (res) {
                  if (res.confirm) {
                    wx.navigateTo({
                      url: '/pages/center/cquptLogin/cquptLogin',
                    })
                  } else if (res.cancel) {
                      
                  }
                }
              })
        }
    },
    // 提交保存信息
    submit() {
        let data = {};
        if (this.data.avatarUrl != "") {
            data.avatarUrl = this.data.avatarUrl;
        }
        if (this.data.nickName != "") {
            data.nickName = this.data.nickName;
        }
        if (this.data.age != "") {
            data.age = this.data.age;
        }
        if (this.data.gender != "") {
            data.gender = this.data.gender;
        }
        if (this.data.country != "") {
            data.country = this.data.country;
        }
        if (this.data.sign != "") {
            data.sign = this.data.sign;
        }
        console.log(data);
        if(Object.keys(data).length==0){
            return;
        }
        this.UserInfoUpdate(data);
    },
    // 照片选择
    onChooseAvatar(e) {
        const {
            avatarUrl
        } = e.detail
        this.setData({
            avatarUrl,
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['userInfo', 'hasUserInfo', 'hasLogin','hasSchool','school'],
            actions: ['GetUserInfo', 'UserInfoUpdate','GetSchool']
        });
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {
        if (this.data.hasUserInfo === false || this.data.hasUserInfo === undefined) {
            wx.navigateTo({
                url: '/pages/login/login',
            })
        }
    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        this.GetUserInfo();
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
        this.submit();
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
    onShareAppMessage() {},    handleNAvatarUrl(e) {
        this.setData({
            avatarUrl: e.detail.value
        })
    },

    // 处理输入
    handleName(e) {
        this.setData({
            nickName: e.detail.value
        })
    },
    handleAge(e) {
        this.setData({
            age: e.detail.value
        })
    },
    handleGender(e) {
        this.setData({
            gender: e.detail.value
        })
    },
    handleCountry(e) {
        this.setData({
            country: e.detail.value
        })
    },
    handleSign(e) {
        this.setData({
            sign: e.detail.value
        })
    }
})