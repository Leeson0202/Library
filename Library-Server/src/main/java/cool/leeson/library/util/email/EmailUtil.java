package cool.leeson.library.util.email;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

@Component
@Slf4j
@PropertySource(value = "classpath:config/private-config.properties")
@ConfigurationProperties(prefix = "email")
@Setter
public class EmailUtil {


    /**
     * 配置发送基本参数
     * 发件人邮箱的SMTP服务器地址
     * 前三个不可更改
     */
    private String hostEmail;//开启授权码的邮箱
    private String AuthorizationCode;//授权码
    private String SMTPEmail;// 网易163邮箱的 SMTP 服务器地址
    private Session session;

    private String destEmail;// 收件人邮箱
    private String title;// 标题
    private String context = "正文内容";// 正文内容

    public static enum Opt {
        Register("验证码为：%s，您正在申请邮箱注册，5分钟内有效！"),
        Login("验证码为：%s，您正在登录，若非本人操作，请勿泄露。"),
        Update("验证码为：%s，您正在尝试变更重要信息，请妥善保管账户信息。");
        private final String method;

        Opt(String s) {
            this.method = s;
        }


        @Override
        public String toString() {
            return this.method;
        }
    }


    public boolean send(String destEmail, Opt opt, String code) {
        this.destEmail = destEmail;
        this.context = String.format(opt.toString(), code);

        this.init();
        try {
            //创建邮件
            MimeMessage message = this.createEmail();
            //使用Session获取邮件传输对象
            Transport transport = session.getTransport();
            //使用邮箱账号和密码连接邮件服务器
            transport.connect(hostEmail, AuthorizationCode);
            //发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            //关闭连接
            transport.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void init() {
        //创建连接邮件服务器的参数配置
        Properties props = new Properties(); // 参数配置
        props.setProperty("mail.smtp.host", SMTPEmail);// 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");// 需要请求认证
        props.setProperty("mail.transport.protocol", "smtp");
        //根据配置创建会话对象和邮件服务器交互
        session = Session.getInstance(props);
        session.setDebug(false);// 设置为debug模式, 可以查看详细的发送日志

    }


    /**
     * 创建邮件
     */
    public MimeMessage createEmail() throws Exception {
        //创建一封邮件
        MimeMessage message = new MimeMessage(session);
        //发件人
        message.setFrom(new InternetAddress(hostEmail, "Leeson", "UTF-8"));
        //收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(destEmail, "用户", "UTF-8"));
        //邮件主题
        message.setSubject(title, "UTF-8");
        //邮件正文
        message.setContent(context, "text/html;charset=UTF-8");
        //设置发件时间
        message.setSentDate(new Date());
        //保存设置
        message.saveChanges();
        return message;
    }

    public static boolean checkEmail(String email) {
        if ((email != null) && (!email.isEmpty())) {
            return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
        }
        return false;
    }

}
