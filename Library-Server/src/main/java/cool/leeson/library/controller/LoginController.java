package cool.leeson.library.controller;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@RestController
@Slf4j
@Validated
public class LoginController {
    @Resource
    private LoginService loginService;
    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtConfig jwtConfig;

    @GetMapping("login/test")
    public Map<String, Object> test() throws MyException {
        return this.loginService.test("test");
    }

    @GetMapping("login/admin")
    public Map<String, Object> admin() throws MyException {
        return this.loginService.test("admin");
    }

    /**
     * 更新token
     *
     * @return 实体对象
     */
    @GetMapping("update")
    public Map<String, Object> update() throws MyException {
        String token = this.request.getHeader("token");
        return this.loginService.loginUpdate(token);
    }

    /**
     * 通过短信登陆，没有则注册（发送验证码）
     *
     * @param tel 电话号码
     * @return 实体对象
     */
    @GetMapping("login/tel")
    public ResponseEntity<Map<String, Object>> loginTel(@NotBlank(message = "手机号不能为空") String tel) {
        Map<String, Object> res = this.loginService.loginTel(tel);
        return ResponseEntity.ok(res);
    }

    /**
     * 电话验证
     *
     * @param code 验证码
     * @param tel  电话
     * @return 实体对象
     */
    @GetMapping("confirm/tel")
    public ResponseEntity<Map<String, Object>> confirmTel(
            @NotBlank(message = "验证码不能为空") String code,
            @NotBlank(message = "手机号不能为空") String tel) {
        return ResponseEntity.ok(this.loginService.confirmTel(code, tel));
    }

    /**
     * 通过email注册，没有则注册（发送验证码）
     *
     * @param email 电子邮件
     * @return 实体对象
     */
    @GetMapping("login/email")
    public ResponseEntity<Map<String, Object>> loginEmail(
            @Email(message = "邮箱错误") String email) {
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
    public ResponseEntity<Map<String, Object>> confirmEmail(@NotBlank(message = "验证码不能为空") String code,@Email(message = "邮箱错误")  String email) {
        Map<String, Object> resMap = this.loginService.confirmEmail(code, email);
        return ResponseEntity.ok(resMap);
    }

    @PostMapping("user/bind/cqupt")
    public ResponseEntity<Map<String, Object>> loginCqupt(String cqupt_id, String password) throws MyException {
        String userId = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        Map<String, Object> map = this.loginService.loginCqupt(cqupt_id, password, userId);
        return ResponseEntity.ok(map);
    }

}
