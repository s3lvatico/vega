package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.domain.Categoria;
import org.gmnz.vega.integration.AllergeneEntity;
import org.gmnz.vega.integration.CategoriaEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CategoriaHbnDao extends BaseHibernateDao implements CategoriaDao {


	@Override
	public List<Categoria> findAll() {
		List<CategoriaEntity> queryResult = wrapInTransaction(new TxManagedExecutor<List<CategoriaEntity>>() {
			@Override
			protected List<CategoriaEntity> execute() {
				Query<CategoriaEntity> q = session.createQuery("from Categoria c", CategoriaEntity.class);
				return q.list();
			}
		});

		List<Categoria> result = new ArrayList<>();
		for (CategoriaEntity entity : queryResult) {
			Categoria cat = new Categoria(entity.getNome());
			result.add(cat);
		}
		return result;
	}



	private CategoriaEntity getSingleEntityByName(Session session, String nome) {
		Query<CategoriaEntity> q = session.createQuery("select cat from Categoria cat left join fetch  cat.allergeni where cat.nome = :nome", CategoriaEntity.class);
		q.setParameter("nome", nome);
		try {
			return q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}



	@Override
	public Categoria findByName(String name) {
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
				categoria.add(new Allergene(allergeneEntity.getNome()));
			}
		}
		return categoria;
	}



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



	private void addSingleEntity(Session s, Categoria c) {
		if (getSingleEntityByName(s, c.getNome()) == null) {

			CategoriaEntity entity = new CategoriaEntity();
			entity.setId(UUID.randomUUID().toString());
			entity.setNome(c.getNome());

			Query<AllergeneEntity> query = s.createQuery("from Allergene aaa where aaa.nome = :nome", AllergeneEntity.class);
			for (Allergene a : c.getAllergeni()) {
				query.setParameter("nome", a.getNome());
				AllergeneEntity allergeneEntity = query.getSingleResult();
				entity.getAllergeni().add(allergeneEntity);
			}

			s.save(entity);
		}
	}



	@Override
	public void create(Categoria categoria) {
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() {
				addSingleEntity(session, categoria);
				return null;
			}
		});
	}



	@Override
	public void delete(String nome) {
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
	public void update(String nome, String newName) {
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
	public void update(Categoria categoria) {
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
