package itsh.isic.exception;

public class BaseDeDatosException extends Exception {
	private static final long serialVersionUID = 2197602657177193103L;

	public BaseDeDatosException(final String message) {
		super(message);
	}

	public BaseDeDatosException(final Throwable throwable) {
		super(throwable);
	}

	public BaseDeDatosException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
	
}
