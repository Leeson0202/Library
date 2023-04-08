package cool.leeson.library.service.library;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.utils.StringUtils;
import cool.leeson.library.config.RedisConfig;
import cool.leeson.library.dao.LibraryRoomDao;
import cool.leeson.library.dao.LibrarySeatDao;
import cool.leeson.library.dao.LibraryTableDao;
import cool.leeson.library.entity.library.LibraryRoom;
import cool.leeson.library.entity.library.LibrarySeat;
import cool.leeson.library.entity.library.LibraryTable;
import cool.leeson.library.entity.tools.RedisTool;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * (LibraryRoom)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-03-11 13:32:55
 */
@Service("libraryRoomService")
@Slf4j
public class LibraryRoomService {
    @Resource
    private LibraryRoomDao libraryRoomDao;
    @Resource
    private LibrarySeatDao librarySeatDao;
    @Resource
    private LibraryTableDao libraryTableDao;
    @Resource
    private RedisTool redisTool;

    /**
     * 通过 roomId 获得房间的所有信息
     *
     * @param roomId id
     * @return 实体
     */
    public Map<String, Object> query(String roomId) {
        LibraryRoom libraryRoom = this.libraryRoomDao.queryById(roomId);
        if (libraryRoom == null) {
            log.error(roomId + " 房间不存在");
            return ResMap.err("房间不存在");
        }
        List<LibrarySeat> librarySeat = this.librarySeatDao.queryByRoomId(libraryRoom.getRoomId());
        List<LibraryTable> libraryTables = this.libraryTableDao.queryByRoomId(libraryRoom.getRoomId());
        for (LibrarySeat seat : librarySeat) {
            seat.setRed(false);
        }
        libraryRoom.setLibrarySeats(librarySeat);
        libraryRoom.setLibraryTables(libraryTables);
        // 加入缓存
        redisTool.set(String.format(RedisConfig.FormatKey.INFO.toString(), roomId), libraryRoom);
        return ResMap.ok(libraryRoom);
    }


    public LibraryRoom queryInfo(String roomId) {
        LibraryRoom room;
        String roomKey = String.format(RedisConfig.FormatKey.INFO.toString(), roomId);
        String s = this.redisTool.get(roomKey);

        if (StringUtils.isEmpty(s) || "".equals(s)) {
            room = this.libraryRoomDao.queryById(roomId);
            if (room == null) {
                return null;
            } else {
                // 储存到 redis
                redisTool.set(roomKey, room);
            }
        } else {
            room = JSON.parseObject(s, LibraryRoom.class);
        }
        return room;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param roomId 主键
     * @param today  今天还是明天的 Tag
     * @param idx    时间是多少 1代表 8:00-10:00
     * @return 实例对象
     */
    public Map<String, Object> queryById(String roomId, Boolean today, Integer idx) {
        // 解析
//        System.out.println(new Date().getDay());
        int day = new Date().getDate();
        if (!today) { // 明天的
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DAY_OF_MONTH, 1);
            day = c.getTime().getDate();
        }
        // 查缓存
        String roomKey = String.format(RedisConfig.FormatKey.INFO.toString(), roomId);
        String room = this.redisTool.get(roomKey);
        LibraryRoom libraryRoom = null;
        if (!StringUtils.isEmpty(room)) {
            // 非空 继续查缓冲 获取seat
            libraryRoom = JSONObject.parseObject(room, LibraryRoom.class);
            for (LibrarySeat seat : libraryRoom.getLibrarySeats()) {
                String seatKey = String.format(RedisConfig.FormatKey.INFO.toString(), seat.getSeatId(), day, idx);
                String s = redisTool.get(seatKey);
                // 有就直接拿出来用
                seat.setRed("true".equals(s));
            }

        } else {
            // 空 查数据库 加入缓冲
            Map<String, Object> query = this.query(roomId); // 自动加入缓存
            libraryRoom = (LibraryRoom) query.get("data");
        }

        return ResMap.ok(libraryRoom);
    }

    /**
     * 获取图书室列表 - 图书馆Id
     *
     * @param libraryId id
     * @return 实体
     */
    public Map<String, Object> queryByLibraryId(String libraryId) {
        List<LibraryRoom> libraryRooms = this.libraryRoomDao.queryByLibraryId(libraryId);
        if (libraryRooms == null || libraryRooms.size() == 0) {
            log.error("房间为空");
            return ResMap.err("房间为空");
        }
        return ResMap.ok(libraryRooms);
    }


    /**
     * 分页查询
     *
     * @param libraryRoom 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    public Page<LibraryRoom> queryByPage(LibraryRoom libraryRoom, PageRequest pageRequest) {
        long total = this.libraryRoomDao.count(libraryRoom);
        return new PageImpl<>(this.libraryRoomDao.queryAllByLimit(libraryRoom, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param libraryRoom 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(LibraryRoom libraryRoom) {
        if (StringUtils.isEmpty(libraryRoom.getLibraryId())) ResMap.err("libraryId不能为空");
        String roomId = UUID.randomUUID().toString();
        libraryRoom.setRoomId(roomId);
        this.libraryRoomDao.insert(libraryRoom);
        this.redisTool.flushAll(); // 删除缓存
        return ResMap.ok(libraryRoom);
    }

    /**
     * 修改数据
     *
     * @param libraryRoom 实例对象
     * @return 实例对象
     */
    public Map<String, Object> update(LibraryRoom libraryRoom) {
        if (StringUtils.isEmpty(libraryRoom.getRoomId())) ResMap.err("roomId不能为空");
        this.libraryRoomDao.update(libraryRoom);

        this.redisTool.flushAll(); // 删除缓存
        return this.query(libraryRoom.getRoomId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roomId 主键
     * @return 是否成功
     */
    public Map<String, Object> deleteById(String roomId) {
        if (StringUtils.isEmpty(roomId)) ResMap.err("roomId不能为空");
        if (this.libraryRoomDao.deleteById(roomId) > 0) {
            return ResMap.ok();
        }
        this.redisTool.flushAll(); // 删除缓存
        return ResMap.err();
    }

}
