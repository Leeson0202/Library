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
    handleTap(e) {
        let that = this
        if (!that.data.hasLogin) {
            wx.navigateTo({
                url: '/pages/center/login/login',
            })
        } else {
            let idx = e.currentTarget.dataset.idx;
            let url = '/pages/center/' + idx + '/' + idx;
            // 进入个人资料
            wx.navigateTo({
                url: url,
            })
        }

    },
    // 获取用户信息
    getUserInfo(e) {
        // 更新操作
        if (this.data.hasLogin) {
            this.GetUserInfo();
        }
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['userInfo', 'hasUserInfo', 'hasLogin'],
            actions: ['GetStorage','GetUserInfo']
        });
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {
        setTimeout(() => {
            if (this.data.hasLogin !== true) {
                wx.navigateTo({
                    url: '/pages/center/login/login',
                })
            }
        }, 500);
    },
    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        this.GetStorage();
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
        if(!this.data.hasLogin){
            wx.stopPullDownRefresh();
            return
        }
        this.getUserInfo();
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