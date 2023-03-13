package cool.leeson.library.dao;

import cool.leeson.library.entity.library.School;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (School)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-03-10 21:55:16
 */
public interface SchoolDao {

    /**
     * 通过ID查询单条数据
     *
     * @param schoolId 主键
     * @return 实例对象
     */
    School queryById(String schoolId);

    /**
     * 查询指定行数据
     *
     * @param school   查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<School> queryAllByLimit(School school, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param school 查询条件
     * @return 总行数
     */
    long count(School school);

    /**
     * 新增数据
     *
     * @param school 实例对象
     * @return 影响行数
     */
    int insert(School school);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<School> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<School> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<School> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<School> entities);

    /**
     * 修改数据
     *
     * @param school 实例对象
     * @return 影响行数
     */
    int update(School school);

    /**
     * 通过主键删除数据
     *
     * @param schoolId 主键
     * @return 影响行数
     */
    int deleteById(String schoolId);

}

