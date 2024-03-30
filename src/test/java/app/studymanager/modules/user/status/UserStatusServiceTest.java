package app.studymanager.modules.user.status;

import app.studymanager.shared.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserStatusServiceTest {
    @InjectMocks
    private UserStatusServiceImpl sut;

    @Mock
    private UserStatusRepository userStatusRepository;

    @Test
    public void testFindOrThrowWhenStatusFound() {
        var expectedResult = new UserStatus();

        doReturn(Optional.of(expectedResult)).when(userStatusRepository).findByStatus(any(UserStatusEnum.class));

        var result = sut.findOrThrow(UserStatusEnum.NEW);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindOrThrowWhenStatusNotFound() {
        doReturn(Optional.empty()).when(userStatusRepository).findByStatus(any(UserStatusEnum.class));

        assertThrows(NotFoundException.class, () -> sut.findOrThrow(UserStatusEnum.NEW));
    }
}
