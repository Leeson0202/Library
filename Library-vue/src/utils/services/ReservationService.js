import http from '../http';

export default {
    /**
     * 预约
     */
    queryReceiveAll: (data) => http.get("api/receive/all", data),
    queryReceive: (receiveId) => http.get("api/receive/id/" + receiveId),
    insertReceive: (data) => http.post("api/receive", { data }),
    updateReceive: (data) => http.put("api/receive", data),
    deleteReceive: (receiveId, userId) =>
        http.put("api/receive/cancel", { receiveId, userId }),

    /**
     * 快速预约
     */
    queryReceiveFastAll: (data) => http.get("api/receive/fast/all", data),
    updateReceiveFast: (data) => http.put("api/receive/fast", data),

}