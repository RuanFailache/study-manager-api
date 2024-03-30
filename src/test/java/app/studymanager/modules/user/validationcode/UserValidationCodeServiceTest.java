package app.studymanager.modules.user.validationcode;

import app.studymanager.modules.user.User;
import app.studymanager.shared.exception.NotFoundException;
import app.studymanager.shared.exception.UnauthorizedException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserValidationCodeServiceTest {
    @InjectMocks
    private UserValidationCodeServiceImpl sut;

    @Mock
    private UserValidationCodeRepository userValidationCodeRepository;

    @Mock
    private UserValidationCodeFactory userValidationCodeFactory;

    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
    }

    @Test
    public void testCreate() {
        UserValidationCode expectedResult = new UserValidationCode();

        doReturn(expectedResult).when(userValidationCodeFactory).create(any(User.class));
        doReturn(expectedResult).when(userValidationCodeRepository).save(expectedResult);

        var result = sut.create(new User());

        verify(userValidationCodeRepository).deleteByUser(any(User.class));

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testValidate() {
        User testUser = new User();
        String testCode = faker.number().digits(6);

        UserValidationCode testValidationCode = new UserValidationCode();
        testValidationCode.setCode(testCode);
        testValidationCode.setExpiresAt(OffsetDateTime.now().plusMinutes(3));

        doReturn(Optional.of(testValidationCode)).when(userValidationCodeRepository).findByUser(testUser);

        sut.validate(testUser, testCode);

        verify(userValidationCodeRepository).delete(testValidationCode);
    }

    @Test
    public void testValidateWhenNoValidationCodeFound() {
        User testUser = new User();
        String testCode = faker.number().digits(6);

        doReturn(Optional.empty()).when(userValidationCodeRepository).findByUser(testUser);

        assertThrows(NotFoundException.class, () -> sut.validate(testUser, testCode));
    }

    @Test
    public void testValidateWhenInvalidCode() {
        User testUser = new User();
        String testCode = faker.number().digits(6);

        UserValidationCode testValidationCode = new UserValidationCode();
        testValidationCode.setCode(faker.number().digits(6));

        doReturn(Optional.of(testValidationCode)).when(userValidationCodeRepository).findByUser(testUser);

        assertThrows(UnauthorizedException.class, () -> sut.validate(testUser, testCode));
    }

    @Test
    public void testValidateWhenExpiredCode() {
        User testUser = new User();
        String testCode = faker.number().digits(6);

        UserValidationCode testValidationCode = new UserValidationCode();
        testValidationCode.setCode(testCode);
        testValidationCode.setExpiresAt(OffsetDateTime.now().minusMinutes(10));

        doReturn(Optional.of(testValidationCode)).when(userValidationCodeRepository).findByUser(testUser);

        assertThrows(UnauthorizedException.class, () -> sut.validate(testUser, testCode));
    }
}
