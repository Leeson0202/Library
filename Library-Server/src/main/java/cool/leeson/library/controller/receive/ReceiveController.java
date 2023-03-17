package cool.leeson.library.controller.receive;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.entity.receive.ReceiveOrder;
import cool.leeson.library.entity.receive.ReceivePost;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.receive.ReceiveOrderService;
import cool.leeson.library.service.receive.ReceiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * (ReceiveOrder)表控制层
 *
 * @author Leeson0202
 * @since 2023-03-17 16:43:00
 */
@RestController
@RequestMapping("receive")
public class ReceiveController {
    /**
     * 服务对象
     */
    @Resource
    private ReceiveOrderService receiveOrderService;
    @Resource
    private ReceiveService receiveService;
    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtConfig jwtConfig;


    /**
     * 一般预约
     *
     * @param receives 预约数据
     * @return 实体
     */
    @PostMapping
    public Map<String, Object> order(@RequestBody List<ReceivePost> receives) throws MyException {
        return this.receiveService.order(receives);
    }

    /**
     * 查询预约列表
     *
     * @return 实体
     */
    @GetMapping("list")
    public Map<String, Object> query(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) throws MyException {
        String userId = jwtConfig.getUsernameFromToken(this.request.getHeader("token"));
        return this.receiveService.query(userId);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public Map<String, Object> queryById(@PathVariable("id") String id) throws MyException {
        return this.receiveService.queryById(id);
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

