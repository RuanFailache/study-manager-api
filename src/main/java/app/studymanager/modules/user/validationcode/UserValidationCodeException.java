package app.studymanager.modules.user.validationcode;

import app.studymanager.shared.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserValidationCodeException {
    static NotFoundException notFound() {
        return new NotFoundException("Código de validação do usuário não encontrado");
    }

    static NotFoundException invalid() {
        return new NotFoundException("Código de validação do usuário inválido");
    }

    static NotFoundException expired() {
        return new NotFoundException("Código de validação do usuário expirado");
    }
}
