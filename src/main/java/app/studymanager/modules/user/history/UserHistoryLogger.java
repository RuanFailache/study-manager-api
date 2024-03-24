package app.studymanager.modules.user.history;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserHistoryLogger {
    static final String INSERT = "Inserindo informações do usuário no histórico";
    static final String INSERT_ERROR = "Falha ao inserir informações no histórico";
}
