package itsh.isic.dao.contacto;

import itsh.isic.exception.BusinessException;
import itsh.isic.models.ConsultaList;
import itsh.isic.models.ContactoModel;

public interface ContactoDao {

	public ContactoModel setContacto(ContactoModel reqContacto) throws BusinessException;

	public ConsultaList<ContactoModel> getContactosList(String reqNombres) throws BusinessException;

	public ContactoModel chngContacto(ContactoModel reqContacto) throws BusinessException;

	public ContactoModel leerContactoPorId(ContactoModel reqContacto) throws BusinessException;

}
