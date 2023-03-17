package cool.leeson.library.entity.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (ReceiveOrder)实体类
 *
 * @author Leeson0202
 * @since 2023-03-17 16:43:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    /**
     * 预约详情
     */
    private List<ReceiveItem> receiveItems;
    private List<ReceiveItemResponse> receiveItemResponses;


}

