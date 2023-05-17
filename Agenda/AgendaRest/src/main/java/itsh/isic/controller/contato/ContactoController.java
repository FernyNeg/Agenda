package itsh.isic.controller.contato;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import itsh.isic.constantes.UrlConstantes;
import itsh.isic.exception.ServiciosException;
import itsh.isic.models.ConsultaList;
import itsh.isic.models.ContactoModel;
import itsh.isic.service.contacto.ContactoService;

@RestController
@RequestMapping("/controller/contacto")
public class ContactoController {
	private static final Logger log = LoggerFactory.getLogger(ContactoController.class);
	private static final String clase="ContactoController: ";

	@Autowired
	private ContactoService contactoCont;

	/*
	 * 
	 * @PostMapping para publicarlo como una ruta de acceso al servicio
	 * 
	 * @RequestBody para asignar atrubitos de RESPUESTA de datos
	 * 
	 * @ResponseBody para asignar atributos de ENTRADA de datos
	 * 
	 */

	@PostMapping(value = UrlConstantes.LEER_CONTACTOS)
	@ResponseBody
	public ConsultaList<ContactoModel> leerContactos(@RequestBody ConsultaList<ContactoModel> reqNombre)
			throws ServiciosException {
		log.info(clase + "se recibe consulta de contactos: " + reqNombre.getParam());
		return this.contactoCont.leerContactos(reqNombre);
	}

	@PostMapping(value = UrlConstantes.LEER_CONTACTO_POR_ID)
	@ResponseBody
	public ContactoModel leerContactoPorId(@RequestBody ContactoModel reqContacto) throws ServiciosException {
		log.info(clase + "se recibe consulta de contactos: " + reqContacto.getIdContacto());
		return this.contactoCont.leerContactoPorId(reqContacto);
	}

	@PostMapping(value = UrlConstantes.ACTUALIZA_CONTACTO)
	@ResponseBody
	public ContactoModel chngContacto(@RequestBody ContactoModel reqContacto) {
		log.info(clase + "se recibe cambio de contacto: " + reqContacto.getIdContacto());
		return contactoCont.chngContacto(reqContacto);
	}

	@PostMapping(value = UrlConstantes.GUARDA_CONTACTO)
	@ResponseBody
	public ContactoModel guardaContacto(@RequestBody ContactoModel reqContacto) throws ServiciosException {
		log.info(clase + "se recibe dato de contacto: " + reqContacto.getNombre());
		return contactoCont.setNuevContacto(reqContacto);
	}

}
