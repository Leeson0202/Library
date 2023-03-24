package cool.leeson.library.dao;

import cool.leeson.library.entity.receive.ReceiveFast;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (ReceiveFast)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-03-24 22:40:36
 */
public interface ReceiveFastDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    ReceiveFast queryById(String userId);

    /**
     * 查询指定行数据
     *
     * @param receiveFast 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<ReceiveFast> queryAllByLimit(ReceiveFast receiveFast, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param receiveFast 查询条件
     * @return 总行数
     */
    long count(ReceiveFast receiveFast);

    /**
     * 新增数据
     *
     * @param receiveFast 实例对象
     * @return 影响行数
     */
    int insert(ReceiveFast receiveFast);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReceiveFast> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ReceiveFast> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReceiveFast> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ReceiveFast> entities);

    /**
     * 修改数据
     *
     * @param receiveFast 实例对象
     * @return 影响行数
     */
    int update(ReceiveFast receiveFast);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(String userId);

}

