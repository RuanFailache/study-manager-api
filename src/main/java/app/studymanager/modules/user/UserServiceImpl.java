package app.studymanager.modules.user;

import app.studymanager.modules.user.history.UserHistoryMessage;
import app.studymanager.modules.user.history.UserHistoryService;
import app.studymanager.shared.constants.HistoryResponsible;
import app.studymanager.shared.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserHistoryService userHistoryService;
    private final UserFactory userFactory;

    public UserServiceImpl(
            UserRepository userRepository,
            UserHistoryService userHistoryService,
            UserFactory userFactory
    ) {
        this.userRepository = userRepository;
        this.userHistoryService = userHistoryService;
        this.userFactory = userFactory;
    }

    @Transactional
    public User findOrCreateByEmail(String email) {
        logger.info(UserLogger.FIND_BY_EMAIL);
        try {
            User foundUser = userRepository.findByEmail(email);
            return nonNull(foundUser) ? foundUser : this.create(email);
        } catch (Exception exception) {
            throw ExceptionUtil.handle(exception, UserLogger.FIND_BY_EMAIL_ERROR);
        }
    }

    @Transactional
    public User create(String email) {
        logger.info(UserLogger.CREATE);
        try {
            User createdUser = userFactory.create(email);
            User savedUser = userRepository.save(createdUser);
            userHistoryService.insert(savedUser, HistoryResponsible.SYSTEM, UserHistoryMessage.CREATE_USER);
            return savedUser;
        } catch (Exception exception) {
            throw ExceptionUtil.handle(exception, UserLogger.CREATE_ERROR);
        }
    }
}
