package io.senai.aulasctrl.web.ws.rest.v1.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import io.senai.aulasctrl.dao.UsuarioDAO;
import io.senai.aulasctrl.models.Usuario;
import io.senai.aulasctrl.services.UsuarioService;
import io.senai.aulasctrl.utils.JwtUtils;

@Component
public class AuthFilter extends GenericFilterBean{
	
	@Autowired
	@Qualifier("usuarioJPA")
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//Verifica se veio token do Header
		String token = request.getHeader("Authorization");
		System.out.println("valor do header: " + token);
		
		if (token != null) {
			if(token.matches("(Bearer) .*")) {
				
				//Pega o token
				token = token.split(" ")[1];
				
				//Pega o usuario do token
				Usuario usuarioToken = null;
				try {
					usuarioToken = JwtUtils.getUsuarioDeTokenAutenticacao(token);
					System.out.println("usuario do token: " + usuarioToken.getEmail());
				} catch (Exception e) {
					response.setHeader("X-Reason", "Token informado incorreto.");
					response.setStatus(400);
					chain.doFilter(req, resp);
					return;
				}
				
				//Pega o usuario do banco de dados e verifica se esta apto a autenticar-se
				Usuario usuarioAutenticado = usuarioDAO.buscar(usuarioToken.getId());
				if(usuarioAptoAAutenticar(usuarioAutenticado, response)) {
					SecurityContextHolder.getContext().setAuthentication(usuarioAutenticado);
				}
				//Usuario nao esta apto a autenticar-se
				else {
					response.setStatus(401);
				}
			}else {
				response.setHeader("X-Reason", "Token deve ser do padr�o Bearer");
				response.setStatus(400);
			}
		}else {
			response.setHeader("X-Reason", "Authorization n�o especificado");
			response.setStatus(400);
		}
		
		chain.doFilter(req, resp);
		
	}
	
	private boolean usuarioAptoAAutenticar(Usuario usuario, HttpServletResponse response) {
		//Verifica se o usuario passado esta nulo
		if (usuario == null) {
			response.setHeader("X-Reason", "Usuario extraido do token nao existe no sistema");
			return false;
		}
		
		if (!usuarioService.aptoAAutenticar(usuario)) {
			response.setHeader("X-Reason", "Usuario inativado");
			return false;
		}
		
		//Caso nenhum erro foi encontrado
		return true;
	}

}
