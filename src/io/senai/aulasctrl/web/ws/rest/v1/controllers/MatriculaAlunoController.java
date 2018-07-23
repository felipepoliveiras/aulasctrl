package io.senai.aulasctrl.web.ws.rest.v1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.senai.aulasctrl.models.MatriculaAluno;
import io.senai.aulasctrl.services.CrudService;
import io.senai.aulasctrl.services.MatriculaAlunoService;

@RestController
@RequestMapping("/rest/v1/matriculas/alunos")
public class MatriculaAlunoController extends CrudController<MatriculaAluno>{
	
	@Autowired
	private MatriculaAlunoService matriculaAlunoService;

	@Override
	protected CrudService<MatriculaAluno> getCrudService() {
		return matriculaAlunoService;
	}

	@Override
	protected Object getIdentifierOf(MatriculaAluno obj) {
		return obj.getId();
	}
	
	@Override
	protected String getRoutePrefix() {
		return "/rest/v1/matriculas/alunos";
	}

}
