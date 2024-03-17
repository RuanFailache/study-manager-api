package app.studymanager.domain.listener;

import app.studymanager.domain.constants.HistoryResponsible;
import app.studymanager.domain.event.CreatedUserEvent;
import app.studymanager.domain.model.UserHistory;
import app.studymanager.domain.repository.UserHistoryRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserHistoryListener {
    private final UserHistoryRepository userHistoryRepository;

    public UserHistoryListener(UserHistoryRepository userHistoryRepository) {
        this.userHistoryRepository = userHistoryRepository;
    }

    @EventListener
    public void onCreateUser(CreatedUserEvent event) {
        UserHistory history = new UserHistory();
        history.insert(event.getUser(), HistoryResponsible.SYSTEM, "Conta criada com sucesso");
        userHistoryRepository.save(history);
    }
}
