package io.senai.aulasctrl.dao;

import io.senai.aulasctrl.models.Usuario;

public interface UsuarioDAO extends DAO<Usuario>{
	
	public Usuario buscarPorEmail(String email);
	
	public Usuario buscarPorEmailESenha(String email, String senha);

}
