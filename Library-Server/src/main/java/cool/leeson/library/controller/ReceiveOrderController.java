package cool.leeson.library.controller;

import cool.leeson.library.entity.receive.ReceiveOrder;
import cool.leeson.library.service.ReceiveOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ReceiveOrder)表控制层
 *
 * @author Leeson0202
 * @since 2023-03-17 16:43:00
 */
@RestController
@RequestMapping("receiveOrder")
public class ReceiveOrderController {
    /**
     * 服务对象
     */
    @Resource
    private ReceiveOrderService receiveOrderService;

    /**
     * 分页查询
     *
     * @param receiveOrder 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<ReceiveOrder>> queryByPage(ReceiveOrder receiveOrder, PageRequest pageRequest) {
        return ResponseEntity.ok(this.receiveOrderService.queryByPage(receiveOrder, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ReceiveOrder> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.receiveOrderService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param receiveOrder 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ReceiveOrder> add(ReceiveOrder receiveOrder) {
        return ResponseEntity.ok(this.receiveOrderService.insert(receiveOrder));
    }

    /**
     * 编辑数据
     *
     * @param receiveOrder 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ReceiveOrder> edit(ReceiveOrder receiveOrder) {
        return ResponseEntity.ok(this.receiveOrderService.update(receiveOrder));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.receiveOrderService.deleteById(id));
    }

}

