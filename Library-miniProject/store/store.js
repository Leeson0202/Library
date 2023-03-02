// 在小程序中，可使用 mobx-miniprogram 配合 mobx-miniprogram-bindings 实现全局数据共享。其中：

//  mobx-miniprogram 用来创建 Store 实例对象
//  mobx-miniprogram-bindings 用来把 Store 中的共享数据或方法，绑定到组件或页面中使用

// 原文链接：https://blog.csdn.net/qq_38388578/article/details/122865066

//在这个JS文件中，专门用来创建 Store 的实例对象
import {
    action,
    observable
} from 'mobx-miniprogram';
import {
    setStorageSyncSecond,
    getStorageSyncTime
} from '../utils/storage'

export const store = observable({
    hasLogin: false,
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
    get HasUserInfo() {
        return this.hasUserInfo;
    },
    // 退出登录
    Logout: action(function () {
        this.userInfo = {};
        this.hasUserInfo = false;
        wx.removeStorageSync("userInfo");
    }),
    // 登陆主要请求
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
                            // 储存
                            wx.setStorageSync("token", res.data.token);
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
    // 微信头像和名称 登陆
    WxLogin: action(function (res) {
        const that = this;
        wx.showLoading({
            title: '正在登陆'
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
    // 程序初始化，从storage中获取数据
    Launch: action(function () {
        console.log('小程序第一次Launching');
        const userInfo = wx.getStorageSync('userInfo');
        if (userInfo !== null && userInfo !== undefined && userInfo !== '') {
            this.hasUserInfo = true;
            this.userInfo = userInfo;
        }
    }),
    IsKeepAlive: action(function () {
        wx.request({
            url: this.baseUrl + "/alive",
            method: "GET",
            header: {
                "token": wx.getStorageSync('token')
            },
            success(res) {
                console.log(res);
            },
            fail() {
                this.ReLogin();
            }
        })
    }),
    // 获取access_token
    GetAccessToken: action(function () {
        var isTime = getStorageSyncTime('access_token');
        if (isTime === undefined) {
            let access_token = wx.getStorageSync('access_token')
            return access_token;
        }
        wx.request({
            url: this.baseUrl + "/wx/login/access",
            method: "GET",
            success(res) {
                // 保存到storage
                const access_token = res.data.data.access_token;
                setStorageSyncSecond('access_token', access_token);
                console.log(access_token);
                return access_token;
            },
            fail() {
                console.log("请求失败");
            }
        })
    }),
    LoginCqupt: action(function (cqupt_id, password) {
        let that = this
        wx.showLoading({
            title: "正在登陆"
        });
        console.log(cqupt_id, password);
        wx.request({
            url: this.baseUrl + '/login/cqupt',
            method: "POST",
            data: {
                cqupt_id: cqupt_id,
                password: password
            },
            header: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            success(res) {
                console.log(res.data);
                let token = res.data.token;
                let userInfo = res.data.userInfo;
                console.log(userInfo);
                that.userInfo.nickname = userInfo.nickName;
                that.hasUserInfo = true;

                // 储存
                wx.setStorageSync("token", token);
                wx.hideLoading();
                wx.navigateBack();

            },
            fail() {
                wx.hideLoading();
                wx.showToast({
                    title: '请检查网络',
                    icon: "error"
                })
            }
        })

    })

})