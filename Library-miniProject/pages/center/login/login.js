import api from '../../../utils/api.js'

import {
    createStoreBindings
} from 'mobx-miniprogram-bindings'
import {
    store
} from '../../../store/store'

Page({
    data: {
        identify: "",
        tag: 1,
    },
    onLoad() {
        this.storeBindings = createStoreBindings(this, {
            store,
            actions: ['InitData', "Login"]
        })
    },
    onUnload() {
        this.storeBindings.destroyStoreBindings()
    },
    // input双向绑定
    updateIdentify(e) {
        this.setData({
            identify: e.detail.value
        })
    },
    // 改变登陆方式
    changeLoginMethod(e) {
        this.setData({
            tag: e.currentTarget.dataset.tag,
            identify: ""
        })
    },
    // 测试登陆
    loginTest() {
        api.loginTest().then(data => {
            wx.setStorageSync('token', data.token);
            store.hasLogin = true;
            this.GetUserInfo();
            setTimeout(() => {
                wx.switchTab({
                    url: '/pages/center/center',
                })
            }, 200)
        })

    },
    // 手机号或邮箱登陆 loginMethod决定
    login() {
        let that = this
        this.InitData();
        console.log(this.data.identify);
        if (this.data.identify == null || this.data.identify == "") {
            wx.showToast({
                title: '请输入' + this.data.tag == 0 ? "手机号" : "邮箱",
                icon: "none"
            })
            return;
        }
        // 判断是否为测试登陆
        if (this.data.identify == 'test') {
            wx.showLoading({
                title: '正在登陆测试账号',
            })
            this.loginTest();
            return;
        }
        // 开始请求
        wx.showLoading({
            title: '正在发送验证码',
        })

        if (that.data.tag == 0) {
            api.loginTel(that.data.identify).then(data => {
                that.loginBack(data)
            })
        } else {
            api.loginEmail(that.data.identify).then(data => {
                that.loginBack(data)
            })
        }
    },
    loginBack(data) {
        if (data.code == 200) {
            wx.navigateTo({
                url: '/pages/center/login/confirm/confirm?tag=' + this.data.tag + "&identify=" + this.data.identify,
            })
        }
    }
})