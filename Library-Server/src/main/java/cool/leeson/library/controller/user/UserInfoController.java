package cool.leeson.library.controller.user;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.entity.user.UserInfo;
import cool.leeson.library.service.user.UserInfoService;
import cool.leeson.library.util.CheckObjectIsNullUtils;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 通过 token 获取用户信息
     *
     * @return 实体
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> query() {
        // 解析token获取userId
        String userId = new JwtConfig().getUsernameFromToken(request.getHeader("token"));
        log.info(userId + ": 获取userInfo");
        UserInfo userInfo = this.userInfoService.query(userId);
        userInfo.setSessionKey(""); // 去掉sessionKey
        return ResponseEntity.ok(ResMap.ok("userInfo", userInfo).build());
    }

    /**
     * 通过userId 获 userInfo
     *
     * @param userId 用户id
     * @return map
     */
    @GetMapping("id/{userId}")
    public Map queryByUserId(@PathVariable("userId") String userId) {
        UserInfo userInfo = this.userInfoService.query(userId);
        userInfo.setOpenid(null); // 去掉openid
        userInfo.setSessionKey(null); // 去掉sessionKey
        userInfo.setUserRecord(null); // 真实姓名
        userInfo.setStuId(null);
        return ResMap.ok("userInfo", userInfo).build();
    }

    /**
     * 更新用户信息
     *
     * @param userInfo userInfo
     * @return 实体
     */
    @PutMapping()
    public ResponseEntity<Map<String, Object>> update(UserInfo userInfo) {
        // 判断是否为空
        if (CheckObjectIsNullUtils.objCheckAllIsNull(userInfo)) {
            return ResponseEntity.ok(ResMap.err("属性为空"));
        }
        // 解析token获取userId
        String userId = new JwtConfig().getUsernameFromToken(request.getHeader("token"));
        userInfo.setUserId(userId);
        // 查询数据库
        UserInfo quserInfo = this.userInfoService.update(userInfo);

        quserInfo.setSessionKey("");
        return ResponseEntity.ok(ResMap.ok("userInfo", quserInfo).build());
    }

}
