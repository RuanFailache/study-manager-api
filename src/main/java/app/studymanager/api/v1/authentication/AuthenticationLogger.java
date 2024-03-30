package app.studymanager.api.v1.authentication;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationLogger {
    public static final String SEND_VALIDATION_CODE = "Enviando código de validação";
    public static final String VALIDATE = "Validando código de validação";
}
