package io.senai.aulasctrl.services;

import java.lang.reflect.Field;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import io.senai.aulasctrl.dao.DAO;
import io.senai.aulasctrl.exceptions.EntidadeNaoEncontradaException;
import io.senai.aulasctrl.exceptions.ValidacaoException;

public abstract class CrudService<T> implements Crud<T>{
	
	public abstract DAO<T> getDAO();
	
	public String[] getIgnoredUpdateFields() {
		return new String[] {"id"};
	}
	
	public String[] getUniqueFields() {
		return new String[] {};
	}
	
	public T buscar(Long id) {
		T obj = getDAO().buscar(id);
		if(obj == null) {
			throw new EntidadeNaoEncontradaException();
		}
		
		return obj;
	}
	
	protected void validateUniqueFields(T obj, BindingResult bindingResult) {
		for (String field : getUniqueFields()) {
			Field prop = ReflectionUtils.findField(obj.getClass(), field);
			boolean accessible = prop.isAccessible();
			prop.setAccessible(true);
			Object fieldValue = ReflectionUtils.getField(prop, obj);
			if (!getDAO().buscar(field, fieldValue).isEmpty()) {
				bindingResult.addError(new FieldError(obj.getClass().getSimpleName(), field, "O valor '" + fieldValue + "' já está em uso."));
			}
			
			if(!accessible) {
				prop.setAccessible(false);
			}
		}
	}
	
	protected void checkAndTriggerUniqueValidationErrors(T obj, BindingResult bindingResult) {
		validateUniqueFields(obj, bindingResult);
		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
	}
	
	public List<T> buscar() {
		return getDAO().buscar();
	}
	
	public T persistir(T obj, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
		
		checkAndTriggerUniqueValidationErrors(obj, bindingResult);
		
		if (!validateBeforeSaveRepository(obj, bindingResult) || !validateBeforePersist(obj, bindingResult)) {
			throw new ValidacaoException(bindingResult);
		}
		
		
		//Do persist
		actionBeforePersist(obj, bindingResult);
		getDAO().persistir(obj);
		
		return obj;
	}
	
	
	public T alterar(Long id, T source, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
		
		checkAndTriggerUniqueValidationErrors(source, bindingResult);
		
		if (!validateBeforeSaveRepository(source, bindingResult) || !validateBeforeUpdate(source, bindingResult)) {
			throw new ValidacaoException(bindingResult);
		}
		
		T queriedObj = buscar(id);
		BeanUtils.copyProperties(source, queriedObj, getIgnoredUpdateFields());
		
		
		//Do update
		actionBeforeUpdate(id, source, queriedObj, bindingResult);
		getDAO().alterar(queriedObj);
		
		return queriedObj;
	}
	
	public void deletar(Long id) {
		T queriedObj = buscar(id);
		
		//Do delete
		actionBeforeDelete(id, queriedObj);
		getDAO().deletar(id);
	}
	
	public boolean validateBeforeSaveRepository(T obj, BindingResult br) {
		return true;
	}
	
	public boolean validateBeforePersist(T obj, BindingResult br) {
		return true;
	}
	
	public boolean validateBeforeUpdate(T obj, BindingResult br) {
		return true;
	}
	
	public void actionBeforePersist(T obj, BindingResult br) {
		
	}
	
	public void actionBeforeUpdate(Long id, T source, T target, BindingResult bindingResult) {
		
	}
	
	public void actionBeforeDelete(Long id, T obj) {
		
	}

}
