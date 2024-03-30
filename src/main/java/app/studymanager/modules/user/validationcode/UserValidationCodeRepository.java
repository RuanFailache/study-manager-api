package app.studymanager.modules.user.validationcode;

import app.studymanager.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserValidationCodeRepository extends JpaRepository<UserValidationCode, Long> {
    Optional<UserValidationCode> findByUser(User user);

    void deleteByUser(User user);
}
