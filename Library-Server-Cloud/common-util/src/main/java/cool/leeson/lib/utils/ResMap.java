package cool.leeson.lib.utils;

import cool.leeson.lib.exceptions.MyException;

import java.util.HashMap;
import java.util.Map;

public class ResMap {
    private Map<String, Object> map;

    public ResMap() {
        this.map = new HashMap<>();
    }

    /**
     * 直接返回 200
     */
    public static Map<String, Object> ok() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        return map;
    }

    // 200 带对象
    public static Map<String, Object> ok(Object data) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("data", data);
        return res;
    }

    // 200 {key value}
    public static ResMap ok(String key, Object value) {
        ResMap resMap = new ResMap();
        resMap.map = new HashMap<>();
        resMap.map.put("code", 200);
        resMap.map.put(key, value);
        return resMap;
    }

    public static Map<String, Object> put(MyException.STATUS status) {
        Map<String, Object> res = new HashMap<>();
        res.put("code", status.getCode());
        res.put("msg", status.getMsg());
        return res;
    }

    // 添加数据
    public ResMap put(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public ResMap putCode(Integer code) {
        this.map.put("code", code);
        return this;
    }

    public ResMap putData(Object data) {
        this.map.put("data", data);
        return this;
    }

    public ResMap putMsg(String msg) {
        this.map.put("msg", msg);
        return this;
    }

    public Map<String, Object> build() {
        return this.map;
    }


    public static Map<String, Object> err() {
        return new HashMap<String, Object>() {{
            put("code", 400);
            put("msg", "请求错误");
        }};
    }

    public static Map<String, Object> err(String msg) {
        return new HashMap<String, Object>() {{
            put("code", 400);
            put("msg", msg);
        }};
    }

    public static Map<String, Object> error() {
        return new HashMap<String, Object>() {{
            put("code", 500);
            put("msg", "服务器内部错误");
        }};
    }

    public static Map<String, Object> error(String msg) {
        return new HashMap<String, Object>() {{
            put("code", 500);
            put("msg", msg);
        }};
    }
}
