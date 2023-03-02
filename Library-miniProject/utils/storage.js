/**
 * 设置时效缓存
 * @param  {String} key    存储的key值
 * @param  {String} value  存储的value值 (不填则默认为1)
 * @param  {Number} time   有效时间，（单位：秒，不填则默认一天）
 */
var setStorageSyncSecond = function (key, value, time) {
    value = value ? value : 1
    wx.setStorageSync(key, value)
    var t = time ? +time : 24 * 3600
    if (t > 0) {
        var timestamp = new Date().getTime()
        timestamp = timestamp / 1000 + t
        wx.setStorageSync(key + 'dtime', timestamp + "")
    } else {
        wx.removeStorageSync(k + 'dtime')
    }
}

/**
 * 读取时效缓存
 * @param   {String}  key  存储的key值
 * @return  {*} true为当前时间已失效、或者该值不存在,undefined默认返回则为当前时间未到失效时间
 */
var getStorageSyncTime = function (key) {
    var deadtime = +wx.getStorageSync(key + 'dtime')
    if (deadtime) {
        if (deadtime < (new Date().getTime()) / 1000) {
            wx.removeStorageSync(key)
            wx.removeStorageSync(key + 'dtime')
            return true
        }
    } else {
        return true
    }
}

module.exports = {
    setStorageSyncSecond: setStorageSyncSecond,
    getStorageSyncTime: getStorageSyncTime
}