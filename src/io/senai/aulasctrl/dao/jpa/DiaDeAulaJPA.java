package io.senai.aulasctrl.dao.jpa;

import java.util.Date;

import org.springframework.stereotype.Repository;

import io.senai.aulasctrl.dao.DiaDeAulaDAO;
import io.senai.aulasctrl.models.DiaDeAula;
import io.senai.aulasctrl.models.Turma;

@Repository
public class DiaDeAulaJPA extends AbstractJPA<DiaDeAula> implements DiaDeAulaDAO{
	@Override
	public Class<? extends DiaDeAula> getEntityClass() {
		return DiaDeAula.class;
	}

	@Override
	public DiaDeAula buscarPorDataETurma(Date date, Turma turma) {
		return 
				getSingleResultOrNullOfQuery(getEntityManager()
						.createQuery("FROM " + getEntitySimpleClassName() + " o WHERE o.data = :data AND o.turma.id = :turmaId")
						.setParameter("data", date)
						.setParameter("turmaId", turma.getId()));
	}

	@Override
	public DiaDeAula buscarPorData(Date date) {
		return getFirstOrNullOf(buscar("data", date));
	}
}
