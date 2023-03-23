package cool.leeson.library.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserOnline)实体类
 *
 * @author Leeson0202
 * @since 2023-03-24 01:05:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOnline implements Serializable {
    private static final long serialVersionUID = 932945363786125073L;
    /**
     * id
     */
    private String userId;
    /**
     * 在座状态
     */
    private Integer online;
    /**
     * 开始就坐时间
     */
    private Date date;

}

