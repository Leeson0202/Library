package cool.leeson.library.controller;

import cool.leeson.library.service.wx.WxServiceImpl;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("wx")
@Slf4j
public class WXController {
    @Resource
    private WxServiceImpl wxService;

    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> Login(String code) {
        log.info("method in wx.Login(code)" + code);

        String token = wxService.getLoginCertificate(code);
        log.info("token: " + token);
        if (token == null)
            return ResponseEntity.ok(ResMap.err("服务器错误"));


        return ResponseEntity.ok(ResMap.ok("token", token).build());
    }
}
