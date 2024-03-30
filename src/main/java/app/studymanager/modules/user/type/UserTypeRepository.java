package app.studymanager.modules.user.type;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    Optional<UserType> findByType(UserTypeEnum status);
}
