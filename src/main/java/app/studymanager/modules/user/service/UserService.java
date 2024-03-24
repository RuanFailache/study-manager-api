package app.studymanager.modules.user.service;

import app.studymanager.shared.constants.HistoryResponsible;
import app.studymanager.modules.user.constant.UserHistoryMessage;
import app.studymanager.modules.user.model.User;
import app.studymanager.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserHistoryService userHistoryService;

    public UserService(UserRepository userRepository, UserHistoryService userHistoryService) {
        this.userRepository = userRepository;
        this.userHistoryService = userHistoryService;
    }

    @Transactional
    public User findByEmailOrCreate(String email) {
        User foundUser = userRepository.findByEmail(email);
        return foundUser != null ? foundUser : this.create(email);
    }

    @Transactional
    public User create(String email) {
        User user = new User();
        user.create(email);
        User createdUser = userRepository.save(user);
        userHistoryService.insert(createdUser, HistoryResponsible.SYSTEM, UserHistoryMessage.CREATE_USER);
        return createdUser;
    }
}
