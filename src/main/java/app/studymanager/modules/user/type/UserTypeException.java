package app.studymanager.modules.user.type;

import app.studymanager.shared.exception.HttpRequestException;
import app.studymanager.shared.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserTypeException {
    static HttpRequestException notFound() {
        return new NotFoundException("Tipo do usuário não encontrado");
    }
}
