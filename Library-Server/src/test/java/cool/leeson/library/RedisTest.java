package cool.leeson.library;

import cool.leeson.library.entity.tools.RedisTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTool redisTool;

    @Test
    public void flushByEnd() {
        redisTool.deleteByEnd("Info");
        redisTool.deleteByPrefix("school");

    }
}
