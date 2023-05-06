package itsh.isic.service.contacto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itsh.isic.dao.contacto.ContactoDao;
import itsh.isic.enums.CodRetornoEnum;
import itsh.isic.enums.MsjEnum;
import itsh.isic.exception.BusinessException;
import itsh.isic.models.ConsultaList;
import itsh.isic.models.ContactoModel;

@Service
public class ContactoService {
	private static final Logger log = LoggerFactory.getLogger(ContactoService.class);

	@Autowired
	private ContactoDao contacto;

	public ConsultaList<ContactoModel> leerContactos(ConsultaList<ContactoModel> reqNombre) throws BusinessException {
		log.info("ContactoBuss: se controla consulta de contactos ");
		String nombre = reqNombre.getParam() == null ? "" : reqNombre.getParam();
		reqNombre = contacto.getContactosList(nombre);
		return reqNombre;
	}

	public ContactoModel leerContactoPorId(ContactoModel reqContacto) throws BusinessException {
		log.info("ContactoBuss: se controla consulta de contacto por id: " + reqContacto.getIdContacto());
		if (reqContacto.getIdContacto() != null) {
			reqContacto = contacto.leerContactoPorId(reqContacto);
		} else {
			log.error("ContactoBuss: El id del contacto esta vacio: " + reqContacto.getIdContacto());
			reqContacto.setCodRetorno(CodRetornoEnum.FALTAN_PARAMS.getDescripcion());
			reqContacto.setMensaje(MsjEnum.CONTACTO_NULL.getDescripcion());
		}
		return reqContacto;
	}

	public ContactoModel chngContacto(ContactoModel reqContacto) {
		log.info("ContactoBuss: se controla cambio de informacion de contacto: " + reqContacto.getIdContacto());
		try {
			ContactoModel res = validaContacto(reqContacto, true);
			if (res.getCodRetorno().equals(CodRetornoEnum.CEROS.getDescripcion())) {
				res = this.contacto.chngContacto(reqContacto);
				reqContacto.setCodRetorno(res.getCodRetorno());
				reqContacto.setMensaje(res.getMensaje());
			} else {
				reqContacto.setCodRetorno(res.getCodRetorno());
				reqContacto.setMensaje(res.getMensaje());
			}
		} catch (Exception e) {
			log.error("ContactoBuss: error en el cambio de informacion de contacto " + e);
			reqContacto.setCodRetorno(CodRetornoEnum.FALLO_SERVER.getDescripcion());
			reqContacto.setMensaje(MsjEnum.FALLO_SERVER.getDescripcion());
		}
		return reqContacto;
	}

	public ContactoModel setNuevContacto(ContactoModel reqContacto) throws BusinessException {
		log.info("ContactoBuss: se recibe insersión de contacto: " + reqContacto.getNombre());
		try {
			ContactoModel res = validaContacto(reqContacto, false);
			if (res.getCodRetorno().equals(CodRetornoEnum.CEROS.getDescripcion())) {
				res = this.contacto.setContacto(reqContacto);
				reqContacto.setCodRetorno(res.getCodRetorno());
				reqContacto.setMensaje(res.getMensaje());
			} else {
				reqContacto.setCodRetorno(res.getCodRetorno());
				reqContacto.setMensaje(res.getMensaje());
			}
		} catch (BusinessException e) {
			log.error("ContactoBuss: Error al guardar nuevo contacto: " + e);
			reqContacto.setCodRetorno(CodRetornoEnum.FALLO_SERVER.getDescripcion());
			reqContacto.setMensaje(MsjEnum.FALLO_SERVER.getDescripcion());
		}
		return reqContacto;
	}

	private ContactoModel validaContacto(ContactoModel contacto, boolean leeId) {
		log.info("ContactoBuss: Se valida información del contacto");
		boolean ret = true;
		if (contacto.getUsuario() == null || contacto.getUsuario().isEmpty() || contacto.getUsuario().length() < 6) {
			log.error("ContactoBuss: El Usuario del contacto esta vacio o es menor de 6 caracteres");
			contacto.setCodRetorno(CodRetornoEnum.FALTAN_PARAMS.getDescripcion());
			contacto.setMensaje(MsjEnum.CONTACTO_USU_NULL.getDescripcion());
			ret = false;
		}
		if (contacto.getCorreo() == null || contacto.getCorreo().isEmpty()) {
			log.error("ContactoBuss: El correo del contacto esta vacio");
			contacto.setCodRetorno(CodRetornoEnum.FALTAN_PARAMS.getDescripcion());
			contacto.setMensaje(MsjEnum.CONTACTO_CORREO_NULL.getDescripcion());
			ret = false;
		}
		if (contacto.getNombre() == null || contacto.getNombre().isEmpty()) {
			log.error("ContactoBuss: El nombre del contato esta vacio");
			contacto.setCodRetorno(CodRetornoEnum.FALTAN_PARAMS.getDescripcion());
			contacto.setMensaje(MsjEnum.CONTACTO_NOMBRE_NULL.getDescripcion());
			ret = false;
		}
		if (leeId) {
			if (contacto.getIdContacto() == null) {
				log.error("ContactoBuss: Id de contacto esta vacio");
				contacto.setCodRetorno(CodRetornoEnum.FALTAN_PARAMS.getDescripcion());
				contacto.setMensaje(MsjEnum.CONTACTO_NULL.getDescripcion());
				ret = false;
			}
		}
		if (ret) {
			contacto.setCodRetorno(CodRetornoEnum.CEROS.getDescripcion());
		}
		return contacto;
	}

}
