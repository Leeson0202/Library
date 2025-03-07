import http from '../http';

export default class ReservationService {
  static queryReceiveAll(data) {
    return http.get('api/receive/all', data);
  }

  static queryReceive(receiveId) {
    return http.get(`api/receive/id/${receiveId}`);
  }

  static insertReceive(data) {
    return http.post('api/receive', { data });
  }

  static updateReceive(data) {
    return http.put('api/receive', data);
  }

  static deleteReceive(receiveId, userId) {
    return http.put('api/receive/cancel', { receiveId, userId });
  }

  static queryReceiveFastAll(data) {
    return http.get('api/receive/fast/all', data);
  }

  static updateReceiveFast(data) {
    return http.put('api/receive/fast', data);
  }
}