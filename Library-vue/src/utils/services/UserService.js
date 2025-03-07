import http from '../http';

export default class UserService {
  static queryUserInfo() {
    return http.get('api/userInfo');
  }

  static updateUserInfo(data) {
    return http.put('api/userInfo', data);
  }

  static userBindCqupt(cqupt_id, password) {
    return http.post('api/user/bind/cqupt', { cqupt_id, password });
  }

  static queryUser() {
    return http.get('api/user');
  }

  static updateUserConfirm(code) {
    return http.put('api/user/confirm', { code });
  }
}