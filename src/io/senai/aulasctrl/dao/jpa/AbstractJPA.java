package io.senai.aulasctrl.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import io.senai.aulasctrl.dao.DAO;

@Transactional
@SuppressWarnings("unchecked")
public abstract class AbstractJPA<T> implements DAO<T>{
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	public abstract Class<? extends T> getEntityClass();
	
	protected String getEntitySimpleClassName() {
		return getEntityClass().getSimpleName();
	}
	
	protected T getSingleResultOrNullOfQuery(Query query) {
		List<T> result = query.getResultList();
		if (!result.isEmpty()) {
			return result.get(0);
		} else {
			return null;
		}
	}
	
	protected T getFirstOrNullOf(List<T> result) {
		if (!result.isEmpty()) {
			return result.get(0);
		} else {
			return null;
		}
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<T> buscar() {
		return
		getEntityManager()
			.createQuery("FROM " + getEntitySimpleClassName())
			.getResultList();
	}

	@Override
	public T buscar(Long id) {
		return getSingleResultOrNullOfQuery(
				getEntityManager()
				.createQuery("FROM " + getEntitySimpleClassName() + " o WHERE o.id = :id")
				.setParameter("id", id));
	}
	
	@Override
	public List<T> buscar(String campo, Object valor) {
		return 
				getEntityManager()
					.createQuery("FROM " + getEntitySimpleClassName() + " o WHERE o."+campo+" = :valor")
					.setParameter("valor", valor)
					.getResultList();
	}

	@Override
	public void alterar(T obj) {
		getEntityManager().merge(obj);
	}

	@Override
	public void deletar(T obj) {
		getEntityManager().remove(obj);
	}
	
	@Override
	public void deletar(Long id) {
		getEntityManager().remove(buscar(id));
	}

	@Override
	public void persistir(T obj) {
		getEntityManager().persist(obj);
	}

}
