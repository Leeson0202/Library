package cool.leeson.library.dao;

import cool.leeson.library.entity.library.LibraryFeedback;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (LibraryFeedback)表数据库访问层
 *
 * @author Leeson0202
 * @since 2023-04-12 16:35:47
 */
public interface LibraryFeedbackDao {

    /**
     * 通过ID查询单条数据
     *
     * @param feedbackId 主键
     * @return 实例对象
     */
    LibraryFeedback queryById(String feedbackId);

    /**
     * 查询指定行数据
     *
     * @param libraryFeedback 查询条件
     * @param pageable        分页对象
     * @return 对象列表
     */
    List<LibraryFeedback> queryAllByLimit(LibraryFeedback libraryFeedback, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param libraryFeedback 查询条件
     * @return 总行数
     */
    long count(LibraryFeedback libraryFeedback);

    /**
     * 新增数据
     *
     * @param libraryFeedback 实例对象
     * @return 影响行数
     */
    int insert(LibraryFeedback libraryFeedback);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<LibraryFeedback> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<LibraryFeedback> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<LibraryFeedback> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<LibraryFeedback> entities);

    /**
     * 修改数据
     *
     * @param libraryFeedback 实例对象
     * @return 影响行数
     */
    int update(LibraryFeedback libraryFeedback);

    /**
     * 通过主键删除数据
     *
     * @param feedbackId 主键
     * @return 影响行数
     */
    int deleteById(String feedbackId);

}

