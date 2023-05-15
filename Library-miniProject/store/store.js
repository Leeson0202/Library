// 在小程序中，可使用 mobx-miniprogram 配合 mobx-miniprogram-bindings 实现全局数据共享。其中：

//  mobx-miniprogram 用来创建 Store 实例对象
//  mobx-miniprogram-bindings 用来把 Store 中的共享数据或方法，绑定到组件或页面中使用

// 原文链接：https://blog.csdn.net/qq_38388578/article/details/122865066

//在这个JS文件中，专门用来创建 Store 的实例对象
import {
    action,
    observable
} from 'mobx-miniprogram';
import api from '../utils/api';

export const store = observable({
    // baseUrl: "https://api.library.leeson.cool",
    baseUrl: "http://localhost:8080",
    header: {
        "Content-Type": "application/x-www-form-urlencoded"
    },
    hasLogin: false,
    hasUserInfo: false,
    userInfo: {
        avatarUrl: "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132",
        nickName: "点击登陆",
        background: "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1010/13/c6/5494373_5494373_1286955435968.jpg"
    },
    hasSchool: false,
    school: null,
    // 计算属性
    get avatarUrl() {
        return hasUserInfo == true ? this.userInfo.avatarUrl : "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132";
    },
    HasLogin: action(function () {
        // console.log(this.hasLogin);
        if (this.hasLogin != true) {
            wx.navigateTo({
                url: '/pages/center/login/login',
            })
            return false;
        }
        return true;
    }),
    HasSchool: action(function () {
        let that = this;
        if (!that.hasSchool) {
            setTimeout(() => {
                wx.showToast({
                    title: '没有绑定学校',
                    icon: "none"
                })

            }, 200);
            wx.navigateTo({
                url: '/pages/center/userInfo/userInfo',
            })
            return false
        }
        return true

    }),
    // 更新 hasLogin
    UpdateHasLogin: action(function (tag) {
        this.hasLogin = tag
    }),
    // 更新 hasUserInfo
    UpdateHasUserInfo: action((tag) => {
        this.hasUserInfo = tag
    }),


    /**
     *  非 数据相关
     */
    // 清除数据 初始化数据
    InitData: action(function () {
        wx.clearStorageSync();
        this.hasLogin = false;
        this.hasUserInfo = false;
        this.hasSchool = false;
        this.userInfo = {
            avatarUrl: "https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132",
            nickName: "点击登陆",
            background: "http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1010/13/c6/5494373_5494373_1286955435968.jpg"
        }
        this.school = {};

    }),

    // 后面不要动
    // 获取storage的数据 或网络请求
    GetStorage: action(function () {
        if (this.hasLogin == false) return;
        console.log("GetStorage");
        let userInfo = wx.getStorageSync('userInfo');
        let school = wx.getStorageSync('school');
        if (userInfo == "") {
            this.GetUserInfo();
        } else {
            this.userInfo = userInfo
            this.hasUserInfo = true
        }
        if (school == "") {
            this.GetSchool();
        } else {
            this.school = school;
            this.hasSchool = true;
        }

    }),
    // 网络请求非200
    CheckError: action(function (data) {
        // console.log(data);
        if (data.code == 200) {
            return false;
        }
        setTimeout(() => {
            if (data.code == 401) {
                wx.showToast({
                    title: '请重新登陆',
                    icon: "none"
                })
                this.InitData();
            } else {
                wx.showToast({
                    title: data.msg,
                    icon: "none"
                })
            }

        }, 100)
        return true;

    }),
    // 程序初始化，从storage中获取数据
    Launch: action(function () {
        const userInfo = wx.getStorageSync('userInfo');
        if (userInfo !== null && userInfo !== undefined && userInfo !== '') {
            this.hasUserInfo = true;
            this.userInfo = userInfo;
        }
    }),
    // 获取完整的图片地址
    LocalImag: action(function (uri) {
        return this.baseUrl + uri;
    }),
    // 登陆cqupt
    LoginCqupt: action(function (cqupt_id, password) {
        let that = this
        wx.showLoading({
            title: "正在登陆"
        });
        // console.log(cqupt_id, password);
        let url = this.baseUrl + '/user/bind/cqupt'
        wx.request({
            url: url,
            method: "POST",
            data: {
                cqupt_id: cqupt_id,
                password: password
            },
            header: {
                "Content-Type": "application/x-www-form-urlencoded",
                token: wx.getStorageSync('token')
            },
            success(res) {
                console.log(res.data);
                wx.hideLoading();
                if (that.CheckError(res.data)) return
                if (res.data.code == 200) {
                    that.GetSchool();
                    wx.showToast({
                        title: '绑定成功',
                        icon: "success"
                    })
                    wx.navigateBack();
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

    /** 获取实体 */
    // 设置头像 和昵称
    Submit: action(function (avatarUrl, nickName) {
        let that = this;
        this.userInfo.avatarUrl = avatarUrl;
        this.userInfo.nickName = nickName;
        this.hasUserInfo = true;
        let data = {
            avatarUrl: avatarUrl,
            nickName: nickName
        }
        // 更新用户信息
        this.UpdateUserInfo(data);
        wx.reLaunch({
            url: '/pages/center/center',
        })
    }),
    // 获取用户
    GetUserInfo: action(function () {
        let that = this;
        // 获取userInfo
        let url = that.baseUrl + '/userInfo'
        console.log(url);
        wx.request({
            url: url,
            method: 'GET',
            header: {
                "Content-Type": "application/x-www-form-urlencoded",
                'token': wx.getStorageSync('token')
            },
            success(res) {
                // console.log(res.data.userInfo);
                if (that.CheckError(res.data)) return
                let userInfo = res.data.userInfo;
                that.userInfo = userInfo;
                wx.setStorageSync('userInfo', userInfo)
                that.hasUserInfo = true;

            }
        })
        // 获取 school
        this.GetSchool();
    }),
    // 更新用户 上传信息 UserInfo
    UpdateUserInfo: action(function (data) {
        let that = this
        // 上传userInfo
        api.updateUserInfo(data).then(data => {
            that.userInfo = data.userInfo;
            wx.setStorageSync('userInfo', data.userInfo)
            that.hasUserInfo = true;
            wx.showToast({
                title: '保存成功',
                icon: "success"
            })
        })
    }),
    // 获取学校
    GetSchool: action(function () {
        let that = this
        let url = that.baseUrl + '/school'
        console.log(url);
        wx.request({
            url: url,
            method: "GET",
            header: {
                token: wx.getStorageSync('token')
            },
            success({
                data
            }) {
                // console.log(data);
                if (that.CheckError(data)) return
                if (data.code == 200 && data.data != null) {
                    that.school = data.data;
                    that.hasSchool = true;
                    wx.setStorageSync('school', data.data)
                }
                // 2. 重新发起请求 并关闭下拉窗口
                wx.stopPullDownRefresh();
            },
            fail() {
                wx.showToast({
                    title: '获取学校失败',
                    icon: "none"
                })
            }

        })
    }),
})