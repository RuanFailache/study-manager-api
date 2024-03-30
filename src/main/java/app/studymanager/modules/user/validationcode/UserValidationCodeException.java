package app.studymanager.modules.user.validationcode;

import app.studymanager.shared.exception.HttpRequestException;
import app.studymanager.shared.exception.NotFoundException;
import app.studymanager.shared.exception.UnauthorizedException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserValidationCodeException {
    static HttpRequestException notFound() {
        return new NotFoundException("Código de validação do usuário não encontrado");
    }

    static HttpRequestException invalid() {
        return new UnauthorizedException("Código de validação do usuário inválido");
    }

    static HttpRequestException expired() {
        return new UnauthorizedException("Código de validação do usuário expirado");
    }
}
