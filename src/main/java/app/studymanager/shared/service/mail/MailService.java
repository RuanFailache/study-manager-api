package app.studymanager.shared.service.mail;

public interface MailService {
    void send(String to, String subject, String text);
}
