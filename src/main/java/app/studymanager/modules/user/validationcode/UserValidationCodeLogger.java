package app.studymanager.modules.user.validationcode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserValidationCodeLogger {
    static final String CREATE = "Criando um código de validação para o usuário";
    static final String CREATE_ERROR = "Falha ao criar um código de validação para o usuário";
}
