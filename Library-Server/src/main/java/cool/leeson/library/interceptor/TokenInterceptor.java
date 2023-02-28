package cool.leeson.library.interceptor;

import cool.leeson.library.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private JwtConfig jwtConfig;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws SignatureException {
        // 地址过滤
        String uri = request.getRequestURI();
        if (uri.contains("/login")) {
            return true;
        }
        // Token 验证
        String token = request.getHeader(jwtConfig.getHeader());
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(jwtConfig.getHeader());
        }
        if (StringUtils.isEmpty(token)) {
            throw new SignatureException(jwtConfig.getHeader() + "不能为空");
        }

        Claims claims;
        try {
            claims = jwtConfig.getTokenClaim(token);
            if (claims == null || jwtConfig.isTokenExpired(claims.getExpiration())) {
                throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
            }
        } catch (Exception e) {
            throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
        }

        // 设置 identityId 用户身份ID
        request.setAttribute("userId", claims.getSubject());
        return true;
    }
}
