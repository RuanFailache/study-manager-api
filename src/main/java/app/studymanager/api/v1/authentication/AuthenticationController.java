package app.studymanager.api.v1.authentication;

import app.studymanager.api.v1.authentication.dto.request.AskValidationCodeRequestDTO;
import app.studymanager.api.v1.authentication.dto.request.ValidateCodeRequestDTO;
import app.studymanager.api.v1.authentication.dto.response.CredentialsResponseDTO;
import app.studymanager.modules.user.User;
import app.studymanager.modules.user.UserService;
import app.studymanager.modules.user.history.UserHistoryMessage;
import app.studymanager.modules.user.history.UserHistoryService;
import app.studymanager.modules.user.validationcode.UserValidationCodeService;
import app.studymanager.shared.constants.HistoryResponsible;
import app.studymanager.shared.service.jwt.JwtService;
import app.studymanager.shared.service.mail.templates.validationcode.ValidationCodeMailTemplate;
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

    private final UserHistoryService userHistoryService;

    private final UserValidationCodeService userValidationCodeService;

    private final ValidationCodeMailTemplate mailTemplate;

    private final JwtService jwtService;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> sendValidationCode(@Valid @RequestBody AskValidationCodeRequestDTO requestDTO) {
        log.info("Enviando código de validação");
        var user = userService.findByEmail(requestDTO.getEmail()).orElseGet(() -> {
            User createdUser = userService.create(requestDTO.getEmail());
            userHistoryService.insert(createdUser, HistoryResponsible.SYSTEM, UserHistoryMessage.CREATE_USER);
            return createdUser;
        });
        var validationCode = userValidationCodeService.create(user);
        userHistoryService.insert(user, HistoryResponsible.SYSTEM, UserHistoryMessage.CREATE_VALIDATION_CODE);
        mailTemplate.sendValidationCode(user.getEmail(), validationCode.getCode());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("validate")
    @Transactional
    public ResponseEntity<CredentialsResponseDTO> validate(@Valid @RequestBody ValidateCodeRequestDTO requestDTO) {
        log.info("Validando código de validação");
        var user = userService.findOrThrowByEmail(requestDTO.getEmail());
        userValidationCodeService.validate(user, requestDTO.getCode());
        var token = jwtService.generate(user);
        userHistoryService.insert(user, HistoryResponsible.SYSTEM, UserHistoryMessage.LOGIN);
        var responseDTO = new CredentialsResponseDTO(token);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
