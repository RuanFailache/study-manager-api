package app.studymanager.modules.user.validationcode;

import app.studymanager.modules.user.User;
import app.studymanager.shared.exception.NotFoundException;
import app.studymanager.shared.exception.UnauthorizedException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationCodeServiceImpl implements UserValidationCodeService {
    private final UserValidationCodeRepository userValidationCodeRepository;

    private final UserValidationCodeFactory userValidationCodeFactory;

    @Transactional
    public UserValidationCode create(User user) {
        log.info(UserValidationCodeLogger.CREATE);
        userValidationCodeRepository.deleteByUser(user);
        UserValidationCode validationCode = userValidationCodeFactory.create(user);
        return userValidationCodeRepository.save(validationCode);
    }

    @Transactional
    public void validate(User user, String code) {
        log.info(UserValidationCodeLogger.VALIDATE);

        var foundValidationCode = userValidationCodeRepository.findByUser(user);

        if (foundValidationCode.isEmpty()) {
            throw new NotFoundException("No validation code found for this user");
        }

        var validationCode = foundValidationCode.get();

        if (!validationCode.getCode().equals(code)) {
            throw new UnauthorizedException("Invalid validation code");
        }

        if (validationCode.isExpired()) {
            throw new UnauthorizedException("Validation code expired");
        }

        userValidationCodeRepository.delete(validationCode);
    }
}
