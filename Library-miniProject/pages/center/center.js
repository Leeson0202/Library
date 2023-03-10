// pages/center/center.js
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

        
    },
    headHandl(){
        let that = this
        if(!that.data.hasLogin){
            wx.navigateTo({
                url: '/pages/center/login/login',
            })
        } else  {
            this.GetUserInfo();
         // 进入个人资料
         wx.navigateTo({
           url: '/pages/center/userInfo/userInfo',
         })
        }

    },
    // 获取用户信息
    getUserInfo(e) {
        if (this.data.hasUserInfo == undefined || this.data.hasUserInfo === false) {
            this.GetUserInfo();
        }
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['userInfo', 'hasUserInfo','hasLogin'],
            actions: ['GetUserInfo']
        });
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {
        if (this.data.hasLogin !== true) {
            console.log(this.data.hasLogin);
            wx.navigateTo({
                url: '/pages/center/login/login',
            })
        }
    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        console.log("onShow", this.data.hasUserInfo, this.data.hasLogin);
        if (this.data.hasLogin===true && this.hasUserInfo !== true) {
            let userInfo = wx.getStorageSync('userInfo');
            console.log("onShow userInfo: " , userInfo);
            if(userInfo === "" ){
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
        // 更新操作
        if(this.data.hasLogin){
            this.GetUserInfo()
        }

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