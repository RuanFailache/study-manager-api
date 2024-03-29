package app.studymanager.shared.service.mail.templates.validationcode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationCodeMailLogger {
    public static final String DEVELOPMENT = "Enviando código {} para {}";
    public static final String SEND_VALIDATION_CODE = "Enviando código de validação para {}";
    public static final String SEND_VALIDATION_CODE_ERROR = "Falha ao enviar código de validação";
}
