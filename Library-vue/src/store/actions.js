import api from "../utils/api";
export default {
    // 初始化
    Launch(context, value) {
        // 登陆并更新token
        api.loginUpdate().then((data) => {
            console.log(data);
            if (data.code == 200) {
                window.localStorage.setItem("token", data.token);
                this.dispatch("QueryUserInfo");
                this.commit("updateLogin", true);
            } else if (data.code == 401 || data.code == 402) {
                window.localStorage.clear();
                this.commit("launch");
                return;
            }
        });
    },
    // 手机号登陆
    LoginTel(context, value) {
        api.loginTel(value).then((data) => {
            context.commit("login");
        });
    },
    // 获取用户信息
    QueryUserInfo(context, value) {
        api.queryUserInfo().then((data) => {
            context.commit("updateUserInfo", data.userInfo);
        });
    },
};
