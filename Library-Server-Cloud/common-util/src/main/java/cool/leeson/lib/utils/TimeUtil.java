package cool.leeson.lib.utils;

import java.util.Date;

public class TimeUtil {

    /**
     * 获取当前时间相对的零点
     *
     * @param day 前后几天
     */
    public static Date getZeroTimeOf(Integer day) {
        Date now = new Date();
        now.setDate(now.getDate() + day);
        return getZeroTimeAt(now, 0);
    }

    /**
     * 获取剩余时间 相对于当前的零点
     *
     * @param day 前后几天
     * @return
     */
    public static Long getLeftToZeroOf(Integer day) {
        Date now = new Date();
        now.setDate(now.getDate() + day);
        return getLeftToTime(now, 0);
    }

    /**
     * 获取当天的零点
     */
    public static Date getZero(Date date) {
        return getZeroTimeAt(date, 0);
    }

    /**
     * 获取到当天零点的剩余时间
     */
    public static Long getLeftToZero(Date date) {
        return getLeftToTime(date, 0);
    }


    /**
     * 获取到当天某整点的剩余时间
     */
    public static Long getLeftToTime(Date date, Integer time) {
        return getZeroTimeAt(date, time).getTime() - new Date().getTime();
    }

    /**
     * 获取当天 某整点的时间
     */

    public static Date getZeroTimeAt(Date date, Integer time) {
        return new Date(date.getYear(), date.getMonth(), date.getDate(), time, 0, 0);
    }
}
