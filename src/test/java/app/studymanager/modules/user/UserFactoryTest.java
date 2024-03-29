package app.studymanager.modules.user;

import app.studymanager.modules.user.status.UserStatusEnum;
import app.studymanager.modules.user.status.UserStatusService;
import app.studymanager.modules.user.type.UserTypeEnum;
import app.studymanager.modules.user.type.UserTypeService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserFactoryTest {
    @InjectMocks
    private UserFactory sut;

    @Mock
    private UserStatusService userStatusService;

    @Mock
    private UserTypeService userTypeService;

    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
    }

    @Test
    public void testCreate() {
        String testEmail = faker.internet().emailAddress();

        User result = sut.create(testEmail);

        verify(userStatusService).findOrThrow(UserStatusEnum.NEW);
        verify(userTypeService).findOrThrow(UserTypeEnum.BASIC);

        assertNotNull(result);
        assertEquals(testEmail, result.getEmail());
    }
}
