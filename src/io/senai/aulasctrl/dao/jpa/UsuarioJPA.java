package io.senai.aulasctrl.dao.jpa;

import org.springframework.stereotype.Repository;

import io.senai.aulasctrl.dao.UsuarioDAO;
import io.senai.aulasctrl.models.Usuario;

@Repository
public class UsuarioJPA extends AbstractJPA<Usuario> implements UsuarioDAO{

	@Override
	public Class<? extends Usuario> getEntityClass() {
		return Usuario.class;
	}

	@Override
	public Usuario buscarPorEmail(String email) {
		return
				getSingleResultOrNullOfQuery(
					getEntityManager().createQuery("FROM "+ getEntitySimpleClassName() + " o WHERE o.email = :email")
					.setParameter("email", email)
					);
	}

	@Override
	public Usuario buscarPorEmailESenha(String email, String senha) {
		return
				getSingleResultOrNullOfQuery(
						getEntityManager()
						.createQuery("FROM "+ getEntitySimpleClassName() + " o WHERE o.email = :email AND o.senha = :senha")
						.setParameter("email", email)
						.setParameter("senha", senha)
						);
	}

}
