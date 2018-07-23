package io.senai.aulasctrl.dao;

import io.senai.aulasctrl.models.Curso;

public interface CursoDAO  extends DAO<Curso>{
	
	public Curso buscarPorNome(String nome);

}
