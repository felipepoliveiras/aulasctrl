package io.senai.aulasctrl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import io.senai.aulasctrl.dao.DAO;
import io.senai.aulasctrl.dao.InstrutorDAO;
import io.senai.aulasctrl.models.Instrutor;

@Service
public class InstrutorService extends CrudService<Instrutor>{
	
	@Autowired
	@Qualifier("instrutorJPA")
	private InstrutorDAO instrutorDAO;
	
	@Override
	public DAO<Instrutor> getDAO() {
		return instrutorDAO;
	}
	
	@Override
	public String[] getUniqueFields() {
		return new String[] {"email"};
	}
	
	@Override
	public Instrutor persistir(Instrutor obj, BindingResult bindingResult) {
		obj.hashearSenha();
		obj.setAtivo(true);
		return super.persistir(obj, bindingResult);
	}
	
	@Override
	public Instrutor alterar(Long id, Instrutor source, BindingResult bindingResult) {
		source.hashearSenha();
		return super.alterar(id, source, bindingResult);
	}
}
