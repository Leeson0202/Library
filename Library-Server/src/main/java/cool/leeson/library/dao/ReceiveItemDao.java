package cool.leeson.library.dao;

import cool.leeson.library.entity.receive.ReceiveItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (ReceiveItem)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-03-21 00:26:40
 */
public interface ReceiveItemDao {

    /**
     * 通过ID查询单条数据
     *
     * @param receiveId 主键
     * @return 实例对象
     */
    ReceiveItem queryById(String receiveId);

    List<ReceiveItem> queryByUserId(String userId);

    /**
     * 查询指定行数据
     *
     * @param receiveItem 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<ReceiveItem> queryAllByLimit(@Param("receiveItem") ReceiveItem receiveItem, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param receiveItem 查询条件
     * @return 总行数
     */
    long count(ReceiveItem receiveItem);

    /**
     * 新增数据
     *
     * @param receiveItem 实例对象
     * @return 影响行数
     */
    int insert(ReceiveItem receiveItem);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReceiveItem> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ReceiveItem> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReceiveItem> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ReceiveItem> entities);

    /**
     * 修改数据
     *
     * @param receiveItem 实例对象
     * @return 影响行数
     */
    int update(ReceiveItem receiveItem);

    /**
     * 通过主键删除数据
     *
     * @param receiveId 主键
     * @return 影响行数
     */
    int deleteById(String receiveId);

}

