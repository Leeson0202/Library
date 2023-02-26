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
    userInfo: {},
    hasUserInfo: false,
    // 计算属性
    get avatarUrl() {
        return hasUserInfo == true ? this.userInfo.avatarUrl : null;
    },
    get nickName() {
        return hasUserInfo == true ? this.userInfo.nickName : null;
    },

    // actions 方式，用来修改store中的数据
    GetUserInfo: action(function () {
        // console.log('GetUserInfo: action');
        wx.getUserProfile({
            desc: '展示用户信息',
            success: (res) => {
                // console.log("store.js" + res.userInfo)
                this.userInfo = res.userInfo
                this.hasUserInfo = true
            }
        })
    }),

})