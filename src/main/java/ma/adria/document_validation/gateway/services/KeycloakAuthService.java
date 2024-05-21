package ma.adria.document_validation.gateway.services;
import ma.adria.document_validation.gateway.dto.LogOutDTO;
import ma.adria.document_validation.gateway.dto.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ma.adria.document_validation.gateway.dto.authentificationResponseDto;

@Service
public class KeycloakAuthService {
	@Value("${app.key_cloak.auth}")
	private String keycloakUrl;
    @Value("${app.key_cloak.logout}")
    private String keycloakLogoutURL;
    @Value("${app.key_cloak.client_id}")
    private String client_id;
    @Value("${app.key_cloak.client_secret}")
    private String client_secret;
    
    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<authentificationResponseDto> authenticateUser(String email, String password) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String requestBody = "grant_type=password&username=" + email + "&password=" + password + "&client_id="+client_id+"&client_secret="+client_secret;
        
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<authentificationResponseDto> response = restTemplate.postForEntity(keycloakUrl, request, authentificationResponseDto.class);

        return response;
    }
    public ResponseEntity<String> getAccessToken(RefreshToken refreshToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String requestBody = "grant_type=refresh_token&refresh_token=" + refreshToken.getRefreshToken() + "&client_id="+client_id+"&client_secret="+client_secret;
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response =restTemplate.postForEntity(keycloakUrl, request,String.class);

        return response;
    }
    public void logOut(LogOutDTO logout) {
        if(logout.getRefreshToken()!=null && !logout.getRefreshToken().equals("")){
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            if( logout.getCodeapp()!=null && logout.getSecret()!=null && !logout.getCodeapp().equals("") && !logout.getSecret().equals("")){
                try{
                    String requestBody = "refresh_token=" + logout.getRefreshToken() + "&client_id="+logout.getCodeapp()+"&client_secret="+logout.getSecret();
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
                    restTemplate.postForEntity(keycloakLogoutURL, request,void.class);
                }catch (Exception ex){
                    throw new RuntimeException("Exception: "+ex.getMessage());
                }
            }else {
                try{
                    String requestBody = "refresh_token=" + logout.getRefreshToken() + "&client_id="+client_id+"&client_secret="+client_secret;
                    HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
                    restTemplate.postForEntity(keycloakLogoutURL, request,void.class);
                }catch (Exception ex){
                    throw new RuntimeException("Exception: "+ex.getMessage());
                }
            }

        }
    }
    public ResponseEntity<authentificationResponseDto> authenticateClientApp(String codeapp,String secret){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String requestBody = "grant_type=client_credentials&client_id="+codeapp+"&client_secret="+secret;

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<authentificationResponseDto> response = restTemplate.postForEntity(keycloakUrl, request, authentificationResponseDto.class);

        return response;
    }


}
