package itsh.isic.models.security;

import java.io.Serializable;

import itsh.isic.models.UsuarioModel;

public class UsuarioSesion implements Serializable{
	private static final long serialVersionUID = 5335821792289813182L;

	private Integer idUser;
	private UsuarioModel user;
	private String userName;
	private String correo;
	private String contrasenia;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public UsuarioModel getUser() {
		return user;
	}

	public void setUser(UsuarioModel user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}
