package ma.adria.document_validation.gateway.services;

import org.springframework.http.ResponseEntity;

import ma.adria.document_validation.gateway.dto.LoginRequest;
import ma.adria.document_validation.gateway.dto.authentificationResponseDto;

public interface authentificationService {

	ResponseEntity<authentificationResponseDto> authenticateUser(LoginRequest loginRequest);

}
