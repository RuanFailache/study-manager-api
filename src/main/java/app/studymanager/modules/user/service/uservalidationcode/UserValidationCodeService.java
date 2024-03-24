package app.studymanager.modules.user.service.uservalidationcode;

import app.studymanager.modules.user.service.userhistory.UserHistoryService;
import app.studymanager.shared.constants.HistoryResponsible;
import app.studymanager.modules.user.constant.UserHistoryMessage;
import app.studymanager.modules.user.model.User;
import app.studymanager.modules.user.model.UserValidationCode;
import app.studymanager.modules.user.repository.UserValidationCodeRepository;
import app.studymanager.shared.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserValidationCodeService {
    private static final Logger logger = LoggerFactory.getLogger(UserValidationCodeService.class);

    private final UserHistoryService userHistoryService;
    private final UserValidationCodeRepository userValidationCodeRepository;

    public UserValidationCodeService(UserHistoryService userHistoryService, UserValidationCodeRepository userValidationCodeRepository) {
        this.userHistoryService = userHistoryService;
        this.userValidationCodeRepository = userValidationCodeRepository;
    }

    @Transactional
    public void create(User user, String code) {
        logger.info(UserValidationCodeLogger.CREATE);
        try {
            UserValidationCode validationCode = new UserValidationCode();
            validationCode.create(user, code);
            userValidationCodeRepository.save(validationCode);
            userHistoryService.insert(user, HistoryResponsible.SYSTEM, UserHistoryMessage.CREATE_VALIDATION_CODE);
        } catch (Exception exception) {
            logger.error(UserValidationCodeLogger.CREATE_ERROR);
            throw ExceptionUtil.handle(exception, UserValidationCodeLogger.CREATE_ERROR);
        }
    }
}
