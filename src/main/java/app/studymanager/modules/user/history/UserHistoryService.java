package app.studymanager.modules.user.history;

import app.studymanager.modules.user.User;
import org.springframework.stereotype.Service;

@Service
public interface UserHistoryService {
    void insert(User user, String responsible, String message);
}
