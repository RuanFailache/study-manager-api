package app.studymanager.api.v1.authentication;

import app.studymanager.api.v1.authentication.dto.request.AskValidationCodeRequestDTO;
import app.studymanager.modules.user.User;
import app.studymanager.modules.user.UserService;
import app.studymanager.modules.user.validationcode.UserValidationCode;
import app.studymanager.modules.user.validationcode.UserValidationCodeService;
import app.studymanager.shared.service.mail.templates.validationcode.ValidationCodeMailTemplate;
import app.studymanager.shared.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/authentication")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController implements AuthenticationOpenApi {
    private final UserService userService;

    private final UserValidationCodeService userValidationCodeService;

    private final ValidationCodeMailTemplate mailTemplate;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> sendValidationCode(@Valid @RequestBody AskValidationCodeRequestDTO dto) {
        log.info(AuthenticationLogger.SEND_VALIDATION_CODE);
        try {
            User user = userService.findOrCreateByEmail(dto.getEmail());
            UserValidationCode userValidationCode = userValidationCodeService.create(user);
            mailTemplate.sendValidationCode(user.getEmail(), userValidationCode.getCode());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception exception) {
            throw ExceptionUtil.handle(exception, AuthenticationLogger.SEND_VALIDATION_CODE_ERROR);
        }
    }
}
