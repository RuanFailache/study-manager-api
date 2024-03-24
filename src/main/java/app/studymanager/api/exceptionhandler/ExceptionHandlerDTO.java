package app.studymanager.api.exceptionhandler;

import app.studymanager.shared.exception.HttpRequestException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Getter
public class ExceptionHandlerDTO {
    private final Integer status;
    private final String message;
    private final OffsetDateTime timestamp;

    public ExceptionHandlerDTO(Exception exception) {
        this.message = exception.getMessage();
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.timestamp = OffsetDateTime.now();
    }

    public ExceptionHandlerDTO(HttpRequestException exception) {
        this.message = exception.getMessage();
        this.status = exception.getStatus().value();
        this.timestamp = OffsetDateTime.now();
    }
}
