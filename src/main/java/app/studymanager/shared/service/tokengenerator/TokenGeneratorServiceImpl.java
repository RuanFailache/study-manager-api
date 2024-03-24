package app.studymanager.shared.service.tokengenerator;

import app.studymanager.shared.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TokenGeneratorServiceImpl implements TokenGeneratorService {
    private static final Logger logger = LoggerFactory.getLogger(TokenGeneratorServiceImpl.class);

    public String generateValidationCode() {
        logger.info(TokenGeneratorLogger.GENERATE_VALIDATION_CODE);
        try {
            Random random = new Random();
            long codeAsNumber = random.nextLong(999999);
            return String.valueOf(codeAsNumber);
        } catch (Exception exception) {
            logger.error(TokenGeneratorLogger.GENERATE_VALIDATION_CODE_ERROR);
            throw ExceptionUtil.handle(exception, TokenGeneratorLogger.GENERATE_VALIDATION_CODE_ERROR);
        }
    }
}
