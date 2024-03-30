package app.studymanager.shared.service.mail;

import app.studymanager.shared.exception.InternalServerErrorException;
import com.github.javafaker.Faker;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class MailServiceTest {
    @InjectMocks
    private MailServiceImpl sut;

    @Mock
    private MailProperties properties;

    @Mock
    private SendGrid sendGrid;

    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
    }

    @Test
    public void testSend() throws IOException {
        Response expectedResponse = new Response();
        expectedResponse.setStatusCode(202);

        String to = faker.internet().emailAddress();
        String subject = faker.lorem().sentence();
        String text = faker.lorem().paragraph();

        doReturn(expectedResponse).when(sendGrid).api(any(Request.class));

        sut.send(to, subject, text);
    }

    @Test
    public void testSendOnInvalidStatusCode() throws IOException {
        Response expectedResponse = new Response();
        expectedResponse.setStatusCode(400);

        String to = faker.internet().emailAddress();
        String subject = faker.lorem().sentence();
        String text = faker.lorem().paragraph();

        doReturn(expectedResponse).when(sendGrid).api(any(Request.class));

        assertThrows(InternalServerErrorException.class, () -> sut.send(to, subject, text));
    }
}
