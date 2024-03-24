package app.studymanager.modules.user.type;

public interface UserTypeService {
    UserType findOrThrow(UserTypeEnum status);
}
