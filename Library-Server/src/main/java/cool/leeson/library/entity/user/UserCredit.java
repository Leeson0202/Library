package cool.leeson.library.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (UserCredit)实体类
 *
 * @author Leeson0202
 * @since 2023-03-07 22:18:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredit implements Serializable {
    private static final long serialVersionUID = 599893950732877222L;
    /**
     * 信用记录id
     */
    private String id;
    /**
     * userId
     */
    private String userId;
    /**
     * 信用条目
     */
    private Integer userCredit;
    /**
     * 备注
     */
    private String description;


}

