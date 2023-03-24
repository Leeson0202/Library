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
    schoolSimple(schoolId) {
        let data = {
            schoolId: schoolId
        }
        return request.get('school/simple', data);
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
    /**
     * 一般预约
     */
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
    /**
     * 快速预约 
     */
    // 获取用的快速预约信息
    getReceiveFast() {
        return request.get("/receive/fast")
    }
    // 修改快速预约
    updateReceiveFast(data) {
        return request.put("/receive/fast", data)
    }
    //关闭快速预约
    closeReceiveFast(){
        return request.get("receive/fast/close")
    }

}
const api = new Api()
export default api