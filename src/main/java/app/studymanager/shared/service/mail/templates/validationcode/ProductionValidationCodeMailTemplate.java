package app.studymanager.shared.service.mail.templates.validationcode;

import app.studymanager.shared.service.mail.MailServiceImpl;
import app.studymanager.shared.service.mail.MailTemplates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("production")
public class ProductionValidationCodeMailTemplate implements ValidationCodeMailTemplate {
    private static final String SUBJECT = "Chegou seu código de validação!";

    private final MailServiceImpl mailService;

    private final TemplateEngine templateEngine;

    public void sendValidationCode(String email, String validationCode) {
        log.info("Enviando código de validação para email do usuário");

        Context context = new Context();
        context.setVariable("code", validationCode);

        String htmlTemplate = templateEngine.process(MailTemplates.VALIDATION_CODE, context);

        mailService.send(email, SUBJECT, htmlTemplate);
    }
}
