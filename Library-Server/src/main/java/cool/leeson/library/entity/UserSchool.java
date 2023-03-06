package cool.leeson.library.entity;

import java.io.Serializable;

/**
 * (UserSchool)实体类
 *
 * @author makejava
 * @since 2023-03-06 19:49:17
 */
public class UserSchool implements Serializable {
    private static final long serialVersionUID = 683797887429877254L;
    /**
     * userId
     */
    private String userId;
    /**
     * 学校id
     */
    private String schoolId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

}

