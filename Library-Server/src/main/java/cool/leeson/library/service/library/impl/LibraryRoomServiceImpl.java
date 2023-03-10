package cool.leeson.library.service.library.impl;

import cool.leeson.library.entity.library.LibraryRoom;
import cool.leeson.library.dao.LibraryRoomDao;
import cool.leeson.library.service.library.LibraryRoomService;
import cool.leeson.library.util.ResMap;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (LibraryRoom)表服务实现类
 *
 * @author makejava
 * @since 2023-03-11 02:02:35
 */
@Service("libraryRoomService")
public class LibraryRoomServiceImpl implements LibraryRoomService {
    @Resource
    private LibraryRoomDao libraryRoomDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roomId 主键
     * @return 实例对象
     */
    @Override
    public Map<String,Object> queryById(String roomId) {
        return ResMap.ok(this.libraryRoomDao.queryById(roomId));
    }

    @Override
    public Map<String, Object> queryByLibraryId(String libraryId) {
        return ResMap.ok(this.libraryRoomDao.queryByLibraryId(libraryId));
    }

    /**
     * 分页查询
     *
     * @param libraryRoom 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
    public boolean deleteById(String roomId) {
        return this.libraryRoomDao.deleteById(roomId) > 0;
    }
}
