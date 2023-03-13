package cool.leeson.library.entity.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.io.Serializable;
import java.util.List;

/**
 * (LibraryRoom)实体类
 *
 * @author Leeson0202
 * @since 2023-03-11 02:02:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryRoom implements Serializable {
    private static final long serialVersionUID = 458821484453473103L;
    /**
     * 图书室id
     */
    private String roomId;
    /**
     * 图书馆Id
     */
    private String libraryId;
    /**
     * 图书室名字
     */
    private String name;
    /**
     * 图书室描述
     */
    private String descs;
    /**
     * 座位数量
     */
    private Integer seatNum;
    /**
     * 周末是否开放
     */
    private Boolean weekend;
    /**
     * 每日开放开始时间
     */
    private LocalTime beginTime;
    /**
     * 每日开放结束时间
     */
    private LocalTime endTime;
    // 椅子
    private List<LibrarySeat> librarySeats;
    // 桌子
    private List<LibraryTable> libraryTables;

}

