package cool.leeson.library.controller.receive;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.entity.receive.ReceiveItemPost;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.receive.ReceiveItemService;
import cool.leeson.library.service.receive.ReceiveService;
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
    @Resource
    private ReceiveService receiveService;
    @Resource
    private ReceiveItemService receiveItemService;
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
    public Map<String, Object> receive(@RequestBody List<ReceiveItemPost> receives) throws MyException {
        return this.receiveService.receive(receives, (String) request.getAttribute("userId"));
    }

    /**
     * 取消预约
     *
     * @param receiveId 预约Id
     */
    @PutMapping("cancel")
    public Map<String, Object> cancel(String receiveId) throws MyException {

        return this.receiveService.cancel( (String) request.getAttribute("userId"), receiveId);
    }

    /**
     * 查询预约行程
     */
    @GetMapping("schedule")
    public Map<String, Object> schedule() throws MyException {
        String userId = jwtConfig.getUsernameFromToken(this.request.getHeader("token"));
        return this.receiveService.schedule(userId);
    }

    @GetMapping
    public Map<String, Object> queryAll() throws MyException {
        String userId = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        return this.receiveService.queryAll(userId);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public Map<String, Object> queryById(@PathVariable("id") String id) throws MyException {
        return this.receiveItemService.queryById(id);
    }


}

