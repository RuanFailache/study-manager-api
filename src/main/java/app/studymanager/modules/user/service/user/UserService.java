package app.studymanager.modules.user.service.user;

import app.studymanager.modules.user.constant.UserHistoryMessage;
import app.studymanager.modules.user.model.User;
import app.studymanager.modules.user.repository.UserRepository;
import app.studymanager.modules.user.service.userhistory.UserHistoryService;
import app.studymanager.shared.constants.HistoryResponsible;
import app.studymanager.shared.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserHistoryService userHistoryService;

    public UserService(UserRepository userRepository, UserHistoryService userHistoryService) {
        this.userRepository = userRepository;
        this.userHistoryService = userHistoryService;
    }

    @Transactional
    public User findByEmailOrCreate(String email) {
        logger.info(UserLogger.FIND_BY_EMAIL);
        try {
            User foundUser = userRepository.findByEmail(email);
            return foundUser != null ? foundUser : this.create(email);
        } catch (Exception exception) {
            logger.error(UserLogger.FIND_BY_EMAIL_ERROR);
            throw ExceptionUtil.handle(exception, UserLogger.FIND_BY_EMAIL_ERROR);
        }
    }

    @Transactional
    public User create(String email) {
        logger.info(UserLogger.CREATE);
        try {
            User user = new User();
            user.create(email);
            User createdUser = userRepository.save(user);
            userHistoryService.insert(createdUser, HistoryResponsible.SYSTEM, UserHistoryMessage.CREATE_USER);
            return createdUser;
        } catch (Exception exception) {
            logger.error(UserLogger.CREATE_ERROR);
            throw ExceptionUtil.handle(exception, UserLogger.CREATE_ERROR);
        }
    }
}
