package app.studymanager.modules.user;

import app.studymanager.modules.user.history.UserHistoryService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    private UserHistoryService userHistoryService;

    @Mock
    private UserFactory userFactory;

    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
    }

    @Test
    public void testCreate() {
        var expectedResult = new User();

        String testEmail = faker.internet().emailAddress();

        doReturn(new User()).when(userFactory).create(anyString());
        doReturn(expectedResult).when(userRepository).save(any(User.class));

        var result = sut.create(testEmail);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindOrCreateByEmailWhenUserNotFound() {
        var expectedResult = new User();

        String testEmail = faker.internet().emailAddress();

        doReturn(null).when(userRepository).findByEmail(testEmail);
        doReturn(any(User.class)).when(userFactory).create(anyString());
        doReturn(expectedResult).when(userRepository).save(any(User.class));

        var result = sut.findOrCreateByEmail(testEmail);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindOrCreateByEmailWhenUserFound() {
        String testEmail = faker.internet().emailAddress();

        User expectedUser = new User();

        doReturn(expectedUser).when(userRepository).findByEmail(testEmail);

        User result = sut.findOrCreateByEmail(testEmail);

        assertEquals(expectedUser, result);
    }
}
