export default {
    //初始化
    launch(state, value) {
        console.log("launch被调用了");
        state.user.userInfo = {
            avatarUrl:
                "https://img1.baidu.com/it/u=1047817807,648960205&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
        };
        state.user.hasLogin = false;
        state.user.hasUserInfo = false;
        state.school = {
            hasSchool: false,
            schoolId: null,
            name: null,
            descs: null,
            icon: null,
            background: null,
            libraries: null,
            schoolRule: null,

            options: [],
        };
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
    logout(state, value) {
        state.user.hasLogin = false;
        state.user.hasUserInfo = false;
        state.school.hasSchool = false;
        window.localStorage.clear();
    },
    // 更新学校信息
    updateSchool(state, value) {
        state.school = value;
        state.school.hasSchool = true;
    },
    updateOptions(state, value) {
        state.school.options = value;
    },
};
