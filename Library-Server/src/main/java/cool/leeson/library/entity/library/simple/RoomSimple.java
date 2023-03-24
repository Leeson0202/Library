package cool.leeson.library.entity.library.simple;

import cool.leeson.library.entity.library.LibraryRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomSimple {
    private String roomId;
    private String name;
    private List<SeatSimple> seatSimpleList;

    public RoomSimple(LibraryRoom libraryRoom) {
        this.roomId = libraryRoom.getRoomId();
        this.name = libraryRoom.getName();
    }
}
