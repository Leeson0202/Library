package cool.leeson.library.controller.clock;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.clock.ClockService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("clock")
public class ClockController {
    @Resource
    private ClockService clockService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private JwtConfig jwtConfig;

    /**
     * 用户入座请求
     *
     * @param seed 种子
     * @param tag  是否为暂时离座 0表示入座或出座 1 表示暂时
     * @return 实体
     * @throws MyException 1
     */
    @PutMapping("user")
    public Map user(String seed, Integer tag) throws MyException {
        String userId = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        return clockService.user(userId, seed, tag);
    }

    @PutMapping("equipment")
    public Map equipment(String equipment, String seed) throws MyException {
        return clockService.equipment(equipment, seed);
    }


}
