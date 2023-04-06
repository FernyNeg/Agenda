package itsh.isic.dao.contacto;

import java.util.List;

import itsh.isic.exception.BusinessException;
import itsh.isic.models.ConsultaList;
import itsh.isic.models.ContactoModel;

public interface ContactoDao {

	public Integer setContacto(ContactoModel reqContacto) throws BusinessException;

	public ContactoModel getContacto(Integer reqId) throws BusinessException;

	public List<ContactoModel> getContactosList(ConsultaList<ContactoModel> reqNombres) throws BusinessException;

	public boolean chngContacto(ContactoModel reqContacto) throws BusinessException;

	public boolean delContacto(Integer reqId) throws BusinessException;

}
