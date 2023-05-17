package itsh.isic.daoimpl.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import itsh.isic.constantes.tablas.TblConstUsuario;
import itsh.isic.dao.connec.ConnectDao;
import itsh.isic.dao.login.LoginDao;
import itsh.isic.enums.MsjEnum;
import itsh.isic.exception.BaseDeDatosException;
import itsh.isic.exception.ResultadosVaciosException;
import itsh.isic.models.UsuarioModel;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	ConnectDao dao = new ConnectDao();

	@Override
	public boolean estaBloqueadoUsuario(String apodo, String correo) throws BaseDeDatosException {
		final MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue(TblConstUsuario.COL_APODO, apodo);
		namedParameters.addValue(TblConstUsuario.COL_CORREO, correo);
		return this.dao.getNamedjdbcTemplate().queryForObject(this.qryGetEstatus(), namedParameters, boolean.class) != null;
	}

	@Override
	public UsuarioModel getUsuarioParaSesionCorreoOrUser(String username, String correo)
			throws ResultadosVaciosException, BaseDeDatosException {
		try {
			final MapSqlParameterSource namedParameters = this.createFindUsernameNamedParameters(username);
			return this.dao.getNamedjdbcTemplate().queryForObject(this.qryGetUsuarioPorSesion(), namedParameters,
					new BeanPropertyRowMapper<UsuarioModel>(UsuarioModel.class));
		} catch (EmptyResultDataAccessException emptyResultDataAccessException) {
			throw new ResultadosVaciosException(emptyResultDataAccessException);
		} catch (DataAccessException dataAccessException) {
			throw new BaseDeDatosException(MsjEnum.ERROR_RECUPERAR_USUARIO.getDescripcion(), dataAccessException);
		}
	}

	@Override
	public Integer getContraseniaPorApodo(final String apodo, final String correo, final String contrasenia)
			throws BaseDeDatosException {
		try {
			final MapSqlParameterSource params = this.paramsGetContraseniaPorApodo(apodo, correo, contrasenia);
			return this.dao.getNamedjdbcTemplate().queryForObject(this.qryGetContraseniaPorApodo(), params, Integer.class);
		} catch (DataAccessException dataAccessException) {
			throw new BaseDeDatosException("Error al consultar la base de datos", dataAccessException);
		}
	}

	public void registraIntento(final Integer idUser, final Integer retryNumber) throws BaseDeDatosException {
		try {
			final MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(TblConstUsuario.COL_IDUSER, idUser);
			params.addValue(TblConstUsuario.COL_CANTINTENTOS, retryNumber);
			this.dao.getNamedjdbcTemplate().update(this.qrySetIntentosLogin(), params);
		} catch (DataAccessException dataAccessException) {
			throw new BaseDeDatosException(dataAccessException);
		}
	}

	private String qrySetIntentosLogin() {
		final StringBuilder query = new StringBuilder();
		query.append("UPDATE USERS SET RetriesNumber = :RetriesNumber WHERE IdUser = :IdUser");
		return query.toString();
	}

	private String qryGetContraseniaPorApodo() {
		final StringBuilder qry = new StringBuilder();
		qry.append("SELECT COUNT(IdUser) FROM USERS WHERE ");
		qry.append("Apodo = :Apodo AND Contrasenia = :Contrasenia OR Correo = :Correo AND Contrasenia = :Contrasenia");
		return qry.toString();
	}

	private MapSqlParameterSource paramsGetContraseniaPorApodo(final String apodo, final String correo,
			final String contrasenia) {
		final MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(TblConstUsuario.COL_APODO, apodo);
		params.addValue(TblConstUsuario.COL_CORREO, correo);
		params.addValue(TblConstUsuario.COL_CONTRASENIA, contrasenia);
		return params;
	}

	private String qryGetUsuarioPorSesion() {
		final StringBuilder querySelect = this.qryGetColsUsuario();
		querySelect.append(", Username, Password, Salt FROM USERS WHERE Username = :Username");
		return querySelect.toString();
	}

	private StringBuilder qryGetColsUsuario() {
		final StringBuilder querySelect = new StringBuilder();
		querySelect.append("SELECT IdUser, IdUnderdirector, IdPosition, IdArea, IdDga, Username, Name, FirstLastName, ");
		querySelect.append("SecondLastName, PhoneNumber, Email, Territory, Status, IsLawyer, IsEvaluator, ");
		querySelect.append("RetriesNumber, StatusLogin, IsDecider ");
		return querySelect;
	}

	private MapSqlParameterSource createFindUsernameNamedParameters(final String username) {
		final MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue(TblConstUsuario.COL_APODO, username);
		return namedParameters;
	}

	private String qryGetEstatus() {
		final StringBuilder qry = new StringBuilder();
		qry.append("SELECT COUNT(1) FROM USERS WHERE ");
		qry.append("Apodo = :Apodo AND Estatus = 'BLOCKED' OR  correo = :correo AND Estatus = 'BLOCKED'");
		return qry.toString();
	}

}
