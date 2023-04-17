package cool.leeson.library.controller.receive;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.entity.receive.ReceiveFast;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.receive.ReceiveFastService;
import cool.leeson.library.util.ResMap;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * (ReceiveFast)表控制层
 *
 * @author Leeson0202
 * @since 2023-03-24 22:40:36
 */
@RestController
@RequestMapping("receive/fast")
public class ReceiveFastController {
    /**
     * 服务对象
     */
    @Resource
    private ReceiveFastService receiveFastService;
    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtConfig jwtConfig;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("id/{id}")
    public Map<String, Object> queryById(@PathVariable("id") String id) throws MyException {
        return this.receiveFastService.queryById(id);
    }

    /**
     * 通过用户token获取
     */
    @GetMapping
    public Map<String, Object> query() throws MyException {
        String userId = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        return this.receiveFastService.queryById(userId);
    }

    /**
     * 通过用户全部
     **/

    @GetMapping("all")
    public Object queryAll(ReceiveFast receiveFast, Integer page, Integer size) throws MyException {
        return this.receiveFastService.queryByPage(receiveFast, PageRequest.of(page, size));
    }


    /**
     * 新增数据
     *
     * @param receiveFast 实体
     * @return 新增结果
     */
    @PostMapping
    public Object add(ReceiveFast receiveFast) throws MyException {
        return ResMap.ok(this.receiveFastService.insert(receiveFast));
    }

    /**
     * 编辑数据
     *
     * @param receiveFast 实体
     * @return 编辑结果
     */
    @PutMapping
    public Map<String, Object> edit(ReceiveFast receiveFast) throws MyException {
        String userId = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        receiveFast.setUserId(userId);
        return this.receiveFastService.update(receiveFast);
    }


    /**
     * 关闭
     */
    @GetMapping("close")
    public Map<String, Object> close() throws MyException {
        String userId = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        return this.receiveFastService.change(userId, false);
    }

}

