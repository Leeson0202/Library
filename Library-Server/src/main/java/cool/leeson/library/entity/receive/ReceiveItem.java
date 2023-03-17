package cool.leeson.library.entity.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

/**
 * (ReceiveItem)实体类
 *
 * @author Leeson0202
 * @since 2023-03-17 20:58:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveItem implements Serializable {
    private static final long serialVersionUID = 853055001697951161L;
    /**
     * id
     */
    private String receiveId;
    /**
     * order_id
     */
    private String orderId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 图书馆id
     */
    private String libraryId;
    /**
     * 图书室id
     */
    private String roomId;
    /**
     * 座位id
     */
    private String seatId;
    /**
     * 预约日期
     */
    private Date receiveDate;
    /**
     * 预约时间
     */
    private LocalTime receiveTime;


}

