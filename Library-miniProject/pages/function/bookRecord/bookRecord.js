// pages/function/bookRecord/bookRecord.js
import api from '../../../utils/api'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        tag: 0,

        recordAll: [],
        recorded: [],
        recording: [],
        recordDone: []

    },




    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        // 获取数据
        this.queryReceiveAll()


    },


    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    queryReceiveAll() {
        let that = this
        api.receiveAll().then(data => {
            // console.log(data);
            let now = new Date(new Date().setHours(0, 0, 0, 0));
            let timeIdx = Math.floor((new Date().getHours() - 8) / 2);
            let date = (now.getMonth + 1) + "月" + now.getDate + '日'
            // console.log(now, timeIdx);
            let recordAll = [],
                itemAll = [];
            let recorded = [],
                itemed = [];
            let recording = [],
                iteming = [];
            let recordDone = [],
                itemDone = [];
            data.data.forEach((el, idx) => {
                let rDate = new Date(el.receiveDate)
                el.date = rDate.getFullYear() + "-" + rDate.getMonth() + "-" + rDate.getDate()
                // console.log(el.date, el.timeIdx, rDate, rDate < now, rDate.getTime() == now.getTime(), rDate > now);
                if (rDate.getTime() < now.getTime() || (rDate.getTime() == now.getTime() && el.timeIdx < timeIdx)) {
                    // receiveDone 已经完成
                    if (itemDone.length > 0 && itemDone[0].receiveDate != el.receiveDate) {
                        // 不相同
                        // 加入recordDone
                        recordDone.push(itemDone);
                        itemDone = []
                    }
                    el.status = -1
                    itemDone.push(el)
                } else if (rDate.getTime() == now.getTime() && el.timeIdx == timeIdx) {
                    if (iteming.length > 0 && iteming[0].receiveDate != el.receiveDate) {
                        // 不相同
                        // 加入recording
                        recordDone.push(iteming);
                        iteming = []
                    }
                    el.status = 2
                    iteming.push(el)
                } else if (rDate > now || (rDate.getTime() == now.getTime() && el.timeIdx > timeIdx)) {
                    // recorded 已经预约
                    if (itemed.length > 0 && itemed[0].receiveDate != el.receiveDate) {
                        // 不相同
                        // 加入recorded
                        recorded.push(itemed);
                        itemed = []
                    }
                    el.status = 0
                    itemed.push(el)
                }
                if (itemAll.length > 0 && itemAll[itemAll.length - 1].receiveDate != el.receiveDate) {
                    // 不相同
                    // 加入recordAll
                    recordAll.push(itemAll);
                    itemAll = []
                }
                itemAll.push(el)
            })
            if (itemAll.length > 0) recordAll.push(itemAll)
            if (itemed.length > 0) recorded.push(itemed)
            if (iteming.length > 0) recording.push(iteming)
            if (itemDone.length > 0) recordDone.push(itemDone)

            that.setData({
                recordAll: recordAll,
                recorded: recorded,
                recording: recording,
                recordDone: recordDone
            })
        })
    },

    // 改变tag
    changeTag(e) {
        this.setData({
            tag: e.currentTarget.dataset.tag
        })
    }

})