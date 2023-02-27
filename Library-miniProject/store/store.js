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
    hasUserInfo: false,
    // 计算属性
    get avatarUrl() {
        return hasUserInfo == true ? this.userInfo.avatarUrl : "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132";
    },
    get nickName() {
        return hasUserInfo == true ? this.userInfo.nickName : '微信用户';
    },
    Logout: action(function () {
        this.userInfo = {};
        this.hasUserInfo = false;
        wx.removeStorageSync("userInfo");
    }),

    WxLogin: action(function (res) {
        let that = this;
        const tempUserInfo = res;
        wx.showLoading({
            title: '正在登陆',
        });
        // 向微信服务器发送请求 code
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
                        let openid = res.data.data
                        // 储存全局变量
                        that.hasUserInfo = true;
                        that.userInfo = tempUserInfo;
                        that.userInfo.openid = openid;// 保存缓冲
                        wx.setStorageSync('userInfo', that.userInfo)
                        that.hasUserInfo = true;
                         // 关闭弹窗并返回上一页
                        wx.hideLoading();
                        wx.navigateBack();
                    },
                    fail: function () {
                        // 关闭弹窗
                        // wx.hideLoading();
                        wx.showToast({
                            title: '登陆失败，请联系管理',
                            icon: 'error'
                        })
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

    }),
    Launch: action(function () {
        console.log('Launching');
        const userInfo = wx.getStorageSync('userInfo');
        console.log('userInfo:', userInfo);
        if (userInfo !== null && userInfo !== undefined && userInfo !== '') {
            this.userInfo = userInfo;
            this.hasUserInfo = true;
        }
    })

})