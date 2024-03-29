package app.studymanager.modules.user.history;

import app.studymanager.modules.user.User;
import app.studymanager.shared.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserHistoryServiceImpl implements UserHistoryService {
    private static final Logger logger = LoggerFactory.getLogger(UserHistoryService.class);

    private final UserHistoryRepository userHistoryRepository;

    public UserHistoryServiceImpl(UserHistoryRepository userHistoryRepository) {
        this.userHistoryRepository = userHistoryRepository;
    }

    @Transactional
    public void insert(User user, String responsible, String message) {
        logger.info(UserHistoryLogger.INSERT);
        try {
            UserHistory history = new UserHistory();
            history.insert(user, responsible, message);
            userHistoryRepository.save(history);
        } catch (Exception exception) {
            logger.error(UserHistoryLogger.INSERT_ERROR, exception);
            throw ExceptionUtil.handle(exception, UserHistoryLogger.INSERT_ERROR);
        }
    }
}
