package itsh.isic.exception;

public class DesencriptadoException extends Exception {
	private static final long serialVersionUID = 7860552243422600993L;

	public DesencriptadoException(final String message) {
		super(message);
	}

	public DesencriptadoException(final Throwable throwable) {
		super(throwable);
	}

	public DesencriptadoException(final String message, final Throwable throwable) {
		super(message, throwable);
	}

}
