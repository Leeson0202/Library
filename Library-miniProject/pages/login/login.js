import request from '../../utils/request.js'
import api from '../../utils/api.js'

import {
    createStoreBindings
} from 'mobx-miniprogram-bindings'
import {
    store
} from '../../store/store'

const defaultAvatarUrl = 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0'
Page({


    data: {
        tag: 0,
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
    submit() {
        if (this.data.avatarUrl === defaultAvatarUrl || this.data.nickname === null) {
            wx.showToast({
                title: '请点击头像和名字',
                icon: 'error'
            })
            return;
        }
        this.WxLogin({
            avatarUrl: this.data.avatarUrl,
            nickname: this.data.nickname
        })

    },
    onLoad() {
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['userInfo', 'hasUserInfo'],
            actions: ['GetUserInfo', 'WxLogin']
        })
    },
    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {
        this.storeBindings.destroyStoreBindings()
    },

})