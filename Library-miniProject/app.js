// app.js
import {
    store
} from './store/store'

App({
    globalData: {
        hasUserInfo: false,
        userInfo: {}
    },
    onLaunch() {
    },
    onShow(options){
        // 加载缓存
        store.Launch();
    }
})