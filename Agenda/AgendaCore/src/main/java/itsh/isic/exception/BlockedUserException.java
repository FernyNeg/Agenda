package itsh.isic.exception;

public class BlockedUserException extends Exception {
	private static final long serialVersionUID = 3807175165143481876L;

	public BlockedUserException(final String message) {
		super(message);
	}

	public BlockedUserException(final String message, final Throwable throwable) {
		super(message, throwable);
	}

}
