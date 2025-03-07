import http from '../http';

class TableService {
  static queryTable(tableId) {
    return http.get(`api/table/id/${tableId}`);
  }

  static insertTable(data) {
    return http.post('api/table', data);
  }

  static updateTable(data) {
    return http.put('api/table', data);
  }

  static insertOrUpdateTable(data) {
    return http.post('api/table/insert', data);
  }

  static deleteTable(tableId) {
    return http.delete('api/table', { tableId });
  }
}

export default TableService;