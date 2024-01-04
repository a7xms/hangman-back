package kg.abdy.hangman.services.impl;

import kg.abdy.hangman.services.IMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailSender implements IMailSender {

    private final JavaMailSender mailSender;

    public MailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    public void sendMail(String subject, String toEmail, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailMessage.setTo(toEmail);
        mailSender.send(mailMessage);
    }
}
