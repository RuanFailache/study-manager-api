package app.studymanager.modules.user.service;

import app.studymanager.modules.user.model.User;
import app.studymanager.modules.user.model.UserHistory;
import app.studymanager.modules.user.repository.UserHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserHistoryService {
    private final UserHistoryRepository userHistoryRepository;

    public UserHistoryService(UserHistoryRepository userHistoryRepository) {
        this.userHistoryRepository = userHistoryRepository;
    }

    @Transactional
    public void insert(User user, String responsible, String message) {
        UserHistory history = new UserHistory();
        history.insert(user, responsible, message);
        userHistoryRepository.save(history);
    }
}
