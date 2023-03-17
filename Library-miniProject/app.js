// app.js
import {
    store
} from './store/store'

App({
    globalData: {
        baseUrl: store.baseUrl
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
    // 登陆检测
    loginUpdate() {
        console.log("login Updating");
        let that = this;
        // 获取token
        let token = wx.getStorageSync('token')
        if (token == "") {
            store.InitData();
            return
        }
        let url = that.globalData.baseUrl + "/update";
        console.log(url);
        wx.request({
            url: url,
            method: "GET",
            header: {
                token: token
            },
            success({
                data
            }) {
                // console.log(data);
                if (data.code != 200) {
                    store.InitData();
                    return;
                }
                if (data.code == 200) {
                    wx.setStorageSync('token', data.token);
                    store.hasLogin = true;
                    store.GetUserInfo();
                }
            },
            fail() {
                wx.showToast({
                    title: '网络错误或浏览器错误',
                    icon: "none"
                })
            }
        })
    },
    onShow(options) {
        console.log("app onShow");
        // 加载缓存
        store.Launch();
        // 检测重新登陆
        this.loginUpdate();
    }
    
})