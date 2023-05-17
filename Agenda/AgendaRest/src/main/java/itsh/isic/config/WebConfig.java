package itsh.isic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import itsh.isic.constantes.Constantes;

@Configuration
@EnableScheduling
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:4200", "*")
		.allowedMethods("*")
		.allowedHeaders(Constantes.HEADER_CONTET,
				Constantes.HEADER_RESQUESTWITH_KEY,
				Constantes.HEADER_TOKEN_AUT , 
				Constantes.HEADER_AUTHORIZACION_KEY,
				Constantes.HEADER_AUTH_KEY,
				Constantes.HEADER_STATUS)
		.exposedHeaders(Constantes.HEADER_TOKEN_AUT,
				Constantes.HEADER_STATUS)
		.allowCredentials(false).maxAge(3600);
	}
}
