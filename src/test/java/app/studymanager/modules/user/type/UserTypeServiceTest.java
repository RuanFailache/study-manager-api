package app.studymanager.modules.user.type;

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
public class UserTypeServiceTest {
    @InjectMocks
    private UserTypeServiceImpl sut;

    @Mock
    private UserTypeRepository userTypeRepository;

    @Test
    public void testFindOrThrowWhenTypeFound() {
        var expectedResult = new UserType();

        doReturn(Optional.of(expectedResult)).when(userTypeRepository).findByType(any(UserTypeEnum.class));

        var result = sut.findOrThrow(UserTypeEnum.BASIC);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindOrThrowWhenTypeNotFound() {
        doReturn(Optional.empty()).when(userTypeRepository).findByType(any(UserTypeEnum.class));

        assertThrows(NotFoundException.class, () -> sut.findOrThrow(UserTypeEnum.BASIC));
    }
}
