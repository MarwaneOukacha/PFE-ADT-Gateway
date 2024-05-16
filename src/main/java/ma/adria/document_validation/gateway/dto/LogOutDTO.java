package ma.adria.document_validation.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogOutDTO {
    private String refreshToken;
    private String codeapp;
    private String secret;
}
