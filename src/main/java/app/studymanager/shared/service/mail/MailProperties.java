package app.studymanager.shared.service.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("mail")
public class MailProperties {
    private String address;
    private String apikey;
}
