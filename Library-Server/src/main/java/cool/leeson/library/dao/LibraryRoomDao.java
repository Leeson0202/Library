package cool.leeson.library.dao;

import cool.leeson.library.entity.library.LibraryRoom;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (LibraryRoom)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-03-11 02:02:35
 */
public interface LibraryRoomDao {

    /**
     * 通过ID查询单条数据
     *
     * @param roomId 主键
     * @return 实例对象
     */
    LibraryRoom queryById(String roomId);
    /**
     * 通过ID查询单条数据
     *
     * @param libraryId 主键
     * @return 实例对象
     */
    List<LibraryRoom> queryByLibraryId(String libraryId);

    /**
     * 查询指定行数据
     *
     * @param libraryRoom 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<LibraryRoom> queryAllByLimit(LibraryRoom libraryRoom, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param libraryRoom 查询条件
     * @return 总行数
     */
    long count(LibraryRoom libraryRoom);

    /**
     * 新增数据
     *
     * @param libraryRoom 实例对象
     * @return 影响行数
     */
    int insert(LibraryRoom libraryRoom);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<LibraryRoom> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<LibraryRoom> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<LibraryRoom> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<LibraryRoom> entities);

    /**
     * 修改数据
     *
     * @param libraryRoom 实例对象
     * @return 影响行数
     */
    int update(LibraryRoom libraryRoom);

    /**
     * 通过主键删除数据
     *
     * @param roomId 主键
     * @return 影响行数
     */
    int deleteById(String roomId);

}

