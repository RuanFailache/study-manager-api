package app.studymanager.modules.user.type;

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
public class UserTypeServiceTest {
    @InjectMocks
    private UserTypeServiceImpl sut;

    @Mock
    private UserTypeRepository userTypeRepository;

    @Test
    public void testFindOrThrowWhenTypeFound() {
        var expectedResult = new UserType();

        doReturn(expectedResult).when(userTypeRepository).findByType(any(UserTypeEnum.class));

        var result = sut.findOrThrow(UserTypeEnum.BASIC);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFindOrThrowWhenTypeNotFound() {
        doReturn(null).when(userTypeRepository).findByType(any(UserTypeEnum.class));

        assertThrows(NotFoundException.class, () -> sut.findOrThrow(UserTypeEnum.BASIC));
    }

    @Test
    public void testFindOrThrowWhenThrowsException() {
        doThrow(new SimulatedException()).when(userTypeRepository).findByType(any(UserTypeEnum.class));

        assertThrows(InternalServerErrorException.class, () -> sut.findOrThrow(UserTypeEnum.BASIC));
    }
}
