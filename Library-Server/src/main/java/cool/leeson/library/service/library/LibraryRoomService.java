package cool.leeson.library.service.library;

import cool.leeson.library.entity.library.LibraryRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * (LibraryRoom)表服务接口
 *
 * @author makejava
 * @since 2023-03-11 02:02:35
 */
public interface LibraryRoomService {

    /**
     * 通过ID查询单条数据
     *
     * @param roomId 主键
     * @return 实例对象
     */
    Map<String, Object> queryById(String roomId);

    Map<String, Object> queryByLibraryId(String libraryId);

    /**
     * 分页查询
     *
     * @param libraryRoom 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<LibraryRoom> queryByPage(LibraryRoom libraryRoom, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param libraryRoom 实例对象
     * @return 实例对象
     */
    LibraryRoom insert(LibraryRoom libraryRoom);

    /**
     * 修改数据
     *
     * @param libraryRoom 实例对象
     * @return 实例对象
     */
    Map<String, Object> update(LibraryRoom libraryRoom);

    /**
     * 通过主键删除数据
     *
     * @param roomId 主键
     * @return 是否成功
     */
    boolean deleteById(String roomId);

}
