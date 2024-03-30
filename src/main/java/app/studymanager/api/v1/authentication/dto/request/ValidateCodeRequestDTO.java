package app.studymanager.api.v1.authentication.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ValidateCodeRequestDTO {
    @NotBlank
    @Email
    @Schema(defaultValue = "user.name@host.com")
    private String email;

    @NotBlank
    @Size(min = 6, max = 6)
    @Schema(defaultValue = "123456")
    private String code;
}
