package app.studymanager.api.v1.openapi;

import app.studymanager.api.v1.dto.request.AskValidationCodeRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Authentication")
public interface AuthenticationOpenApi {
    @Operation(
            summary = "Send an validation code and, if the user not exists, create one",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Validation code sent with success"),
                    @ApiResponse(responseCode = "400", description = "Sent email is invalid"),
            }
    )
    ResponseEntity<Void> sendValidationCode(@RequestBody(required = true) AskValidationCodeRequestDto dto);
}
