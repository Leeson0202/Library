package cool.leeson.library.entity.receive;

import java.util.Date;
import java.io.Serializable;

/**
 * (ReceiveOrder)实体类
 *
 * @author Leeson0202
 * @since 2023-03-17 16:43:01
 */
public class ReceiveOrder implements Serializable {
    private static final long serialVersionUID = 548121936339946160L;
    /**
     * id
     */
    private String orderId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 创建时间
     */
    private Date time;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}

