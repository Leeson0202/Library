package cool.leeson.library.controller.user;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.entity.user.UserInfo;
import cool.leeson.library.service.user.UserInfoService;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("userInfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private HttpServletRequest request;

    @RequestMapping
    public ResponseEntity<Map<String, Object>> query() {
        // 获取token
        String token = request.getHeader("token");
        // 解析token获取userId
        String userId = new JwtConfig().getUsernameFromToken(token);
        log.info(userId+" 获取userInfo");
        UserInfo userInfo = this.userInfoService.query(userId);
        userInfo.setSessionKey(""); // 去掉sessionKey
        return ResponseEntity.ok(ResMap.ok("userInfo", userInfo).build());
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> update(UserInfo userInfo) {
        // 获取token
        String token = request.getHeader("token");
        // 解析token获取userId
        String userId = new JwtConfig().getUsernameFromToken(token);
        userInfo.setUserId(userId);
        // 查询数据库
        UserInfo quserInfo = this.userInfoService.update(userInfo);

        quserInfo.setSessionKey("");
        return ResponseEntity.ok(ResMap.ok("userInfo", quserInfo).build());
    }
}
