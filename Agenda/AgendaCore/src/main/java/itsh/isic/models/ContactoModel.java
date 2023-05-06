package itsh.isic.models;

import itsh.isic.enums.StatusContactoEnum;

public class ContactoModel extends BaseBean {

	private Integer idContacto;
	private String nombre;
	private String direccion;
	private String correo;
	private String usuario;
	private String aPaterno;
	private String aMaterno;
	private String ciudad;
	private String pais;
	private String notas;
	private Integer codPostal;
	private StatusContactoEnum status;

	public final String getNombre() {
		return nombre;
	}

	public final void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public final String getDireccion() {
		return direccion;
	}

	public final void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public final String getCorreo() {
		return correo;
	}

	public final void setCorreo(String correo) {
		this.correo = correo;
	}

	public final StatusContactoEnum getStatus() {
		return status;
	}

	public final void setStatus(StatusContactoEnum status) {
		this.status = status;
	}

	public Integer getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Integer idContacto) {
		this.idContacto = idContacto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getaPaterno() {
		return aPaterno;
	}

	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}

	public String getaMaterno() {
		return aMaterno;
	}

	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Integer getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(Integer codPostal) {
		this.codPostal = codPostal;
	}

}