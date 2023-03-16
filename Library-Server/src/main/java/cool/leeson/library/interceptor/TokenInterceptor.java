package cool.leeson.library.interceptor;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.exceptions.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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
    private RedisTemplate<String, String> redisTemplate;

    private final String UserTokenKeyFormat = "%s:token"; // userId:token  7天

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws MyException {
        // 地址过滤
        String uri = request.getRequestURI();
        // 登陆注册相关
        if (uri.contains("img") || uri.contains("library") || uri.contains("login") || uri.contains("common") || uri.contains("/confirm")) {
            return true;
        }
        if(uri.contains("error")){
            throw new MyException(400,"没有该请求");
        }
        // Token 验证
        String token = request.getHeader(jwtConfig.getHeader());
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(jwtConfig.getHeader());
        }
        if (StringUtils.isEmpty(token)) {
//            log.warn(uri + " token 不能为空");
            throw new MyException(MyException.STATUS.badToken);
        }

        try {
            // 查询缓冲
            String userId = jwtConfig.getUsernameFromToken(token);
            String rToken = this.redisTemplate.opsForValue().get(String.format(UserTokenKeyFormat, userId)); // redis中的token
            if (StringUtils.isEmpty(rToken) || !token.equals(rToken)) {
                throw new MyException(MyException.STATUS.badToken);
            }
            // 验证token是否合法
            if (jwtConfig.isTokenExpired(token)) {
                log.info("token 失效: " + token);
                return false;
            }
        } catch (Exception e) {
            throw new MyException(MyException.STATUS.badToken);
        }
        return true;
    }
}
