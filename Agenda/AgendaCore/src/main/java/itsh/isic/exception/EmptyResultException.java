package itsh.isic.exception;

public class EmptyResultException extends Exception {
	private static final long serialVersionUID = -6642507812411405633L;

	public EmptyResultException(final String message) {
		super(message);
	}

	public EmptyResultException(final Throwable throwable) {
		super(throwable);
	}
	
}
