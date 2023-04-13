package cool.leeson.library.service.library;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.utils.StringUtils;
import cool.leeson.library.dao.LibraryRoomDao;
import cool.leeson.library.dao.LibrarySeatDao;
import cool.leeson.library.entity.library.LibraryRoom;
import cool.leeson.library.entity.library.LibrarySeat;
import cool.leeson.library.entity.tools.RedisTool;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * (LibrarySeat)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-03-21 03:28:03
 */
@Service("librarySeatService")
@Slf4j
public class LibrarySeatService {
    @Resource
    private LibrarySeatDao librarySeatDao;
    @Resource
    private LibraryRoomDao libraryRoomDao;
    @Resource
    private RedisTool redisTool;

    /**
     * 通过ID查询单条数据
     *
     * @param seatId 主键
     * @return 实例对象
     */
    public Map<String, Object> queryById(String seatId) {
        LibrarySeat seat = this.queryInfo(seatId);
        if (seat == null) {
            log.warn(seatId + " 没有座位信息");
            return ResMap.err("没有座位信息");
        }
        return ResMap.ok(seat);
    }

    /**
     * 简单信息
     */
    public LibrarySeat queryInfo(String seatId) {
        LibrarySeat seat;
        String infoKey = String.format(RedisTool.FormatKey.INFO.toString(), seatId);
        String s = redisTool.get(infoKey);
        if (StringUtils.isEmpty(s) || "".equals(s)) {
            seat = this.librarySeatDao.queryById(seatId);
            if (seat != null) {
                // 储存到 redis
                redisTool.set(infoKey, seat);
            }
        } else {
            seat = JSON.parseObject(s, LibrarySeat.class);
        }
        return seat;
    }

    /**
     * 分页查询
     *
     * @param librarySeat 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    public Page<LibrarySeat> queryByPage(LibrarySeat librarySeat, PageRequest pageRequest) {
        long total = this.librarySeatDao.count(librarySeat);
        return new PageImpl<>(this.librarySeatDao.queryAllByLimit(librarySeat, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param librarySeat 实例对象
     * @return 实例对象
     */
    public Map<String, Object> insert(LibrarySeat librarySeat) throws MyException {
        String seatId = UUID.randomUUID().toString();
        librarySeat.setSeatId(seatId);
        if (this.librarySeatDao.insert(librarySeat) == 0) {
            return ResMap.err();
        }
        LibrarySeat librarySeat1 = new LibrarySeat();
        librarySeat1.setRoomId(librarySeat.getRoomId());
        long count = this.librarySeatDao.count(librarySeat1);
        LibraryRoom libraryRoom = this.libraryRoomDao.queryById(librarySeat.getRoomId());
        libraryRoom.setSeatNum((int) count - 1);
        this.libraryRoomDao.update(libraryRoom);
        this.redisTool.deleteByPrefix("school"); // 删除缓存

        return this.queryById(seatId);
    }

    public Object insertOrUpdate(List<LibrarySeat> librarySeatList) {
        for (LibrarySeat librarySeat : librarySeatList) {
            if (librarySeat.getSeatId() == null) {
                librarySeat.setSeatId(UUID.randomUUID().toString());
            }
        }
        this.librarySeatDao.insertOrUpdateBatch(librarySeatList);
        return ResMap.ok("保存成功");
    }

    /**
     * 修改数据
     *
     * @param librarySeat 实例对象
     * @return 实例对象
     */
    public Map<String, Object> update(LibrarySeat librarySeat) {
        this.librarySeatDao.update(librarySeat);
        this.redisTool.deleteByPrefix("school"); // 删除缓存
        return this.queryById(librarySeat.getSeatId());
    }

    /**
     * 通过主键删除数据
     *
     * @param seatId 主键
     * @return 是否成功
     */
    public Map<String, Object> deleteById(String seatId) throws MyException {
        LibrarySeat librarySeat = this.librarySeatDao.queryById(seatId);
        LibrarySeat librarySeat1 = new LibrarySeat();
        librarySeat1.setRoomId(librarySeat.getRoomId());
        long count = this.librarySeatDao.count(librarySeat1);
        LibraryRoom libraryRoom = this.libraryRoomDao.queryById(librarySeat.getRoomId());
        libraryRoom.setSeatNum((int) count - 1);
        this.libraryRoomDao.update(libraryRoom);
        if (this.librarySeatDao.deleteById(seatId) > 0) {
            return ResMap.ok();
        }
        this.redisTool.deleteByPrefix("school"); // 删除缓存
        this.redisTool.deleteByPrefix("receive"); // 删除缓存
        return ResMap.err();
    }


}
