package cool.leeson.library.config;

import cool.leeson.library.exceptions.MyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT的token，区分大小写
 */

@Component
public class JwtConfig {

    private String secret = "cool.Leeson";
    private long expire = 7 * 24 * 60 * 60; // 7天
    private String header = "token";

    /**
     * 生成token
     *
     * @param subject 传入的数据参数
     * @return 返回对象
     */
    public String createToken(String subject) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);//过期时间

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 获取token中注册信息
     *
     * @param token 传入token
     * @return 返回claims
     */
    public Claims getTokenClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
            /*  catch (ExpiredJwtException e){
                    return e.getClaims(); //防止jwt过期解析报错
                }
            */
        }
    }

    /**
     * 验证token是否过期失效
     *
     * @param expirationTime 验证时间
     * @return 是否有效
     */
    public boolean isTokenExpired(Date expirationTime) {
        return expirationTime.before(new Date());
    }

    public boolean isTokenExpired(String token) {
        Date expirationTime = this.getExpirationDateFromToken(token);
        return expirationTime.before(new Date());
    }

    /**
     * 获取token失效时间
     *
     * @param token token
     * @return 实体对象
     */
    public Date getExpirationDateFromToken(String token) {
        return getTokenClaim(token).getExpiration();
    }

    /**
     * 获取用户名从token中
     */
    public String getUsernameFromToken(String token) throws MyException {
        try {
            return getTokenClaim(token).getSubject();
        } catch (NullPointerException p) {
            throw new MyException(MyException.STATUS.badToken);
        }
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getTokenClaim(token).getIssuedAt();
    }

    // --------------------- getter & setter ---------------------

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
