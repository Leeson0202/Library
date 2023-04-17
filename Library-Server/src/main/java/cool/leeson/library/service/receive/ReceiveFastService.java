package cool.leeson.library.service.receive;

import cool.leeson.library.dao.ReceiveFastDao;
import cool.leeson.library.dao.UserInfoDao;
import cool.leeson.library.entity.receive.ReceiveFast;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.library.LibraryRoomService;
import cool.leeson.library.service.library.LibrarySeatService;
import cool.leeson.library.service.library.LibraryService;
import cool.leeson.library.util.ResMap;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (ReceiveFast)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-03-24 22:40:36
 */
@Service("receiveFastService")
public class ReceiveFastService {
    @Resource
    private ReceiveFastDao receiveFastDao;
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private LibraryService libraryService;
    @Resource
    private LibraryRoomService libraryRoomService;
    @Resource
    private LibrarySeatService librarySeatService;


    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    public Map<String, Object> queryById(String userId) throws MyException {
        ReceiveFast receiveFast = this.receiveFastDao.queryById(userId);
        if (receiveFast == null) {
            receiveFast = new ReceiveFast(userId, null, null, null, null, null, null, null, null, false);
            if (this.receiveFastDao.insert(receiveFast) != 1) {
                throw new MyException(MyException.STATUS.err);
            }
        }
        return ResMap.ok(receiveFast);
    }

    public Object queryByPage(ReceiveFast receiveFast, PageRequest pageRequest) {
        long total = this.receiveFastDao.count(receiveFast);
        List<ReceiveFast> receiveFasts = this.receiveFastDao.queryAllByLimit(receiveFast, pageRequest);
        for (ReceiveFast fast : receiveFasts) {
            fast.setNickName(this.userInfoDao.queryById(fast.getUserId()).getNickName());
            fast.setLibraryName(this.libraryService.queryInfo(fast.getLibraryId()).getName());
            fast.setRoomName(this.libraryRoomService.queryInfo(fast.getRoomId()).getName());
            fast.setSeatName(this.librarySeatService.queryInfo(fast.getSeatId()).getName());
        }
        return ResMap.ok(new PageImpl<>(receiveFasts, pageRequest, total));
    }

    /**
     * 新增数据
     *
     * @param receiveFast 实例对象
     * @return 实例对象
     */
    public ReceiveFast insert(ReceiveFast receiveFast) throws MyException {
        ReceiveFast receiveFast1 = new ReceiveFast();
        receiveFast1.setSeatId(receiveFast.getSeatId());
        List<ReceiveFast> receiveFasts = this.receiveFastDao.queryAllByLimit(receiveFast1, PageRequest.of(0, 10));
        if (receiveFasts.size() > 0) throw new MyException("该座位已有人预约");
        this.receiveFastDao.insert(receiveFast);
        return receiveFast;
    }

    /**
     * 修改数据
     *
     * @param receiveFast 实例对象
     * @return 实例对象
     */
    public Map<String, Object> update(ReceiveFast receiveFast) throws MyException {
        ReceiveFast receiveFast1 = new ReceiveFast();
        receiveFast1.setSeatId(receiveFast.getSeatId());
        List<ReceiveFast> receiveFasts = this.receiveFastDao.queryAllByLimit(receiveFast1, PageRequest.of(0, 10));
        if (receiveFasts.size() > 0) throw new MyException("该座位已有人预约");
        this.receiveFastDao.update(receiveFast);
        return this.queryById(receiveFast.getUserId());
    }


    public Map<String, Object> change(String userId, boolean i) throws MyException {
        ReceiveFast receiveFast = (ReceiveFast) this.queryById(userId).get("data");
        receiveFast.setOpen(i);
        return this.update(receiveFast);
    }

}
