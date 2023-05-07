package cool.leeson.library.controller;

import cool.leeson.library.util.code.CodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@RestController
@Slf4j
@RequestMapping("common")
public class CommonController {

    @GetMapping
    public String test() {
        return "hello";
    }

    @GetMapping("codeImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws Exception {
        System.out.println("正在生成验证码");
        //生产验证码 6位
        String securityCode = CodeUtil.createCode();
        BufferedImage image = CodeUtil.createImage(securityCode);
        session.setAttribute("sessionCode", securityCode);  // 存入session作用域
        // 响应图片
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
}
