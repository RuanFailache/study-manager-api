package app.studymanager.modules.user.validationcode;

import app.studymanager.modules.user.User;
import app.studymanager.shared.service.tokengenerator.TokenGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class UserValidationCodeFactory {
    private final TokenGeneratorService tokenGeneratorService;

    public UserValidationCode create(User user) {
        UserValidationCode validationCode = new UserValidationCode();
        validationCode.setCode(tokenGeneratorService.generateValidationCode());
        validationCode.setExpiresAt(OffsetDateTime.now().plusMinutes(5));
        validationCode.setUser(user);
        return validationCode;
    }
}
