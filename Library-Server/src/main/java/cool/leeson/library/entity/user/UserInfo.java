package cool.leeson.library.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    // user_id
    private String userId;
    //OPEN_id
    private String openid;
    //会话密钥
    private String sessionKey;
    //头像路径
    private String avatarUrl;
    //城市
    private String city;
    //省份
    private String province;
    //国家
    private String country;
    //性别
    private String gender;
    //语言
    private String language;
    //昵称
    private String nickName;
    //备注名或真实名
    private String realName;
    // 年龄
    private Short age;
    //学生ID
    private Integer stuId;
    // 背景
    private String background;
    // 用户记录
    private UserRecord userRecord;
}
