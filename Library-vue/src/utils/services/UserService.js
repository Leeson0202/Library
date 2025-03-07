import http from '../http';

export default {

    // 获取用户信息 UserInfo
    queryUserInfo: () => http.get("api/userInfo"),
    // 更新用户信息
    updateUserInfo: (data) => http.put("api/userInfo", data),
    // 重邮信息登陆
    userBindCqupt: (cqupt_id, password) =>
        http.post("api/user/bind/cqupt", { cqupt_id, password }),
    // 获取用户 User
    queryUser: () => http.get("api/user"),
    // 修改用户确定-验证码
    updateUserConfirm: (code) => http.put("api/usre/comfirm", { code }),
    /**
     * 用户管理
     */
    queryUserAll: (schoolId) => http.get("api/user/all", { schoolId }),
    insertUser: (data) => http.post("api/user", data),
    updateUser: (data) => http.put("api/user", data),
    deleteUser: (userId) => http.delete("api/user", { userId }),
}