// 在小程序中，可使用 mobx-miniprogram 配合 mobx-miniprogram-bindings 实现全局数据共享。其中：

//  mobx-miniprogram 用来创建 Store 实例对象
//  mobx-miniprogram-bindings 用来把 Store 中的共享数据或方法，绑定到组件或页面中使用

// 原文链接：https://blog.csdn.net/qq_38388578/article/details/122865066

//在这个JS文件中，专门用来创建 Store 的实例对象
import {
    action,
    observable
} from 'mobx-miniprogram'

export const store = observable({
    baseUrl: "http://localhost:8080",
    userInfo: {},
    hasUserInfo: undefined,
    // 计算属性
    get avatarUrl() {
        return hasUserInfo == true ? this.userInfo.avatarUrl : "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132";
    },
    get nickName() {
        return hasUserInfo == true ? this.userInfo.nickname : '微信用户';
    },
    get HasUserInfo(){
        return this.hasUserInfo;
    },
    Logout: action(function () {
        this.userInfo = {};
        this.hasUserInfo = false;
        wx.removeStorageSync("userInfo");
    }),
    ReLogin: action(function () {
        let that = this;
        // 向微信服务器发送请求 code

        return new Promise((resolve, reject) => {
            wx.login({
                success: ({
                    code
                }) => {
                    // 拿到code后，向开发者服务器请求,返回openid
                    wx.request({
                        url: that.baseUrl + '/wx/login',
                        data: {
                            code: code
                        },
                        method: "POST",
                        header: {
                            "Content-Type": "application/x-www-form-urlencoded"
                        },
                        success: function (res) {
                            console.log(res.data.token);
                            that.token = res.data.token;
                            wx.setStorageSync("token",res.data.token);
                            resolve(true);
                        },
                        fail: function () {
                            reject(false);
                        }
                    })
                },
                fail() {
                    wx.showToast({
                        title: '请检查网络',
                        icon: 'error'
                    })
                }
            })
        });
    }),

    WxLogin: action(function (res) {
        const that = this;
        wx.showLoading({
            title: '正在登陆',
        });
        this.ReLogin().then((tag) => {
            if (tag) {
                // 储存全局变量
                that.hasUserInfo = true;
                that.userInfo = res
                wx.setStorageSync('userInfo', that.userInfo)
                that.hasUserInfo = true;
                // 关闭弹窗并返回上一页
                wx.hideLoading();
                wx.navigateBack();
            } else {
                // 登陆失败
                wx.hideLoading();
                wx.showToast({
                    title: '登陆失败，请联系管理',
                    icon: 'error'
                })
            }
        })

    }),
    Launch: action(function () {
        console.log('小程序第一次Launching');
        const userInfo = wx.getStorageSync('userInfo');
        if (userInfo !== null && userInfo !== undefined && userInfo !== '') {
            this.hasUserInfo = true;
            this.userInfo = userInfo;
        }
    })

})