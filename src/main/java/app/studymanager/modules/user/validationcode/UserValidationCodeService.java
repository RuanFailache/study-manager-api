package app.studymanager.modules.user.validationcode;

import app.studymanager.modules.user.User;

public interface UserValidationCodeService {
    UserValidationCode create(User user);

    void validate(User user, String code);
}
