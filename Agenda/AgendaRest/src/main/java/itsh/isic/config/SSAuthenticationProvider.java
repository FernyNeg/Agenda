package itsh.isic.config;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class SSAuthenticationProvider implements AuthenticationProvider {

//	@Autowired
//	private LoginBuss buss;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		System.out.println(username);
		System.out.println(password);
		try {
//    		if(buss.validaSession(username, password))
			return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
//    		else
//    			return new UsernamePasswordAuthenticationToken("usuario_invalido", "XXX", new ArrayList<>());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadCredentialsException("Error de Autenticacion");
		}
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}
