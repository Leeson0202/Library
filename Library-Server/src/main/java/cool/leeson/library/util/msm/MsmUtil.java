package cool.leeson.library.util.msm;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class MsmUtil {
    // 测试账号
//    private static final String signName = "阿里云短信测试";
    // signName
    private static final String signName = "悠点单词";
    /**
     * use STS Token
     * DefaultProfile profile = DefaultProfile.getProfile(
     * "<your-region-id>",           // The region ID
     * "<your-access-key-id>",       // The AccessKey ID of the RAM account
     * "<your-access-key-secret>",   // The AccessKey Secret of the RAM account
     * "<your-sts-token>");          // STS Token
     **/
    private final DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5tPopBCUj5yt31T9QT76", "3CyKbDS07IQQKQ2SF5CFiaiBMIt8MF");

    // templateCode
    public static enum Opt {
        Test("SMS_154950909"), // 测试
        Register("SMS_227741458"),
        Login("SMS_254210472"),
        Update("SMS_254215484");
        private final String method;

        Opt(String s) {
            this.method = s;
        }

        @Override
        public String toString() {
            return this.method;
        }
    }

    private static final HashMap<Integer, String> map = new HashMap<Integer, String>() {
        {
            put(0, "SMS_227741458"); // 注册
            put(1, "SMS_254210472"); // 登陆
            put(2, "SMS_254215484"); // 修改信息
        }
    };

    public boolean send(Opt opt, String phone, String code) {

        log.info(phone + " 正在发送短信。");

        IAcsClient client = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("SMS_154950909".equals(opt.toString())?"阿里云短信测试": signName); // 签名
        request.setTemplateCode(opt.toString());  // 注册？登陆？改？
        request.setPhoneNumbers(phone);
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        try {
            log.info(phone + "正在发送。。");
            SendSmsResponse response = client.getAcsResponse(request);
            String reCode = response.getCode();
            System.out.println(new Gson().toJson(response));
            return "OK".equalsIgnoreCase(reCode);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
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

    public static void main(String[] args) {
        boolean sent = new MsmUtil().send(Opt.Test, "18523681435", "234567");
        System.out.println(sent);
    }
}
