package cool.leeson.library.service.receive;

import cool.leeson.library.entity.receive.ReceiveItem;
import cool.leeson.library.dao.ReceiveItemDao;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (ReceiveItem)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-03-17 20:22:17
 */
@Service("receiveItemService")
public class ReceiveItemService {
    @Resource
    private ReceiveItemDao receiveItemDao;

    /**
     * 通过ID查询单条数据
     *
     * @param receiveId 主键
     * @return 实例对象
     */
    public ReceiveItem queryById(String receiveId) {
        return this.receiveItemDao.queryById(receiveId);
    }

    /**
     * 分页查询
     *
     * @param receiveItem 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    public Page<ReceiveItem> queryByPage(ReceiveItem receiveItem, PageRequest pageRequest) {
        long total = this.receiveItemDao.count(receiveItem);
        return new PageImpl<>(this.receiveItemDao.queryAllByLimit(receiveItem, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param receiveItem 实例对象
     * @return 实例对象
     */
    public ReceiveItem insert(ReceiveItem receiveItem) {
        this.receiveItemDao.insert(receiveItem);
        return receiveItem;
    }

    /**
     * 修改数据
     *
     * @param receiveItem 实例对象
     * @return 实例对象
     */
    public ReceiveItem update(ReceiveItem receiveItem) {
        this.receiveItemDao.update(receiveItem);
        return this.queryById(receiveItem.getReceiveId());
    }

    /**
     * 通过主键删除数据
     *
     * @param receiveId 主键
     * @return 是否成功
     */
    public boolean deleteById(String receiveId) {
        return this.receiveItemDao.deleteById(receiveId) > 0;
    }
}
