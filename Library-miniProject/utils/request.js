class Request {

    baseURL = 'http://localhost:8080'; // 请求后台地址
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
        return new Promise((resolve, reject) => {
            wx.request({
                url: that.baseURL + url,
                data,
                method, // 一种做法，在header中带上登录态，方式取决于和后端的约定
                header: {
                    Authorization: wx.getStorageSync('token'),
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                success(res) {
                    if (res.statusCode === 200) {
                        resolve(res.data)
                    } else {
                        reject(res)
                    }
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