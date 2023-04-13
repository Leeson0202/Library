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
    loginTest: () => http.get("api/login/admin"),
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
    // 获取整个学校信息
    querSchoolSimple: (schoolId) => http.get("api/school/simple", { schoolId }),
    // 获取学校信息-token
    querySchool: () => http.get("api/school"),
    // 修改学校
    updateSchool: (data) => http.put("api/school", data),
    // 添加学校
    insertSchool: (data) => http.post("api/school", data),
    // 删除学校
    deleteSchool: (schoolId) => http.delete("api/school", { schoolId }),

    /**
     * 图书馆
     */
    // 获取图书馆
    queryLibraries: (schoolId) => http.get("api/library", { schoolId }),
    queryLibrariesByToken: () => http.get("api/library"),
    queryLibrary: (libraryId) => http.get("api/library/id/" + libraryId),
    insertLibrary: (data) => http.post("api/library", data),
    updateLibrary: (data) => http.put("api/library", data),
    deleteLibrary: (libraryId) => http.delete("api/library", { libraryId }),

    /**
     * 图书室
     */
    queryRooms: (libraryId) => http.get("api/room/rooms", { libraryId }),
    queryRoomByTime: (libraryId, today, idx) =>
        http.get("api/room/time", {
            libraryId,
            today,
            idx,
        }),
    queryRoom: (roomId) => http.get("api/room/id/" + roomId),
    insertRoom: (data) => http.post("api/room", data),
    updateRoom: (data) => http.put("api/room", data),
    deleteRoom: (roomId) => http.delete("api/room", { roomId }),

    /**
     * 椅子
     */
    querySeat: (seatId) => http.get("api/seat/id/" + seatId),
    insertSeat: (data) => http.post("api/seat", { data }),
    updateSeat: (data) => http.put("api/seat", data),
    deleteSeat: (seatId) => http.delete("api/seat", { seatId }),

    /**
     * 桌子
     */
    queryTable: (tableId) => http.get("api/table/id/" + tableId),
    insertTable: (data) => http.post("api/table", { data }),
    updateTable: (data) => http.put("api/table", data),
    deleteTable: (tableId) => http.delete("api/table", { tableId }),

    /**
     * 通知
     */
    queryNotifications: (data) => http.get("api/notification", data),
    queryNotification: (notificationId) =>
        http.get("api/notification/id" + notificationId),
    insertNotification: (data) => http.post("api/notification", data),
    updateNotification: (data) => http.put("api/notification", data),
    deleteNotification: (notificationId) =>
        http.delete("api/notification", { notificationId }),
    /**
     * 规则
     */
    queryRule: (schoolId) => http.get("api/rule/id/" + schoolId),
    insertRule: (data) => http.post("api/rule", { data }),
    updateRule: (data) => http.put("api/rule", data),
    deleteRule: (ruleId) => http.delete("api/rule", { ruleId }),
    /**
     * 预约
     */
    queryReceiveAll: (data) => http.get("api/receive/all", data),
    queryReceive: (receiveId) => http.get("api/receive/id/" + receiveId),
    insertReceive: (data) => http.post("api/receive", { data }),
    updateReceive: (data) => http.put("api/receive", data),
    deleteReceive: (receiveId, userId) =>
        http.put("api/receive/cancel", { receiveId, userId }),
};

//其实，也不一定就是params，也可以是 query 还有 data 的呀！
//params是添加到url的请求字符串中的，用于get请求。会将参数加到 url后面。所以，传递的都是字符串。无法传递参数中含有json格式的数据
//而data是添加到请求体（body）中的， 用于post请求。添加到请求体（body）中，json 格式也是可以的。
