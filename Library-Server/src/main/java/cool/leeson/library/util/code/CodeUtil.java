package cool.leeson.library.util.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class CodeUtil {
    public static String createCode() {
        StringBuilder code = new StringBuilder(String.valueOf(new Random().nextInt(10000)));
        while (code.length() < 6) {
            code.append(new Random().nextInt(10));
        }
        return code.toString();
    }

}
