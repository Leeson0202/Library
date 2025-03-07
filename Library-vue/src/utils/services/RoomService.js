import http from '../http';

export default class RoomService {
  static queryRooms(libraryId) {
    return http.get('api/room/rooms', { libraryId });
  }

  static queryRoomByTime(libraryId, today, idx) {
    return http.get('api/room/time', { 
      libraryId,
      today,
      idx 
    });
  }

  static queryRoom(roomId) {
    return http.get(`api/room/id/${roomId}`);
  }

  static insertRoom(data) {
    return http.post('api/room', data);
  }

  static updateRoom(data) {
    return http.put('api/room', data);
  }

  static deleteRoom(roomId) {
    return http.delete('api/room', { roomId });
  }
}