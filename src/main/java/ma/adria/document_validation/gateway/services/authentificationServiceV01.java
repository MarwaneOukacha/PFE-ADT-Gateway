package ma.adria.document_validation.gateway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ma.adria.document_validation.gateway.dto.LoginRequest;
import ma.adria.document_validation.gateway.dto.authentificationResponseDto;
import ma.adria.document_validation.gateway.exceptions.PasswordException;

@Service
public class authentificationServiceV01 implements authentificationService {
	@Autowired
	private KeycloakAuthService authService;
	private ResponseEntity<authentificationResponseDto> response;
	@Override
	public ResponseEntity<authentificationResponseDto> authenticateUser(LoginRequest loginRequest) {
		authentificationResponseDto dto = new authentificationResponseDto();
		try {
			// Appel de la méthode d'authentification
			response = authService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
			// Traiter la réponse de Keycloak
			if (response.getStatusCode().is2xxSuccessful()) {
				return response;
			}
		} catch (Exception e) {
			// Gérer les cas d'échec d'authentification
			throw new PasswordException("Une erreur s'est produite lors de l'authentification");
		}
		return null;
	}

	@Override
	public ResponseEntity<authentificationResponseDto> authenticateClientApp(String codeApp, String secret) {
		try {
			// Appel de la méthode d'authentification
			response = authService.authenticateClientApp(codeApp, secret);
			// Traiter la réponse de Keycloak
			if (response.getStatusCode().is2xxSuccessful()) {
				return response;
			}
		} catch (Exception e) {
			// Gérer les cas d'échec d'authentification
			throw new PasswordException("Une erreur s'est produite lors de l'authentification");
		}
		return null;
	}
}
