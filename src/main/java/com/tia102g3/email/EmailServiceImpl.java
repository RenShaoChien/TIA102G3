package com.tia102g3.email;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * ClassName： EmailServiceImpl
 * package：com.tia102g3.email
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/10 下午7:01
 * @Version 1.0
 */
@Service
public class EmailServiceImpl implements EmailServic{

    private final String myGmail = "ixlogic.wu@gmail.com";
    private final String myGmail_password = "ddjomltcnypgcstn";

    @Override
    public void sendEmail(String emailTo, String emailSubject, String emailText) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(myGmail, myGmail_password);
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(myGmail));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo));

            message.setSubject(emailSubject);

            message.setText(emailText);

            Transport.send(message);

            System.out.println("郵件發送成功");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
