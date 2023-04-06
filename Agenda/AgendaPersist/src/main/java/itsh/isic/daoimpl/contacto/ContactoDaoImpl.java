package itsh.isic.daoimpl.contacto;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
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
import itsh.isic.enums.DBEnum;
import itsh.isic.exception.BusinessException;
import itsh.isic.models.ConsultaList;
import itsh.isic.models.ContactoModel;

@Repository
public class ContactoDaoImpl implements ContactoDao {

	private static final Logger log = LoggerFactory.getLogger(ContactoDaoImpl.class);

	@Autowired
	private ConnectDao dao;

	@Override
	public Integer setContacto(ContactoModel contacto) throws BusinessException {
		log.info("ContactoDaoImpl: Inicia insersion de contacto: " + contacto.getNombre());
		BeanPropertySqlParameterSource paramContacto = new BeanPropertySqlParameterSource(contacto);
		try {
			KeyHolder key = new GeneratedKeyHolder();
			this.dao.getNamedjdbcTemplate().update(this.genQrySetContacto(), paramContacto, key,
					new String[] { TblConsContac.COL_IDCONTACTO.toString() });
			return key.getKey().intValue();
		} catch (DuplicateKeyException dke) {
			log.error("ContactoDaoImpl: error en la insersion del contacto: correo existente en los registros" + dke);
			return null;
		} catch (Exception e) {
			log.error("ContactoDaoImpl: error en la insersion del contacto" + e);
			return null;
		}
	}

	@Override
	public ContactoModel getContacto(Integer id) {
		// SELECT * FROM agendarest.contacto where Nombre like "%%"
		return null;
	}

	@Override
	public List<ContactoModel> getContactosList(ConsultaList<ContactoModel> reqNombre) {
		log.info("ContactoDaoImpl: Inicia consulta de contactos ");
		List<ContactoModel> res = null;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource("Nombre", reqNombre.getParam());
		try {
			res = this.dao.getNamedjdbcTemplate().query(this.genQryGetContactos(), namedParameters,
					new BeanPropertyRowMapper<ContactoModel>(ContactoModel.class));
		} catch (DataAccessException e) {
			log.error("Error al consultar información de contactos", e);
		}
		return res;
	}

	@Override
	public boolean chngContacto(ContactoModel reqContacto) throws BusinessException {
		log.info("ContactoDaoImpl: se inicia actualizacion de contacto: " + reqContacto.getNombre());
		final BeanPropertySqlParameterSource paramContacto = new BeanPropertySqlParameterSource(reqContacto);
		boolean res = false;
		try {
			res = (this.dao.getNamedjdbcTemplate().update(this.genQryChngContacto(), paramContacto) == 1);
		} catch (DuplicateKeyException dke) {
			log.error("ContactoDaoImpl: ya existe el correo en los registros: " + dke);
		} catch (Exception e) {
			log.error("ContactoDaoImpl: Error en la actualizacion de contacto: " + e);
		}
		return res;
	}

	@Override
	public boolean delContacto(Integer reqId) {
		// TODO Auto-generated method stub
		return false;
	}

	private String genQryChngContacto() {
		StringBuilder qry = new StringBuilder();
		qry.append("UPDATE ").append(DBEnum.CONTACTO.toString().toLowerCase());
		qry.append(" SET ");
		qry.append(TblConsContac.COL_NOMBRE).append("=").append(":nombre").append(Constantes.COMMA);
		qry.append(TblConsContac.COL_DIRECCION).append("=").append(":direccion").append(Constantes.COMMA);
		qry.append(TblConsContac.COL_CORREO).append("=").append(":correo");
		qry.append(" WHERE ").append(TblConsContac.COL_IDCONTACTO).append("=").append(":idContacto");
		return qry.toString();
	}

	private String genQryGetContactos() {
		StringBuilder qry = new StringBuilder();
//		qry.append("call getContactos(:Nombre)");
		qry.append(" SELECT * FROM ").append(DBEnum.CONTACTO.toString().toLowerCase());
		qry.append(" WHERE ");
		qry.append(TblConsContac.COL_NOMBRE.toString());
		qry.append(" LIKE CONCAT('%',");
		qry.append(":Nombre,");
		qry.append("'%')");
		return qry.toString();
	}

	private String genQrySetContacto() {
		StringBuilder qry = new StringBuilder();
		qry.append(" insert into ").append(DBEnum.CONTACTO.toString().toLowerCase());
		qry.append(" ( ");
		qry.append(TblConsContac.COL_IDCONTACTO + Constantes.COMMA);
		qry.append(TblConsContac.COL_NOMBRE + Constantes.COMMA);
		qry.append(TblConsContac.COL_DIRECCION + Constantes.COMMA);
		qry.append(TblConsContac.COL_CORREO);
		qry.append(") ");
		qry.append(" values ( ").append(Constantes.CERO_NUM).append(", ");
		qry.append(":nombre, :direccion, :correo )");
		return qry.toString();
	}

}
