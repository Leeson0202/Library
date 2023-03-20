package cool.leeson.library.entity.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * (ReceiveItem)实体类
 *
 * @author Leeson0202
 * @since 2023-03-17 20:58:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveItemResponse implements Serializable, Comparable {
    private static final long serialVersionUID = 853155001697951161L;

    /**
     * id
     */
    private String receiveId;
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
    private String libraryName;
    private String roomName;
    private String seatName;
    /**
     * 预约日期
     */
    private Date receiveDate;
    private String date; // 几月几日
    /**
     * 预约时间
     */
    private Integer timeIdx;
    /**
     * 创建时间
     */
    private Date time;

    /**
     * 是不是这个时间段
     */
    private Integer status;
    /**
     * 记录预约和就坐状态
     * 0 未入座
     * 1 已入座
     * 2 暂时离开
     */
    private Integer online;

    public ReceiveItemResponse(ReceiveItem receiveItem, int status, int online) {
        this.receiveId = receiveItem.getReceiveId();
        this.userId = receiveItem.getUserId();
        this.libraryId = receiveItem.getLibraryId();
        this.roomId = receiveItem.getRoomId();
        this.seatId = receiveItem.getSeatId();
        this.receiveDate = receiveItem.getReceiveDate();
        this.timeIdx = receiveItem.getTimeIdx();
        this.time = receiveItem.getTime();
        this.status = status;
        this.online = online;
    }


    @Override
    public int compareTo(Object o) {
        ReceiveItemResponse O = (ReceiveItemResponse) o;
        Date d1 = this.getReceiveDate();
        Date d2 = O.getReceiveDate();
        Integer t1 = this.getTimeIdx();
        Integer t2 = O.getTimeIdx();
        if (d1.before(d2) || (d1.equals(d2) && t1 < t2)) {
            return -1;
        } else if (d1.equals(d2) && Objects.equals(t1, t2)) {
            return 0;
        } else {
            return 1;
        }
    }
}

