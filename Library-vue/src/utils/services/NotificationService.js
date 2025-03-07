import http from '../http';

export default class NotificationService {
  static queryNotifications(data) {
    return http.get('api/notification', data);
  }

  static queryNotification(notificationId) {
    return http.get(`api/notification/id${notificationId}`);
  }

  static insertNotification(data) {
    return http.post('api/notification', data);
  }

  static updateNotification(data) {
    return http.put('api/notification', data);
  }

  static deleteNotification(notificationId) {
    return http.delete('api/notification', { notificationId });
  }
}
