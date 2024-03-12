package ma.adria.document_validation.gateway.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@Configuration
//@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Mohamed Benmansour", email = "	mohamed.benmansour@adria-bt.com"), title = "ADT-Documents-validation Gateway", version = "V1"))
public class SwaggerConfig implements WebMvcConfigurer{

	 @Bean
	    public OpenAPI springShopOpenAPI() {
	        return new OpenAPI();
	                
	    }
}
