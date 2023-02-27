package cool.leeson.library.util;

import java.util.HashMap;
import java.util.Map;

public class ResMap {
//    private Map<String, Object> resMap = new HashMap<>();


    public static Map<String, Object> ok(Object data) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("data", data);
        return res;
    }

    public static Map<String, Object> err(String data) {
        return new HashMap<String, Object>() {{
            put("code", 200);
            put("err", data);
        }};

    }
}
