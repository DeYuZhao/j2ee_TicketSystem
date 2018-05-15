package edu.nju.ticketsystem.tools;

import com.sun.mail.util.MailSSLSocketFactory;

import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Administrator on 2018/1/30.
 */
public class MailTools {
    private static Properties properties;
    private static Message message;
    private static Transport transport;
    private static MailTools mailTools = new MailTools();

    public static MailTools getInstance(){
        return mailTools;
    }

    public MailTools() {
        MailSSLSocketFactory mailSSLSocketFactory = null;
        try {
            mailSSLSocketFactory = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        mailSSLSocketFactory.setTrustAllHosts(true);

        properties = new Properties();

        properties.setProperty("mail.smtp.ssl.enable", "true");
        properties.setProperty("mail.smtp.ssl.socketFactory", String.valueOf(mailSSLSocketFactory));
        properties.setProperty("mail.debug", "true");//调试模式发送
        properties.setProperty("mail.smtp.auth", "true");//身份验证设置
        properties.setProperty("mail.host", "smtp.qq.com");//发件人邮箱主机名
        properties.setProperty("mail.transport.protocol", "smtp");//发件协议

        Session session = Session.getInstance(properties);

        message = new MimeMessage(session);

        try {
            message.setSubject("Tickets验证邮件");
            message.setFrom(new InternetAddress("2239645870@qq.com"));//设置发件人

            transport = session.getTransport();
            transport.connect("2239645870@qq.com", "uszbcucthoaeebch");//设置发件人在该邮箱主机上的用户名密码
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送邮件
     * @param address 收件人邮箱
     * @param text 验证码信息
     * @throws AddressException
     * @throws MessagingException
     */
    public void sendMail(String address,String text) throws AddressException, MessagingException{
        message.setText(text);
        transport.sendMessage(message, new Address[] {new InternetAddress(address)});
//        transport.close();
    }

    /**
     * 关闭邮箱发送功能
     * @throws MessagingException
     */
    public void closeMailTransport() throws MessagingException {
        transport.close();
    }

}
