package app.studymanager.shared.service.tokengenerator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenGeneratorLogger {
    public static final String GENERATE_VALIDATION_CODE = "Gerando código de validação";
    public static final String GENERATE_VALIDATION_CODE_ERROR = "Falha ao gerar código de validação";
}
