package cool.leeson.library.entity;

import java.io.Serializable;

/**
 * (UserCredit)实体类
 *
 * @author makejava
 * @since 2023-03-06 19:48:26
 */
public class UserCredit implements Serializable {
    private static final long serialVersionUID = -41469830687420172L;
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


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserCredit() {
        return userCredit;
    }

    public void setUserCredit(Integer userCredit) {
        this.userCredit = userCredit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

