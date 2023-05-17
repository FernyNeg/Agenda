package itsh.isic.enums;

public enum MsjEnum {
	// Errores de login
	MESSAGE_BLOCLED_USER_ERROR(
			"Usuario bloquedo por intentos fallidos. Comuniquese con el administrador de la aplicación para desbloquear su usuario"),
	ERROR_RECUPERAR_USUARIO("Error al recuperar el usuario por nombre de usuario"),
	ERROR_VALIDANDO_CREDENTIALES("Hubo un error al validar las credenciales del usuario"),
	CREDENCIALES_INVALIDAS("Credenciales del usuario inválidas"),

	// Errores en services
	CONTACTO_NULL("El id del contacto no puede estar vacio"), //
	CONTACTO_USU_NULL("El usuario del contacto no puede estar vacio y ser menor de 6 caracteres"), //
	CONTACTO_CORREO_NULL("El correo del contacto no puede estar vacio"), //
	CONTACTO_NOMBRE_NULL("El nombre del contacto no puede estar vacio"), //
	CORREO_REPETIDO("Ya existe el correo en los registros"), //

	// consultas vacias
	EMPTY_RES_LIST("No se encontraron datos de la consulta"), //
	EMPTY_RES_OBJ("No se encontró el dato de la consulta"), //

	// Operaciones Completas
	OP_COMPLETADA("Operación completada con exito"), //

	// Mensajes de servidor
	FALLO_SERVER("Error interno del servidor"), //
	; //

	private String descripcion;

	MsjEnum(String descripcionEnt) {
		this.descripcion = descripcionEnt;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
