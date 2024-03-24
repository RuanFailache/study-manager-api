package app.studymanager.modules.user.type;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserTypeLogger {
    static final String FIND_OR_THROW = "Buscando tipo do usuário";
    static final String FIND_OR_THROW_NOT_FOUND = "Tipo do usuário não encontrado";
    static final String FIND_OR_THROW_ERROR = "Falha ao buscar tipo do usuário";
}
