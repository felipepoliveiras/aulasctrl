package io.senai.aulasctrl.dao;

import java.util.List;

public interface DAO<T> {
	
	public List<T> buscar();
	
	public List<T> buscar(String campo, Object valor);
	
	public T buscar(Long id);
	
	public void alterar(T obj);
	
	public void deletar(Long id);
	
	public void deletar(T obj);
	
	public void persistir(T obj);
	

}
