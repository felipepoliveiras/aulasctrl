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

import io.senai.aulasctrl.models.Aluno;
import io.senai.aulasctrl.services.AlunoService;
import io.senai.aulasctrl.services.CrudService;

@RestController
@RequestMapping("/rest/v1/alunos")
public class AlunoController extends CrudController<Aluno>{
	
	@Autowired
	private AlunoService alunoService;

	@Override
	protected CrudService<Aluno> getCrudService() {
		return alunoService;
	}

	@Override
	protected Object getIdentifierOf(Aluno obj) {
		return obj.getId();
	}
	
	@GetMapping("/{id}")
	@Secured("ROLE_TURMA_ADM")
	public ResponseEntity<Object> buscar(@PathVariable Long id) {
		return super.buscar(id);
	}
	
	@GetMapping
	@Secured("ROLE_TURMA_ADM")
	public ResponseEntity<Object> buscar() {
		return super.buscar();
	}
	
	@PutMapping("/{id}")
	@Secured("ROLE_TURMA_ADM")
	public ResponseEntity<Object> alterar(@PathVariable Long id, @RequestBody @Valid Aluno obj, BindingResult bindingResult) {
		return super.alterar(id, obj, bindingResult);
	}
	
	@PostMapping
	@Secured("ROLE_TURMA_ADM")
	public ResponseEntity<Object> persistir(@RequestBody @Valid Aluno obj, BindingResult bindingResult) {
		return super.persistir(obj, bindingResult);
	}
	
	@DeleteMapping("/{id}")
	@Secured("ROLE_TURMA_ADM")
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		return super.deletar(id);
	}
	

}
