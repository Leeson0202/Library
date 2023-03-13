package cool.leeson.library.dao;

import cool.leeson.library.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-02-26 15:42:15
 */
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    User queryById(String userId);

    /**
     * 通过openid查询单条数据
     * @param openId 微信id
     * @return 实例对象
     */
    User queryByOpenid(String openId);

    /**
     * 通过手机号查找
     * @param tel  手机号
     */
    User queryByTel(String tel);

    /**
     * 通过邮件查找
     * @param email 邮件
     * @return 用户信息
     */
    User queryByEmail(String email);

    /**
     * 查询指定行数据
     *
     * @param user     查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<User> queryAllByLimit(User user, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param user 查询条件
     * @return 总行数
     */
    long count(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<User> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<User> entities);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

}

