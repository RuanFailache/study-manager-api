package app.studymanager.modules.user.status;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserStatusLogger {
    static final String FIND_OR_THROW = "Buscando status do usuário";
    static final String FIND_OR_THROW_NOT_FOUND = "Status do usuário não encontrado";
    static final String FIND_OR_THROW_ERROR = "Falha ao buscar status do usuário";
}
