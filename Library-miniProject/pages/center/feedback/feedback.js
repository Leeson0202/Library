// pages/center/feedback/feedback.js
import {
    store
} from '../../../store/store'
import api from '../../../utils/api'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        title: "",
        text: "",
        textNum: 0

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {

    },


    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        this.setData({
            title: "",
            text: "",
            textNum: 0
        })

    },
    inputTitle(e) {
        this.setData({
            title: e.detail.value,
        })
    },
    inputBind(e) {
        this.setData({
            text: e.detail.text,
            textNum: e.detail.text.length
        })
    },
    submit() {
        let form = {
            schoolId: store.school.schoolId,
            userId: store.userInfo.userId,
            title: this.data.title,
            context: this.data.text.trim()
        }
        // console.log(form);
        api.insertFeedback(form).then(data => {
            wx.showToast({
                title: '提交成功',
            })
            setTimeout(() => {
                wx.switchTab({
                    url: '/pages/center/center',
                })
            }, 1000);
        })

    }
})