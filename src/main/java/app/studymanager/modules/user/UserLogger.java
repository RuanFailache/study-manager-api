package app.studymanager.modules.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserLogger {
    static final String FIND_BY_EMAIL = "Buscando o usuário com e-mail";
    static final String FIND_BY_EMAIL_ERROR = "Falha ao buscar o usuário com e-mail";
    static final String CREATE = "Criando o usuário";
    static final String CREATE_ERROR = "Falha ao criar o usuário";
}
