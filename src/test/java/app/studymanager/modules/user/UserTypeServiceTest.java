package app.studymanager.modules.user;

import app.studymanager.modules.user.type.UserType;
import app.studymanager.modules.user.type.UserTypeEnum;
import app.studymanager.modules.user.type.UserTypeRepository;
import app.studymanager.modules.user.type.UserTypeServiceImpl;
import app.studymanager.shared.exception.NotFoundException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
