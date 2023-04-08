// pages/function/bookGuide/bookGuide.js
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
        selected: 2,
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
        wx.navigateTo({
            url: '/pages/function/book/book?schoolId=' + store.school.schoolId + '&libraryId=' + libraryId,

        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        let that = this;
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['school', 'hasSchool', 'hasLogin','baseUrl'],
            actions: ['GetSchool','InitData','LocalImg','CheckError']
        });

    },
    localImag(uri){
        return this.LocalImag(uri);
    },


    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {
        setTimeout(() => {
            if(store.hasLogin!=true){
                wx.navigateTo({
                  url: '/pages/center/login/login',
                })
            }
        }, 1000);

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        if(store.hasSchool!=true){
            let school =  wx.getStorageSync('school')
            if(school != ""){
                store.school = school;
                store.hasSchool = true;
                return;
            }
            store.GetSchool();
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
    onShareAppMessage() {

    }
})