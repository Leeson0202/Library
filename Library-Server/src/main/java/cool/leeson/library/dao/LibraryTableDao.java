package cool.leeson.library.dao;

import cool.leeson.library.entity.library.LibraryTable;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (LibraryTable)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-11 02:27:34
 */
public interface LibraryTableDao {

    /**
     * 通过ID查询单条数据
     *
     * @param tableId 主键
     * @return 实例对象
     */
    LibraryTable queryById(String tableId);
    List<LibraryTable> queryByRoomId(String roomId);

    /**
     * 查询指定行数据
     *
     * @param libraryTable 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<LibraryTable> queryAllByLimit(LibraryTable libraryTable, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param libraryTable 查询条件
     * @return 总行数
     */
    long count(LibraryTable libraryTable);

    /**
     * 新增数据
     *
     * @param libraryTable 实例对象
     * @return 影响行数
     */
    int insert(LibraryTable libraryTable);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<LibraryTable> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<LibraryTable> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<LibraryTable> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<LibraryTable> entities);

    /**
     * 修改数据
     *
     * @param libraryTable 实例对象
     * @return 影响行数
     */
    int update(LibraryTable libraryTable);

    /**
     * 通过主键删除数据
     *
     * @param tableId 主键
     * @return 影响行数
     */
    int deleteById(String tableId);

}

