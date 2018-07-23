package io.senai.aulasctrl.dao.jpa;

import org.springframework.stereotype.Repository;

import io.senai.aulasctrl.dao.MatriculaAlunoDAO;
import io.senai.aulasctrl.models.Aluno;
import io.senai.aulasctrl.models.MatriculaAluno;
import io.senai.aulasctrl.models.Turma;

@Repository
public class MatriculaAlunoJPA extends AbstractJPA<MatriculaAluno> implements MatriculaAlunoDAO{

	@Override
	public boolean alunoEstaMatriculado(Aluno aluno, Turma turma) {
		return
			!getEntityManager()
					.createQuery("FROM " + getEntitySimpleClassName() + " o WHERE o.aluno.id = :alunoId AND o.turma.id = :turmaId")
					.setParameter("alunoId", aluno.getId())
					.setParameter("turmaId", turma.getId())
					.getResultList()
						.isEmpty();
	}

	@Override
	public Class<? extends MatriculaAluno> getEntityClass() {
		return MatriculaAluno.class;
	}

}
