package app.studymanager.modules.user.service;

import app.studymanager.shared.constants.HistoryResponsible;
import app.studymanager.modules.user.constant.UserHistoryMessage;
import app.studymanager.modules.user.model.User;
import app.studymanager.modules.user.model.UserValidationCode;
import app.studymanager.modules.user.repository.UserValidationCodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserValidationCodeService {
    private final UserHistoryService userHistoryService;
    private final UserValidationCodeRepository userValidationCodeRepository;

    public UserValidationCodeService(UserHistoryService userHistoryService, UserValidationCodeRepository userValidationCodeRepository) {
        this.userHistoryService = userHistoryService;
        this.userValidationCodeRepository = userValidationCodeRepository;
    }

    @Transactional
    public void create(User user, String code) {
        UserValidationCode validationCode = new UserValidationCode();
        validationCode.create(user, code);
        userValidationCodeRepository.save(validationCode);
        userHistoryService.insert(user, HistoryResponsible.SYSTEM, UserHistoryMessage.CREATE_VALIDATION_CODE);
    }
}
