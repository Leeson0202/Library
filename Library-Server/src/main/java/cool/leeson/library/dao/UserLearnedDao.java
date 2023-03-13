package cool.leeson.library.dao;

import cool.leeson.library.entity.user.UserLearned;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (UserLearned)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-03-07 22:21:28
 */
public interface UserLearnedDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserLearned queryById(String id);

    /**
     * 通过 userId 查询
     *
     * @param userId 用户id
     * @return 实体
     */
    UserLearned queryByUserId(String userId);

    /**
     * 查询指定行数据
     *
     * @param userLearned 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<UserLearned> queryAllByLimit(UserLearned userLearned, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param userLearned 查询条件
     * @return 总行数
     */
    long count(UserLearned userLearned);

    /**
     * 新增数据
     *
     * @param userLearned 实例对象
     * @return 影响行数
     */
    int insert(UserLearned userLearned);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserLearned> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserLearned> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserLearned> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserLearned> entities);

    /**
     * 修改数据
     *
     * @param userLearned 实例对象
     * @return 影响行数
     */
    int update(UserLearned userLearned);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

