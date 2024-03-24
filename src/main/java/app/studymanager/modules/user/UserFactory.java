package app.studymanager.modules.user;

import app.studymanager.modules.user.status.UserStatusEnum;
import app.studymanager.modules.user.status.UserStatusService;
import app.studymanager.modules.user.type.UserTypeEnum;
import app.studymanager.modules.user.type.UserTypeService;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    private final UserStatusService userStatusService;
    private final UserTypeService userTypeService;

    public UserFactory(UserStatusService userStatusService, UserTypeService userTypeService) {
        this.userStatusService = userStatusService;
        this.userTypeService = userTypeService;
    }

    public User create(String email) {
        User user = new User();
        user.setEmail(email);
        user.setStatus(userStatusService.findOrThrow(UserStatusEnum.NEW));
        user.setType(userTypeService.findOrThrow(UserTypeEnum.BASIC));
        return user;
    }
}
