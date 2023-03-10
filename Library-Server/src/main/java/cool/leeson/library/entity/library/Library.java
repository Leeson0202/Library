package cool.leeson.library.entity.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * (Library)实体类
 *
 * @author makejava
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
    private List<LibraryRoom> libraryRoom;


}

