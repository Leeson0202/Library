package cool.leeson.library.controller.library;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.dao.LibraryRoomDao;
import cool.leeson.library.entity.library.Library;
import cool.leeson.library.entity.library.LibraryRoom;
import cool.leeson.library.service.library.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * (Library)表控制层
 *
 * @author makejava
 * @since 2023-03-10 21:55:47
 */
@RestController
@RequestMapping("library")
public class LibraryController {
    @Resource
    private HttpServletRequest request;
    /**
     * 服务对象
     */
    @Resource
    private LibraryService libraryService;


    /**
     * 用户直接 获取library
     *
     * @return 查询结果
     */
    @GetMapping
    public Map<String, Object> queryLibraryByUserId() {
        String token = request.getHeader("token");
        // 解析token获取userId
        String userId = new JwtConfig().getUsernameFromToken(token);
        return this.libraryService.queryLibraryByUserId(userId);
    }

    /**
     * 通过schoolId 获取libraries
     *
     * @return 查询结果
     */
    @GetMapping("school")
    public Map<String, Object> queryLibraryBySchoolId(String schoolId) {

        return this.libraryService.queryLibraryBySchoolId(schoolId);
    }

    /**
     * 通过schoolId 获取Rooms
     *
     * @return 查询结果
     */
    @GetMapping("rooms")
    public Map<String, Object> queryLibraryRoomByLibraryId(String libraryId) {
        return this.libraryService.queryRoomByLibraryId(libraryId);
    }

    /**
     * 通过schoolId 获取library
     *
     * @return 查询结果
     */
    @GetMapping("room/{roomId}")
    public Map<String, Object> queryLibraryRoomByRoomId(@PathVariable("roomId") String roomId) {
        return this.libraryService.queryRoomByRoomId(roomId);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Library> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.libraryService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param library 实体
     * @return 新增结果
     */
    @PostMapping("insert")
    public ResponseEntity<Library> add(Library library) {
        return ResponseEntity.ok(this.libraryService.insert(library));
    }

    /**
     * 编辑数据
     *
     * @param library 实体
     * @return 编辑结果
     */
    @PutMapping("update")
    public ResponseEntity<Library> edit(Library library) {
        return ResponseEntity.ok(this.libraryService.update(library));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.libraryService.deleteById(id));
    }

}

