package app.studymanager.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
abstract public class HttpRequestException extends RuntimeException {
    private final HttpStatus status;

    protected HttpRequestException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
