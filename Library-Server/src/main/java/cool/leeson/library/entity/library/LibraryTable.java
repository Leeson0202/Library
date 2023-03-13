package cool.leeson.library.entity.library;

import java.io.Serializable;

/**
 * (LibraryTable)实体类
 *
 * @author Leeson0202
 * @since 2023-03-11 02:09:06
 */
public class LibraryTable implements Serializable {
    private static final long serialVersionUID = 513201302054058878L;
    /**
     * 桌子id
     */
    private String tableId;
    /**
     * 图书室id
     */
    private String roomId;
    /**
     * 座位名称
     */
    private String name;
    /**
     * x坐标
     */
    private Integer x;
    /**
     * y坐标
     */
    private Integer y;
    /**
     * 宽
     */
    private Integer width;
    /**
     * 高
     */
    private Integer height;


    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}

