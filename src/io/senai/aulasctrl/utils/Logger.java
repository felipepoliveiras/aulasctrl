package io.senai.aulasctrl.utils;

public abstract class Logger {
	
	public abstract void error(String msg);
	public abstract void msg(String msg);	
	
	public void errorl(String msg) {
		error(msg);
		breakLine();
	}
	
	public void msgl(String msg) {
		msg(msg);
		breakLine();
	}
	
	/**
	 * Log an line separator into console
	 */
	public void breakLine() {
		System.out.print(System.lineSeparator());
	}

}
