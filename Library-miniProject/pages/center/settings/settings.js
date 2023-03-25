// pages/center/settings/settings.js

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

    },

    logout() {
        this.InitData();
        wx.reLaunch({
          url: '/pages/center/login/login',
        })
    },
    

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['userInfo', 'hasUserInfo', 'hasLogin'],
            actions: ['GetUserInfo','InitData','CheckError']
        });

    },

  

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {

    },

    /**
 

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },


    toUser(){
        wx.navigateTo({
          url: '/pages/center/user/user',
        })
    }
})