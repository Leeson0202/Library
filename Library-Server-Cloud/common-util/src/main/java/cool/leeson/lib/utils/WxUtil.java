package cool.leeson.lib.utils;

public class WxUtil {

    private final static String APP_ID = "wx120544f5baefb8a0";
    private final static String APP_SECRET = "49d8a66ca5a8936462d84ce4956596c8";
    private final static String WX_LOGIN_SERVER_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
    private final static String WX_TOKEN_SERVER_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    /**
     * 获取用户 openid 和 session_key 的 url
     * @param code 小程序code
     * @return url
     */
    public static String getWxLoginUrl(String code) {
        return String.format(WX_LOGIN_SERVER_URL, APP_ID, APP_SECRET, code);

    }

    /**
     * 获取 session_token的url
     * @return url
     */
    public static String getWxTokenUrl() {
        return String.format(WX_TOKEN_SERVER_URL, APP_ID, APP_SECRET);

    }
}
