// index.js
// 获取应用实例
const app = getApp()

Page({
    data: {
        notifyText: "劳动节放假通知，本馆将于5.1正式放假三天，放假时间为5.1-5.3，5.4正常开馆"

    },
    notifyHandel() {
        wx.navigateTo({
          url: '../notify/notify',
        })
    },
    onLoad() {}
})