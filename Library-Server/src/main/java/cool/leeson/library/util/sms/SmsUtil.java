package cool.leeson.library.util.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
@Setter
@Configuration
@ConfigurationProperties(prefix = "sms")
@PropertySource("classpath:config/private-config.properties")
public class SmsUtil {
    private String signName;
    private String regionId;
    private String accessKeyId;
    private String secret;

    // templateCode
    public static enum Opt {
        Test("SMS_154950909"), // 测试
        Register("SMS_274745554"),
        Login("SMS_274535909"),
        Update("SMS_274660909"),
        FastReceive("");
        private final String method;

        Opt(String s) {
            this.method = s;
        }

        @Override
        public String toString() {
            return this.method;
        }
    }

    public boolean sendMsg(String phone,Opt opt){
        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("SMS_154950909".equals(opt.toString()) ? "阿里云短信测试" : signName); // 签名
        request.setTemplateCode(opt.toString());  // 注册？登陆？改？
        request.setPhoneNumbers(phone);
        request.setTemplateParam("");
        return this.sent(request);
    }


    public boolean sendCode(String phone, Opt opt, String code) {
        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("SMS_154950909".equals(opt.toString()) ? "阿里云短信测试" : signName); // 签名
        request.setTemplateCode(opt.toString());  // 注册？登陆？改？
        request.setPhoneNumbers(phone);
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        return this.sent(request);
    }

    private boolean sent(SendSmsRequest request) {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);
        try {
            SendSmsResponse response = client.getAcsResponse(request);
            String reCode = response.getCode();
            System.out.println(new Gson().toJson(response));
            return "OK".equalsIgnoreCase(reCode);
        } catch (ServerException e) {
            log.warn("发送失败！");
        } catch (ClientException e) {
            log.warn("ErrCode:" + e.getErrCode());
            log.warn("ErrMsg:" + e.getErrMsg());
            log.warn("RequestId:" + e.getRequestId());
        }
        return false;
    }


    @SuppressWarnings("RegExpDuplicateCharacterInClass")
    public static boolean checkMobileNum(String mobile) {
        if (mobile.length() != 11) {
            return false;
        } else {
            /*
             * 移动号段正则表达式
             */
            String pat1 = "^((13[4-9])|(147)|(15[0-2,7-9])|(178)|(18[2-4,7-8]))\\d{8}|(1705)\\d{7}$";
            /*
             * 联通号段正则表达式
             */
            String pat2 = "^((13[0-2])|(145)|(15[5-6])|(176)|(18[5,6]))\\d{8}|(1709)\\d{7}$";
            /*
             * 电信号段正则表达式
             */
            String pat3 = "^((133)|(153)|(177)|(18[0,1,9])|(149))\\d{8}$";
            /*
             * 虚拟运营商正则表达式
             */
            String pat4 = "^((170))\\d{8}|(1718)|(1719)\\d{7}$";
            Pattern pattern1 = Pattern.compile(pat1);
            Matcher match1 = pattern1.matcher(mobile);
            boolean isMatch1 = match1.matches();
            if (isMatch1) {
                return true;
            }
            Pattern pattern2 = Pattern.compile(pat2);
            Matcher match2 = pattern2.matcher(mobile);
            boolean isMatch2 = match2.matches();
            if (isMatch2) {
                return true;
            }
            Pattern pattern3 = Pattern.compile(pat3);
            Matcher match3 = pattern3.matcher(mobile);
            boolean isMatch3 = match3.matches();
            if (isMatch3) {
                return true;
            }
            Pattern pattern4 = Pattern.compile(pat4);
            Matcher match4 = pattern4.matcher(mobile);
            return match4.matches();
        }
    }


}
