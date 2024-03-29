package app.studymanager.modules.user.history;

import app.studymanager.modules.user.User;
import app.studymanager.shared.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserHistoryServiceImpl implements UserHistoryService {
    private final UserHistoryFactory userHistoryFactory;

    private final UserHistoryRepository userHistoryRepository;

    @Transactional
    public void insert(User user, String responsible, String message) {
        log.info(UserHistoryLogger.INSERT);
        try {
            UserHistory history = userHistoryFactory.insert(user, responsible, message);
            userHistoryRepository.save(history);
        } catch (Exception exception) {
            throw ExceptionUtil.handle(exception, UserHistoryLogger.INSERT_ERROR);
        }
    }
}
