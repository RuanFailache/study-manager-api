package app.studymanager.domain.repository;

import app.studymanager.domain.model.User;
import app.studymanager.domain.model.UserValidationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserValidationCodeRepository extends JpaRepository<UserValidationCode, Long> {
}
