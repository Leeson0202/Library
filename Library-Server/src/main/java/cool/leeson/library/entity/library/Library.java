package cool.leeson.library.entity.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "schoolId不能为空")
    private String schoolId;
    /**
     * 图书馆名字
     */
    @NotBlank(message = "名字不能为空")
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
    private Boolean weekend;
    /**
     * 每日开放开始时间
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = "开始时间不能为空")
    private LocalTime beginTime;
    /**
     * 每日开放结束时间
     */
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = "结束时间不能为空")
    private LocalTime endTime;
    /**
     * 时段长度
     */
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = "时间段不能为空")
    private LocalTime tt;

    private List<LibraryRoom> libraryRooms;


}

