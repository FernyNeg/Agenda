package itsh.isic.models;

import itsh.isic.enums.StatusContactoEnum;

public class ContactoModel {

	private Integer idContacto;
	private String nombre;
	private String direccion;
	private String correo;
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

}