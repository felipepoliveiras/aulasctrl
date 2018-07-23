package io.senai.aulasctrl.exceptions;

public class NaoAutorizadoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NaoAutorizadoException() {
		super();
	}

	public NaoAutorizadoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NaoAutorizadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public NaoAutorizadoException(String message) {
		super(message);
	}

	public NaoAutorizadoException(Throwable cause) {
		super(cause);
	}
	
	

}
