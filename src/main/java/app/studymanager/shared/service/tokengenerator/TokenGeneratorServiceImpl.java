package app.studymanager.shared.service.tokengenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TokenGeneratorServiceImpl implements TokenGeneratorService {
    private static final Logger logger = LoggerFactory.getLogger(TokenGeneratorService.class);

    public String generateValidationCode() {
        Random random = new Random();
        long codeAsNumber = random.nextLong(999999);
        return String.valueOf(codeAsNumber);
    }
}
