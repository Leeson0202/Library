package cool.leeson.library.controller;

import cool.leeson.library.service.wx.WxServiceImpl;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("wx")
public class WXController {
    @Resource
    private WxServiceImpl wxService;

    /**
     * 微信登陆 注册过程
     *
     * @param code 微信code
     * @return 实体对象
     */
    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> Login(String code) {
        log.info("method in wx.Login(code)" + code);

        String token = wxService.getLoginCertificate(code);
        log.info("token: " + token);
        if (token == null)
            return ResponseEntity.ok(ResMap.err("服务器错误"));


        return ResponseEntity.ok(ResMap.ok("token", token).build());
    }

    @GetMapping("/login/access")
    public ResponseEntity<Map<String, Object>> accessToken(){
        return ResponseEntity.ok(this.wxService.getAccessToken());
    }

}
