import request from './request.js'
//引入request.js 文件
class Api {
    //请求方法
    wxLogin(code) {
        return request.post('/wx/login', code)
    }

    /**
     * 登陆
     */
    // 手机号登陆
    loginTel = (tel) => request.get("/login/tel", {
        tel
    });
    // 登陆验证 - 手机号
    conformTel = (tel, code) => request.get("confirm/tel", {
        tel,
        code
    })
    // 邮箱登陆
    loginEmail = email => request.get("login/email", {
        email
    });
    // 登陆验证 - 邮箱
    confirmEmail = (email, code) => request.get("confirm/email", {
        email,
        code
    })
    // 重新登陆
    update = () => request.get("/update");
    // 登陆-测试
    loginTest = () => request.get("/login/test");


    /**
     * 用户
     */
    // 获取用户信息
    queryUserInfo = () => request.get("userInfo")
    // 更新用户信息
    updateUserInfo = (data) => request.put("userInfo", data);
    // 学校信息绑定接口
    userBindSchool = (user, pwd) => request.get("/user/bind/cqupt", {
        user,
        pwd
    });



    /**
     * 学校
     */
    // 获取学校Simple - schoolId
    schoolSimple = (schoolId) => request.get('school/simple', {
        schoolId
    });
    // 获取学校信息 - token
    querySchool = () => request.get("school");

    // 获取图书馆列表
    queryLibraries = (schoolId) => request.get("library/school", {
        schoolId
    })
    // 获取图书馆信息 - libraryId
    queryLibrary = (libraryId) => request.get("/library/id/" + libraryId);

    // 获取图书室信息 - 房间id 今天还是明天 时间idx
    queryRoom = (roomId, today, idx) => request.get('/room/time', {
        roomId,
        today,
        idx
    })
    /**
     * 通知
     */

    queryNotifications = (data) => request.get('notification', data)
    queryNotification = (notificationId) => request.get('notification/id/' + notificationId)
    readNotification = (data) => request.put('notification', data)




    /**
     * 预约
     */
    // 获取全部预约
    queryReceives = () => request.get('/receive')
    // 提交一般预约
    receive = (data) => request.post('/receive', data);
    // 取消一般预约
    cancelReceive = (receiveId) => request.put('receive/cancel', {
        receiveId
    })
    // 获取行程计划
    queryReceiveSchedule = () => request.get('/receive/schedule')

    // 获取快速预约 - token
    queryReceiveFast = () => request.get("receive/fast")
    // 修改快速预约
    updateReceiveFast = (data) => request.put("receive/fast", data)
    // 关闭快速预约
    closeReceiveFast = () => request.get("receive/fast/close")


    /**
     * 打卡
     */
    // 查询用户在线状态
    online = () => request.get('/online');
    // 用户打卡
    clock = (seed, tag) => request.put("clock/user", {
        seed,
        tag
    })
    /**
     * 信息反馈
     */
    feedback = data => request.post('feedback', data)

}
const api = new Api()
export default api