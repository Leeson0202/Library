import http from '../http';

export default {
    querySeat: (seatId) => http.get("api/seat/id/" + seatId),
    insertSeat: (data) => http.post("api/seat", { data }),
    updateSeat: (data) => http.put("api/seat", data),
    insertOrUpdateSeat: (data) => http.post("api/seat/insert", data),
    deleteSeat: (seatId) => http.delete("api/seat", { seatId }),
}