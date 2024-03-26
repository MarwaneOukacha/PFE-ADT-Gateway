package ma.adria.document_validation.gateway.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequestApp {
    private String codeapp;
    private String secret;
}
