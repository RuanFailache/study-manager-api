package app.studymanager.modules.user;

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

    public Optional<User> findByEmail(String email) {
        log.info("Buscando o usuário com e-mail");
        return userRepository.findByEmail(email);
    }

    public User findOrThrowByEmail(String email) {
        return findByEmail(email).orElseThrow(UserException::notFound);
    }

    @Transactional
    public User create(String email) {
        log.info("Criando o usuário");
        User createdUser = userFactory.create(email);
        return userRepository.save(createdUser);
    }
}
