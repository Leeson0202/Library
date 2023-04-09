package cool.leeson.library.entity.tools;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisTool {
    @Resource
    private RedisTemplate<String, String> redisTemplate;


    public enum FormatKey {

        CODE("%s:code"), // 身份认证 code  {tel/email}:code  123456
        REPEAT("%s:repeat"), // 判断是否一分钟内重复  {tel/email}:repeat ""
        TOKEN("%s:token"), // 用户token信息 {userId}:token akdvhaviawkvawvd
        INFO("school:%s:info"), // 储存信息{schoolId/libraryId/roomId/ seat}:Info
        SIMPLE("school:%s:simple"), // {school}:simple
        ROOM("school:%s:roomId"), // 房间信息+seat信息 {roomId}:roomId
        RECEIVE("receive:%s:%s:%s"), // 预约信息 {seatId/userId}:{day}:{timeIdx}  true/false
        ONLINE("receive:%s:online"), // 用户在线 {userId}:online
        SEED("receive:%s:seed"); // 用户打卡 {userId}:seed     1分钟

        private final String format;

        FormatKey(String s) {
            this.format = s;
        }

        @Override
        public String toString() {
            return this.format;
        }
    }

    public void set(String key, Object data) {
        if (StringUtils.isEmpty(key)) return;
        this.redisTemplate.opsForValue().set(key, JSON.toJSONString(data));
    }


    public void set(String key, Object data, long timeout, TimeUnit unit) {
        if (StringUtils.isEmpty(key)) return;
        this.redisTemplate.opsForValue().set(key, JSON.toJSONString(data), timeout, unit);
    }

    public String get(String key) {
        if (StringUtils.isEmpty(key)) return null;
        if (Boolean.TRUE.equals(this.redisTemplate.hasKey(key))) {
            return this.redisTemplate.opsForValue().get(key);
        }
        return null;
    }

    public void delete(String key) {
        if (StringUtils.isEmpty(key)) return;
        this.redisTemplate.delete(key);
    }

    public void flushAll() {
        log.warn("redis flushAll");
        Set<String> keys = redisTemplate.keys("*");
        assert keys != null;
        redisTemplate.delete(keys);
    }


    public void deleteByPrefix(String prefix) {
        log.warn("redis flushByPrefix");
        Set<String> keys = redisTemplate.keys(prefix + "*");
        assert keys != null;
        redisTemplate.delete(keys);
    }

    public void deleteByEnd(String end) {
        log.warn("redis flushByEnd");
        Set<String> keys = redisTemplate.keys("*" + end);
        assert keys != null;
        redisTemplate.delete(keys);
    }

    public Boolean hasKey(String key) {
        if (StringUtils.isEmpty(key)) return null;
        return this.redisTemplate.hasKey(key);
    }

    public Boolean expire(String key, long timeout, TimeUnit unit) {
        if (StringUtils.isEmpty(key)) return false;
        return redisTemplate.expire(key, timeout, unit);
    }

    public Boolean expireAt(String key, Date date) {
        if (StringUtils.isEmpty(key)) return false;
        return redisTemplate.expireAt(key, date);
    }

    public Long getExpire(String key) {
        if (StringUtils.isEmpty(key)) return null;
        return redisTemplate.getExpire(key);
    }


}
