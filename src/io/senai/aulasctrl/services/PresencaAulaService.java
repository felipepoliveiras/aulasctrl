package io.senai.aulasctrl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import io.senai.aulasctrl.dao.DAO;
import io.senai.aulasctrl.dao.PresencaAulaDAO;
import io.senai.aulasctrl.models.PresencaAula;

@Service
public class PresencaAulaService extends CrudService<PresencaAula>{
	
	@Autowired
	@Qualifier("presencaAulaJPA")
	private PresencaAulaDAO presencaAulaDAO;

	@Override
	public DAO<PresencaAula> getDAO() {
		return presencaAulaDAO;
	}
	
	PresencaAula persistir(PresencaAula obj) {
		presencaAulaDAO.persistir(obj);
		
		return obj;
	}
}
