package app.studymanager.modules.user;

import app.studymanager.modules.user.status.UserStatus;
import app.studymanager.modules.user.status.UserStatusEnum;
import app.studymanager.modules.user.status.UserStatusRepository;
import app.studymanager.modules.user.status.UserStatusServiceImpl;
import app.studymanager.shared.exception.InternalServerErrorException;
import app.studymanager.shared.exception.NotFoundException;
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
public class UserStatusServiceTest {
    @InjectMocks
    private UserStatusServiceImpl sut;

    @Mock
    private UserStatusRepository userStatusRepository;

    @Test
    public void testFindOrThrowWhenStatusFound() {
        var expectedResult = new UserStatus();

        doReturn(expectedResult).when(userStatusRepository).findByStatus(any(UserStatusEnum.class));

        var result = sut.findOrThrow(UserStatusEnum.NEW);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindOrThrowWhenStatusNotFound() {
        doReturn(null).when(userStatusRepository).findByStatus(any(UserStatusEnum.class));

        assertThrows(NotFoundException.class, () -> sut.findOrThrow(UserStatusEnum.NEW));
    }

    @Test
    public void testFindOrThrowWhenThrowsException() {
        doThrow(new SimulatedException()).when(userStatusRepository).findByStatus(any(UserStatusEnum.class));

        assertThrows(InternalServerErrorException.class, () -> sut.findOrThrow(UserStatusEnum.NEW));
    }
}
