package app.studymanager.shared.service.mail.templates.validationcode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("development")
public class DevelopmentValidationCodeMailTemplate implements ValidationCodeMailTemplate {
    public void sendValidationCode(String email, String validationCode) {
        log.info("Enviando código {} para {}", validationCode, email);
    }
}