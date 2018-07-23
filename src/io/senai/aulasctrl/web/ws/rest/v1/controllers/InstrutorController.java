package io.senai.aulasctrl.web.ws.rest.v1.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.senai.aulasctrl.models.Instrutor;
import io.senai.aulasctrl.services.CrudService;
import io.senai.aulasctrl.services.InstrutorService;

@RestController
@RequestMapping("/rest/v1/instrutores")
public class InstrutorController extends CrudController<Instrutor>{
	
	@Autowired
	private InstrutorService instrutorService;

	@Override
	protected CrudService<Instrutor> getCrudService() {
		return instrutorService;
	}

	@Override
	protected Object getIdentifierOf(Instrutor obj) {
		return obj.getId();
	}

	@Override
	protected String getRoutePrefix() {
		return "/rest/v1/instrutores";
	}
	
	@GetMapping("/{id}")
	@Secured("ROLE_ADMINISTRADOR")
	public ResponseEntity<Object> buscar(@PathVariable Long id) {
		return super.buscar(id);
	}
	
	@GetMapping
	@Secured("ROLE_ADMINISTRADOR")
	public ResponseEntity<Object> buscar() {
		return super.buscar();
	}
	
	@PutMapping("/{id}")
	@Secured("ROLE_ADMINISTRADOR")
	public ResponseEntity<Object> alterar(@PathVariable Long id, @RequestBody @Valid Instrutor obj, BindingResult bindingResult) {
		return super.alterar(id, obj, bindingResult);
	}
	
	@PostMapping
	@Secured("ROLE_ADMINISTRADOR")
	public ResponseEntity<Object> persistir(@RequestBody @Valid Instrutor obj, BindingResult bindingResult) {
		return super.persistir(obj, bindingResult);
	}
	
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMINISTRADOR")
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		return super.deletar(id);
	}
}
