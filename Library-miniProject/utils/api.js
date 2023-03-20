import request from './request.js'
//引入request.js 文件
class Api {
    //请求方法
    wxLogin(code) {
        return request.post('/wx/login', code)
    }
    // 获取行程计划
    receiveSchedule() {
        return request.get('/receive/schedule')
    }

}
const api = new Api()
export default api