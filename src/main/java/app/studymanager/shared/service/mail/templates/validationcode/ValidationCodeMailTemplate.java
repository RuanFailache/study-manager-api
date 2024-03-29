package app.studymanager.shared.service.mail.templates.validationcode;

public interface ValidationCodeMailTemplate {
    void sendValidationCode(String email, String validationCode);
}
