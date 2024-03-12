package ma.adria.document_validation.gateway.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequest {
	@Email(message = "invalid email address")
	private String email;
	@NotNull @NotBlank
	private String password;
}
