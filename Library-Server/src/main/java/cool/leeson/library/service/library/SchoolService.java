package cool.leeson.library.service.library;

import cool.leeson.library.entity.library.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * (School)表服务接口
 *
 * @author makejava
 * @since 2023-03-10 21:55:16
 */
public interface SchoolService {

    /**
     * 通过ID查询单条数据
     *
     * @param schoolId 主键
     * @return 实例对象
     */
    Map<String, Object> queryById(String schoolId);

    /**
     * 分页查询
     *
     * @param school      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<School> queryByPage(School school, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param school 实例对象
     * @return 实例对象
     */
    School insert(School school);

    /**
     * 修改数据
     *
     * @param school 实例对象
     * @return 实例对象
     */
    Map<String, Object> update(School school);

    /**
     * 通过主键删除数据
     *
     * @param schoolId 主键
     * @return 是否成功
     */
    boolean deleteById(String schoolId);

    Map<String, Object> queryByUserId(String userId);
}
