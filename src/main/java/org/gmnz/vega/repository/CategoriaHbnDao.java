package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.domain.Categoria;
import org.gmnz.vega.integration.AllergeneEntity;
import org.gmnz.vega.integration.CategoriaEntity;
import org.gmnz.vega.integration.EntityFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;


public class CategoriaHbnDao extends BaseHibernateDao implements CategoriaDao {


	@Override
	public List<Categoria> findAll() throws DaoException {
		List<String> queryResult = wrapInTransaction(new TxManagedExecutor<List<String>>() {
			@Override
			protected List<String> execute() {
				Query<String> q = session.createQuery("select c.nome from Categoria c", String.class);
				return q.list();
			}
		});

		List<Categoria> result = new ArrayList<>();
		for (String entity : queryResult) {
			Categoria cat = new Categoria(entity);
			result.add(cat);
		}
		return result;
	}



	boolean existsByName(Session session, String nome) {
		Query<CategoriaEntity> q = session.createQuery("select cat from Categoria cat where cat.nome = :nome", CategoriaEntity.class);
		q.setParameter("nome", nome);
		try {
			q.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}



	CategoriaEntity getSingleEntityByName(Session session, String nome) {
		Query<CategoriaEntity> q = session.createQuery("select cat from Categoria cat left join fetch  cat.allergeni where cat.nome = :nome", CategoriaEntity.class);
		q.setParameter("nome", nome);
		try {
			return q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}



	@Override
	public Categoria findByName(String name) throws DaoException {
		if (name == null || name.length() == 0) {
			return null;
		}
		CategoriaEntity entity = wrapInTransaction(new TxManagedExecutor<CategoriaEntity>() {
			@Override
			protected CategoriaEntity execute() {
				return getSingleEntityByName(session, name);
			}
		});
		Categoria categoria = null;
		if (entity != null) {
			categoria = new Categoria(entity.getNome());
			for (AllergeneEntity allergeneEntity : entity.getAllergeni()) {
				Allergene allergeneBO = new Allergene(allergeneEntity.getNome());
				allergeneBO.setCategoria(categoria);
				categoria.add(allergeneBO);
			}
		}
		return categoria;
	}



/*
	@Override
	public List<Categoria> findByPattern(String pattern) {
		List<CategoriaEntity> queryResult = wrapInTransaction(new TxManagedExecutor<List<CategoriaEntity>>() {
			@Override
			protected List<CategoriaEntity> execute() {
				String query = "select categoria from Categoria categoria where categoria.nome like :pattern";
				Query<CategoriaEntity> q = session.createQuery(query, CategoriaEntity.class);
				q.setParameter("pattern", pattern);
				return q.getResultList();
			}
		});

		List<Categoria> result = new ArrayList<>();
		for (CategoriaEntity ae : queryResult) {
			Categoria a = new Categoria(ae.getNome());
			result.add(a);
		}
		return result;
	}
*/


//	private void addSingleEntity(Session s, Categoria c) {
//		if (getSingleEntityByName(s, c.getNome()) == null) {
//
//			CategoriaEntity entity = new CategoriaEntity();
//			entity.setId(UUID.randomUUID().toString());
//			entity.setNome(c.getNome());
//
//			Query<AllergeneEntity> query = s.createQuery("from Allergene aaa where aaa.nome = :nome", AllergeneEntity.class);
//			for (Allergene a : c.getAllergeni()) {
//				query.setParameter("nome", a.getNome());
//				AllergeneEntity allergeneEntity = query.getSingleResult();
//				entity.getAllergeni().add(allergeneEntity);
//			}
//
//			s.save(entity);
//		}
//	}



	@Override
	public void create(Categoria categoria) throws DaoException {
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() throws DaoException {
				if (getSingleEntityByName(session, categoria.getNome()) == null) {

					CategoriaEntity categoriaEntity = EntityFactory.getInstance().createCategoriaEntity(categoria.getNome());

					// WARN: HHH000437: Attempting to save one or more entities that have a non-nullable association with
					// an unsaved transient entity. The unsaved transient entity must be saved in an operation prior
					// to saving these dependent entities

					session.save(categoriaEntity);

					AllergeneHbnDao allergeneDao = new AllergeneHbnDao();

					AllergeneEntity allergeneEntity;
					for (Allergene a : categoria.getAllergeni()) {
						allergeneEntity = allergeneDao.getSingleEntityByName(session, a.getNome());
						if (allergeneEntity != null) {
							allergeneEntity.setCategoria(categoriaEntity);
							session.merge(allergeneEntity);
						} else {
							allergeneEntity = EntityFactory.getInstance().createAllergeneEntity(a.getNome());
							allergeneEntity.setCategoria(categoriaEntity);
							session.save(allergeneEntity);
						}
						categoriaEntity.getAllergeni().add(allergeneEntity);
					}
					session.update(categoriaEntity);
				} else {
					throw new DaoException("specified category <" + categoria.getNome() + "> already exists");
				}
				return null;
			}
		});
	}



	@Override
	public void delete(String nome) throws DaoException {
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() {
				CategoriaEntity entity = getSingleEntityByName(session, nome);
				if (entity != null) {
					session.remove(entity);
				}
				return null;
			}
		});
	}



	@Override
	public void updateRename(String nome, String newName) throws DaoException {
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() {
				CategoriaEntity entity = getSingleEntityByName(session, nome);
				if (entity != null) {
					entity.setNome(newName);
					session.update(entity);
				}
				return null;
			}
		});
	}



	@Override
	public void updateAllergeni(Categoria categoria) throws DaoException {
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() {
				CategoriaEntity entity = getSingleEntityByName(session, categoria.getNome());
				if (entity != null) {
					entity.getAllergeni().clear();
					AllergeneEntity allergeneEntity;
					Query<AllergeneEntity> allergeneQuery = session.createQuery("select allergene from Allergene allergene where allergene.nome = :nome", AllergeneEntity.class);
					for (Allergene a : categoria.getAllergeni()) {
						allergeneEntity = allergeneQuery.setParameter("nome", a.getNome()).getSingleResult();
						entity.getAllergeni().add(allergeneEntity);
					}
					session.update(entity);
				}
				return null;
			}
		});
	}


}
