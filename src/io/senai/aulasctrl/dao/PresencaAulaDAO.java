package io.senai.aulasctrl.dao;

import io.senai.aulasctrl.models.DiaDeAula;
import io.senai.aulasctrl.models.PresencaAula;

public interface PresencaAulaDAO extends DAO<PresencaAula>{
	
	public void deletarPorDiaDeAula(DiaDeAula diaDeAula);
}
