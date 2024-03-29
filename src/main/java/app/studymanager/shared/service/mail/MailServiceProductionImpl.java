package app.studymanager.shared.service.mail;

import app.studymanager.shared.exception.InternalServerErrorException;
import app.studymanager.shared.util.ExceptionUtil;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Profile("production")
public class MailServiceProductionImpl implements MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    private final TemplateEngine templateEngine;
    private final MailProperties properties;

    public MailServiceProductionImpl(TemplateEngine templateEngine, MailProperties properties) {
        this.templateEngine = templateEngine;
        this.properties = properties;
    }

    public void sendValidationCode(String email, String validationCode) {
        Context context = new Context();
        context.setVariable("code", validationCode);
        String htmlTemplate = templateEngine.process(MailTemplates.VALIDATION_CODE, context);
        this.configureMail(email, MailSubjectText.VALIDATION_CODE, htmlTemplate);
    }

    private void configureMail(String to, String subject, String text) {
        logger.info(MailLogger.CONFIGURE_MAIL, to);
        try {
            Email from = new Email(properties.getAddress());
            Email toEmail = new Email(to);
            Content content = new Content("text/html", text);
            sendMail(new Mail(from, subject, toEmail, content));
        } catch (Exception exception) {
            throw ExceptionUtil.handle(exception, MailLogger.CONFIGURE_MAIL_ERROR);
        }
    }

    private void sendMail(Mail mail) {
        logger.info(MailLogger.SEND_MAIL);
        try {
            SendGrid sendGrid = new SendGrid(properties.getApikey());

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            if (response.getStatusCode() >= 400) {
                throw new InternalServerErrorException(MailLogger.SEND_MAIL_ERROR);
            }
        } catch (Exception exception) {
            throw ExceptionUtil.handle(exception, MailLogger.SEND_MAIL_ERROR);
        }
    }
}
