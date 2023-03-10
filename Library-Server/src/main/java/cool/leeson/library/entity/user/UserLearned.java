package cool.leeson.library.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserLearned)实体类
 *
 * @author makejava
 * @since 2023-03-07 22:21:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLearned implements Serializable {
    private static final long serialVersionUID = 821278655247545109L;
    /**
     * 用户的单日记录
     */
    private String id;
    /**
     * userId
     */
    private String userId;
    /**
     * 日期
     */
    private Date date;
    /**
     * 今日的时长
     */
    private Integer learnTime;


}

