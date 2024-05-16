package ma.adria.document_validation.gateway.web;
import ma.adria.document_validation.gateway.dto.LoginRequestApp;
import ma.adria.document_validation.gateway.dto.RefreshTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ma.adria.document_validation.gateway.dto.LoginRequest;
import ma.adria.document_validation.gateway.dto.authentificationResponseDto;
import ma.adria.document_validation.gateway.services.authentificationService;

@RestController
@Validated
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private authentificationService authService;

    @PostMapping("/login")
    public ResponseEntity<authentificationResponseDto> login(@Valid @RequestBody LoginRequest loginRequest) {
    	return authService.authenticateUser(loginRequest);
    }
    @PostMapping("/login/app")
    public ResponseEntity<authentificationResponseDto> loginApp(@Valid @RequestBody LoginRequestApp loginRequestApp) {
        return authService.authenticateClientApp(loginRequestApp.getCodeapp(),loginRequestApp.getSecret());
    }
    @PostMapping("/logout")
    public void logout(@Valid @RequestBody RefreshTokenDTO refreshToken) {
        authService.logout(refreshToken);
    }
}
