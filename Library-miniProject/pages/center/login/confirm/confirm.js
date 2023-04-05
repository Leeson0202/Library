import {
    createStoreBindings
} from 'mobx-miniprogram-bindings'
import {
    store
} from '../../../../store/store'
import api from '../../../../utils/api';

const defaultAvatarUrl = 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0'

Page({
    data: {
        identify: "",
        tag: "",
        code: "",
        avatarUrl: defaultAvatarUrl,
        nickName: null
    },
    onLoad(options) {
        this.setData({
            identify: options.identify,
            tag: options.tag
        })
        this.storeBindings = createStoreBindings(this, {
            store,
            actions: ['UpdateHasLogin', 'GetUserInfo', 'Confirm', 'UpdateUserInfo']
        })
    },
    onUnload() {
        this.storeBindings.destroyStoreBindings()
    },
    submit() {
        let that = this
        if (this.data.avatarUrl === defaultAvatarUrl || this.data.nickName === null) {
            wx.showToast({
                title: '请点击头像和名字',
                icon: 'error'
            })
            return;
        }
        let data = {
            avatarUrl: this.data.avatarUrl,
            nickName: this.data.nickName
        }
        // 更新用户信息
        this.UpdateUserInfo(data);
        setTimeout(() => {
            if (store.hasUserInfo == true) {
                wx.reLaunch({
                    url: '/pages/center/center',
                })
            }
        }, 2000);
        // this.Submit(this.data.avatarUrl, this.data.nickName)



    },
    // 提交验证码
    confirm() {
        let that = this
        // 检测
        if (that.data.code === null || that.data.code.length < 6) {
            wx.showToast({
                title: '请输入完整的验证码',
                icon: "none"
            })
        }
        if (this.data.tag == 0) {
            api.conformTel(that.data.identify, that.data.code).then((data) => {
                this.confirmBack(data);
            })
        } else {
            api.confirmEmail(that.data.identify, that.data.code).then(data => {
                this.confirmBack(data);
            })
        }
    },
    // 处理
    confirmBack(data) {
        let that = this
        if (data.code == 200) {
            wx.setStorageSync('token', data.token); // 储存token
            that.UpdateHasLogin(true);
            // 是否为新用户
            if (data.tag == 2) {
                that.setData({
                    tag: 2
                })
                return
            }
            // 登陆成功

            that.GetUserInfo();
            wx.showToast({
                title: '登陆成功',
                icon: "success"
            });
            wx.reLaunch({
                url: '/pages/center/center',
            })

        }

    },
    codeInput(e) {
        this.setData({
            code: e.detail.value
        })
    },
    // 照片选择
    onChooseAvatar(e) {
        const {
            avatarUrl
        } = e.detail
        this.setData({
            avatarUrl,
        })
    },

})