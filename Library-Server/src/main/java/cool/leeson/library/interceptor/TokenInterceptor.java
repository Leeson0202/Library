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
    static final String UNKNOWN = "unknown";
    @Resource
    private HttpServletRequest request;

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
        if (uri.contains("error")) {
            throw new MyException(400, "没有该请求");
        }
        String ipAddress = this.getIpAddress();
        log.info(ipAddress + "正在请求：" + uri);

        // 登陆注册相关
        if (uri.contains("img") || uri.contains("library") || uri.contains("login") || uri.contains("common") || uri.contains("/confirm")) {
            return true;
        }
        // Token 验证
        String token = request.getHeader(jwtConfig.getHeader());
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(jwtConfig.getHeader());
        }
        if (StringUtils.isEmpty(token)) {
//            log.warn(uri + " token 不能为空");
            throw new MyException(MyException.STATUS.noToken);
        }

        try {
            // 查询缓冲
            String userId = jwtConfig.getUsernameFromToken(token);
            String rToken = this.redisTemplate.opsForValue().get(String.format(UserTokenKeyFormat, userId)); // redis中的token
            if (StringUtils.isEmpty(rToken) || !token.equals(rToken) || jwtConfig.isTokenExpired(token)) {
                throw new MyException(MyException.STATUS.badToken);
            }
        } catch (Exception e) {
            throw new MyException(MyException.STATUS.badToken);
        }
        return true;
    }

    public String getIpAddress() {
        if (request == null) {
            return UNKNOWN;
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
        ip = String.format("%-16s", ip);
        return ip;
    }
}
