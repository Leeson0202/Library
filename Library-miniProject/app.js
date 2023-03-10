// app.js
import {
    store
} from './store/store'

App({
    globalData: {
        baseUrl: "http://localhost:8080"
    },
    onLaunch() {
        let that = this
        wx.getSystemInfo({
          success: res => {
            that.globalData.screenHeight = res.screenHeight;
            that.globalData.screenWidth = res.screenWidth;
            that.globalData.statusBarHeight = res.statusBarHeight
          }
        })
    },
    onShow(options){
        // 加载缓存
        store.Launch();
        let token = wx.getStorageSync('token');
        if(token !== ""){
            store.hasLogin = true;
        }
    }
})