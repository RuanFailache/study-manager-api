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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final MailProperties properties;

    private final SendGrid sendGrid;

    public void send(String to, String subject, String text) {
        log.info(MailLogger.SEND_MAIL);
        try {
            Mail mail = getMail(to, subject, text);
            Request request = getRequest(mail);
            Response response = sendGrid.api(request);

            if (response.getStatusCode() < 200 || response.getStatusCode() >= 300) {
                throw new InternalServerErrorException(MailLogger.SEND_MAIL_ERROR);
            }
        } catch (Exception exception) {
            throw ExceptionUtil.handle(exception, MailLogger.SEND_MAIL_ERROR);
        }
    }

    private Mail getMail(String to, String subject, String text) {
        Email from = new Email(properties.getAddress());
        Email toEmail = new Email(to);
        Content content = new Content("text/html", text);
        return new Mail(from, subject, toEmail, content);
    }

    private Request getRequest(Mail mail) throws IOException {
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        return request;
    }
}
