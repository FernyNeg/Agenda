package itsh.isic.exception;

public class UsuarioBloqueadoException extends Exception {
	private static final long serialVersionUID = 3807175165143481876L;

	public UsuarioBloqueadoException(final String message) {
		super(message);
	}

	public UsuarioBloqueadoException(final String message, final Throwable throwable) {
		super(message, throwable);
	}

}
