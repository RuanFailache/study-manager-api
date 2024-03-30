package app.studymanager.modules.user.validationcode;

import app.studymanager.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserValidationCodeRepository extends JpaRepository<UserValidationCode, Long> {
    UserValidationCode findByUser(User user);

    void deleteByUser(User user);
}
