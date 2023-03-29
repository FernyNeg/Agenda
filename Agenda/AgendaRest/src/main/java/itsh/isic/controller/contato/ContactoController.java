package itsh.isic.controller.contato;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import itsh.isic.business.contacto.ContactoService;
import itsh.isic.constantes.UrlConstantes;
import itsh.isic.dao.contacto.ContactoDao;
import itsh.isic.exception.BusinessException;
import itsh.isic.models.ContactoModel;

@Controller
public class ContactoController {
	private static final Logger log = LoggerFactory.getLogger(ContactoController.class);

	@Autowired
	private ContactoService contactoCont;

	/*
	 * 
	 * @postMaping para publicarlo como una ruta de acceso al servicio
	 * 
	 * @responseBody para asignar atrubitos de RESPUESTA de datos
	 * 
	 * @requestBody para asignar atributos de ENTRADA de datos
	 * 
	 */

	@PostMapping(value = UrlConstantes.ACTUALIZA_CONTACTO)
	@ResponseBody
	public boolean chngContacto(@RequestBody ContactoModel reqContacto) {
		log.info("ContactoController: se recibe cambio de contacto: " + reqContacto.getIdContacto());
		boolean res = false;
		try {
			res = contactoCont.chngContacto(reqContacto);
		} catch (Exception e) {
			log.error("ContactoController: Error en el cambio de contacto: " + reqContacto.getIdContacto());
		}
		return res;
	}

	@PostMapping(value = UrlConstantes.LEER_CONTACTOS)
	@ResponseBody
	public List<ContactoModel> leerContactos(@RequestBody(required = false) String reqNombre) throws BusinessException {
		log.info("ContactoController: se recibe consulta de contactos: " + reqNombre);
		List<ContactoModel> res = null;
		try {
			res = contactoCont.leerContactos(reqNombre);
		} catch (Exception e) {
			log.error("ContactoController: error en la recepcion de nombre: " + reqNombre);
			res = null;
		}
		return res;
	}

	@PostMapping(value = UrlConstantes.GUARDA_CONTACTO)
	@ResponseBody
	public Integer guardaContacto(@RequestBody ContactoModel reqContacto) throws BusinessException {
		log.info("ContactoController: se recibe dato de contacto: " + reqContacto.getNombre());
		Integer res;
		try {
			res = contactoCont.setNuevContacto(reqContacto);
		} catch (Exception e) {
			log.error("ContactoController: error en la recepcion de contacto: " + reqContacto.getNombre());
			res = null;
		}
		return res;
	}

}
