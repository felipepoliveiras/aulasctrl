package io.senai.aulasctrl.services;

import java.util.List;

import org.springframework.validation.BindingResult;

public interface Crud<T> {
	
	public T buscar(Long id);
	
	public List<T> buscar();
	
	public T alterar(Long id, T obj, BindingResult bindingResult);
	
	public T persistir(T obj, BindingResult bindingResult);
	
	public void deletar(Long id);

}
