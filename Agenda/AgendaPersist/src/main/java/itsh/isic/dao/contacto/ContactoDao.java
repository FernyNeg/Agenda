package itsh.isic.dao.contacto;

import itsh.isic.exception.ServiciosException;
import itsh.isic.models.ConsultaList;
import itsh.isic.models.ContactoModel;

public interface ContactoDao {

	public ContactoModel setContacto(ContactoModel reqContacto) throws ServiciosException;

	public ConsultaList<ContactoModel> getContactosList(String reqNombres) throws ServiciosException;

	public ContactoModel chngContacto(ContactoModel reqContacto) throws ServiciosException;

	public ContactoModel leerContactoPorId(ContactoModel reqContacto) throws ServiciosException;

}
