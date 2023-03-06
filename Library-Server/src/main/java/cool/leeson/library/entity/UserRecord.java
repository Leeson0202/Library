package cool.leeson.library.entity;

import java.io.Serializable;

/**
 * (UserRecord)实体类
 *
 * @author makejava
 * @since 2023-03-06 19:47:53
 */
public class UserRecord implements Serializable {
    private static final long serialVersionUID = -31470452963223112L;
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


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    public Integer getAllTime() {
        return allTime;
    }

    public void setAllTime(Integer allTime) {
        this.allTime = allTime;
    }

    public Integer getDayRank() {
        return dayRank;
    }

    public void setDayRank(Integer dayRank) {
        this.dayRank = dayRank;
    }

    public Integer getWeekRank() {
        return weekRank;
    }

    public void setWeekRank(Integer weekRank) {
        this.weekRank = weekRank;
    }

    public Integer getMonthRank() {
        return monthRank;
    }

    public void setMonthRank(Integer monthRank) {
        this.monthRank = monthRank;
    }

    public Integer getAllRank() {
        return allRank;
    }

    public void setAllRank(Integer allRank) {
        this.allRank = allRank;
    }

}

