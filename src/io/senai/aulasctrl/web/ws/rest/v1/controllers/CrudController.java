package io.senai.aulasctrl.web.ws.rest.v1.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.senai.aulasctrl.services.CrudService;
import io.senai.aulasctrl.utils.WebUtils;

public abstract class CrudController<T> {
	
	protected abstract CrudService<T> getCrudService();
	
	protected abstract Object getIdentifierOf(T obj);
	
	protected String getRoutePrefix() {
		return "/";
	}
	
	@Autowired
	protected WebUtils webUtils;
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(getCrudService().buscar(id));
	}
	
	@GetMapping
	public ResponseEntity<Object> buscar() {
		return ResponseEntity.ok(getCrudService().buscar());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> alterar(@PathVariable Long id, @RequestBody @Valid T obj, BindingResult bindingResult) {
		return ResponseEntity.ok(getCrudService().alterar(id, obj, bindingResult));
	}
	
	@PostMapping
	public ResponseEntity<Object> persistir(@RequestBody @Valid T obj, BindingResult bindingResult) {
		getCrudService().persistir(obj, bindingResult);
		return ResponseEntity.created(webUtils.uri(getRoutePrefix() + "/" + getIdentifierOf(obj))).body(obj);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		getCrudService().deletar(id);
		return ResponseEntity.noContent().build();
	}
	
}
