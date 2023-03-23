package cool.leeson.library.service.clock;

import cool.leeson.library.dao.UserOnlineDao;
import cool.leeson.library.entity.user.UserOnline;
import cool.leeson.library.exceptions.MyException;
import cool.leeson.library.service.user.UserOnlineService;
import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ClockService {
    @Resource
    private UserOnlineService userOnlineService;
    @Resource
    private UserOnlineDao userOnlineDao;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    // online
    // 0: 未入座
    // 1： 入座
    // 2： 暂时离开


    public Map<String, Object> user(String userId, String seed, Integer tag) throws MyException {
        log.info(userId + " " + seed + " " + tag);
        // seed：userId
        redisTemplate.opsForValue().set(seed, userId + ":" + tag, 60, TimeUnit.SECONDS);
        return ResMap.ok();

    }

    public Map<String, Object> equipment(String equipment, String seed) throws MyException {// 先查询状态
        String s = redisTemplate.opsForValue().get(seed);
        if (s == null) {
            throw new MyException(MyException.STATUS.err);
        }
        String userId = s.split(":")[0];
        int tag = Integer.parseInt(s.split(":")[1]);
        log.info("userId: " + s + " tag:" + tag);

        Map<String, Object> res = userOnlineService.queryById(userId);
        UserOnline userOnline = (UserOnline) res.get("data");
        Integer online = userOnline.getOnline();
        Date now = new Date();

        // 判断状态
        if (online == 0 || online == 2) {
            // 没有入座 或暂时离开
            online = 1;
            userOnline.setDate(now);
        } else if (tag == 1 && online == 1) {
            // 暂时离座
            online = 2;
            // 计算时间

        } else {
            // 直接离开
            online = 0;
            // 计算时间
        }
        userOnline.setOnline(online);
        userOnlineService.update(userOnline);

        return ResMap.ok();

    }
}
