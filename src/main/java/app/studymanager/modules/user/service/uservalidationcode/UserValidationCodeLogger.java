package app.studymanager.modules.user.service.uservalidationcode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserValidationCodeLogger {
    public static final String CREATE = "Criando um código de validação para o usuário";
    public static final String CREATE_ERROR = "Falha ao criar um código de validação para o usuário";
}
