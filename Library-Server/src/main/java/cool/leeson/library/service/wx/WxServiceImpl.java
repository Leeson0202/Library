package cool.leeson.library.service.wx;

import com.alibaba.fastjson.JSONObject;
import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.dao.UserDao;
import cool.leeson.library.dao.UserInfoDao;
import cool.leeson.library.entity.User;
import cool.leeson.library.entity.UserInfo;
import cool.leeson.library.util.HttpClientUtil;
import cool.leeson.library.util.WxUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WxServiceImpl {
    @Resource
    private UserDao userDao;
    @Resource
    private UserInfoDao userInfoDao;

    public String getLoginCertificate(String code) {
        Integer userId;
        //请求地址
        String requestUrl = WxUtil.getWxServerUrl(code);
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

}
