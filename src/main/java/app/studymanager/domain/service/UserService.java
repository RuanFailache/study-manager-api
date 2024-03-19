package app.studymanager.domain.service;

import app.studymanager.domain.constants.HistoryResponsible;
import app.studymanager.domain.constants.UserHistoryMessage;
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
    public User findByEmailOrCreate(String email) {
        return userRepository.findByEmail(email).orElse(create(email));
    }

    @Transactional
    public User create(String email) {
        User user = new User();
        user.create(email);
        user.addHistory(HistoryResponsible.SYSTEM, UserHistoryMessage.CREATE_USER);
        return userRepository.save(user);
    }
}
