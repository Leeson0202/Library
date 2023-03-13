package cool.leeson.library.dao;

import cool.leeson.library.entity.library.LibrarySeat;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (LibrarySeat)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-03-11 02:24:17
 */
public interface LibrarySeatDao {

    /**
     * 通过ID查询单条数据
     *
     * @param seatId 主键
     * @return 实例对象
     */
    LibrarySeat queryById(String seatId);
    /**
     * 通过ID查询单条数据
     *
     * @param roomId 主键
     * @return 实例对象
     */
    List<LibrarySeat> queryByRoomId(String roomId);

    /**
     * 查询指定行数据
     *
     * @param librarySeat 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<LibrarySeat> queryAllByLimit(LibrarySeat librarySeat, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param librarySeat 查询条件
     * @return 总行数
     */
    long count(LibrarySeat librarySeat);

    /**
     * 新增数据
     *
     * @param librarySeat 实例对象
     * @return 影响行数
     */
    int insert(LibrarySeat librarySeat);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<LibrarySeat> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<LibrarySeat> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<LibrarySeat> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<LibrarySeat> entities);

    /**
     * 修改数据
     *
     * @param librarySeat 实例对象
     * @return 影响行数
     */
    int update(LibrarySeat librarySeat);

    /**
     * 通过主键删除数据
     *
     * @param seatId 主键
     * @return 影响行数
     */
    int deleteById(String seatId);

}

