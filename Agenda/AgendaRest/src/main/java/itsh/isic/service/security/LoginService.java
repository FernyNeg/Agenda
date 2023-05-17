package itsh.isic.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import itsh.isic.dao.login.LoginDao;
import itsh.isic.enums.MsjEnum;
import itsh.isic.enums.NumerosEnum;
import itsh.isic.enums.EstatusUsuarioEnum;
import itsh.isic.exception.UsuarioBloqueadoException;
import itsh.isic.exception.ServiciosException;
import itsh.isic.exception.BaseDeDatosException;
import itsh.isic.exception.DesencriptadoException;
import itsh.isic.exception.ResultadosVaciosException;
import itsh.isic.exception.CredencialesInvalidasException;
import itsh.isic.models.UsuarioModel;
import itsh.isic.utilidades.Desenciptador;
import itsh.isic.utilidades.SeguridadService;

@Service
public class LoginService {
	private static final Logger log = LoggerFactory.getLogger(LoginService.class);

	private static final String clase = "LoginService: ";
	private static final Integer CERO_INTENTES_EN_LOGIN = 0;

	@Autowired
	@Lazy
	private LoginDao login;

	public UsuarioModel validateUserCredentials(final String username, final String password)
			throws ServiciosException, UsuarioBloqueadoException {
		UsuarioModel usuario = new UsuarioModel();
		try {
			final String apodoEncript = this.encryptUsername(username);
			this.validateIsNoBlockedUser(apodoEncript, usuario.getCorreo());
			usuario = this.getUsuarioParaSesion(apodoEncript, usuario.getCorreo());
			this.validateCredentials(apodoEncript, usuario.getCorreo(),
					this.signOn(password, usuario.getContrasenia(), usuario.getEstatus()));
			this.login.registraIntento(usuario.getId(), CERO_INTENTES_EN_LOGIN);
			log.info(clase + "Inicio de sesión del usuario: " + usuario.getNomCompleto() + ", con Id: " + usuario.getId());
			return usuario;
		} catch (BaseDeDatosException | DesencriptadoException databaseException) {
			log.error(clase + MsjEnum.ERROR_VALIDANDO_CREDENTIALES.getDescripcion() + ": " + username, databaseException);
			throw new ServiciosException(MsjEnum.ERROR_VALIDANDO_CREDENTIALES.getDescripcion(),
					databaseException.getMessage());
		} catch (CredencialesInvalidasException | ResultadosVaciosException invalidCredentialsException) {
			this.registerInvalidAttempt(usuario, invalidCredentialsException);
			throw new ServiciosException(MsjEnum.CREDENCIALES_INVALIDAS.getDescripcion(),
					invalidCredentialsException.getMessage());
		}
	}

	private boolean isInvalidCredentials(final String apodo, final String correo, final String contrasenia)
			throws BaseDeDatosException {
		return this.login.getContraseniaPorApodo(apodo, correo, contrasenia) != NumerosEnum.UNO.getNumero();
	}

	private void validateCredentials(final String username, final String correo, final String password)
			throws BaseDeDatosException, CredencialesInvalidasException {
		if (this.isInvalidCredentials(username, correo, password))
			throw new CredencialesInvalidasException(MsjEnum.CREDENCIALES_INVALIDAS.getDescripcion());
	}

	private String signOn(final String pass, final String salt, final EstatusUsuarioEnum status)
			throws ServiciosException {
		log.info("Login:Validando Datos Password");
		if (status == EstatusUsuarioEnum.ACTIVO)
			return SeguridadService.encrypt(pass, Base64Coder.encodeString(salt));
		throw new ServiciosException(MsjEnum.CREDENCIALES_INVALIDAS.getDescripcion(), "");
	}

	private void registerInvalidAttempt(final UsuarioModel user, final Exception invalidCredentialsException)
			throws ServiciosException, UsuarioBloqueadoException {
		log.error(invalidCredentialsException.getMessage());
		this.registerAttemptWhenInvalidCredentials(invalidCredentialsException, user);
	}

	private void registerAttemptWhenInvalidCredentials(final Exception exception, final UsuarioModel user)
			throws ServiciosException, UsuarioBloqueadoException {
		if (exception instanceof CredencialesInvalidasException)
			this.setIntentoLoginFallo(user);
	}

	private void setIntentoLoginFallo(final UsuarioModel user) throws ServiciosException, UsuarioBloqueadoException {
//		try {
//			user.setNumIntentos(user.getNumIntentos() + 1);
//			log.info("Registrando acceso fallido para el usuario con id: " + user.getId() + ". Intento número: "
//					+ user.getNumIntentos());
//			final Integer maxIntentos = Integer.parseInt(this.configurable.findByName("LOGIN_RETRIES"));
//			this.userable.registraIntento(user.getId(), user.getNumIntentos());
//			if (user.getNumIntentos() >= maxIntentos) {
//				this.userable.changeUserStatus(user.getId(), StatusContactoEnum.BLOQUEADO);
//				throw new BlockedUserException(MsjEnum.MESSAGE_BLOCLED_USER_ERROR.getDescripcion());
//			}
//		} catch (DatabaseException databaseException) {
//			throw new BusinessException("Hubo un problema al registrar el reintento de login",
//					databaseException.getMessage());
//		}
	}

	private UsuarioModel getUsuarioParaSesion(final String username, final String correo)
			throws BaseDeDatosException, ResultadosVaciosException, DesencriptadoException {
		return this.login.getUsuarioParaSesionCorreoOrUser(username, correo);
	}

	private void validateIsNoBlockedUser(final String userNameEncripted, final String correo)
			throws UsuarioBloqueadoException, BaseDeDatosException {
		if (this.login.estaBloqueadoUsuario(userNameEncripted, correo))
			throw new UsuarioBloqueadoException(MsjEnum.MESSAGE_BLOCLED_USER_ERROR.getDescripcion());
	}

	private String encryptUsername(final String username) throws DesencriptadoException {
		final Desenciptador encryptor = new Desenciptador();
		return encryptor.encrypt(username);
	}

}
