package app.studymanager.shared.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TokenGeneratorService {
    public String generateValidationCode() {
        Random random = new Random();
        long codeAsNumber = random.nextLong(999999);
        return String.valueOf(codeAsNumber);
    }
}
