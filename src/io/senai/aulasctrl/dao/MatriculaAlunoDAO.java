package io.senai.aulasctrl.dao;

import io.senai.aulasctrl.models.Aluno;
import io.senai.aulasctrl.models.MatriculaAluno;
import io.senai.aulasctrl.models.Turma;

public interface MatriculaAlunoDAO extends DAO<MatriculaAluno>{
	
	public boolean alunoEstaMatriculado(Aluno aluno, Turma turma);

}
