package cool.leeson.library.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (UserSchool)实体类
 *
 * @author makejava
 * @since 2023-03-07 22:24:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSchool implements Serializable {
    private static final long serialVersionUID = -69430525157765173L;
    /**
     * 用户学校记录id
     */
    private String id;
    /**
     * userId
     */
    private String userId;
    /**
     * 学校id
     */
    private String schoolId;


}

