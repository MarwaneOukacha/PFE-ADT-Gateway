package ma.adria.document_validation.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig implements WebFluxConfigurer {

    private static final String[] PUBLIC_RESOURCES = {
            "/api-docs/**",
            "/swagger-ui/**",
            "/api/**",
            "/**"
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        return serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(Customizer.withDefaults())
                .authorizeExchange(exchange ->
                        exchange.pathMatchers(PUBLIC_RESOURCES).permitAll()
                                .anyExchange().authenticated()
                ).oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults())).build();
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "OPTIONS", "PATCH")
                .allowCredentials(false)
                .exposedHeaders(HttpHeaders.CONTENT_DISPOSITION)
        ;
    }
}
