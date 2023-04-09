package cool.leeson.library.service.library;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.utils.StringUtils;
import cool.leeson.library.dao.LibraryRoomDao;
import cool.leeson.library.dao.LibrarySeatDao;
import cool.leeson.library.dao.LibraryTableDao;
import cool.leeson.library.entity.library.LibraryRoom;
import cool.leeson.library.entity.library.LibrarySeat;
import cool.leeson.library.entity.library.LibraryTable;
import cool.leeson.library.entity.tools.RedisTool;
import cool.leeson.library.exceptions.MyException;
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
    private LibrarySeatService librarySeatService;
    @Resource
    private LibraryTableService libraryTableService;
    @Resource
    private LibrarySeatDao librarySeatDao;
    @Resource
    private LibraryTableDao libraryTableDao;
    @Resource
    private RedisTool redisTool;

    /**
     * 通过 roomId 获得房间的所有信息 包括椅子 座子 不包含椅子状态
     *
     * @param roomId id
     * @return 实体
     */
    public Map<String, Object> queryById(String roomId) throws MyException {
        LibraryRoom libraryRoom;
        String roomKey = String.format(RedisTool.FormatKey.ROOM.toString(), roomId);
        String s = redisTool.get(roomKey);
        if (StringUtils.isEmpty(s)) {
            libraryRoom = this.queryInfo(roomId);
        } else {
            libraryRoom = JSON.parseObject(s, LibraryRoom.class);
        }
        if (libraryRoom == null) {
            log.error(roomId + " 房间不存在");
            throw new MyException("房间不存在");
        }
        List<LibrarySeat> librarySeat = this.librarySeatDao.queryByRoomId(libraryRoom.getRoomId());
        List<LibraryTable> libraryTables = this.libraryTableDao.queryByRoomId(libraryRoom.getRoomId());
        for (LibrarySeat seat : librarySeat) {
            seat.setRed(false);
        }
        libraryRoom.setLibrarySeats(librarySeat);
        libraryRoom.setLibraryTables(libraryTables);
        // 进缓存
        redisTool.set(roomKey, libraryRoom);
        return ResMap.ok(libraryRoom);
    }

    /**
     * 通过roomId 查找简单信息
     *
     * @param roomId roomId
     */
    public LibraryRoom queryInfo(String roomId) {
        LibraryRoom room;
        String infoKey = String.format(RedisTool.FormatKey.INFO.toString(), roomId);
        String s = this.redisTool.get(infoKey);

        if (StringUtils.isEmpty(s) || "".equals(s)) {
            room = this.libraryRoomDao.queryById(roomId);
            if (room != null) {
                // 储存到 redis
                redisTool.set(infoKey, room);
            }
        } else {
            room = JSON.parseObject(s, LibraryRoom.class);
        }
        return room;
    }

    /**
     * 通过ID查询单条数据 包含椅子的状态
     *
     * @param roomId 主键
     * @param today  今天还是明天的 Tag
     * @param idx    时间是多少 1代表 8:00-10:00
     * @return 实例对象
     */
    public Map<String, Object> queryByTime(String roomId, Boolean today, Integer idx) throws MyException {
        // 解析
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        if (!today) c.add(Calendar.DAY_OF_MONTH, 1); // 明天的
        int day = c.getTime().getDate();
        Map<String, Object> query = this.queryById(roomId); // 获取全部信息 没有椅子状态
        LibraryRoom libraryRoom = (LibraryRoom) query.get("data");

        // 查缓存 - 椅子是否预约
        for (LibrarySeat seat : libraryRoom.getLibrarySeats()) {
            String seatKey = String.format(RedisTool.FormatKey.RECEIVE.toString(), seat.getSeatId(), day, idx);
            String s = redisTool.get(seatKey);
            s = JSON.parseObject(s, String.class);
            // 有就直接拿出来用
            seat.setRed("true".equals(s));
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
        this.redisTool.deleteByPrefix("school"); // 删除缓存
        return ResMap.ok(libraryRoom);
    }

    /**
     * 修改数据
     *
     * @param libraryRoom 实例对象
     * @return 实例对象
     */
    public Map<String, Object> update(LibraryRoom libraryRoom) throws MyException {
        if (StringUtils.isEmpty(libraryRoom.getRoomId())) ResMap.err("roomId不能为空");
        this.libraryRoomDao.update(libraryRoom);

        this.redisTool.deleteByPrefix("school"); // 删除缓存
        return this.queryById(libraryRoom.getRoomId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roomId 主键
     * @return 是否成功
     */
    public Map<String, Object> deleteById(String roomId) throws MyException {
        if (StringUtils.isEmpty(roomId)) ResMap.err("roomId不能为空");
        LibraryRoom libraryRoom = (LibraryRoom) this.queryById(roomId).get("data");
        // 删除椅子
        List<LibrarySeat> librarySeats = libraryRoom.getLibrarySeats();
        for (LibrarySeat librarySeat : librarySeats) {
            this.librarySeatService.deleteById(librarySeat.getSeatId());
        }
        // 删除桌子
        List<LibraryTable> libraryTables = libraryRoom.getLibraryTables();
        for (LibraryTable libraryTable : libraryTables) {
            this.libraryTableService.deleteById(libraryTable.getTableId());
        }
        // 删除图书室
        if (this.libraryRoomDao.deleteById(roomId) > 0) {
            return ResMap.ok();
        }
        this.redisTool.deleteByPrefix("school"); // 删除缓存
        return ResMap.err();
    }

}
