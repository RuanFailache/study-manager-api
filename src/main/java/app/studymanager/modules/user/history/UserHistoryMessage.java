package app.studymanager.modules.user.history;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserHistoryMessage {
    public static final String CREATE_USER = "Usuário criado";
    public static final String CREATE_VALIDATION_CODE = "Usuário solicitou um código de validação";
}
