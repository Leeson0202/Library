import {
    createStoreBindings
} from 'mobx-miniprogram-bindings'
import {
    store
} from '../../../../store/store'

const defaultAvatarUrl = 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0'

Page({
    data: {
        code: "",
        avatarUrl: defaultAvatarUrl,
        nickName: null
    },
    // 手机号或邮箱登陆 tag决定
    login() {
        let that = this
        wx.showLoading({
            title: '正在发送验证码',
        })
        console.log(that);
        let uri = that.data.tag == 0 ? "/login/tel" : "/login/email";
        let url = that.data.baseUrl + uri;
        console.log(url);
        wx.request({
            url: url,
            method: "GET",
            data: {
                tel: that.data.tel,
                email: that.data.email
            },
            success(res) {
                console.log(res.data);
                wx.hideLoading();
                wx.showToast({
                    title: res.data.data,
                    icon: "none"
                })
                if (res.data.code === 200) {}
            },
            fail() {
                wx.hideLoading();
                wx.showToast({
                    title: '请连接网络',
                    icon: "none"
                })
            }
        })
    },
    submit() {
        if (this.data.avatarUrl === defaultAvatarUrl || this.data.nickName === null) {
            wx.showToast({
                title: '请点击头像和名字',
                icon: 'error'
            })
            return;
        }
        wx.navigateBack(2)
        this.Submit(this.data.avatarUrl, this.data.nickName)



    },
    onLoad() {
        this.storeBindings = createStoreBindings(this, {
            store,
            fields: ['tel', 'email', 'tag', 'loginMethod'],
            actions: ['Confirm', 'Submit']
        })
    },
    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {
        this.storeBindings.destroyStoreBindings()
    },
    confirm() {
        this.Confirm(this.data.code);
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