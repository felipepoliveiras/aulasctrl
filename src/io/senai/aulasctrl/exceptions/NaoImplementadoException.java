package io.senai.aulasctrl.exceptions;

public class NaoImplementadoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NaoImplementadoException() {
		super();
	}

	public NaoImplementadoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NaoImplementadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public NaoImplementadoException(String message) {
		super(message);
	}

	public NaoImplementadoException(Throwable cause) {
		super(cause);
	}
	
	

}
