package cool.leeson.library.dao;

import cool.leeson.library.entity.user.UserRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (UserRecord)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-07 22:17:03
 */
public interface UserRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserRecord queryByUserId(String userId);

    /**
     * 查询指定行数据
     *
     * @param userRecord 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<UserRecord> queryAllByLimit(UserRecord userRecord, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param userRecord 查询条件
     * @return 总行数
     */
    long count(UserRecord userRecord);

    /**
     * 新增数据
     *
     * @param userRecord 实例对象
     * @return 影响行数
     */
    int insert(UserRecord userRecord);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserRecord> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserRecord> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserRecord> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserRecord> entities);

    /**
     * 修改数据
     *
     * @param userRecord 实例对象
     * @return 影响行数
     */
    int update(UserRecord userRecord);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(String userId);

}

