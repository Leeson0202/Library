package cool.leeson.library.dao;

import cool.leeson.library.entity.user.UserOnline;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (UserOnline)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-03-24 01:05:34
 */
public interface UserOnlineDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserOnline queryById(String userId);

    /**
     * 查询指定行数据
     *
     * @param userOnline 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<UserOnline> queryAllByLimit(UserOnline userOnline, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param userOnline 查询条件
     * @return 总行数
     */
    long count(UserOnline userOnline);

    /**
     * 新增数据
     *
     * @param userOnline 实例对象
     * @return 影响行数
     */
    int insert(UserOnline userOnline);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserOnline> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserOnline> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserOnline> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserOnline> entities);

    /**
     * 修改数据
     *
     * @param userOnline 实例对象
     * @return 影响行数
     */
    int update(UserOnline userOnline);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(String userId);

}

