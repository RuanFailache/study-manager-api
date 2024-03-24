package app.studymanager.api.v1.controller;

import app.studymanager.api.v1.dto.request.AskValidationCodeRequestDto;
import app.studymanager.api.v1.openapi.AuthenticationOpenApi;
import app.studymanager.modules.user.model.User;
import app.studymanager.shared.service.TokenGeneratorService;
import app.studymanager.modules.user.service.UserService;
import app.studymanager.modules.user.service.UserValidationCodeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/authentication")
public class AuthenticationController implements AuthenticationOpenApi {
    private final UserService userService;
    private final UserValidationCodeService userValidationCodeService;
    private final TokenGeneratorService tokenGeneratorService;

    public AuthenticationController(UserService userService, UserValidationCodeService userValidationCodeService, TokenGeneratorService tokenGeneratorService) {
        this.userService = userService;
        this.userValidationCodeService = userValidationCodeService;
        this.tokenGeneratorService = tokenGeneratorService;
    }

    @PostMapping
    public ResponseEntity<Void> sendValidationCode(@Valid @RequestBody AskValidationCodeRequestDto dto) {
        User user = userService.findByEmailOrCreate(dto.getEmail());
        String validationCode = tokenGeneratorService.generateValidationCode();
        userValidationCodeService.create(user, validationCode);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
