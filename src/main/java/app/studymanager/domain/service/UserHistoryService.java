package app.studymanager.domain.service;

import app.studymanager.domain.model.User;
import app.studymanager.domain.model.UserHistory;
import app.studymanager.domain.repository.UserHistoryRepository;
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
