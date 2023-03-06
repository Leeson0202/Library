package cool.leeson.library.dao;

import cool.leeson.library.entity.UserCredit;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (UserCredit)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-06 19:48:26
 */
public interface UserCreditDao {

    /**
     * 通过ID查询单条数据
     *
     * @param 主键
     * @return 实例对象
     */
    UserCredit queryById();

    /**
     * 查询指定行数据
     *
     * @param userCredit 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<UserCredit> queryAllByLimit(UserCredit userCredit, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param userCredit 查询条件
     * @return 总行数
     */
    long count(UserCredit userCredit);

    /**
     * 新增数据
     *
     * @param userCredit 实例对象
     * @return 影响行数
     */
    int insert(UserCredit userCredit);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserCredit> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserCredit> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserCredit> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserCredit> entities);

    /**
     * 修改数据
     *
     * @param userCredit 实例对象
     * @return 影响行数
     */
    int update(UserCredit userCredit);

    /**
     * 通过主键删除数据
     *
     * @param 主键
     * @return 影响行数
     */
    int deleteById();

}

