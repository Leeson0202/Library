package cool.leeson.library.entity.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

/**
 * (Library)实体类
 *
 * @author Leeson0202
 * @since 2023-03-10 22:59:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Library implements Serializable {
    private static final long serialVersionUID = 507207274478901005L;
    /**
     * 图书馆Id
     */
    private String libraryId;
    /**
     * 学校id
     */
    private String schoolId;
    /**
     * 图书馆名字
     */
    private String name;
    /**
     * 简介
     */
    private String descs;
    /**
     * 背景
     */
    private String background;
    /**
     * 周末是否开放
     */
    private Integer weekend;
    /**
     * 每日开放开始时间
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime beginTime;
    /**
     * 每日开放结束时间
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;
    /**
     * 时段长度
     */
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime tt;

    private List<LibraryRoom> libraryRooms;


}

