import http from '../http';

export default {
    queryTable: (tableId) => http.get("api/table/id/" + tableId),
    insertTable: (data) => http.post("api/table", { data }),
    updateTable: (data) => http.put("api/table", data),
    insertOrUpdateTable: (data) => http.post("api/table/insert", data),
    deleteTable: (tableId) => http.delete("api/table", { tableId }),
}
