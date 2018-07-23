package io.senai.aulasctrl.web.ws.rest.v1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.senai.aulasctrl.exceptions.EntidadeNaoEncontradaException;
import io.senai.aulasctrl.exceptions.NaoAutorizadoException;
import io.senai.aulasctrl.models.Usuario;
import io.senai.aulasctrl.services.UsuarioService;
import io.senai.aulasctrl.utils.JwtUtils;
import io.senai.aulasctrl.utils.MapUtils;

@RestController
@RequestMapping("/rest/v1/auth")
public class AuthRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/jwt")
	public ResponseEntity<Object> gerarJwt(@RequestBody Usuario usuario) {
		try {
			Usuario usuarioBuscado = usuarioService.buscarPorEmailESenha(usuario);
			return ResponseEntity.ok(MapUtils.mapaDe("token", JwtUtils.gerarTokenAutenticacao(usuarioBuscado)));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NaoAutorizadoException();
		}
		
		
	}

}
