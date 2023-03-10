package cool.leeson.library.service.library;

import cool.leeson.library.entity.library.Library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * (Library)表服务接口
 *
 * @author makejava
 * @since 2023-03-10 21:55:47
 */
public interface LibraryService {
    /**
     * 通过userId 查询图书馆
     *
     * @param userId 用户id
     * @return 实体
     */
    Map<String, Object> queryLibraryByUserId(String userId);

    /**
     * schoolId 查询图书馆
     *
     * @param schoolId 学校id
     * @return 实体
     */
    Map<String, Object> queryLibraryBySchoolId(String schoolId);

    /**
     * 获取room信息 libraryId
     * @param libraryId libraryId
     * @return 实体
     */
    Map<String, Object> queryRoomByLibraryId(String libraryId);


    /**
     * 通过ID查询单条数据
     *
     * @param libraryId 主键
     * @return 实例对象
     */
    Library queryById(String libraryId);

    /**
     * 分页查询
     *
     * @param library     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Library> queryByPage(Library library, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param library 实例对象
     * @return 实例对象
     */
    Library insert(Library library);

    /**
     * 修改数据
     *
     * @param library 实例对象
     * @return 实例对象
     */
    Library update(Library library);

    /**
     * 通过主键删除数据
     *
     * @param libraryId 主键
     * @return 是否成功
     */
    boolean deleteById(String libraryId);

    Map<String, Object> queryRoomByRoomId(String roomId);
}
