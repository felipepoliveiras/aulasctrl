package io.senai.aulasctrl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import io.senai.aulasctrl.dao.CursoDAO;
import io.senai.aulasctrl.dao.DAO;
import io.senai.aulasctrl.models.Curso;

@Service
public class CursoService extends CrudService<Curso>{
	
	@Autowired
	@Qualifier("cursoJPA")
	private CursoDAO cursoDAO;

	@Override
	public DAO<Curso> getDAO() {
		return cursoDAO;
	}
	
	@Override
	public String[] getUniqueFields() {
		return new String[] {"nome"};
	}
	

}
