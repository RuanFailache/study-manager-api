package app.studymanager.modules.user.status;

import app.studymanager.shared.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserStatusException {
    static NotFoundException notFound() {
        return new NotFoundException("Status do usuário não encontrado");
    }
}
