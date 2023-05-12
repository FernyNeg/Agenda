package itsh.isic.exception;

public class DatabaseException extends Exception {
	private static final long serialVersionUID = 2197602657177193103L;

	public DatabaseException(final String message) {
		super(message);
	}

	public DatabaseException(final Throwable throwable) {
		super(throwable);
	}

	public DatabaseException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
	
}
