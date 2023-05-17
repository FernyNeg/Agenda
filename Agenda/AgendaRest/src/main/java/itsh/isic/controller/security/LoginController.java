package itsh.isic.controller.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import itsh.isic.constantes.Constantes;
import itsh.isic.constantes.UrlConstantes;
import itsh.isic.exception.UsuarioBloqueadoException;
import itsh.isic.exception.ServiciosException;
import itsh.isic.models.UsuarioModel;
import itsh.isic.models.security.UsuarioSesion;
import itsh.isic.service.security.LoginService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/public/security")
public class LoginController {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UsuarioSesion sesion;

	@Autowired
	private LoginService login;

	@RequestMapping(value = UrlConstantes.LOGIN, method = RequestMethod.POST)
	@ResponseBody
	public UsuarioModel login(@RequestBody UsuarioModel user, final HttpServletResponse response)
			throws ServiciosException {

//		this.binnacleBusiness.save(LoggingUtils.createBinnacleForLogging(
//				"Intento de Logueo del Usuario: " + user.getUsername(), this.session, LogCategoryEnum.TRY));
		try {
			user = this.login.validateUserCredentials(user.getApodo(), user.getContrasenia());
			this.setUserProfileAndSession(user);
//			this.binnacleBusiness
//					.save(LoggingUtils.createBinnacleForLogging("Logueo del Usuario", this.session, LogCategoryEnum.LOGIN));
			return user;
		} catch (UsuarioBloqueadoException businessException) {
//			this.binnacleBusiness.save(LoggingUtils.createBinnacleForLogging(
//					"Usuario: " + user.getUsername() + ". Bloqueado.", this.session, LogCategoryEnum.BLOCK));
			response.setHeader(Constantes.HEADER_STATUS, Constantes.COD_STATUS_ERROR);
			response.setHeader(Constantes.HEADER_MESSAGE, businessException.getMessage());
		} catch (ServiciosException businessException) {
			log.error(businessException.getMessage(), businessException);
			response.setHeader(Constantes.HEADER_STATUS, Constantes.COD_STATUS_ERROR);
			response.setHeader(Constantes.HEADER_MESSAGE, businessException.getMessage());
		}
		return new UsuarioModel();
	}

	private void setUserProfileAndSession(final UsuarioModel user) throws ServiciosException {
		user.setContrasenia(Constantes.BLANK);
		user.setApodo(Constantes.BLANK);
//    user.setProfileList(this.userBusiness.findProfilesByidUser(user.getId()));
		this.sesion.setIdUser(user.getId());
		this.sesion.setUser(user);
	}

}
