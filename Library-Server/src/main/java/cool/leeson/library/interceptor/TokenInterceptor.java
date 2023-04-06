package cool.leeson.library.interceptor;

import com.alibaba.druid.util.StringUtils;
import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.exceptions.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
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
                             Object handler) throws Exception {

        // 地址过滤
        String uri = request.getRequestURI();
        if (uri.contains("error")) {
            throw new MyException(400, "没有该请求 ");
        }

        String ipAddress = this.getIpAddress();
        log.info(ipAddress + "正在请求：" + request.getMethod() + " " + uri);
        // 哪些不需要token认证
        String[] dismiss = new String[]{
                "/login", "/confirm", "/update"};
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
            String rToken = this.redisTemplate.opsForValue().get(String.format(UserTokenKeyFormat, userId)); // redis中的token
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
