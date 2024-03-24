package app.studymanager.api.v1.authentication;

import app.studymanager.api.v1.authentication.dto.request.AskValidationCodeRequestDto;
import app.studymanager.modules.user.User;
import app.studymanager.modules.user.UserService;
import app.studymanager.modules.user.validationcode.UserValidationCodeService;
import app.studymanager.shared.util.ExceptionUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/authentication")
public class AuthenticationController implements AuthenticationOpenApi {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final UserService userService;
    private final UserValidationCodeService userValidationCodeService;

    public AuthenticationController(
            UserService userService,
            UserValidationCodeService userValidationCodeService
    ) {
        this.userService = userService;
        this.userValidationCodeService = userValidationCodeService;
    }

    @PostMapping
    public ResponseEntity<Void> sendValidationCode(@Valid @RequestBody AskValidationCodeRequestDto dto) {
        logger.info(AuthenticationLogger.SEND_VALIDATION_CODE);
        try {
            User user = userService.findOrCreateByEmail(dto.getEmail());
            userValidationCodeService.create(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception exception) {
            logger.error(AuthenticationLogger.SEND_VALIDATION_CODE_ERROR);
            throw ExceptionUtil.handle(exception, AuthenticationLogger.SEND_VALIDATION_CODE_ERROR);
        }
    }
}
