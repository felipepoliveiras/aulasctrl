package io.senai.aulasctrl.web.ws.rest.v1.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.senai.aulasctrl.exceptions.EntidadeNaoEncontradaException;
import io.senai.aulasctrl.exceptions.NaoAutorizadoException;
import io.senai.aulasctrl.exceptions.NaoImplementadoException;
import io.senai.aulasctrl.exceptions.ValidacaoException;
import io.senai.aulasctrl.utils.MapUtils;

@ControllerAdvice
public class GlocalControllerExceptionHandler {
	
	/*
	 * 401
	 */
	@ExceptionHandler(NaoAutorizadoException.class)
	public ResponseEntity<Object> naoAutorizadoException() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	/*
	 * 403
	 */
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ResponseEntity<Object> forbiddenCode() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
	
	/*
	 * 404
	 */
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> entidadeNaoEncontradaException() {
		return ResponseEntity.notFound().build();
	}
	/*
	 * 405
	 */
	@ExceptionHandler(NaoImplementadoException.class)
	public ResponseEntity<Object> naoImplementadoException() {
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
	}
	
	/*
	 * 422
	 */
	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<Object> validacaoException(ValidacaoException e) {
		return ResponseEntity.unprocessableEntity().body(MapUtils.mapaDe(e.getBindingResult()));
	}
	
	/*
	 * 500
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exception(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(500).build();
	}

}
