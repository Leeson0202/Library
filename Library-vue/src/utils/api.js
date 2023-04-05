import http from "./http";

export default {
    /**
     * 登陆
     */
    // 登陆-tel
    loginTel: (tel) => http.get("api/login/tel", { tel }),
    confirmTel: (tel, code) => http.get("api/confirm/tel", { tel, code }),

    // 登陆 - 邮箱
    loginEmail: (email) => http.get("api/login/email", { email }),
    confirmEmail: (email, code) =>
        http.get("api/confirm/email", { email, code }),

    // 重新登陆
    loginUpdate: () => http.get("api/update"),
    // 测试登陆
    loginTest: () => http.get("api/login/test"),
    /**
     * 用户
     */
    
    // 获取用户信息 UserInfo
    queryUserInfo: () => http.get("api/userInfo"),
    // 更新用户信息
    updateUserInfo: (data) => http.put("api/userInfo", data),
    // 重邮信息登陆
    userBindCqupt: (cqupt_id, password) =>
        http.post("api/user/bind/cqupt", { cqupt_id, password }),
    // 获取用户 User
    queryUser: () => http.get("api/user"),
    // 更新用户信息
    updateUser: (data) => http.put("api/user", data),
    // 修改用户确定-验证码
    updateUserConfirm: (code) => http.put("api/usre/comfirm", { code }),

    /**
        学校
     */
    // 获取学校信息
    querySchool: () => http.get("api/school"),
};

//其实，也不一定就是params，也可以是 query 还有 data 的呀！
//params是添加到url的请求字符串中的，用于get请求。会将参数加到 url后面。所以，传递的都是字符串。无法传递参数中含有json格式的数据
//而data是添加到请求体（body）中的， 用于post请求。添加到请求体（body）中，json 格式也是可以的。
