// app.js
import {
    store
} from './store/store'

App({
    globalData: {},
    onLaunch() {
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