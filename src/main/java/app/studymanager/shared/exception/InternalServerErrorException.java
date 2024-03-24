package app.studymanager.shared.exception;


import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends HttpRequestException {
    public InternalServerErrorException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
