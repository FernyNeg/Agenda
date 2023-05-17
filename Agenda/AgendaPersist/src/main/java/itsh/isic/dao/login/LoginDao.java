package itsh.isic.dao.login;

import itsh.isic.exception.BaseDeDatosException;
import itsh.isic.exception.ResultadosVaciosException;
import itsh.isic.models.UsuarioModel;

public interface LoginDao {

	boolean estaBloqueadoUsuario(String usuario, String correo) throws BaseDeDatosException;

	UsuarioModel getUsuarioParaSesionCorreoOrUser(final String username, final String correo)
			throws ResultadosVaciosException, BaseDeDatosException;

	Integer getContraseniaPorApodo(final String username, final String correo, final String password)
			throws BaseDeDatosException;

	void registraIntento(final Integer idUser, final Integer retryNumber) throws BaseDeDatosException;

}
