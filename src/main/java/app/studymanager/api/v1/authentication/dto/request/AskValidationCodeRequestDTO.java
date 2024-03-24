package app.studymanager.api.v1.authentication.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AskValidationCodeRequestDTO {
    @NotBlank
    @Email
    @Schema(defaultValue = "user.name@host.com")
    private String email;
}
