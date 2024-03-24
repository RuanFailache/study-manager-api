package app.studymanager.api.exceptionhandler;

import app.studymanager.shared.exception.HttpRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionHandlerDTO> handler(Exception exception) {
        ExceptionHandlerDTO dto = new ExceptionHandlerDTO(exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
    }

    @ExceptionHandler(HttpRequestException.class)
    public ResponseEntity<ExceptionHandlerDTO> handler(HttpRequestException exception) {
        ExceptionHandlerDTO dto = new ExceptionHandlerDTO(exception);
        return ResponseEntity.status(exception.getStatus()).body(dto);
    }
}
