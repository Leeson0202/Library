package cool.leeson.library.dao;

import cool.leeson.library.entity.user.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (UserInfo)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-03-01 15:13:05
 */
public interface UserInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserInfo queryById(String userId);

    /**
     * 通过openid查询单条
     *
     * @param openid 微信id
     * @return 实体对象
     */
    UserInfo queryByOpenid(String openid);

    /**
     * 查询指定行数据
     *
     * @param userInfo 查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<UserInfo> queryAllByLimit(UserInfo userInfo, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param userInfo 查询条件
     * @return 总行数
     */
    long count(UserInfo userInfo);

    /**
     * 新增数据
     *
     * @param userInfo 实例对象
     * @return 影响行数
     */
    int insert(UserInfo userInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserInfo> entities);

    /**
     * 修改数据
     *
     * @param userInfo 实例对象
     * @return 影响行数
     */
    int update(UserInfo userInfo);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

}

