import {
    store
} from '../store/store'
class Request {

    get(url, data) {
        return this.request('GET', url, data)
    }
    post(url, data) {
        return this.request('POST', url, data)
    }
    put(url, data) {
        return this.request('PUT', url, data)
    }
    request(method, url, data) {
        const that = this
        url = store.baseUrl + url
        console.log(url);
        return new Promise((resolve, reject) => {
            wx.request({
                url: url,
                data,
                method, // 一种做法，在header中带上登录态，方式取决于和后端的约定
                header: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    "token": wx.getStorageSync('token')
                },
                success(res) {
                    resolve(res.data)
                },
                fail(res) {
                    reject({
                        message: res.errMsg,
                        url: that.baseURL + url,
                        method,
                        data,
                        statusCode: res.statusCode,
                        result: res.data
                    })
                }
            })
        })
    }
}


const request = new Request()

module.exports = request