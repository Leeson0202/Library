package cool.leeson.library.entity.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (LibraryTable)实体类
 *
 * @author Leeson0202
 * @since 2023-03-11 02:09:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryTable implements Serializable {
    private static final long serialVersionUID = 513201302054058878L;
    /**
     * 桌子id
     */
    private String tableId;
    /**
     * 图书室id
     */
    private String roomId;
    /**
     * 座位名称
     */
    private String name;
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

}

