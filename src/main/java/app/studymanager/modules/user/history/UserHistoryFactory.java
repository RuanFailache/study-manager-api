package app.studymanager.modules.user.history;

import app.studymanager.modules.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserHistoryFactory {
    public UserHistory insert(User user, String responsible, String description) {
        UserHistory history = new UserHistory();
        history.setUser(user);
        history.setType(user.getType());
        history.setStatus(user.getStatus());
        history.setDescription(description);
        history.setResponsible(responsible);
        return history;
    }
}
