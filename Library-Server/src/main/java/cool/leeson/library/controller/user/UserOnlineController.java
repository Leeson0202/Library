package cool.leeson.library.controller.user;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.entity.user.UserOnline;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.user.UserOnlineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * (UserOnline)表控制层
 *
 * @author Leeson0202
 * @since 2023-03-24 01:05:34
 */
@RestController
@RequestMapping("online")
public class UserOnlineController {
    /**
     * 服务对象
     */
    @Resource
    private UserOnlineService userOnlineService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private JwtConfig jwtConfig;


    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping()
    public Map<String,Object> queryById() throws MyException {
        String userId = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        return this.userOnlineService.queryById(userId);
    }



}

