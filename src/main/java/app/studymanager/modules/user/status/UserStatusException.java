package app.studymanager.modules.user.status;

import app.studymanager.shared.exception.HttpRequestException;
import app.studymanager.shared.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserStatusException {
    static HttpRequestException notFound() {
        return new NotFoundException("Status do usuário não encontrado");
    }
}
