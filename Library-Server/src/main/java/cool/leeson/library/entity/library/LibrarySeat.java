package cool.leeson.library.entity.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (LibrarySeat)实体类
 *
 * @author Leeson0202
 * @since 2023-03-11 02:08:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibrarySeat implements Serializable {
    private static final long serialVersionUID = -26186246891426743L;
    /**
     * 座位id
     */
    private String seatId;
    /**
     * 图书室id
     */
    private String roomId;
    /**
     * 座位名称
     */
    private String name;
    /**
     * 维修状态
     */
    private Boolean repair;
    /**
     * x坐标
     */
    private Integer x;
    /**
     * y坐标
     */
    private Integer y;
    /**
     * 方向
     */
    private Integer direction;
    /**
     * 宽
     */
    private Integer width;
    /**
     * 高
     */
    private Integer height;
    /**
     * 有没有人
     */
    private Boolean red;


}

