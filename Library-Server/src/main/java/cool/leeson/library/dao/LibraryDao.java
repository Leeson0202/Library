package cool.leeson.library.dao;

import cool.leeson.library.entity.library.Library;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (Library)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-10 21:55:47
 */
public interface LibraryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param libraryId 主键
     * @return 实例对象
     */
    Library queryById(String libraryId);

    /**
     * 通过schoolId查询单条数据
     *
     * @param schoolId 主键
     * @return 实例对象
     */
    List<Library> queryBySchoolId(String schoolId);

    /**
     * 查询指定行数据
     *
     * @param library  查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Library> queryAllByLimit(Library library, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param library 查询条件
     * @return 总行数
     */
    long count(Library library);

    /**
     * 新增数据
     *
     * @param library 实例对象
     * @return 影响行数
     */
    int insert(Library library);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Library> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Library> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Library> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Library> entities);

    /**
     * 修改数据
     *
     * @param library 实例对象
     * @return 影响行数
     */
    int update(Library library);

    /**
     * 通过主键删除数据
     *
     * @param libraryId 主键
     * @return 影响行数
     */
    int deleteById(String libraryId);

}

