package cool.leeson.library.entity.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveItemPost {
    private String libraryId;
    private String roomId;
    private String seatId;
    private Boolean today;
    private Integer timeIdx;

}
