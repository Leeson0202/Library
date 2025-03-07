import http from '../http';

export default {
    queryNotifications: (data) => http.get("api/notification", data),
    queryNotification: (notificationId) =>
        http.get("api/notification/id" + notificationId),
    insertNotification: (data) => http.post("api/notification", data),
    updateNotification: (data) => http.put("api/notification", data),
    deleteNotification: (notificationId) =>
        http.delete("api/notification", { notificationId }),
 
}
