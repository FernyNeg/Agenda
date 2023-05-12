package itsh.isic.exception;

public class BusinessException extends Exception {
	/*
	 * Clase generada para cahear las exepciones generales sin imprimir texto
	 * exagerado a la hora de compilar e imprimir en el log
	 */
	private static final long serialVersionUID = -3179587548947498495L;
	private final String id;
	private final String mensaje;

	public BusinessException(String id, String mensaje) {
		super(id);
		this.id = id;
		this.mensaje = mensaje;
	}

	public BusinessException(String id, String mensaje, Throwable exc) {
		super(id, exc);
		this.mensaje = mensaje;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getMensaje() {
		return mensaje;
	}
	
}