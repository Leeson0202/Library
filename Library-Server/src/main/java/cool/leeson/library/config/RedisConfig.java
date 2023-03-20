package cool.leeson.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    public enum FormatKey {
        // 储存信息{schoolId/libraryId/roomId/ seat}:Info
        INFO("%s:Info"),
        // 房间信息 {roomId}:roomId
        ROOM("%s:roomId"),
        // 座位信息 {seatId}:{day}:{timeIdx}  true/false
        SEAT("%s:%s:%s"),
        // 用户预约信息  {userId}:{day}:{timeIdx}  true/false
        USER("%s:%s:%s"),
        // 用户在线信息 {userId}:online    0:未入座，1：已入座，2：暂时离开
        ONLINE("%s:online");

        private final String format;

        FormatKey(String s) {
            this.format = s;
        }

        @Override
        public String toString() {
            return this.format;
        }
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //key序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //value序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //hash类型key序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //hash类型value序列化
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        //注入连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

}

