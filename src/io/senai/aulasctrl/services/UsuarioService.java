package io.senai.aulasctrl.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import io.senai.aulasctrl.dao.DAO;
import io.senai.aulasctrl.dao.UsuarioDAO;
import io.senai.aulasctrl.exceptions.EntidadeNaoEncontradaException;
import io.senai.aulasctrl.models.Usuario;

@Service
public class UsuarioService extends CrudService<Usuario> {
	
	@Autowired
	@Qualifier("usuarioJPA")
	private UsuarioDAO usuarioDAO;
	
	public boolean aptoAAutenticar(Usuario usuario) {
		if (usuario.getAtivo() == null || !usuario.getAtivo()) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param email
	 * @throws EntidadeNaoEncontradaException - Caso o usuario nao seja encontrado atraves do email
	 * @return
	 */
	public Usuario buscarPorEmail(String email) {
		Usuario usuario = usuarioDAO.buscarPorEmail(email);
		
		//Usuario nao encontrado
		if (usuario == null) {
			throw new EntidadeNaoEncontradaException();
		}
		
		return usuario;
	}
	
	/**
	 * 
	 * Busca um usuário através da combinação de email e senha
	 * @param email - O email que usuario que sera buscado
	 * @param senha - A senha do usuario que sera buscado
	 * @throws EntidadeNaoEncontradaException - Caso o usuario nao exista
	 * @return
	 */
	public Usuario buscarPorEmailESenha(Usuario usuario) {
		usuario.hashearSenha();
		Usuario usuarioBuscado = usuarioDAO.buscarPorEmailESenha(usuario.getEmail(), usuario.getSenha());
		
		if (usuarioBuscado == null) {
			throw new EntidadeNaoEncontradaException();
		}
		
		return usuarioBuscado;
	}


	@Override
	public DAO<Usuario> getDAO() {
		return usuarioDAO;
	}
	
	@Override
	public String[] getUniqueFields() {
		return new String[] {"email"};
	}
	
	@Override
	public Usuario persistir(Usuario obj, BindingResult bindingResult) {
		obj.hashearSenha();
		obj.setAtivo(true);
		return super.persistir(obj, bindingResult);
	}
	
	@Override
	public Usuario alterar(Long id, Usuario source, BindingResult bindingResult) {
		source.hashearSenha();
		return super.alterar(id, source, bindingResult);
	}
}
