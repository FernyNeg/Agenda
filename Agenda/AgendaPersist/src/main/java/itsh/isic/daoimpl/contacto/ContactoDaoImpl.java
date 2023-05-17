package itsh.isic.daoimpl.contacto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import itsh.isic.constantes.Constantes;
import itsh.isic.constantes.tablas.TblConsContac;
import itsh.isic.dao.connec.ConnectDao;
import itsh.isic.dao.contacto.ContactoDao;
import itsh.isic.enums.CodRetornoEnum;
import itsh.isic.enums.DBEnum;
import itsh.isic.enums.MsjEnum;
import itsh.isic.enums.NumerosEnum;
import itsh.isic.exception.ServiciosException;
import itsh.isic.models.ConsultaList;
import itsh.isic.models.ContactoModel;

@Repository
public class ContactoDaoImpl implements ContactoDao {

	private static final Logger log = LoggerFactory.getLogger(ContactoDaoImpl.class);
	private static final String clase = "ContactoDaoImpl: ";

	@Autowired
	private ConnectDao dao;

	@Override
	public ContactoModel setContacto(ContactoModel contacto) throws ServiciosException {
		log.info(clase + "Inicia insersion de contacto: " + contacto.getNombre());
		BeanPropertySqlParameterSource paramContacto = new BeanPropertySqlParameterSource(contacto);
		try {
			KeyHolder key = new GeneratedKeyHolder();
			this.dao.getNamedjdbcTemplate().update(this.genQrySetContacto(), paramContacto, key,
					new String[] { TblConsContac.COL_IDCONTACTO.toString() });
			if (key.getKey().intValue() == 1) {
				contacto.setCodRetorno(CodRetornoEnum.CEROS.getDescripcion());
				contacto.setMensaje(MsjEnum.OP_COMPLETADA.getDescripcion());
			} else {
				contacto.setCodRetorno(CodRetornoEnum.FALLO_SERVER.getDescripcion());
				contacto.setMensaje(MsjEnum.FALLO_SERVER.getDescripcion());
			}
		} catch (DuplicateKeyException dke) {
			log.error(clase + "error en la insersion del contacto: correo existente en los registros " + dke);
			contacto.setCodRetorno(CodRetornoEnum.PARAMS_REPETIDOS.getDescripcion());
			contacto.setMensaje(MsjEnum.CORREO_REPETIDO.getDescripcion());
		} catch (Exception e) {
			log.error(clase + "error en la insersion del contacto" + e);
			contacto.setCodRetorno(CodRetornoEnum.FALLO_SERVER.getDescripcion());
			contacto.setMensaje(MsjEnum.FALLO_SERVER.getDescripcion());
		}
		return contacto;
	}

	@Override
	public ContactoModel leerContactoPorId(ContactoModel reqContacto) throws ServiciosException {
		log.info(clase + "Inicia consulta de contacto por id: " + reqContacto.getIdContacto());
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(reqContacto);
		ContactoModel res = new ContactoModel();
		try {
			res = dao.getNamedjdbcTemplate().queryForObject(this.genQryGetContactoPorId(), params,
					new BeanPropertyRowMapper<ContactoModel>(ContactoModel.class));
			res.setCodRetorno(CodRetornoEnum.CEROS.getDescripcion());
			res.setMensaje(MsjEnum.OP_COMPLETADA.getDescripcion());
		} catch (EmptyResultDataAccessException e) {
			log.error(clase + "No hay datos para obtener de la consulta: " + reqContacto.getIdContacto());
			res.setCodRetorno(CodRetornoEnum.CEROS.getDescripcion());
			res.setMensaje(MsjEnum.EMPTY_RES_OBJ.getDescripcion());
		} catch (Exception e) {
			log.error(clase + "Error al obtener el contacto por id: " + reqContacto.getIdContacto() + " " + e);
			res.setCodRetorno(CodRetornoEnum.FALLO_SERVER.getDescripcion());
			res.setMensaje(MsjEnum.FALLO_SERVER.getDescripcion());
		}
		return res;
	}

	@Override
	public ConsultaList<ContactoModel> getContactosList(String reqNombre) {
		log.info(clase + "Inicia consulta de contactos ");
		ConsultaList<ContactoModel> res = new ConsultaList<ContactoModel>();
		MapSqlParameterSource namedParameters = new MapSqlParameterSource("Nombre", reqNombre);
		try {
			res.setList(this.dao.getNamedjdbcTemplate().query(this.genQryGetContactos(), namedParameters,
					new BeanPropertyRowMapper<ContactoModel>(ContactoModel.class)));
			res.setCodRetorno(CodRetornoEnum.CEROS.getDescripcion());
			res.setMensaje(MsjEnum.OP_COMPLETADA.getDescripcion());
		} catch (EmptyResultDataAccessException e) {
			log.error(clase + "No se encontraron datos de la consulta: " + reqNombre);
			res.setCodRetorno(CodRetornoEnum.CEROS.getDescripcion());
			res.setMensaje(MsjEnum.EMPTY_RES_LIST.getDescripcion());
		} catch (Exception e) {
			log.error(clase + "Error al obtener los datos de la consulta", e);
			res.setCodRetorno(CodRetornoEnum.FALLO_SERVER.getDescripcion());
			res.setMensaje(MsjEnum.FALLO_SERVER.getDescripcion());
		}
		return res;
	}

	@Override
	public ContactoModel chngContacto(ContactoModel reqContacto) throws ServiciosException {
		log.info(clase + "se inicia actualizacion de contacto: " + reqContacto.getNombre());
		final BeanPropertySqlParameterSource paramContacto = new BeanPropertySqlParameterSource(reqContacto);
		try {
			boolean res = (this.dao.getNamedjdbcTemplate().update(this.genQryChngContacto(), paramContacto) == 1);
			reqContacto
					.setCodRetorno(res ? CodRetornoEnum.CEROS.getDescripcion() : CodRetornoEnum.FALLO_SERVER.getDescripcion());
			log.info(clase + "se cambia correctamente la información del contacto: " + reqContacto.getNombre());
			reqContacto.setMensaje(MsjEnum.OP_COMPLETADA.getDescripcion());
		} catch (DuplicateKeyException dke) {
			log.error(clase + "ya existe el correo en los registros: " + dke);
			reqContacto.setCodRetorno(CodRetornoEnum.PARAMS_REPETIDOS.getDescripcion());
			reqContacto.setMensaje(MsjEnum.CORREO_REPETIDO.getDescripcion());
		} catch (Exception e) {
			log.error(clase + "Error en la actualizacion de contacto: " + e);
			reqContacto.setCodRetorno(CodRetornoEnum.FALLO_SERVER.getDescripcion());
			reqContacto.setMensaje(MsjEnum.FALLO_SERVER.getDescripcion());
		}
		return reqContacto;
	}

	private String genQryChngContacto() {
		StringBuilder qry = new StringBuilder();
		qry.append("UPDATE ").append(DBEnum.CONTACTO.getDescripcion());
		qry.append(" SET ");
		qry.append(TblConsContac.COL_USUARIO).append("=").append(":usuario").append(Constantes.COMMA);
		qry.append(TblConsContac.COL_CORREO).append("=").append(":correo").append(Constantes.COMMA);
		qry.append(TblConsContac.COL_NOMBRE).append("=").append(":nombre").append(Constantes.COMMA);
		qry.append(TblConsContac.COL_PATERNO).append("=").append(":aPaterno").append(Constantes.COMMA);
		qry.append(TblConsContac.COL_MATERNO).append("=").append(":aMaterno").append(Constantes.COMMA);
		qry.append(TblConsContac.COL_DIRECCION).append("=").append(":direccion").append(Constantes.COMMA);
		qry.append(TblConsContac.COL_CIUDAD).append("=").append(":ciudad").append(Constantes.COMMA);
		qry.append(TblConsContac.COL_PAIS).append("=").append(":pais").append(Constantes.COMMA);
		qry.append(TblConsContac.COL_CODPOSTAL).append("=").append(":codPostal").append(Constantes.COMMA);
		qry.append(TblConsContac.COL_NOTAS).append("=").append(":notas");
		qry.append(" WHERE ").append(TblConsContac.COL_IDCONTACTO).append("=").append(":idContacto");
		return qry.toString();
	}

	private String genQryGetContactos() {
		StringBuilder qry = new StringBuilder();
//		qry.append("call getContactos(:Nombre)");
		qry.append(" SELECT ");
		qry.append(TblConsContac.COL_IDCONTACTO.toString() + Constantes.COMMA);
		qry.append(TblConsContac.COL_NOMBRE.toString() + Constantes.COMMA);
		qry.append(TblConsContac.COL_DIRECCION.toString() + Constantes.COMMA);
		qry.append(TblConsContac.COL_CORREO.toString());
		qry.append(" FROM ").append(DBEnum.CONTACTO.getDescripcion());
		qry.append(" WHERE ");
		qry.append(TblConsContac.COL_NOMBRE.toString());
		qry.append(" LIKE CONCAT('%',");
		qry.append(":Nombre,");
		qry.append("'%')");
		return qry.toString();
	}

	private String genQrySetContacto() {
		StringBuilder qry = new StringBuilder();
		qry.append(" insert into ").append(DBEnum.CONTACTO.getDescripcion());
		qry.append(" ( ");
		qry.append(TblConsContac.COL_IDCONTACTO + Constantes.COMMA);
		qry.append(TblConsContac.COL_NOMBRE + Constantes.COMMA);
		qry.append(TblConsContac.COL_DIRECCION + Constantes.COMMA);
		qry.append(TblConsContac.COL_CORREO);
		qry.append(") ");
		qry.append(" values ( ").append(NumerosEnum.CERO.getNumero()).append(", ");
		qry.append(":nombre, :direccion, :correo )");
		return qry.toString();
	}

	private String genQryGetContactoPorId() {
		StringBuilder qry = new StringBuilder();
//		qry.append("select * from ");
//		qry.append(DBEnum.CONTACTO.toString().toLowerCase());
//		qry.append(" where " + TblConsContac.COL_IDCONTACTO);
//		qry.append(" = :idContacto");
		qry.append("call getContacto( :idContacto )");
		return qry.toString();
	}

}
