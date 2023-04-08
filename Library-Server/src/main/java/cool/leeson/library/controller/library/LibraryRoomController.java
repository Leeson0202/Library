package cool.leeson.library.controller.library;

import cool.leeson.library.entity.library.LibraryRoom;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.library.LibraryRoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * (Library)表控制层
 *
 * @author Leeson0202
 * @since 2023-03-10 21:55:47
 */
@RestController
@RequestMapping("room")
public class LibraryRoomController {
    @Resource
    private HttpServletRequest request;
    /**
     * 服务对象
     */
    @Resource
    private LibraryRoomService libraryRoomService;


    /**
     * 通过schoolId 获取Rooms
     *
     * @return 查询结果
     */
    @GetMapping("rooms")
    public Map<String, Object> queryByLibraryId(String libraryId) {
        return this.libraryRoomService.queryByLibraryId(libraryId);
    }

    /**
     * 通过roomId 获取room
     *
     * @return 查询结果
     */
    @GetMapping("id/{roomId}")
    public Map<String, Object> queryByRoomId(@PathVariable("roomId") String roomId) throws MyException {
        return this.libraryRoomService.queryById(roomId);
    }

    /**
     * 通过roomId 获取room
     *
     * @return 查询结果
     */
    @GetMapping("time")
    public Map<String, Object> queryByTime(String roomId, Boolean today, Integer idx) throws MyException {
        return this.libraryRoomService.queryByTime(roomId, today, idx);
    }

    /**
     * 新增数据
     *
     * @param libraryRoom 实体
     * @return 新增结果
     */
    @PostMapping
    public Map<String, Object> add(LibraryRoom libraryRoom) {
        return this.libraryRoomService.insert(libraryRoom);
    }

    /**
     * 编辑数据
     *
     * @param libraryRoom 实体
     * @return 编辑结果
     */
    @PutMapping
    public Map<String, Object> edit(LibraryRoom libraryRoom) throws MyException {
        return this.libraryRoomService.update(libraryRoom);
    }


    /**
     * 删除数据
     *
     * @param roomId 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Map<String, Object> deleteById(String roomId) {
        return this.libraryRoomService.deleteById(roomId);
    }

}

