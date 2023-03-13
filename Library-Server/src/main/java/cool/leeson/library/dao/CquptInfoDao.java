package cool.leeson.library.dao;

import cool.leeson.library.entity.user.CquptInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (CquptInfo)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-03-01 15:14:03
 */
public interface CquptInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    CquptInfo queryById(String userId);

    /**
     * 通过cqupt_id查询对象
     *
     * @param cqupt_id id
     * @return 实体对象
     */
    CquptInfo queryByCquptId(String cqupt_id);

    /**
     * 查询指定行数据
     *
     * @param cquptinfo 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<CquptInfo> queryAllByLimit(CquptInfo cquptinfo, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param cquptinfo 查询条件
     * @return 总行数
     */
    long count(CquptInfo cquptinfo);

    /**
     * 新增数据
     *
     * @param cquptinfo 实例对象
     * @return 影响行数
     */
    int insert(CquptInfo cquptinfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CquptInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CquptInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CquptInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CquptInfo> entities);

    /**
     * 修改数据
     *
     * @param cquptinfo 实例对象
     * @return 影响行数
     */
    int update(CquptInfo cquptinfo);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Integer userId);

}

