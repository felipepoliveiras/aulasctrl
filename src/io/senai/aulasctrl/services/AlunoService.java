package io.senai.aulasctrl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import io.senai.aulasctrl.dao.AlunoDAO;
import io.senai.aulasctrl.dao.DAO;
import io.senai.aulasctrl.models.Aluno;

@Service
public class AlunoService extends CrudService<Aluno>{
	
	@Autowired
	@Qualifier("alunoJPA")
	private AlunoDAO alunoDAO;

	@Override
	public DAO<Aluno> getDAO() {
		return alunoDAO;
	}
	
	@Override
	public String[] getUniqueFields() {
		return new String[] {"email"};
	}
	
	@Override
	public Aluno persistir(Aluno obj, BindingResult bindingResult) {
		obj.hashearSenha();
		obj.setAtivo(true);
		return super.persistir(obj, bindingResult);
	}
	
	@Override
	public Aluno alterar(Long id, Aluno source, BindingResult bindingResult) {
		source.hashearSenha();
		return super.alterar(id, source, bindingResult);
	}

}
