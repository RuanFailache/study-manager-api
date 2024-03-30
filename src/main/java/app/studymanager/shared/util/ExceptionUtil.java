package app.studymanager.shared.util;

import app.studymanager.shared.exception.HttpRequestException;
import app.studymanager.shared.exception.InternalServerErrorException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil {
    public static HttpRequestException handle(Exception exception, String message) {
        if (exception instanceof HttpRequestException) {
            return (HttpRequestException) exception;
        }
        log.error(message, exception);
        return new InternalServerErrorException(message);
    }
}
