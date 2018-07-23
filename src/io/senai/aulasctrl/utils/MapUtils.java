package io.senai.aulasctrl.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class MapUtils {
	
	public static Map<String, String> mapaDe(String chave, String valor) {
		Map<String, String> map = new HashMap<>();
		map.put(chave, valor);
		
		return map;
	}
	
	public static Map<String, String> mapaDe(BindingResult bindingResult) {
		Map<String, String> map = new HashMap<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return map;
	}

}
