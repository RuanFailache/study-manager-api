package app.studymanager.modules.user.validationcode;

import app.studymanager.modules.user.User;
import app.studymanager.modules.user.history.UserHistoryMessage;
import app.studymanager.modules.user.history.UserHistoryService;
import app.studymanager.shared.constants.HistoryResponsible;
import app.studymanager.shared.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationCodeServiceImpl implements UserValidationCodeService {
    private final UserHistoryService userHistoryService;

    private final UserValidationCodeRepository userValidationCodeRepository;

    private final UserValidationCodeFactory userValidationCodeFactory;

    @Transactional
    public UserValidationCode create(User user) {
        log.info(UserValidationCodeLogger.CREATE);
        try {
            UserValidationCode validationCode = userValidationCodeFactory.create(user);
            userValidationCodeRepository.save(validationCode);
            userHistoryService.insert(user, HistoryResponsible.SYSTEM, UserHistoryMessage.CREATE_VALIDATION_CODE);
            return validationCode;
        } catch (Exception exception) {
            throw ExceptionUtil.handle(exception, UserValidationCodeLogger.CREATE_ERROR);
        }
    }
}
