package app.studymanager.modules.user.validationcode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserValidationCodeLogger {
    static final String VALIDATE = "Validando o código de validação do usuário";
    static final String CREATE = "Criando um código de validação para o usuário";
}
