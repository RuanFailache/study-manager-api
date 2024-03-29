package app.studymanager.shared.util;

import app.studymanager.shared.exception.HttpRequestException;
import app.studymanager.shared.exception.InternalServerErrorException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

    public static HttpRequestException handle(Exception exception, String message) {
        if (exception instanceof HttpRequestException) {
            return (HttpRequestException) exception;
        }
        logger.error(message, exception);
        return new InternalServerErrorException(message);
    }
}
