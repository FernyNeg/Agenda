package itsh.isic.enums;

public enum DBEnum {
	/*
	 * Enumerador con los nombres de las tablas de la bases de datos
	 */
	CONTACTO("contacto"), //
	USUARIO("usuario");

	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	private DBEnum(String descripcion) {
		this.descripcion = descripcion;
	}
}
