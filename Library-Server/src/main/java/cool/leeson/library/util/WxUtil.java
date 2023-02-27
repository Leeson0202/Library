package cool.leeson.library.util;

public class WxUtil {

    private final static String APP_ID = "wx120544f5baefb8a0";
    private final static String APP_SECRET = "49d8a66ca5a8936462d84ce4956596c8";
    private final static String WX_LOGIN_SERVER_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    public static String getWxServerUrl(String code) {
        return String.format(WX_LOGIN_SERVER_URL,APP_ID, APP_SECRET, code);

    }
}
