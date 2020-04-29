package com.classManage.tusdt.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {
    public static void main(String[] args) {
        //测试发送邮件及验证码
        sendMail("1985298599@qq.com","123456");
    }

    /**
     * 外网邮件发送
     *
     * @param to
     * @param code
     */
    public static void sendMail(String to, String code) {
        // Session对象:
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.163.com"); // 设置主机地址
        // smtp.163.com
        // smtp.qq.com
        // smtp.sina.com
        props.setProperty("mail.smtp.auth", "true");// 认证
        // 2.产生一个用于邮件发送的Session对象
        Session session = Session.getInstance(props);

        // Message对象:
        Message message = new MimeMessage(session);
        // 设置发件人：
        try {

            // 4.设置消息的发送者
            Address fromAddr = new InternetAddress("tus_asset_manage@qq.com");
            message.setFrom(fromAddr);

            // 5.设置消息的接收者 nkpxcloxbtpxdjai
            Address toAddr = new InternetAddress(to);
            // TO 直接发送 CC抄送 BCC密送
            message.setRecipient(MimeMessage.RecipientType.TO, toAddr);

            // 6.设置主题
            message.setSubject("高校教室排课及教室预约云平台的验证码");
            // 7.设置正文
            message.setContent("尊敬的用户您好：\n\n\t\t您的验证码为：" + code, "text/html;charset=UTF-8");

            // 8.准备发送，得到火箭
            Transport transport = session.getTransport("smtp");
            // 9.设置火箭的发射目标
            transport.connect("smtp.qq.com", "hulixiaSera@163.com", "nkpxcloxbtpxdjai");
            // 10.发送
            transport.sendMessage(message, message.getAllRecipients());
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
