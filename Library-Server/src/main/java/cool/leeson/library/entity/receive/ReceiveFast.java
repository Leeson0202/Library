package cool.leeson.library.entity.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (ReceiveFast)实体类
 *
 * @author Leeson0202
 * @since 2023-03-24 22:40:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveFast implements Serializable {
    private static final long serialVersionUID = -99968772837888274L;
    /**
     * id
     */
    private String userId;
    private String nickName;
    /**
     * 学校id
     */
    private String schoolId;
    /**
     * 图书馆Id
     */
    private String libraryId;
    private String libraryName;
    /**
     * 图书室id
     */
    private String roomId;
    private String roomName;

    /**
     * 座位id
     */
    private String seatId;
    private String seatName;

    /**
     * 是否打开
     */
    private Boolean open;


}

