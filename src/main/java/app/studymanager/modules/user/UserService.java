package app.studymanager.modules.user;

public interface UserService {
    User findOrCreateByEmail(String email);

    User create(String email);
}
