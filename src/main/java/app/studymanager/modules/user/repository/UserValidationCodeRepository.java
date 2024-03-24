package app.studymanager.modules.user.repository;

import app.studymanager.modules.user.model.UserValidationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserValidationCodeRepository extends JpaRepository<UserValidationCode, Long> {
}
