package io.senai.aulasctrl.dao.jpa;

import org.springframework.stereotype.Repository;

import io.senai.aulasctrl.dao.CursoDAO;
import io.senai.aulasctrl.models.Curso;

@Repository
public class CursoJPA extends AbstractJPA<Curso> implements CursoDAO {

	@Override
	public Curso buscarPorNome(String nome) {
		return 
				getSingleResultOrNullOfQuery(
						getEntityManager()
						.createQuery("FROM Curso c WHERE c.nome = :nome")
						.setParameter("nome", nome));
	}

	@Override
	public Class<? extends Curso> getEntityClass() {
		return Curso.class;
	}

}
