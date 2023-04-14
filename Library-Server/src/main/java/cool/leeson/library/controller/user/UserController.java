package cool.leeson.library.controller.user;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.entity.user.User;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.user.UserService;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author Leeson0202
 * @since 2023-02-26 15:42:15
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Resource
    private HttpServletRequest request;


    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("id/{userId}")
    public Map<String, Object> queryById(@PathVariable("userId") String userId) {
        return this.userService.queryById(userId);
    }

    /**
     * 通过token获取
     *
     * @return 实体
     */
    @GetMapping()
    public Map<String, Object> query() throws MyException {
        // 解析token获取userId
        String userId = new JwtConfig().getUsernameFromToken(request.getHeader("token"));
        log.info(userId + " 获取userInfo");
        return this.userService.queryById(userId);
    }

    @GetMapping("all")
    public Object queryAll(String schoolId) throws MyException {
        return this.userService.queryAll(schoolId);

    }


    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping
    public Map edit(User user) throws MyException {
        return this.userService.update(user);
    }

    @PostMapping
    public Object add(@RequestBody User user) throws MyException {
        return this.userService.insert(user);
    }

    /**
     * 删除数据
     *
     * @param userId 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Object deleteById(String userId) {
        return ResMap.ok(this.userService.deleteById(userId));
    }


    /**
     * 通过token获取 学校身份信息
     *
     * @return 实体
     */
    @GetMapping("studentInfo")
    public Map<String, Object> queryCquptInfoByToken() throws MyException {
        String userId = new JwtConfig().getUsernameFromToken(request.getHeader("token"));
        return this.userService.queryCquptInfoByToken(userId);
    }

}

