import request from '../../../utils/request.js'
import api from '../../../utils/api.js'

import {
    createStoreBindings
} from 'mobx-miniprogram-bindings'
import {
    store
} from '../../../store/store'

const defaultAvatarUrl = 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0'
Page({
    data: {

        tel: null,
        email: "",

        avatarUrl: defaultAvatarUrl,
        nickname: null
    },
    onChooseAvatar(e) {
        const {
            avatarUrl
        } = e.detail
        this.setData({
            avatarUrl,
        })
    },
    // 手机号或邮箱登陆 loginMethod决定
    login() {
        this.Login(this.data.loginMethod == 0 ? this.data.tel : this.data.email);
    },
    onLoad() {
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['userInfo', 'hasUserInfo', 'baseUrl','loginMethod'],
            actions: ["ChangeLoginMethod","Login" ]
        })
    },
    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {
        this.storeBindings.destroyStoreBindings()
    },
    // input双向绑定
    setPhone(e) {
        this.setData({
            tel: e.detail.value
        })
    },
    // input双向绑定
    setEmail(e) {
        this.setData({
            email: e.detail.value
        })
    },
    // 改变登陆方式
    changeLoginMethod(e) {
        this.ChangeLoginMethod(e.currentTarget.dataset.tag)
    }

})