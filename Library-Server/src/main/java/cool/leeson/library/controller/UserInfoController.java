package cool.leeson.library.controller;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.entity.UserInfo;
import cool.leeson.library.service.user.UserInfoService;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
        log.info("token: " + token);
        // 解析token获取userId
        String userId = new JwtConfig().getUsernameFromToken(token);
        UserInfo userInfo = this.userInfoService.queryById(Integer.valueOf(userId));
        userInfo.setSessionKey("");
        return ResponseEntity.ok(ResMap.ok("userInfo", userInfo).build());
    }
}
