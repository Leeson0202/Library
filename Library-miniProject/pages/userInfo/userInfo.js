//先引入
import {
    createStoreBindings
} from 'mobx-miniprogram-bindings'
import {
    store
} from '../../store/store'
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        avatarUrl: "",
        nickNmae: "",
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
    handleNAvatarUrl(e) {
        this.setData({
            avatarUrl: e.detail.value
        })
    },
    handleName(e) {
        this.setData({
            nickNmae: e.detail.value
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
    },
    submit() {
        let data = {};
        if (this.data.avatarUrl != "") {
            data.avatarUrl = this.data.avatarUrl;
        }
        if (this.data.nickNmae != "") {
            data.nickNmae = this.data.nickNmae;
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

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['userInfo', 'hasUserInfo', 'hasLogin'],
            actions: ['GetUserInfo', 'UserInfoUpdate']
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
        console.log("onShow", this.data.hasUserInfo, this.data.hasLogin);
        if (this.data.hasLogin === true && this.hasUserInfo !== true) {
            let userInfo = wx.getStorageSync('userInfo');
            console.log("onShow userInfo: ", userInfo);
            if (userInfo === "") {
                this.GetUserInfo();
            }
        }
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
    onShareAppMessage() {}
})