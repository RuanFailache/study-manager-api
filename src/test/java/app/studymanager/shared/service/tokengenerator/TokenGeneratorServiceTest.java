package app.studymanager.shared.service.tokengenerator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TokenGeneratorServiceTest {
    @InjectMocks
    private TokenGeneratorServiceImpl sut;

    @Test
    public void testGenerateToken() {
        String result = sut.generateValidationCode();

        assertNotNull(result);
        assertEquals(6, result.length());
    }
}
