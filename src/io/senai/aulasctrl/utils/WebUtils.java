package io.senai.aulasctrl.utils;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class WebUtils {
	
	@Autowired
	private ServletContext context;
	
	public URI uri(String suffix) {
		
		try {
			return new URI(context.getContextPath() + suffix);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		
	}

}
