package cool.leeson.lib.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (User)表实体类
 *
 * @author Leeson0202
 * @since 2023-04-25 20:41:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //id
    @TableId(type = IdType.AUTO)
    private String userId;
    //电话
    private String phoneNum;
    //邮件
    private String email;
    //密码
    private String pwd;
    //微信id
    private String openid;


}

