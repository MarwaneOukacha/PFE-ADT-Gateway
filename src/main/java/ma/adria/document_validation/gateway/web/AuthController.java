package ma.adria.document_validation.gateway.web;
import ma.adria.document_validation.gateway.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
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
    public ResponseEntity<Void>  logout(@Valid @RequestBody LogOutDTO logout) {
        authService.logout(logout);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    @PostMapping("/accessToken")
    public ResponseEntity<String>  getAccessToken(@Valid @RequestBody RefreshToken refreshToken) {
        return authService.getAccessToken(refreshToken);
    }
}
