package ma.adria.document_validation.gateway.services;

import ma.adria.document_validation.gateway.dto.RefreshTokenDTO;
import org.springframework.http.ResponseEntity;

import ma.adria.document_validation.gateway.dto.LoginRequest;
import ma.adria.document_validation.gateway.dto.authentificationResponseDto;

public interface authentificationService {

	ResponseEntity<authentificationResponseDto> authenticateUser(LoginRequest loginRequest);
	public ResponseEntity<authentificationResponseDto> authenticateClientApp(String codeApp, String secret);

    void logout(RefreshTokenDTO refreshToken);
}
