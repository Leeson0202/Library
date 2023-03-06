package cool.leeson.library.service;

import com.alibaba.fastjson.JSONObject;
import cool.leeson.library.config.JwtConfig;
import cool.leeson.library.dao.*;
import cool.leeson.library.entity.*;
import cool.leeson.library.util.HttpClientUtil;
import cool.leeson.library.util.ResMap;
import cool.leeson.library.util.code.CodeUtil;
import cool.leeson.library.util.email.EmailUtil;
import cool.leeson.library.util.email.LibraryEmailUtil;
import cool.leeson.library.util.msm.MsmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginServiceImpl {
    @Resource
    private UserDao userDao;
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private CquptInfoDao cquptInfoDao;
    @Resource
    private UserRecordDao userRecordDao;
    @Resource
    private UserCreditDao userCreditDao;
    @Resource
    private UserLearnedDao userLearnedDao;
    @Resource
    private UserSchoolDao userSchoolDao;


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 通过短信注册
     *
     * @param tel 电话号码
     * @return 实体对象
     */
    public Map<String, Object> loginTel(String tel) {
        // 检测手机号
        if (!MsmUtil.checkMobileNum(tel)) {
            log.error(tel + "输入正确手机号：");
            return ResMap.err("请输入正确的手机号");
        }
        String codeKey = "loginTel:" + tel;
        String confirmKey = "confirmTel:" + tel;
        // （1分钟内）?
        String s = redisTemplate.opsForValue().get(confirmKey);
        if (s != null) {
            log.error(tel + "在一分钟内多次请求");
            return ResMap.err("请在1分后请求");
        }
        // 生成随机验证码
        log.info(tel + "正在发送短信");
        String code = CodeUtil.createCode();
        // 测试
//        boolean send = new MsmUtil().send(MsmUtil.Opt.Test, tel, code);
//        // 实际
        boolean send = new MsmUtil().send(MsmUtil.Opt.Login, tel, code);
        if (!send) {
            log.error(tel + "验证码发送失败");
            return ResMap.err("验证码发送失败");
        }
        // 存入缓存
        this.redisTemplate.opsForValue().set(codeKey, code, 5, TimeUnit.MINUTES);
        this.redisTemplate.opsForValue().set(confirmKey, code, 1, TimeUnit.MINUTES);
        log.info(tel + "验证码发送成功 " + code);
        return ResMap.ok("验证码发送成功");
    }

    /**
     * 手机号验证
     *
     * @param code 验证码
     * @param tel  手机号
     * @return 实体对象
     */
    public Map<String, Object> confirmTel(String code, String tel) {
        // tag : {0:手机， 1:邮箱， 2:新用户}
        int tag = 0; // 用于下一步操作的tag

        if (!MsmUtil.checkMobileNum(tel)) {
            log.error(tel + "输入正确手机号");
            return ResMap.err("请输入正确的手机号");
        }
        log.info(tel + "正在验证短信验证码");
        // 查询缓存
        String codeKey = "loginTel:" + tel;
        String rCode = redisTemplate.opsForValue().get(codeKey);
        if (rCode == null || !rCode.equals(code)) {
            log.error(tel + "验证码错误 code：" + code + ", rcode：" + rCode);
            return ResMap.err("验证码错误");
        }
        log.info(tel + " 验证码正确");

        // 查询是否有该用户
        User quser = this.userDao.queryByTel(tel);
        if (quser == null) {
            // 开始注册
            tag = 2; // 新用户
            boolean register = this.register(tel, null);
            if (!register) return ResMap.err("注册失败");
            quser = this.userDao.queryByTel(tel);
        }
        log.info(tel + "开始生成token");
        String userId = quser.getUserId();
        // 注册成功后返回token
        return ResMap
                .ok("token", new JwtConfig().createToken(userId))
                .put("tag", tag)
                .build();
    }

    /**
     * 通过email注册，发送电子邮件
     *
     * @param email 电子邮件
     * @return 实体对象
     */
    public Map<String, Object> loginEmail(String email) {
        // 检查邮箱是否正确
        if (!EmailUtil.checkEmail(email)) {
            log.error(email + " 请输入正确的邮箱");
            return ResMap.err("请输入正确的邮箱");
        }
        String codeKey = "loginEmail:" + email;
        String confirmKey = "confirmEmail:" + email;
        // （1分钟内）?
        String s = redisTemplate.opsForValue().get(confirmKey);
        if (s != null) {
            log.error(email + "在一分钟内多次请求");
            return ResMap.err("请在1分后请求");
        }

        // 生成验证码
        log.info(email + "正在发送验证码");
        String code = CodeUtil.createCode();
        Boolean sent = new LibraryEmailUtil().send(email, code, LibraryEmailUtil.Opt.Register);
        if (!sent) {
            log.info(email + "验证码发送失败");
            return ResMap.err("验证码发送失败");
        }

        // 验证码进入缓冲 5分钟
        this.redisTemplate.opsForValue().set(codeKey, code, 5, TimeUnit.MINUTES);
        this.redisTemplate.opsForValue().set(confirmKey, code, 1, TimeUnit.MINUTES);

        log.info(email + "验证码发送成功 " + code);
        return ResMap.ok("验证码发送成功");

    }

    /**
     * 邮箱验证
     *
     * @param code  验证码
     * @param email 邮箱
     * @return 实体
     */
    public Map<String, Object> confirmEmail(String code, String email) {
        // tag : {0:手机， 1:邮箱， 2:新用户}
        int tag = 1;

        // 检测缓冲是否有code
        if (!EmailUtil.checkEmail(email)) {
            log.error(email + "输入正确的邮箱");
            return ResMap.err("请输入正确的邮箱");
        }
        log.info(email + "正在验证验证码");
        // 查询缓存
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
            tag = 2; //新用户
            boolean register = this.register(null, email);
            if (!register) return ResMap.err("注册失败");
            quser = this.userDao.queryByEmail(email);
        }
        log.info(email + "开始生成token");
        String userId = quser.getUserId();

        // 注册成功后返回token
        return ResMap
                .ok("token", new JwtConfig().createToken(userId))
                .put("tag", tag)
                .build();

    }

    private boolean register(String tel, String email) {
        log.info(email + "开始注册");
        String userId = UUID.randomUUID().toString();
        User user = new User(); // User
        UserInfo userInfo = new UserInfo(); // UserInfo
        UserRecord userRecord = new UserRecord(); // UserRecord
        user.setUserId(userId);
        // tel
        if (!StringUtils.isEmpty(tel)) user.setPhoneNum(tel);
        // email
        if (!StringUtils.isEmpty(email)) user.setEmail(email);
        userInfo.setUserId(userId);
        userRecord.setUserId(userId);
        userRecord.setCredit(100);
        userRecord.setMaxTime(0);
        userRecord.setAllTime(0);
        userRecord.setDayRank(0);
        userRecord.setWeekRank(0);
        userRecord.setMonthRank(0);
        userRecord.setAllRank(0);


        if (userDao.insert(user) < 1 || userInfoDao.insert(userInfo) < 1 || this.userRecordDao.insert(userRecord) < 1) {
            log.info(email + " 数据库插入失败");
            return false;
        }
        log.info(email + "注册，数据库插入成功");
        return true;
    }


    /**
     * 重邮账号登陆
     *
     * @param cqupt_id 账号
     * @param password 密码
     * @return 实体
     */
    public Map<String, Object> loginCqupt(String cqupt_id, String password) {
        if (StringUtils.isEmpty(cqupt_id) || StringUtils.isEmpty(password)) {
            return ResMap.err("账号密码不能空");
        }
        // 向重邮服务器发送请求
        HashMap<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Linux; Android 12; RMX2121 Build/SP1A.210812.016; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/86.0.4240.99 XWEB/4425 MMWEBSDK/20220903 Mobile Safari/537.36 MMWEBID/5026 MicroMessenger/8.0.28.2240(0x28001C35) WeChat/arm64 Weixin NetType/WIFI Language/zh_CN ABI/arm64 MiniProgramEnv/android");
        headers.put("Referer", "https://servicewechat.com/wx8227f55dc4490f45/184/page-frame.html");
        headers.put("traefik", "user");
        String url = String.format("https://we.cqupt.edu.cn/api/login?cqupt_id=%s&password=%s", cqupt_id, password);
        String response = null;

        // 发送请求
        try {
            response = HttpClientUtil.postRequest(url, null, headers);
            System.out.println(response);
        } catch (Exception e) {
            log.info("请求失败：" + cqupt_id + " " + password);
        }

//        response = "{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"user_info\":{\"role\":\"undergraduate\",\"name\":\"李瑶鑫\",\"cqupt_id\":\"1665412\",\"student_id\":\"2019210138\",\"grade\":\"2019\",\"class\":\"01041902\",\"academy_name\":\"通信与信息工程学院\",\"profession_name\":\"信息工程\",\"gender\":\"男\",\"counselor_name\":\"黄彩映\",\"counselor_cqupt_id\":\"0103117\"}}}\n";

        response = response.replace("class", "classs");
        //格式化JSON数据
        HashMap<Object, Object> map = JSONObject.parseObject(response, HashMap.class);
        if (!Integer.valueOf(0).equals(map.get("code"))) {
            return ResMap.err((String) map.get("msg"));
        }
        JSONObject data = (JSONObject) map.get("data");
        JSONObject Info = (JSONObject) data.get("user_info");
        CquptInfo cquptInfo = Info.toJavaObject(CquptInfo.class);

        // 查询数据库是否有 cqupt_id
        CquptInfo qCquptInfo = this.cquptInfoDao.queryByCquptId(cqupt_id);
        String userId = "1000" + Integer.parseInt(cqupt_id);
        if (qCquptInfo == null) {
            // 插入
            User user = new User();
            user.setUserId(userId);
            if (this.userDao.insert(user) < 1) return ResMap.err("插入User表失败");
            cquptInfo.setUserId(userId);
            if (this.cquptInfoDao.insert(cquptInfo) < 1) return ResMap.err("插入cquptInfo表失败");
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setRealName(cquptInfo.getName());
            userInfo.setNickName(cquptInfo.getName());
            userInfo.setGender(cquptInfo.getGender());
            userInfo.setGender(cquptInfo.getGender());
            if (this.userInfoDao.insert(userInfo) < 1) return ResMap.err("插入userInfo表失败");
        }
        UserInfo userInfo = this.userInfoDao.queryById(userId);
        // 直接返回token
        return ResMap.ok("token", new JwtConfig().createToken(userId)).put("userInfo", userInfo).build();
    }
}
