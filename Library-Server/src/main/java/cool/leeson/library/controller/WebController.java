package cool.leeson.library.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class WebController {

    @GetMapping
    public void index(HttpServletResponse response) throws Exception {
        response.sendRedirect("https://library.leeson.cool");
    }

}
