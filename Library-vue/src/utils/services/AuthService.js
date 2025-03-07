import http from '../http';

export default class AuthService {
  static loginTel(tel) {
    return http.get('api/login/tel', { tel });
  }

  static confirmTel(tel, code) {
    return http.get('api/confirm/tel', { tel, code });
  }

  static loginEmail(email) {
    return http.get('api/login/email', { email });
  }

  static confirmEmail(email, code) {
    return http.get('api/confirm/email', { email, code });
  }

  static loginUpdate() {
    return http.get('api/update');
  }

  static loginTest() {
    return http.get('api/login/admin');
  }
}