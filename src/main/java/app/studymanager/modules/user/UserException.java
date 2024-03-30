package app.studymanager.modules.user;

import app.studymanager.shared.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserException {
    static NotFoundException notFound() {
        return new NotFoundException("Usuário não encontrado");
    }
}
