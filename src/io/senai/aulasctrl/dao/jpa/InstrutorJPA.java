package io.senai.aulasctrl.dao.jpa;

import org.springframework.stereotype.Repository;

import io.senai.aulasctrl.dao.InstrutorDAO;
import io.senai.aulasctrl.models.Instrutor;

@Repository
public class InstrutorJPA extends AbstractJPA<Instrutor> implements InstrutorDAO{
	
	@Override
	public Class<? extends Instrutor> getEntityClass() {
		return Instrutor.class;
	}

}
