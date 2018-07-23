package io.senai.aulasctrl.dao.jpa;

import org.springframework.stereotype.Repository;

import io.senai.aulasctrl.dao.AlunoDAO;
import io.senai.aulasctrl.models.Aluno;

@Repository
public class AlunoJPA extends AbstractJPA<Aluno> implements AlunoDAO {

	@Override
	public Class<? extends Aluno> getEntityClass() {
		return Aluno.class;
	}

}
