package io.senai.aulasctrl.dao;

import io.senai.aulasctrl.models.Turma;

public interface TurmaDAO extends DAO<Turma>{
	
	public Turma buscarPorNome(String nome);
}
