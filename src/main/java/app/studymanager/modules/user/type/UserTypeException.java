package app.studymanager.modules.user.type;

import app.studymanager.shared.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserTypeException {
    static NotFoundException notFound() {
        return new NotFoundException("Tipo do usuário não encontrado");
    }
}
