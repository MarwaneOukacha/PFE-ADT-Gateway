package ma.adria.document_validation.gateway.services;

import ma.adria.document_validation.gateway.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ma.adria.document_validation.gateway.exceptions.PasswordException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class authentificationServiceV01 implements authentificationService {
	@Autowired
	private KeycloakAuthService authService;
	@Autowired
	private RestTemplate restTemplate;
	private ResponseEntity<authentificationResponseDto> response;
	@Override
	public ResponseEntity<authentificationResponseDto> authenticateUser(LoginRequest loginRequest) {
		authentificationResponseDto dto = new authentificationResponseDto();
		try {
			response = authService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
			if (response.getStatusCode().is2xxSuccessful()) {
				return response;
			}
		} catch (Exception e) {
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

	@Override
	public void logout(LogOutDTO logout) {
		authService.logOut(logout);
	}

	@Override
	public void logoutClientApp(LogoutApplicationRequestDTO request) {
		String codeapp = request.getApplicationCode() ;
		String Secret = getClientDetails(codeapp).getBody().getSecret();
		LogOutDTO dto=new LogOutDTO();
		dto.setCodeapp(codeapp);
		dto.setSecret(Secret);
		dto.setRefreshToken(request.getRefreshToken());
		authService.logOut(dto);
	}

	@Override
	public ResponseEntity<String> getAccessToken(RefreshToken refreshToken) {

        return authService.getAccessToken(refreshToken);
    }

	public ResponseEntity<ClientDetailsResponseDTO> getClientDetails(@PathVariable String codeApp) {
		String endpointUrl = "http://localhost:8282/clients/details/app/"+codeApp;
		ResponseEntity<ClientDetailsResponseDTO> response = restTemplate.getForEntity(endpointUrl, ClientDetailsResponseDTO.class);
		return response;
	}
}

