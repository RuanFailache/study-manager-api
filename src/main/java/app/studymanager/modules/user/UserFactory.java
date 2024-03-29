package app.studymanager.modules.user;

import app.studymanager.modules.user.status.UserStatusEnum;
import app.studymanager.modules.user.status.UserStatusService;
import app.studymanager.modules.user.type.UserTypeEnum;
import app.studymanager.modules.user.type.UserTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {
    private final UserStatusService userStatusService;

    private final UserTypeService userTypeService;

    public User create(String email) {
        User user = new User();
        user.setEmail(email);
        user.setStatus(userStatusService.findOrThrow(UserStatusEnum.NEW));
        user.setType(userTypeService.findOrThrow(UserTypeEnum.BASIC));
        return user;
    }
}
