package app.studymanager.domain.service;

import app.studymanager.domain.constants.HistoryResponsible;
import app.studymanager.domain.constants.UserHistoryMessage;
import app.studymanager.domain.model.User;
import app.studymanager.domain.model.UserValidationCode;
import app.studymanager.domain.repository.UserValidationCodeRepository;
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
