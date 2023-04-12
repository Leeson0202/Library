package cool.leeson.library.service.receive;

import cool.leeson.library.dao.ReceiveFastDao;
import cool.leeson.library.entity.receive.ReceiveFast;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.util.ResMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    public Map<String, Object> queryById(String userId) throws MyException {
        ReceiveFast receiveFast = this.receiveFastDao.queryById(userId);
        if (receiveFast == null) {
            receiveFast = new ReceiveFast(userId, null, null, null, null,0);
            if (this.receiveFastDao.insert(receiveFast) != 1) {
                throw new MyException(MyException.STATUS.err);
            }
        }
        return ResMap.ok(receiveFast);
    }


    /**
     * 新增数据
     *
     * @param receiveFast 实例对象
     * @return 实例对象
     */
    public ReceiveFast insert(ReceiveFast receiveFast) {
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
        this.receiveFastDao.update(receiveFast);
        return this.queryById(receiveFast.getUserId());
    }


    public Map<String, Object> change(String userId, int i) throws MyException {
        ReceiveFast receiveFast = (ReceiveFast) this.queryById(userId).get("data");
        receiveFast.setOpen(i);
        return this.update(receiveFast);
    }

}
