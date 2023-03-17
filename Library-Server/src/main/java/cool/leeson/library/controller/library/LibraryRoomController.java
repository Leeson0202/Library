package cool.leeson.library.controller.library;

import cool.leeson.library.entity.library.LibraryRoom;
import cool.leeson.library.service.library.LibraryRoomService;
import org.springframework.http.ResponseEntity;
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
    public Map<String, Object> queryByRoomId(@PathVariable("roomId") String roomId, Boolean today, Integer idx) {
        return this.libraryRoomService.queryById(roomId, today, idx);
    }

    /**
     * 新增数据
     *
     * @param libraryRoom 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<LibraryRoom> add(LibraryRoom libraryRoom) {
        return ResponseEntity.ok(this.libraryRoomService.insert(libraryRoom));
    }

    /**
     * 编辑数据
     *
     * @param libraryRoom 实体
     * @return 编辑结果
     */
    @PutMapping
    public Map<String, Object> edit(LibraryRoom libraryRoom) {
        return this.libraryRoomService.update(libraryRoom);
    }


    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.libraryRoomService.deleteById(id));
    }

}

