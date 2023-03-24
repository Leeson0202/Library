package cool.leeson.library.entity.library.simple;

import cool.leeson.library.entity.library.LibrarySeat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatSimple {
    private String seatId;
    private String name;

    public SeatSimple(LibrarySeat librarySeat) {
        this.seatId = librarySeat.getSeatId();
        this.name = librarySeat.getName();
    }
}
