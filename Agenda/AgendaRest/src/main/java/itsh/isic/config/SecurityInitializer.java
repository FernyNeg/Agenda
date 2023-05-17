package itsh.isic.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityInitializer() {
		super(AppConfig.class, SecurityConfig.class, HttpSessionConfig.class, WebConfig.class);
	}

}
