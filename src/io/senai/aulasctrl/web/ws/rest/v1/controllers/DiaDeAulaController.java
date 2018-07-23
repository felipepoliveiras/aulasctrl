package io.senai.aulasctrl.web.ws.rest.v1.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.senai.aulasctrl.exceptions.NaoImplementadoException;
import io.senai.aulasctrl.models.DiaDeAula;
import io.senai.aulasctrl.services.CrudService;
import io.senai.aulasctrl.services.DiaDeAulaService;

@RestController
@RequestMapping("/rest/v1/diasDeAula")
public class DiaDeAulaController extends CrudController<DiaDeAula>{
	
	@Autowired
	private DiaDeAulaService diaDeAulaAService;

	@Override
	protected CrudService<DiaDeAula> getCrudService() {
		return diaDeAulaAService;
	}

	@Override
	protected Object getIdentifierOf(DiaDeAula obj) {
		return obj.getId();
	}
	
	@Override
	protected String getRoutePrefix() {
		return "/rest/v1/diasDeAula";
	}
	
	@Override
	@Secured("ROLE_ADMINISTRADOR")
	public ResponseEntity<Object> buscar() {
		return super.buscar();
	}
	
	@Override
	@Secured("ROLE_ADMINISTRADOR")
	public ResponseEntity<Object> buscar(Long id) {
		return super.buscar(id);
	}
	
	
	@Override
	@Secured("ROLE_TURMA_ADM")
	public ResponseEntity<Object> persistir(@Valid @RequestBody DiaDeAula obj, BindingResult bindingResult) {
		return super.persistir(obj, bindingResult);
	}
	
	@Override
	public ResponseEntity<Object> alterar(Long id, @Valid DiaDeAula obj, BindingResult bindingResult) {
		throw new NaoImplementadoException();
	}
	
	@Override
	@Secured("ROLE_TURMA_ADM")
	public ResponseEntity<Object> deletar(Long id) {
		return super.deletar(id);
	}

}
