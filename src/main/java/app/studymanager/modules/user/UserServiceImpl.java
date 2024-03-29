package app.studymanager.modules.user;

import app.studymanager.modules.user.history.UserHistoryMessage;
import app.studymanager.modules.user.history.UserHistoryService;
import app.studymanager.shared.constants.HistoryResponsible;
import app.studymanager.shared.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserHistoryService userHistoryService;

    private final UserFactory userFactory;

    @Transactional
    public User findOrCreateByEmail(String email) {
        log.info(UserLogger.FIND_BY_EMAIL);
        try {
            User foundUser = userRepository.findByEmail(email);
            return nonNull(foundUser) ? foundUser : this.create(email);
        } catch (Exception exception) {
            throw ExceptionUtil.handle(exception, UserLogger.FIND_BY_EMAIL_ERROR);
        }
    }

    @Transactional
    public User create(String email) {
        log.info(UserLogger.CREATE);
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
