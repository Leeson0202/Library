export default {
    //执行加
    launch(state, value) {
        console.log("launch被调用了");
        state.user.userInfo = {};
        state.user.hasLogin = false;
        state.user.hasUserInfo = false;
    },
    updateLogin(state, value) {
        state.user.hasLogin = value;
    },
    // 更新userInfo
    updateUserInfo(state, value) {
        state.user.userInfo = value;
        state.user.hasUserInfo = true;
        state.user.hasLogin = true;
    },
};
