package app.studymanager.modules.user.service.userhistory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserHistoryLogger {
    public static final String INSERT = "Inserindo informações do usuário no histórico";
    public static final String INSERT_ERROR = "Falha ao inserir informações no histórico";
}
