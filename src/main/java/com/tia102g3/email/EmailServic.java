package com.tia102g3.email;

import javax.mail.MessagingException;

/**
 * ClassName： EmailServic
 * package：com.tia102g3.email
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/10 下午6:59
 * @Version 1.0
 */
public interface EmailServic {
    void sendEmail(String emailTo, String emailSubject, String emailText) throws MessagingException;
}
