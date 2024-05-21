package ma.adria.document_validation.gateway.services;

import ma.adria.document_validation.gateway.dto.*;
import org.springframework.http.ResponseEntity;

public interface authentificationService {

	ResponseEntity<authentificationResponseDto> authenticateUser(LoginRequest loginRequest);
	public ResponseEntity<authentificationResponseDto> authenticateClientApp(String codeApp, String secret);

    void logout(LogOutDTO refreshToken);

	void logoutClientApp(LogoutApplicationRequestDTO request);

	ResponseEntity<String> getAccessToken(RefreshToken logout);
}
