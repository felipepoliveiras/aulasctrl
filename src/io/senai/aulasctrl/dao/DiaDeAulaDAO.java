package io.senai.aulasctrl.dao;

import java.util.Date;

import io.senai.aulasctrl.models.DiaDeAula;
import io.senai.aulasctrl.models.Turma;

public interface DiaDeAulaDAO extends DAO<DiaDeAula>{
	
	public DiaDeAula buscarPorData(Date date);
	
	public DiaDeAula buscarPorDataETurma(Date date, Turma turma);

}
