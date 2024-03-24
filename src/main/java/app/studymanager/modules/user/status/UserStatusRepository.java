package app.studymanager.modules.user.status;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatusRepository  extends JpaRepository<UserStatus, Long> {
    UserStatus findByStatus(UserStatusEnum status);
}
