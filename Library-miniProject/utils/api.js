import request from './request.js'
//引入request.js 文件
class Api {
    //请求方法
    wxLogin(code) {
        return request.post('/wx/login', code)
    }
    loginTest() {
        return request.get("/login/test");
    }


    // 获取学校信息
    school() {

    }
    // 获取图书馆信息
    queryLibrary(libraryId) {
        return request.get("/library/id/" + libraryId);
    }
    // 获取座位信息
    queryRoom(roomId, today, idx) {
        return request.get('/room/id/' + roomId, {
            today,
            idx
        })
    }
    // 提交一般预约
    receive(data) {
        return request.post('/receive', data);
    }
    // 查询用户状态
    online() {
        return request.get('/online');
    }
    // 获取行程计划
    receiveSchedule() {
        return request.get('/receive/schedule')
    }
    // 获取全部预约
    receiveAll() {
        return request.get('/receive')
    }
    // 取消预约
    receiveCancel(receiveId) {
        return request.put("/receive/cancel", {
            receiveId: receiveId
        });
    }

}
const api = new Api()
export default api