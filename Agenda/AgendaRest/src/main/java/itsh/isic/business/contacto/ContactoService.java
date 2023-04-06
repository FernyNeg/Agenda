package itsh.isic.business.contacto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itsh.isic.dao.contacto.ContactoDao;
import itsh.isic.exception.BusinessException;
import itsh.isic.models.ConsultaList;
import itsh.isic.models.ContactoModel;

@Service
public class ContactoService {
	private static final Logger log = LoggerFactory.getLogger(ContactoService.class);

	@Autowired
	private ContactoDao contacto;

	public boolean chngContacto(ContactoModel reqContacto) {
		log.info("ContactoBuss: se controla cambio de informacion de contacto: " + reqContacto.getIdContacto());
		boolean res = false;
		try {
			res = this.contacto.chngContacto(reqContacto);
		} catch (Exception e) {
			log.error("ContactoBuss: error en el cambio de informacion de contacto " + e);
		}
		return res;
	}

	public ConsultaList<ContactoModel> leerContactos(ConsultaList<ContactoModel> reqNombre) throws BusinessException {
		log.info("ContactoBuss: se controla consulta de contactos ");
		ConsultaList<ContactoModel> res = new ConsultaList<ContactoModel>();
		try {
			res.setList(contacto.getContactosList(reqNombre));
		} catch (Exception e) {
			log.error("ContactoBuss: Error al consultar contactos: " + reqNombre + e);
			res = null;
		}
		return res;
	}

	public Integer setNuevContacto(ContactoModel reqContacto) throws BusinessException {
		log.info("ContactoBuss: se recibe dato de contacto: " + reqContacto.getNombre());
		Integer res;
		try {
			res = this.contacto.setContacto(reqContacto);
		} catch (BusinessException e) {
			log.error("ContactoBuss: Error al guardar nuevo contacto: " + e);
			res = null;
		}
		return res;
	}

}
