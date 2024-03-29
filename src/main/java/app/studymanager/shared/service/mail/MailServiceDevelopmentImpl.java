package app.studymanager.shared.service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("development")
public class MailServiceDevelopmentImpl implements MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    public void sendValidationCode(String email, String validationCode) {
        logger.info(MailLogger.DEVELOPMENT, email);
    }
}
