package cn.edu.sjtu.sip_server.util;


import cn.edu.sjtu.sip_server.constant.Const;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

//import sun.security.krb5.EncryptionKey;


@Slf4j
public class EmailUtil {
    public static final String PROTOCOL = "smtp";
    public static final int PORT = 25;
    public static final String HOST = "smtp.163.com";
    public static final String FROM = Enycryption.decryptBASE64(Const.FROM_EMAIL);
    public static final String PWD = Enycryption.decryptBASE64(Const.IMPORT_DATA);

    /**
     * @return
     */
    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.store.protocol", PROTOCOL);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", true);

        Authenticator authenticator = new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }

        };
        Session session = Session.getDefaultInstance(props, authenticator);

        return session;
    }

    public static String getCurrentHostName() {
        return Const.LOCAL_IP;
    }

    public static boolean sendUpdatePassword(String email, String dataStr) {
        StringBuilder builder = new StringBuilder(
                "请点击链接重置您双创平台的账户密码！\n");
        String halfUrl = "http://" + getCurrentHostName() + ":" + Const.PORT + "/sip/user/updatePasswordByLink";
        String url = halfUrl +
                "?data=" + Enycryption.encryptBASE64(dataStr.getBytes());
        builder.append("<a href='" + url + "'>" + halfUrl + "</a>");
        return send(email, builder.toString());
    }

    public static boolean sendAdd(String email, String dataStr) {
        StringBuilder builder = new StringBuilder(
                "请点击链接激活双创平台的注册账号！(若未在双创平台注册账号，请忽略此邮件）\n");
        String halfUrl = "http://" + getCurrentHostName() + ":" + Const.PORT + "/sip/user/add";
        String url = halfUrl
                + "?data=" + Enycryption.encryptBASE64(dataStr.getBytes());
        builder.append("<a href='" + url + "'>" + halfUrl + "</a>");
        return send(email, builder.toString());
    }


    public static boolean send(String email, String content) {
        Session session = getSession();
        try {
            // Instantiate a message
            Message msg = new MimeMessage(session);

            //Set message attributes
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(email)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("双创平台");
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html;charset=utf-8");

            //Send the message
            Transport.send(msg);
            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }

    /**
     * 判断字符串是否是email格式
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(Const.EMAIL_PATTERN, email);
    }

}

