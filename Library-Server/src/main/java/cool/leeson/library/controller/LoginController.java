package cool.leeson.library.controller;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.service.LoginServiceImpl;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {
    @Resource
    private LoginServiceImpl loginService;
    @Resource
    private HttpServletRequest request;


    /**
     * 更新token
     *
     * @return 实体对象
     */
    @GetMapping("update")
    public ResponseEntity<Map<String, Object>> update() {
        String token = this.request.getHeader("token");
        token = new JwtConfig().createToken(new JwtConfig().getUsernameFromToken(token));
        return ResponseEntity.ok(ResMap.ok("token", token).build());
    }

    /**
     * 通过短信登陆，没有则注册（发送验证码）
     *
     * @param tel 电话号码
     * @return 实体对象
     */
    @GetMapping("login/tel")
    public ResponseEntity<Map<String, Object>> loginTel(String tel) {
        return ResponseEntity.ok(ResMap.ok(true));
    }

    /**
     * 电话验证
     *
     * @param code 验证码
     * @param tel  电话
     * @return 实体对象
     */
    @GetMapping("confirm/tel")
    public ResponseEntity<Map<String, Object>> confirmTel(String code, String tel) {
        return ResponseEntity.ok(null);
    }

    /**
     * 通过email注册，没有则注册（发送验证码）
     *
     * @param email 电子邮件
     * @return 实体对象
     */
    @GetMapping("login/email")
    public ResponseEntity<Map<String, Object>> loginEmail(String email) {
        Map<String, Object> res = this.loginService.loginEmail(email);
        return ResponseEntity.ok(res);
    }

    /**
     * 邮箱验证
     *
     * @param code  验证码
     * @param email 邮箱
     * @return 实体对象
     */

    @GetMapping("confirm/email")
    public ResponseEntity<Map<String, Object>> confirmEmail(String code, String email) {
        Map<String, Object> resMap = this.loginService.confirmEmail(code, email);
        return ResponseEntity.ok(resMap);
    }

    @PostMapping("login/cqupt")
    public ResponseEntity<Map<String, Object>> loginCqupt(String cqupt_id, String password) {
        Map<String, Object> map = this.loginService.loginCqupt(cqupt_id, password);
        return ResponseEntity.ok(map);
    }

}
