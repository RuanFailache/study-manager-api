package app.studymanager.modules.user.status;

public interface UserStatusService {
    UserStatus findOrThrow(UserStatusEnum status);
}
