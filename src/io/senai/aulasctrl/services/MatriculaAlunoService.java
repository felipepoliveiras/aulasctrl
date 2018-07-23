package io.senai.aulasctrl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import io.senai.aulasctrl.dao.DAO;
import io.senai.aulasctrl.dao.MatriculaAlunoDAO;
import io.senai.aulasctrl.exceptions.EntidadeNaoEncontradaException;
import io.senai.aulasctrl.models.Aluno;
import io.senai.aulasctrl.models.MatriculaAluno;
import io.senai.aulasctrl.models.Turma;

@Service
public class MatriculaAlunoService extends CrudService<MatriculaAluno>{
	
	@Autowired
	private MatriculaAlunoDAO matriculaAlunoDAO;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private TurmaService turmaService;

	@Override
	public DAO<MatriculaAluno> getDAO() {
		return matriculaAlunoDAO;
	}
	
	public boolean alunoEstaMatriculadoNaTurma(Aluno aluno, Turma turma) {
		return matriculaAlunoDAO.alunoEstaMatriculado(aluno, turma);
	}
	
	@Override
	public boolean validateBeforeSaveRepository(MatriculaAluno obj, BindingResult br) {
	
		
		//Verificando se aluno existe...
		try {
			 obj.setAluno(alunoService.buscar(obj.getAluno().getId()));	
		} catch (EntidadeNaoEncontradaException e) {
			br.addError(new FieldError("matricula", "aluno", "O aluno informado não existe"));
			return false;
		}
		
		//Verificando se turma existe
		try {
			 obj.setTurma(turmaService.buscar(obj.getTurma().getId()));	
		} catch (EntidadeNaoEncontradaException e) {
			br.addError(new FieldError("matricula", "turma", "A turma informada não existe"));
			return false;
		}
		
		if (matriculaAlunoDAO.alunoEstaMatriculado(obj.getAluno(), obj.getTurma())) {
			br.addError(new FieldError("matricula", "aluno", "O aluno já está matriculado nesta turma"));
			return false;
		}
		
		return true;
	}

}
