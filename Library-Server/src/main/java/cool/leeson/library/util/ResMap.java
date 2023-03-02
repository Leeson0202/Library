package cool.leeson.library.util;

import java.util.HashMap;
import java.util.Map;

public class ResMap {
    private Map<String, Object> map;

    public static Map<String, Object> ok() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        return map;
    }

    public static ResMap ok(String key, Object value) {
        ResMap resMap = new ResMap();
        resMap.map = new HashMap<>();
        resMap.map.put("code", 200);
        resMap.map.put(key, value);
        return resMap;
    }

    public ResMap put(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public Map<String, Object> build() {
        return this.map;
    }

    public static Map<String, Object> ok(Object data) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("data", data);
        return res;
    }

    public static Map<String, Object> err() {
        return new HashMap<String, Object>() {{
            put("code", 500);
            put("err", "服务器内部错误");
        }};
    }

    public static Map<String, Object> err(String data) {
        return new HashMap<String, Object>() {{
            put("code", 500);
            put("err", data);
        }};
    }
}
