package app.studymanager.modules.user.validationcode;

import app.studymanager.modules.user.User;
import app.studymanager.shared.service.tokengenerator.TokenGeneratorService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserValidationCodeFactoryTest {
    @InjectMocks
    private UserValidationCodeFactory sut;

    @Mock
    private TokenGeneratorService tokenGeneratorService;

    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
    }

    @Test
    public void testCreate() {
        User testUser = new User();

        String expectedCode = faker.number().digits(6);

        doReturn(expectedCode).when(tokenGeneratorService).generateValidationCode();

        var result = sut.create(testUser);

        assertEquals(expectedCode, result.getCode());
        assertEquals(testUser, result.getUser());
        assertNotNull(result.getExpiresAt());
    }
}
