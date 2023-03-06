package cool.leeson.library.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserLearned)实体类
 *
 * @author makejava
 * @since 2023-03-06 19:48:51
 */
public class UserLearned implements Serializable {
    private static final long serialVersionUID = 830130412881277113L;
    /**
     * userId
     */
    private String userId;
    /**
     * 日期
     */
    private Date date;
    /**
     * 单日时长
     */
    private Integer learnTime;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLearnTime() {
        return learnTime;
    }

    public void setLearnTime(Integer learnTime) {
        this.learnTime = learnTime;
    }

}

