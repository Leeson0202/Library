package cool.leeson.library.service.library;

import cool.leeson.library.dao.LibraryRoomDao;
import cool.leeson.library.dao.LibrarySeatDao;
import cool.leeson.library.dao.LibraryTableDao;
import cool.leeson.library.entity.library.LibraryRoom;
import cool.leeson.library.entity.library.LibrarySeat;
import cool.leeson.library.entity.library.LibraryTable;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    /**
     * 通过ID查询单条数据
     *
     * @param roomId 主键
     * @return 实例对象
     */
    public Map<String, Object> queryById(String roomId) {
        LibraryRoom libraryRoom = this.libraryRoomDao.queryById(roomId);
        if (libraryRoom == null) {
            log.error(roomId + " 房间不存在");
            return ResMap.err("房间不存在");
        }
        List<LibrarySeat> librarySeat = this.librarySeatDao.queryByRoomId(libraryRoom.getRoomId());
        List<LibraryTable> libraryTables = this.libraryTableDao.queryByRoomId(libraryRoom.getRoomId());
        libraryRoom.setLibrarySeats(librarySeat);
        libraryRoom.setLibraryTables(libraryTables);
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
    public LibraryRoom insert(LibraryRoom libraryRoom) {
        this.libraryRoomDao.insert(libraryRoom);
        return libraryRoom;
    }

    /**
     * 修改数据
     *
     * @param libraryRoom 实例对象
     * @return 实例对象
     */
    public Map<String, Object> update(LibraryRoom libraryRoom) {
        this.libraryRoomDao.update(libraryRoom);
        return this.queryById(libraryRoom.getRoomId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roomId 主键
     * @return 是否成功
     */
    public boolean deleteById(String roomId) {
        return this.libraryRoomDao.deleteById(roomId) > 0;
    }
}
