package cool.leeson.library.entity.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * (School)实体类
 *
 * @author Leeson0202
 * @since 2023-03-10 22:59:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School implements Serializable {
    private static final long serialVersionUID = 166594818735096072L;
    /**
     * id
     */
    private String schoolId;
    /**
     * 名称
     */
    private String name;
    /**
     * 简介
     */
    private String descs;
    /**
     * 图标
     */
    private String icon;
    /**
     * 背景
     */
    private String background;
    /**
     * 图书馆列表
     */
    private List<Library> libraries;
    /**
     * 预约规则
     */
    private SchoolRule schoolRule;


}

