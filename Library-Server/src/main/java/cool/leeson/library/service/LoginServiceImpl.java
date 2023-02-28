package cool.leeson.library.service;

import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.dao.UserDao;
import cool.leeson.library.dao.UserInfoDao;
import cool.leeson.library.entity.User;
import cool.leeson.library.entity.UserInfo;
import cool.leeson.library.service.wx.WxServiceImpl;
import cool.leeson.library.util.ResMap;
import cool.leeson.library.util.code.CodeUtil;
import cool.leeson.library.util.email.EmailUtil;
import cool.leeson.library.util.email.LibraryEmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginServiceImpl {
    @Resource
    private WxServiceImpl wxService;
    @Resource
    private UserDao userDao;
    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 通过短信注册
     *
     * @param tel 电话号码
     * @return 实体对象
     */
    public Boolean loginTel(String tel) {
        return true;
    }

    /**
     * 通过email注册，发送电子邮件
     *
     * @param email 电子邮件
     * @return 实体对象
     */
    public Map<String, Object> loginEmail(String email) {
        if (!EmailUtil.checkEmail(email)) {
            return ResMap.err("请输入正确的邮箱");
        }
        // 生成验证码
        String codeKey = "loginEmail:" + email;
        String code = CodeUtil.createCode();
        // 发送验证码
        Boolean sent = new LibraryEmailUtil().send(email, code, LibraryEmailUtil.Opt.Register);
        if (sent) {
            // 验证码进入缓冲 5分钟
            this.redisTemplate.opsForValue().set(codeKey, code, 5, TimeUnit.MINUTES);
            return ResMap.ok("邮件发送成功");
        }

        return ResMap.err("发送失败");
    }

    /**
     * 邮箱验证
     *
     * @param code  验证码
     * @param email 邮箱
     * @return 实体
     */
    public Map<String, Object> confirmEmail(String code, String email) {
        // 检测缓冲是否有code
        if (!EmailUtil.checkEmail(email)) {
            return ResMap.ok("请输入正确的邮箱");
        }
        log.info("email: " + email + "; code: " + code);
        String codeKey = "loginEmail:" + email;
        String rCode = redisTemplate.opsForValue().get(codeKey);
        if (rCode == null || !rCode.equals(code)) {
            log.info(email + "验证码错误 code：" + code + " rcode：" + rCode);
            return ResMap.err("验证码错误");
        }
        log.info(email + " 验证码正确");

        // 查询是否有该用户
        User quser = this.userDao.queryByEmail(email);
        if (quser == null) {
            // 开始注册
            log.info(email + "开始注册");
            User user = new User();
            user.setEmail(email);
            if (userDao.insert(user) < 1) {
                return ResMap.err("User数据库插入失败");
            }
            log.info(email + "注册，User插入成功");
            quser = userDao.queryByEmail(email);
            Integer userId = quser.getUserId();
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            if (userInfoDao.insert(userInfo) < 1) {
                return ResMap.err("UserInfo数据库插入失败");
            }
            log.info(email + "注册，UserInfo插入成功");
        }
        log.info(email + "开始生成token");
        Integer userId = quser.getUserId();
        // 注册成功后返回token
        return ResMap.ok(
                "token",
                new JwtConfig().createToken(String.valueOf(userId))).build();

    }

    /**
     * 微信登陆 注册过程
     *
     * @param code 微信code
     * @return token
     */
    public Map<String, Object> loginWx(String code) {
        log.info("code:" + code);

        String token = wxService.getLoginCertificate(code);
        log.info("token: " + token);
        if (token == null)
            return ResMap.err("服务器错误");


        return ResMap.ok("token", token).build();


    }


}
