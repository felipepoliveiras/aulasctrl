package io.senai.aulasctrl.web.ws.rest.v1.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.senai.aulasctrl.models.Aluno;
import io.senai.aulasctrl.models.PresencaAula;
import io.senai.aulasctrl.models.Turma;
import io.senai.aulasctrl.services.CrudService;
import io.senai.aulasctrl.services.TurmaService;

@RestController
@RequestMapping("/rest/v1/turmas")
public class TurmaController extends CrudController<Turma>{
	
	@Autowired
	private TurmaService turmaService;

	@Override
	protected CrudService<Turma> getCrudService() {
		return turmaService;
	}

	@Override
	protected Object getIdentifierOf(Turma obj) {
		return obj.getId();
	}
	
	@Override
	protected String getRoutePrefix() {
		return "/rest/v1/turmas";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id) {
		return super.buscar(id);
	}
	
	@GetMapping
	public ResponseEntity<Object> buscar() {
		return super.buscar();
	}
	
	@PostMapping("/{id}/presencas")
	@Secured("ROLE_ALUNO")
	public ResponseEntity<Object> aplicarPresenca(@PathVariable Long id, @Valid Principal principal) {
		PresencaAula presenca = turmaService.aplicarPresenca((Aluno) principal, id, new BeanPropertyBindingResult(new PresencaAula(), "presencaAula"));
		return ResponseEntity.created(webUtils.uri(getRoutePrefix() + "/" + presenca.getId())).body(presenca);
	}
	
	@PostMapping("/{id}/diasDeAula")
	@Secured("ROLE_TURMA_ADM")
	public ResponseEntity<Object> cadastrarDiaDeAula(@PathVariable Long id) {
		
	}
	
	@PutMapping("/{id}")
	@Secured("ROLE_ADMINISTRADOR")
	public ResponseEntity<Object> alterar(@PathVariable Long id, @RequestBody @Valid Turma obj, BindingResult bindingResult) {
		return super.alterar(id, obj, bindingResult);
	}
	
	@PostMapping
	@Secured("ROLE_ADMINISTRADOR")
	public ResponseEntity<Object> persistir(@RequestBody @Valid Turma obj, BindingResult bindingResult) {
		return super.persistir(obj, bindingResult);
	}
	
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMINISTRADOR")
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		return super.deletar(id);
	}

}
