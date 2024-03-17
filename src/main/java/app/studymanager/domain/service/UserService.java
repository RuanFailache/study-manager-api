package app.studymanager.domain.service;

import app.studymanager.domain.model.User;
import app.studymanager.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User findOrCreateByEmail(String email) {
        return userRepository.findByEmail(email).orElseGet(() -> {
            User user = new User();
            user.create(email);
            return userRepository.save(user);
        });
    }
}
