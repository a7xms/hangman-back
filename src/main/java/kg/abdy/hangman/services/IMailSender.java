package kg.abdy.hangman.services;

public interface IMailSender {
    void sendMail(String subject, String toEmail, String content);
}
