package cool.leeson.library.dao;

import cool.leeson.library.entity.receive.ReceiveOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (ReceiveOrder)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-03-17 16:43:01
 */
public interface ReceiveOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    ReceiveOrder queryById(String orderId);

    /**
     * 查询指定行数据
     *
     * @param receiveOrder 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<ReceiveOrder> queryAllByLimit(ReceiveOrder receiveOrder, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param receiveOrder 查询条件
     * @return 总行数
     */
    long count(ReceiveOrder receiveOrder);

    /**
     * 新增数据
     *
     * @param receiveOrder 实例对象
     * @return 影响行数
     */
    int insert(ReceiveOrder receiveOrder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReceiveOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ReceiveOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReceiveOrder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ReceiveOrder> entities);

    /**
     * 修改数据
     *
     * @param receiveOrder 实例对象
     * @return 影响行数
     */
    int update(ReceiveOrder receiveOrder);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    int deleteById(String orderId);

}

