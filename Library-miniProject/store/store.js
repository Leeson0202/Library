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
    baseUrl: "http://localhost:8080",
    header: {
        "Content-Type": "application/x-www-form-urlencoded"
    },
    hasLogin: false,
    hasUserInfo: false,
    userInfo: {
        avatarUrl: "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132",
        nickName: "点击登陆"
    },
    // 注册相关
    tel: "",
    email: "",
    // {0:手机， 1:邮箱}
    loginMethod: 1,
    tag: false, // 新用户
    // 计算属性
    get avatarUrl() {
        return hasUserInfo == true ? this.userInfo.avatarUrl : "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132";
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
    // 登陆cqupt
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
                that.userInfo.nickName = userInfo.nickName;
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

    }),
    // 设置 hasUserInfo 【true false】
    changeHasUserInfo: action(function (tag) {
        this.hasUserInfo = tag;
    }),
    // 设置登陆方式
    ChangeLoginMethod: action(function (tag) {
        this.loginMethod = tag;
    }),
    // 登陆login 发送验证码
    Login: action(function (str) {
        if (str === null || str.length === 0) {
            wx.showToast({
                title: '请输入',
                icon: "none"
            })
            return;
        }
        let that = this
        that.hasLogin = false;
        that.tag = false
        if (that.loginMethod === 0) {
            that.tel = str
        } else if (that.loginMethod === 1) {
            that.email = str
        }
        wx.showLoading({
            title: '正在发送验证码',
        })
        let uri = that.loginMethod == 0 ? "/login/tel" : "/login/email";
        let url = that.baseUrl + uri;
        console.log(url);
        wx.request({
            url: url,
            method: "GET",
            data: {
                tel: that.tel,
                email: that.email
            },
            success(res) {
                wx.hideLoading();
                if (res.data.code == 200) {
                    wx.showToast({
                        title: res.data.data,
                        icon: "none"
                    })
                    wx.navigateTo({
                        url: '/pages/confirm/confirm',
                    })
                } else {
                    wx.showToast({
                        title: res.data.err,
                        icon: "none"
                    })
                }
            },
            fail() {
                wx.hideLoading();
                wx.showToast({
                    title: '请连接网络',
                    icon: "none"
                })
            }
        })

    }),
    // 登陆验证
    Confirm: action(function (code) {
        let that = this;
        that.hasLogin = false;
        that.tag = false;
        // 检测
        if (code === null || code.length < 6) {
            wx.showToast({
                title: '请输入完整的验证码',
                icon: "none"
            })
        }
        let url = that.baseUrl + (that.loginMethod === 0 ? "/confirm/tel" : "/confirm/email");
        console.log(url);
        wx.showLoading({
            title: '正在登陆',
        })
        wx.request({
            url: url,
            method: "GET",
            data: {
                tel: that.tel,
                email: that.email,
                code: code
            },
            success(res) {
                console.log(res.data);
                wx.hideLoading();
                if (res.data.code !== 200) {
                    wx.showToast({
                        title: res.data.err,
                        icon: "none"
                    })
                    return false;
                }
                if (res.data.code === 200) {
                    console.log(res.data);
                    wx.setStorageSync('token', res.data.token);
                    that.hasLogin = true;

                    // 是否为新用户
                    if (res.data.tag !== that.loginMethod) {
                        that.tag = true;
                    } else {
                        wx.showToast({
                            title: '登陆成功',
                            icon: "success"
                        });
                        wx.reLaunch({
                            url: '/pages/center/center',
                        })
                    }
                }
            },
            fail() {
                wx.hideLoading();
                wx.showToast({
                    title: '请检查网络',
                    icon: "error"
                })
            }
        })
    }),
    // 设置头像 和昵称
    Submit: action(function (avatarUrl, nickName) {
        let that = this;
        this.userInfo.avatarUrl = avatarUrl;
        this.userInfo.nickName = nickName;
        this.hasUserInfo = true;
        wx.setStorageSync('userInfo', this.userInfo);
        wx.reLaunch({
            url: '/pages/center/center',
        })
        // 上传名字 和 头像
        wx.request({
            url: that.baseUrl + '/userInfo/update',
            method: "POST",
            header: {
                "Content-Type": "application/x-www-form-urlencoded",
                "token": wx.getStorageSync('token')
            },
            data:{
                "avatarUrl": avatarUrl,
                nickName: nickName
            },success(res){
                console.log(res.data);
                that.userInfo = res.data.userInfo;
                wx.setStorageSync('userInfo', res.data.userInfo)
                that.hasUserInfo = true;
            },fail(){
                console.log("上传失败");
            }
        })
    }),
    // 获取用户
    GetUserInfo: action(function () {
        let that = this ;
        wx.request({
          url: that.baseUrl+'/userInfo',
          method: 'GET',
          success(res){
              console.log(res.data);
          }
        })
        
    })
})