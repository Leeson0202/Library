package cool.leeson.library.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2023-02-26 15:42:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -14002840480059827L;
    /**
     * id
     */
    private String userId;
    /**
     * 电话
     */
    private String phoneNum;
    /**
     * 邮件
     */
    private String email;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 微信id
     */
    private String openId;

}

