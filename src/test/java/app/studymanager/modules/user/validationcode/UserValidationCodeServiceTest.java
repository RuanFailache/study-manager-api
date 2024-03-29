package app.studymanager.modules.user.validationcode;

import app.studymanager.modules.user.User;
import app.studymanager.modules.user.history.UserHistoryService;
import app.studymanager.shared.exception.InternalServerErrorException;
import app.studymanager.utils.SimulatedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class UserValidationCodeServiceTest {
    @InjectMocks
    private UserValidationCodeServiceImpl sut;

    @Mock
    private UserHistoryService userHistoryService;

    @Mock
    private UserValidationCodeRepository userValidationCodeRepository;

    @Mock
    private UserValidationCodeFactory userValidationCodeFactory;

    @Test
    public void testCreate() {
        UserValidationCode expectedResult = new UserValidationCode();

        doReturn(expectedResult).when(userValidationCodeFactory).create(any(User.class));

        var result = sut.create(new User());

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCreateWhenThrowsException() {
        doThrow(new SimulatedException()).when(userValidationCodeFactory).create(any(User.class));

        assertThrows(InternalServerErrorException.class, () -> sut.create(new User()));
    }
}
