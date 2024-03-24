package app.studymanager.modules.user.repository;

import app.studymanager.modules.user.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}
