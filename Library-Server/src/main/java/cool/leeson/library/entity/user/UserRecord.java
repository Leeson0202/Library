package cool.leeson.library.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (UserRecord)实体类
 *
 * @author Leeson0202
 * @since 2023-03-07 22:17:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRecord implements Serializable {
    private static final long serialVersionUID = -50965009124288477L;
    /**
     * userId
     */
    private String userId;
    /**
     * 信用
     */
    private Integer credit;
    /**
     * 单日最长时间
     */
    private Integer maxTime;
    /**
     * 总时间
     */
    private Integer allTime;
    /**
     * 单日排名
     */
    private Integer dayRank;
    /**
     * 周排名
     */
    private Integer weekRank;
    /**
     * 月排名
     */
    private Integer monthRank;
    /**
     * 总排名
     */
    private Integer allRank;


}

