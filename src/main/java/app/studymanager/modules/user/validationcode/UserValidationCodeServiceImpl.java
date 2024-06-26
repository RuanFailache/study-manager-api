package app.studymanager.modules.user.validationcode;

import app.studymanager.modules.user.User;
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
        log.info("Criando um código de validação para o usuário");
        userValidationCodeRepository.deleteByUser(user);
        var validationCode = userValidationCodeFactory.create(user);
        return userValidationCodeRepository.save(validationCode);
    }

    @Transactional
    public void validate(User user, String code) {
        log.info("Validando o código de validação do usuário");

        var foundValidationCode = userValidationCodeRepository.findByUser(user);

        if (foundValidationCode.isEmpty()) {
            throw UserValidationCodeException.notFound();
        }

        var validationCode = foundValidationCode.get();

        if (!validationCode.getCode().equals(code)) {
            throw UserValidationCodeException.invalid();
        }

        if (validationCode.isExpired()) {
            throw UserValidationCodeException.expired();
        }

        userValidationCodeRepository.delete(validationCode);
    }
}
