package app.studymanager.modules.user;

import app.studymanager.shared.exception.NotFoundException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl sut;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserFactory userFactory;

    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
    }

    @Test
    public void testFindOrThrowByEmailWhenUserNotFound() {
        String testEmail = faker.internet().emailAddress();

        doReturn(Optional.empty()).when(userRepository).findByEmail(testEmail);

        assertThrows(NotFoundException.class, () -> sut.findOrThrowByEmail(testEmail));
    }

    @Test
    public void testFindOrThrowByEmailWhenUserFound() {
        String testEmail = faker.internet().emailAddress();

        User expectedUser = new User();

        doReturn(Optional.of(expectedUser)).when(userRepository).findByEmail(testEmail);

        var result = sut.findOrThrowByEmail(testEmail);

        assertNotNull(result);
        assertEquals(expectedUser, result);
    }

    @Test
    public void testFindByEmailWhenUserNotFound() {
        String testEmail = faker.internet().emailAddress();

        doReturn(Optional.empty()).when(userRepository).findByEmail(testEmail);

        var result = sut.findByEmail(testEmail);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindByEmailWhenUserFound() {
        String testEmail = faker.internet().emailAddress();

        User expectedUser = new User();

        doReturn(Optional.of(expectedUser)).when(userRepository).findByEmail(testEmail);

        var result = sut.findByEmail(testEmail);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(expectedUser, result.get());
    }

    @Test
    public void testCreate() {
        var expectedResult = new User();

        String testEmail = faker.internet().emailAddress();

        doReturn(expectedResult).when(userFactory).create(anyString());
        doReturn(expectedResult).when(userRepository).save(any(User.class));

        var result = sut.create(testEmail);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }
}
