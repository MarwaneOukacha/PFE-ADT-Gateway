package ma.adria.document_validation.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig{
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
		return serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
				.authorizeExchange(exchage->exchage.pathMatchers("/login/**","/swagger-ui/**","/api-docs/**","/api/**","/swagger-ui.html").permitAll()
				.anyExchange().authenticated())
				.oauth2ResourceServer((oauth)->oauth.jwt(Customizer.withDefaults())).build();
	}
	
}
