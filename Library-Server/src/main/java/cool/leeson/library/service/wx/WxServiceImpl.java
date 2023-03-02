package cool.leeson.library.service.wx;

import com.alibaba.fastjson.JSONObject;
import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.dao.UserDao;
import cool.leeson.library.dao.UserInfoDao;
import cool.leeson.library.entity.User;
import cool.leeson.library.entity.UserInfo;
import cool.leeson.library.entity.wx.AccessToken;
import cool.leeson.library.util.HttpClientUtil;
import cool.leeson.library.util.ResMap;
import cool.leeson.library.util.WxUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WxServiceImpl {
    @Resource
    private UserDao userDao;
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public String getLoginCertificate(String code) {
        Integer userId;
        // 请求地址
        String requestUrl = WxUtil.getWxLoginUrl(code);
        String response;
        // 发送请求
        try {
            response = HttpClientUtil.getRequest(requestUrl);
        } catch (Exception e) {
            System.out.println("login 请求失败");
            return null;
        }
        //格式化JSON数据
        UserInfo userInfo = JSONObject.parseObject(response, UserInfo.class);
        // 判断微信服务器返回的结果
        if (userInfo == null) {
            return null;
        }
        // 检查数据库中是否存在 openid
        UserInfo qUserInfo = this.userInfoDao.queryByOpenid(userInfo.getOpenid());
        if (qUserInfo == null) {
            User newuser = new User();
            newuser.setOpenId(userInfo.getOpenid()); // 填入openid
            // 没有openid记录，进行注册
            if (this.userDao.insert(newuser) == 0) {
                // 注册失败
                return null;
            }
            // User表注册成功后，查询得到userId
            userId = userDao.queryByOpenid(userInfo.getOpenid()).getUserId();
            userInfo.setUserId(userId); // 赋值
            // 注册 userInfo
            if (this.userInfoDao.insert(userInfo) == 0) {
                return null;
            }

        } else {
            // 已经有这个用户了
            // 判断sessionKey是否相同
            if (!qUserInfo.getSessionKey().equals(userInfo.getSessionKey())) {
                qUserInfo.setSessionKey(userInfo.getSessionKey());
                this.userInfoDao.update(qUserInfo);
            }
            userId = qUserInfo.getUserId();
        }
        if (userId == null) return null;
        //返回 token
        return new JwtConfig().createToken(String.valueOf(userId));
    }


    public Map<String, Object> getAccessToken() {
        // 判断是否过期
        String accessToken = redisTemplate.opsForValue().get("access_token");
        if (!StringUtils.isEmpty(accessToken)) {
            Long last = redisTemplate.getExpire("access_token");
            AccessToken accessTokenObj = new AccessToken(accessToken, last);

            return ResMap.ok(accessTokenObj);
        }

        // 获取地址
        String wxTokenUrl = WxUtil.getWxTokenUrl();
        String response;
        // 发送请求
        try {
            response = HttpClientUtil.getRequest(wxTokenUrl);
            System.out.println(response);
        } catch (Exception e) {
            log.info("服务器获取session_token失败");
            return ResMap.err("服务器获取session_token失败");
        }
        //格式化JSON数据
        AccessToken accessTokenObj = JSONObject.parseObject(response, AccessToken.class);
        if (accessTokenObj == null) {
            return ResMap.err("服务器获取session_token失败");
        }
        redisTemplate.opsForValue().set("access_token", accessTokenObj.getAccess_token(), accessTokenObj.getExpires_in(), TimeUnit.SECONDS);
        log.info("获取的access_token: " + accessTokenObj);
        return ResMap.ok(accessTokenObj);
    }

}
