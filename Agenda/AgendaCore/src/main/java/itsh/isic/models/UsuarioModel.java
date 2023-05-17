package itsh.isic.models;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import itsh.isic.enums.EstatusUsuarioEnum;

@Component
public class UsuarioModel extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1035479338680325026L;

	private Integer id;
	private String apodo;
	private String correo;
	private String contrasenia;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private String nomCompleto;
	private Integer numIntentos;
	private EstatusUsuarioEnum estatus;

	public Integer getNumIntentos() {
		return numIntentos;
	}

	public void setNumIntentos(Integer numIntentos) {
		this.numIntentos = numIntentos;
	}

	public String getNomCompleto() {
		this.nomCompleto = this.getNombre() + this.getaPaterno() + this.getaMaterno();
		return nomCompleto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public EstatusUsuarioEnum getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusUsuarioEnum estatus) {
		this.estatus = estatus;
	}

}
