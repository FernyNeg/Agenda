package itsh.isic.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class AutenticationErrorHandler implements AuthenticationEntryPoint {
	private static final Logger log = LoggerFactory.getLogger(AutenticationErrorHandler.class);
	
private static final String clase="AutenticationErrorHandler: ";

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		log.error(clase+"ALERTA: Se ha intentado acceder al sistema sin autorizaciones"+ authException);
//		authException.printStackTrace();
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
	}

	@ExceptionHandler(value = { AccessDeniedException.class })
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException {
		System.out.println("Error 403");
		accessDeniedException.printStackTrace();
		response.sendError(HttpServletResponse.SC_FORBIDDEN,
				"Authorization Failed : " + accessDeniedException.getMessage());
	}

	@ExceptionHandler(value = { MissingServletRequestParameterException.class })
	public void commence(HttpServletRequest request, HttpServletResponse response,
			MissingServletRequestParameterException accessDeniedException) throws IOException {
		System.out.println("Error 400 MissingServletRequestParameterException");
		accessDeniedException.printStackTrace();
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	public void commence(HttpServletRequest request, HttpServletResponse response,
			IllegalArgumentException accessDeniedException) throws IOException {
		System.out.println("Error 400 IllegalArgumentException");
//	    	  accessDeniedException.printStackTrace();
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
	}

	@ExceptionHandler(value = { Exception.class })
	public void commence(HttpServletRequest request, HttpServletResponse response, Exception exception)
			throws IOException {
		System.out.println("Error 500");
		exception.printStackTrace();
		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
				"Internal Server Error : " + exception.getMessage());
	}

}
