package app.studymanager.modules.user;

import app.studymanager.shared.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserFactory userFactory;

    public User findOrThrowByEmail(String email) {
        log.info(UserLogger.FIND_BY_EMAIL);
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public Optional<User> findByEmail(String email) {
        log.info(UserLogger.FIND_BY_EMAIL);
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User create(String email) {
        log.info(UserLogger.CREATE);
        User createdUser = userFactory.create(email);
        return userRepository.save(createdUser);
    }
}
