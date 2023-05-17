package itsh.isic.exception;

public class ResultadosVaciosException extends Exception {
	private static final long serialVersionUID = -6642507812411405633L;

	public ResultadosVaciosException(final String message) {
		super(message);
	}

	public ResultadosVaciosException(final Throwable throwable) {
		super(throwable);
	}
	
}
