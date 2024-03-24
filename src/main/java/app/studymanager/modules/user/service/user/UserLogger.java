package app.studymanager.modules.user.service.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLogger {
    public static final String FIND_BY_EMAIL = "Buscando o usuário com e-mail";
    public static final String FIND_BY_EMAIL_ERROR = "Falha ao buscar o usuário com e-mail";
    public static final String CREATE = "Criando o usuário";
    public static final String CREATE_ERROR = "Falha ao criando o usuário";
}
