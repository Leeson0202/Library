package cool.leeson.library.service.receive;

import cool.leeson.library.dao.ReceiveOrderDao;
import cool.leeson.library.entity.receive.ReceiveOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (ReceiveOrder)表服务实现类
 *
 * @author Leeson0202
 * @since 2023-03-17 16:43:01
 */
@Service("receiveOrderService")
public class ReceiveOrderService {
    @Resource
    private ReceiveOrderDao receiveOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    public ReceiveOrder queryById(String orderId) {
        return this.receiveOrderDao.queryById(orderId);
    }

    /**
     * 分页查询
     *
     * @param receiveOrder 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    public Page<ReceiveOrder> queryByPage(ReceiveOrder receiveOrder, PageRequest pageRequest) {
        long total = this.receiveOrderDao.count(receiveOrder);
        return new PageImpl<>(this.receiveOrderDao.queryAllByLimit(receiveOrder, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param receiveOrder 实例对象
     * @return 实例对象
     */
    public ReceiveOrder insert(ReceiveOrder receiveOrder) {
        this.receiveOrderDao.insert(receiveOrder);
        return receiveOrder;
    }

    /**
     * 修改数据
     *
     * @param receiveOrder 实例对象
     * @return 实例对象
     */
    public ReceiveOrder update(ReceiveOrder receiveOrder) {
        this.receiveOrderDao.update(receiveOrder);
        return this.queryById(receiveOrder.getOrderId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    public boolean deleteById(String orderId) {
        return this.receiveOrderDao.deleteById(orderId) > 0;
    }
}
