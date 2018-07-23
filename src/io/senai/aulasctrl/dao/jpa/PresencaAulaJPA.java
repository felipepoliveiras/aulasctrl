package io.senai.aulasctrl.dao.jpa;

import org.springframework.stereotype.Repository;

import io.senai.aulasctrl.dao.PresencaAulaDAO;
import io.senai.aulasctrl.models.DiaDeAula;
import io.senai.aulasctrl.models.PresencaAula;

@Repository
public class PresencaAulaJPA extends AbstractJPA<PresencaAula> implements PresencaAulaDAO{

	@Override
	public void deletarPorDiaDeAula(DiaDeAula diaDeAula) {
		getEntityManager()
		.createQuery("DELETE FROM " + getEntitySimpleClassName() + " o WHERE o.diaDeAula.id = :diaDeAula.id")
		.setParameter("diaDeAula.id", diaDeAula.getId())
		.executeUpdate();
	}

	@Override
	public Class<? extends PresencaAula> getEntityClass() {
		return PresencaAula.class;
	}

}
