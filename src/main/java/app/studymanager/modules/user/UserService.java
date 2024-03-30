package app.studymanager.modules.user;

import java.util.Optional;

public interface UserService {
    User findOrThrowByEmail(String email);

    Optional<User> findByEmail(String email);

    User create(String email);
}
