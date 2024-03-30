package app.studymanager.shared.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HttpRequestException {
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
