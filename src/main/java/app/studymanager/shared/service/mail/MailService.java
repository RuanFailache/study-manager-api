package app.studymanager.shared.service.mail;

public interface MailService {
    void sendValidationCode(String email, String validationCode);
}
