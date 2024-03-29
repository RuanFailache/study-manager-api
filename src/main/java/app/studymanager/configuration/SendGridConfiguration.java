package app.studymanager.configuration;

import app.studymanager.shared.service.mail.MailProperties;
import com.sendgrid.SendGrid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfiguration {
    @Bean
    public SendGrid sendGrid(MailProperties properties) {
        return new SendGrid(properties.getApikey());
    }
}
