package cool.leeson.lib.controller;

import cool.leeson.lib.service.LoginServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {
    @Resource
    private LoginServiceImpl loginService;

    @RequestMapping("login/{id}")
    public String login(@PathVariable("id") String id) {
        return loginService.login(id);
    }


}
