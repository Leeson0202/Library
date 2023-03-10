package cool.leeson.library.entity.library;

import java.io.Serializable;

/**
 * (LibrarySeat)实体类
 *
 * @author makejava
 * @since 2023-03-11 02:08:49
 */
public class LibrarySeat implements Serializable {
    private static final long serialVersionUID = -26186246891426743L;
    /**
     * 座位id
     */
    private String seatId;
    /**
     * 图书室id
     */
    private String roomId;
    /**
     * 座位名称
     */
    private String name;
    /**
     * 维修状态
     */
    private Integer repair;
    /**
     * x坐标
     */
    private Integer x;
    /**
     * y坐标
     */
    private Integer y;
    /**
     * 方向
     */
    private Integer direction;
    /**
     * 宽
     */
    private Integer width;
    /**
     * 高
     */
    private Integer height;


    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
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

    public Integer getRepair() {
        return repair;
    }

    public void setRepair(Integer repair) {
        this.repair = repair;
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

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
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

