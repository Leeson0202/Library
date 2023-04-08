package cool.leeson.library.controller.library;

import cool.leeson.library.entity.library.LibraryTable;
import cool.leeson.library.service.library.LibraryTableService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (LibraryTable)表控制层
 *
 * @author Leeson0202
 * @since 2023-04-07 00:01:35
 */
@RestController
@RequestMapping("table")
public class LibraryTableController {
    /**
     * 服务对象
     */
    @Resource
    private LibraryTableService libraryTableService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public Map<String, Object> queryById(@PathVariable("id") String id) {
        return this.libraryTableService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param libraryTable 实体
     * @return 新增结果
     */
    @PostMapping
    public Map<String, Object> add(LibraryTable libraryTable) {
        return this.libraryTableService.insert(libraryTable);
    }

    /**
     * 编辑数据
     *
     * @param libraryTable 实体
     * @return 编辑结果
     */
    @PutMapping
    public Map<String, Object> edit(LibraryTable libraryTable) {
        return this.libraryTableService.update(libraryTable);
    }

    /**
     * 删除数据
     *
     * @param tableId 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Map<String, Object> deleteById(String tableId) {
        return this.libraryTableService.deleteById(tableId);
    }

}

