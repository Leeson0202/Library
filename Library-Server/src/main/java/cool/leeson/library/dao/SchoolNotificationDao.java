package cool.leeson.library.dao;

import cool.leeson.library.entity.library.SchoolNotification;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (SchoolNotification)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-04-10 00:28:20
 */
public interface SchoolNotificationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param notificationId 主键
     * @return 实例对象
     */
    SchoolNotification queryById(String notificationId);

    /**
     * 查询指定行数据
     *
     * @param schoolNotification 查询条件
     * @param pageable           分页对象
     * @return 对象列表
     */
    List<SchoolNotification> queryAllByLimit(SchoolNotification schoolNotification, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param schoolNotification 查询条件
     * @return 总行数
     */
    long count(SchoolNotification schoolNotification);

    /**
     * 新增数据
     *
     * @param schoolNotification 实例对象
     * @return 影响行数
     */
    int insert(SchoolNotification schoolNotification);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SchoolNotification> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SchoolNotification> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SchoolNotification> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SchoolNotification> entities);

    /**
     * 修改数据
     *
     * @param schoolNotification 实例对象
     * @return 影响行数
     */
    int update(SchoolNotification schoolNotification);

    /**
     * 通过主键删除数据
     *
     * @param notificationId 主键
     * @return 影响行数
     */
    int deleteById(String notificationId);

}

