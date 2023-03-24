package cool.leeson.library.controller.receive;

import cool.leeson.library.entity.receive.ReceiveItem;
import cool.leeson.library.service.receive.ReceiveItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (ReceiveItem)表控制层
 *
 * @author Leeson0202
 * @since 2023-03-21 00:26:40
 */
@RestController
@RequestMapping("receiveItem")
public class ReceiveItemController {
    /**
     * 服务对象
     */
    @Resource
    private ReceiveItemService receiveItemService;

    /**
     * 分页查询
     *
     * @param receiveItem 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ReceiveItem>> queryByPage(ReceiveItem receiveItem, PageRequest pageRequest) {
        return ResponseEntity.ok(this.receiveItemService.queryByPage(receiveItem, pageRequest));
    }


    /**
     * 新增数据
     *
     * @param receiveItem 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ReceiveItem> add(ReceiveItem receiveItem) {
        return ResponseEntity.ok(this.receiveItemService.insert(receiveItem));
    }

    /**
     * 编辑数据
     *
     * @param receiveItem 实体
     * @return 编辑结果
     */
    @PutMapping
    public Map edit(ReceiveItem receiveItem) {
        return this.receiveItemService.update(receiveItem);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.receiveItemService.deleteById(id));
    }

}

