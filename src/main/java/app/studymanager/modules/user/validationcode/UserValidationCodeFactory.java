package app.studymanager.modules.user.validationcode;

import app.studymanager.modules.user.User;
import app.studymanager.shared.service.tokengenerator.TokenGeneratorService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class UserValidationCodeFactory {
    private final TokenGeneratorService tokenGeneratorService;

    public UserValidationCodeFactory(TokenGeneratorService tokenGeneratorService) {
        this.tokenGeneratorService = tokenGeneratorService;
    }

    public UserValidationCode create(User user) {
        UserValidationCode validationCode = new UserValidationCode();
        validationCode.setCode(tokenGeneratorService.generateValidationCode());
        validationCode.setExpiresAt(OffsetDateTime.now().plusMinutes(5));
        validationCode.setUser(user);
        return validationCode;
    }
}
