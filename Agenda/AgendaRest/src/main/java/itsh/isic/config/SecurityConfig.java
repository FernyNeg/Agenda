package itsh.isic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests( //
				(authz) -> authz.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() //
						.requestMatchers("/*").authenticated() //
						.requestMatchers("/public/**").authenticated() //
						.requestMatchers("/controller/**").permitAll() //
						.requestMatchers("/admin/**").authenticated() //
						.requestMatchers("/controller/*.go").authenticated() //
						.requestMatchers("/controller/*.do").permitAll() //
		) //
				.httpBasic().and().exceptionHandling().authenticationEntryPoint(new AutenticationErrorHandler()); //
		return http.build();
	}

//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {

//				httpSecurity.csrf().disable()
//				.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//				.antMatchers("/*").permitAll()
//				.antMatchers("/swagger-ui/**").permitAll()
//				.antMatchers("/csws**").permitAll()
//				.antMatchers("/assets/**").permitAll()
//				.antMatchers("/v2/**").permitAll()
//				.antMatchers("/public/**").permitAll()
//				.antMatchers("/controller/**").permitAll()
//				.antMatchers("/admin/**").permitAll()
//				.antMatchers("/controller/*.go").permitAll()
//				.antMatchers("/controller/*.do").authenticated()
//				.and().httpBasic().and()
//				.exceptionHandling().authenticationEntryPoint(new AutenticationErrorHandler());
//	}

}
