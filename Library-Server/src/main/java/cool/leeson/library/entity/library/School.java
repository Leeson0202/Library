package cool.leeson.library.entity.library;

import java.io.Serializable;

/**
 * (School)实体类
 *
 * @author makejava
 * @since 2023-03-10 22:59:40
 */
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


    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

}

