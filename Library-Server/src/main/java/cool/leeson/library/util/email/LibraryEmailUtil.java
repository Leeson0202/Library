package cool.leeson.library.util.email;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LibraryEmailUtil {
    public static String title = "Leeson Library";

    public static enum Opt {
        Register("【Leeson Library】您正在申请邮箱注册，验证码为：%s，5分钟内有效！"),
        Login("【Leeson Library】验证码为：%s，您正在登录，若非本人操作，请勿泄露。"),
        Update("【Leeson Library】验证码为：%s，您正在尝试变更重要信息，请妥善保管账户信息。");
        private final String method;

        Opt(String s) {
            this.method = s;
        }


        @Override
        public String toString() {
            return this.method;
        }
    }

    ;
    private final Map<Integer, String> map = new HashMap<Integer, String>() {{
        put(0, "【Leeson Library】您正在申请邮箱注册，验证码为：%s，5分钟内有效！");
        put(1, "【Leeson Library】验证码为：%s，您正在登录，若非本人操作，请勿泄露。");
        put(2, "【Leeson Library】验证码为：%s，您正在尝试变更重要信息，请妥善保管账户信息。");
    }};

    public Boolean send(String destEmail, String code, Opt opt) {
        try {
            String context = String.format(opt.toString(), code);
            new EmailUtil().send(destEmail, title, context);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        new LibraryEmailUtil().send("2224351168@qq.com", "123456", Opt.Register);
        new LibraryEmailUtil().send("2224351168@qq.com", "123456", Opt.Login);
        new LibraryEmailUtil().send("2224351168@qq.com", "123456", Opt.Update);
    }


}
