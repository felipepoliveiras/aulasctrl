package io.senai.aulasctrl.dao.jpa;

import org.springframework.stereotype.Repository;

import io.senai.aulasctrl.dao.TurmaDAO;
import io.senai.aulasctrl.models.Turma;

@Repository
public class TurmaJPA extends AbstractJPA<Turma> implements TurmaDAO{

	@Override
	public Class<? extends Turma> getEntityClass() {
		return Turma.class;
	}

	@Override
	public Turma buscarPorNome(String nome) {
		return getFirstOrNullOf(buscar("nome", nome));
	}

}
