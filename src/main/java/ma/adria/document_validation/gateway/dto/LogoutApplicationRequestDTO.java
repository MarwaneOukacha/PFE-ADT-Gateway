package ma.adria.document_validation.gateway.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutApplicationRequestDTO {

    @NotEmpty
    @JsonAlias({"refreshToken", "refresh_token"})
    private String refreshToken;

    @NotEmpty
    private String applicationCode;

}
