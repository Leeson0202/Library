package cool.leeson.library.service.library.impl;

import cool.leeson.library.dao.*;
import cool.leeson.library.entity.library.Library;
import cool.leeson.library.entity.library.LibraryRoom;
import cool.leeson.library.entity.library.LibrarySeat;
import cool.leeson.library.entity.library.LibraryTable;
import cool.leeson.library.entity.user.UserSchool;
import cool.leeson.library.service.library.LibraryService;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Library)表服务实现类
 *
 * @author makejava
 * @since 2023-03-10 21:55:47
 */
@Service("libraryService")
@Slf4j
public class LibraryServiceImpl implements LibraryService {
    @Resource
    private LibraryDao libraryDao;
    @Resource
    private UserSchoolDao userSchoolDao;
    @Resource
    private LibraryRoomDao libraryRoomDao;
    @Resource
    private LibrarySeatDao librarySeatDao;
    @Resource
    private LibraryTableDao libraryTableDao;

    /**
     * 通过userId 查询图书馆
     *
     * @param userId 用户id
     * @return 实体
     */
    @Override
    public Map<String, Object> queryLibraryByUserId(String userId) {
        log.info(userId + " 正在查询图书馆列表");
        UserSchool userSchool = this.userSchoolDao.queryByUserId(userId);
        if (userSchool == null) {
            log.error(userId + " 没有绑定图书馆");
            return ResMap.err("请绑定学校");
        }
        return this.queryLibraryBySchoolId(userSchool.getSchoolId());
    }


    /**
     * 通过userId 查询图书馆
     *
     * @param schoolId 用户id
     * @return 实体
     */
    @Override
    public Map<String, Object> queryLibraryBySchoolId(String schoolId) {
        List<Library> libraries = this.libraryDao.queryBySchoolId(schoolId);
        return ResMap.ok(libraries);
    }

    @Override
    public Map<String, Object> queryRoomByLibraryId(String libraryId) {
        List<LibraryRoom> libraryRooms = this.libraryRoomDao.queryByLibraryId(libraryId);
        if (libraryRooms == null || libraryRooms.size() == 0) {
            log.error("房间为空");
            return ResMap.err("房间为空");
        }
        return ResMap.ok(libraryRooms);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param libraryId 主键
     * @return 实例对象
     */
    @Override
    public Library queryById(String libraryId) {
        return this.libraryDao.queryById(libraryId);
    }

    /**
     * 分页查询
     *
     * @param library     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Library> queryByPage(Library library, PageRequest pageRequest) {
        long total = this.libraryDao.count(library);
        return new PageImpl<>(this.libraryDao.queryAllByLimit(library, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param library 实例对象
     * @return 实例对象
     */
    @Override
    public Library insert(Library library) {
        this.libraryDao.insert(library);
        return library;
    }

    /**
     * 修改数据
     *
     * @param library 实例对象
     * @return 实例对象
     */
    @Override
    public Library update(Library library) {
        this.libraryDao.update(library);
        return this.queryById(library.getLibraryId());
    }

    /**
     * 通过主键删除数据
     *
     * @param libraryId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String libraryId) {
        return this.libraryDao.deleteById(libraryId) > 0;
    }

    @Override
    public Map<String, Object> queryRoomByRoomId(String roomId) {
        LibraryRoom libraryRoom = this.libraryRoomDao.queryById(roomId);
        if (libraryRoom == null) {
            return ResMap.err("没有该房间信息");
        }
        List<LibrarySeat> librarySeat = this.librarySeatDao.queryByRoomId(libraryRoom.getRoomId());
        List<LibraryTable> libraryTables = this.libraryTableDao.queryByRoomId(libraryRoom.getRoomId());
        libraryRoom.setLibrarySeats(librarySeat);
        libraryRoom.setLibraryTables(libraryTables);
        return ResMap.ok(libraryRoom);
    }
}
