package io.senai.aulasctrl.utils;

import java.util.HashMap;
import java.util.Map;

public final class ClassScopedLogger extends Logger{
	
	private static Map<Class<? extends Object>, ClassScopedLogger> instances = new HashMap<>();
	
	/**
	 * Get the instance of the logger based on the given type.
	 * @param type
	 * @return
	 */
	public static ClassScopedLogger getInstance(Class<? extends Object> type) {
		ClassScopedLogger logger = instances.get(type);
		if (logger == null) {
			logger = new ClassScopedLogger(type);
			instances.put(type, logger);
		}
		
		return logger;
	}
	
	
	private Class<? extends Object> type;
	
	private ClassScopedLogger(Class<? extends Object> type) {
		this.type = type;
	}
	
	private String getPrefix() {
		return "[" + type.getName() + "]: ";
	}

	@Override
	public void error(String msg) {
		System.err.print(getPrefix() + msg);
	}

	@Override
	public void msg(String msg) {
		System.out.print(getPrefix() + msg);
	}
	
	

}
