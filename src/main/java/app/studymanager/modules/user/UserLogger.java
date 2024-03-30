package app.studymanager.modules.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserLogger {
    static final String FIND_BY_EMAIL = "Buscando o usuário com e-mail";
    static final String CREATE = "Criando o usuário";
}
