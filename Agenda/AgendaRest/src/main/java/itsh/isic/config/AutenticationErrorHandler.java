//package itsh.isic.config;
//
//import java.io.IOException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@ControllerAdvice
//public class AutenticationErrorHandler implements AuthenticationEntryPoint {
//
//	private static final Logger log = LoggerFactory.getLogger(AutenticationErrorHandler.class);
//
//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
//			throws IOException, ServletException {
//		log.error("Error 401: " + authException.getMessage());
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
//	}
//
//	@ExceptionHandler(value = { AccessDeniedException.class })
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			AccessDeniedException accessDeniedException) throws IOException {
//		log.error("Error 403: " + accessDeniedException.getMessage());
//		response.sendError(HttpServletResponse.SC_FORBIDDEN,
//				"Authorization Failed : " + accessDeniedException.getMessage());
//	}
//
//	@ExceptionHandler(value = { MissingServletRequestParameterException.class })
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			MissingServletRequestParameterException accessDeniedException) throws IOException {
//		log.error("Error 400 MissingServletRequestParameterException: " + accessDeniedException.getMessage());
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
//	}
//
//	@ExceptionHandler(value = { IllegalArgumentException.class })
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			IllegalArgumentException accessDeniedException) throws IOException {
//		log.error("Error 400 IllegalArgumentException: " + accessDeniedException.getMessage());
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
//	}
//
//	@ExceptionHandler(value = { Exception.class })
//	public void commence(HttpServletRequest request, HttpServletResponse response, Exception exception)
//			throws IOException {
//		log.error("Error 500: " + exception.getMessage());
//		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
//				"Internal Server Error : " + exception.getMessage());
//	}
//
//}
