package app.studymanager.domain.service;

import app.studymanager.domain.constants.HistoryResponsible;
import app.studymanager.domain.constants.UserHistoryMessage;
import app.studymanager.domain.model.User;
import app.studymanager.domain.model.UserHistory;
import app.studymanager.domain.model.UserValidationCode;
import app.studymanager.domain.repository.UserHistoryRepository;
import app.studymanager.domain.repository.UserRepository;
import app.studymanager.domain.repository.UserValidationCodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserHistoryRepository userHistoryRepository;
    private final UserValidationCodeRepository userValidationCodeRepository;

    public UserService(UserRepository userRepository, UserHistoryRepository userHistoryRepository, UserValidationCodeRepository userValidationCodeRepository) {
        this.userRepository = userRepository;
        this.userHistoryRepository = userHistoryRepository;
        this.userValidationCodeRepository = userValidationCodeRepository;
    }

    @Transactional
    public User findByEmailOrCreate(String email) {
        User foundUser = userRepository.findByEmail(email);
        return foundUser != null ? foundUser : this.create(email);
    }

    @Transactional
    public User create(String email) {
        User user = new User();
        user.create(email);
        User createdUser = userRepository.save(user);
        this.addHistory(createdUser, HistoryResponsible.SYSTEM, UserHistoryMessage.CREATE_USER);
        return createdUser;
    }

    @Transactional
    public void createValidationCode(User user, String code) {
        UserValidationCode validationCode = new UserValidationCode();
        validationCode.create(user, code);
        userValidationCodeRepository.save(validationCode);
        this.addHistory(user, HistoryResponsible.SYSTEM, UserHistoryMessage.CREATE_VALIDATION_CODE);
    }

    private void addHistory(User user, String responsible, String message) {
        UserHistory history = new UserHistory();
        history.insert(user, responsible, message);
        userHistoryRepository.save(history);
    }
}
