package app.studymanager.api.v1.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AskValidationCodeRequestDto {
    @NotBlank
    @Email
    private String email;
}
