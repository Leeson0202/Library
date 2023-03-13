package cool.leeson.library.dao;

import cool.leeson.library.entity.library.SchoolRule;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (SchoolRule)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-03-11 20:50:35
 */
public interface SchoolRuleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param schoolId 主键
     * @return 实例对象
     */
    SchoolRule queryById(String schoolId);

    /**
     * 查询指定行数据
     *
     * @param schoolRule 查询条件
     * @param pageable   分页对象
     * @return 对象列表
     */
    List<SchoolRule> queryAllByLimit(SchoolRule schoolRule, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param schoolRule 查询条件
     * @return 总行数
     */
    long count(SchoolRule schoolRule);

    /**
     * 新增数据
     *
     * @param schoolRule 实例对象
     * @return 影响行数
     */
    int insert(SchoolRule schoolRule);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SchoolRule> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SchoolRule> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SchoolRule> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SchoolRule> entities);

    /**
     * 修改数据
     *
     * @param schoolRule 实例对象
     * @return 影响行数
     */
    int update(SchoolRule schoolRule);

    /**
     * 通过主键删除数据
     *
     * @param schoolId 主键
     * @return 影响行数
     */
    int deleteById(String schoolId);

}

