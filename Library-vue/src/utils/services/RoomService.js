import http from '../http';

export default {
    queryRooms: (libraryId) => http.get("api/room/rooms", { libraryId }),
    queryRoomByTime: (libraryId, today, idx) =>
        http.get("api/room/time", {
            libraryId,
            today,
            idx,
        }),
    queryRoom: (roomId) => http.get("api/room/id/" + roomId),
    insertRoom: (data) => http.post("api/room", data),
    updateRoom: (data) => http.put("api/room", data),
    deleteRoom: (roomId) => http.delete("api/room", { roomId }),

}