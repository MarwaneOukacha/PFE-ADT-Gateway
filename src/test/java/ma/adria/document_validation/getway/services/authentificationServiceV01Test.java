package ma.adria.document_validation.getway.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;

import ma.adria.document_validation.gateway.dto.LoginRequest;
import ma.adria.document_validation.gateway.dto.authentificationResponseDto;
import ma.adria.document_validation.gateway.services.KeycloakAuthService;

public class authentificationServiceV01Test {
	@InjectMocks
	private KeycloakAuthService authService;
	@Test
	public ResponseEntity<authentificationResponseDto> authenticateUserTest() {
		LoginRequest loginRequest;
		authentificationResponseDto dto;
		authService.authenticateUser(null, null);
		
		return null;
	}
}
