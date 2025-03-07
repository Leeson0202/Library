import request from '@/utils/request';

export default class ReceiveService {
  static receive(items) {
    return request.post('/receive', items);
  }

  static cancel(receiveId) {
    return request.put('/receive/cancel', { receiveId });
  }

  static schedule() {
    return request.get('/receive/schedule');
  }
}