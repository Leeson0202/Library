import http from '../http';

export default {
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
    loginTest: () => http.get("api/login/admin")

}