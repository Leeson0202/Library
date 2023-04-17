package cool.leeson.library.interceptor;

import com.alibaba.druid.util.StringUtils;
import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.entity.tools.RedisTool;
import cool.leeson.library.exceptions.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class TokenInterceptor extends HandlerInterceptorAdapter {


    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private RedisTool redisTool;


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 地址过滤
        String uri = request.getRequestURI();
        if (uri.contains("error")) {
            throw new MyException(400, "没有该请求 ");
        }
        // 哪些不需要token认证
        String[] dismiss = new String[]{
                "/login", "/confirm", "/update", "/img", "/css", "/js", "/download"};
        boolean f = false;
        for (String string : dismiss) {
            if (uri.contains(string)) {
                f = true;
                break;
            }
        }
        // 登陆注册相关
        if (f) return true;
        // Token 验证
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            throw new MyException(MyException.STATUS.noToken);

        }
        try {
            // 查询缓冲
            String userId = jwtConfig.getUsernameFromToken(token);
            String tokenKey = String.format(RedisTool.FormatKey.TOKEN.toString(), userId);
            String rToken = this.redisTool.get(tokenKey); // redis中的token
            if (StringUtils.isEmpty(rToken) || !token.equals(rToken) || jwtConfig.isTokenExpired(token)) {
                throw new MyException(MyException.STATUS.badToken);
            }
            // userId 写进去
            if (StringUtils.isEmpty((String) request.getAttribute("userId"))) {
                request.setAttribute("userId", userId);
            }
        } catch (Exception e) {
            throw new MyException(MyException.STATUS.badToken);
        }
        return true;
    }


}
