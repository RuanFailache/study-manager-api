package app.studymanager.modules.user.validationcode;

import app.studymanager.modules.user.User;
import app.studymanager.modules.user.history.UserHistoryMessage;
import app.studymanager.modules.user.history.UserHistoryService;
import app.studymanager.shared.constants.HistoryResponsible;
import app.studymanager.shared.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserValidationCodeServiceImpl implements UserValidationCodeService {
    private static final Logger logger = LoggerFactory.getLogger(UserValidationCodeService.class);

    private final UserHistoryService userHistoryService;
    private final UserValidationCodeRepository userValidationCodeRepository;
    private final UserValidationCodeFactory userValidationCodeFactory;

    public UserValidationCodeServiceImpl(
            UserHistoryService userHistoryService,
            UserValidationCodeRepository userValidationCodeRepository,
            UserValidationCodeFactory userValidationCodeFactory
    ) {
        this.userHistoryService = userHistoryService;
        this.userValidationCodeRepository = userValidationCodeRepository;
        this.userValidationCodeFactory = userValidationCodeFactory;
    }

    @Transactional
    public UserValidationCode create(User user) {
        logger.info(UserValidationCodeLogger.CREATE);
        try {
            UserValidationCode validationCode = userValidationCodeFactory.create(user);
            userValidationCodeRepository.save(validationCode);
            userHistoryService.insert(user, HistoryResponsible.SYSTEM, UserHistoryMessage.CREATE_VALIDATION_CODE);
            return validationCode;
        } catch (Exception exception) {
            logger.error(UserValidationCodeLogger.CREATE_ERROR, exception);
            throw ExceptionUtil.handle(exception, UserValidationCodeLogger.CREATE_ERROR);
        }
    }
}
