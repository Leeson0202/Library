package cool.leeson.library.controller;

import cool.leeson.library.entity.WxUserInfo;
import cool.leeson.library.service.wx.WxServiceImpl;
import cool.leeson.library.util.ResMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class WXController {
    @Resource
    private WxServiceImpl wxService;

    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> Login(String code) {
        System.out.println(code);
        WxUserInfo loginCertificate = wxService.getLoginCertificate(code);
        System.out.println(loginCertificate);


        return ResponseEntity.ok(ResMap.ok(loginCertificate.getOpenId()));
    }
}
