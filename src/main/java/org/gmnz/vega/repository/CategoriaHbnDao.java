package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.domain.Categoria;
import org.gmnz.vega.integration.AllergeneEnt;
import org.gmnz.vega.integration.CategoriaEnt;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CategoriaHbnDao extends BaseHibernateDao implements CategoriaDao {


	@Override
	public List<Categoria> findAll() {
		List<CategoriaEnt> queryResult = wrapInTransaction(new TxManagedExecutor<List<CategoriaEnt>>() {
			@Override
			protected List<CategoriaEnt> execute() {
				Query<CategoriaEnt> q = session.createQuery("from Categoria c", CategoriaEnt.class);
				return q.list();
			}
		});

		List<Categoria> result = new ArrayList<>();
		for (CategoriaEnt entity : queryResult) {
			Categoria cat = new Categoria(entity.getNome());
			result.add(cat);
		}
		return result;
	}



	private CategoriaEnt getSingleEntityByName(Session session, String nome) {
		Query<CategoriaEnt> q = session.createQuery("select a from Categoria a where a.nome = :nome", CategoriaEnt.class);
		q.setParameter("nome", nome);
		try {
			return q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}



	@Override
	public Categoria findByName(String name) {
		CategoriaEnt entity = wrapInTransaction(new TxManagedExecutor<CategoriaEnt>() {
			@Override
			protected CategoriaEnt execute() {
				return getSingleEntityByName(session, name);
			}
		});
		return entity != null ? new Categoria(entity.getNome()) : null;
	}



	@Override
	public List<Categoria> findByPattern(String pattern) {
		List<CategoriaEnt> queryResult = wrapInTransaction(new TxManagedExecutor<List<CategoriaEnt>>() {
			@Override
			protected List<CategoriaEnt> execute() {
				String query = "select categoria from Categoria categoria where categoria.nome like :pattern";
				Query<CategoriaEnt> q = session.createQuery(query, CategoriaEnt.class);
				q.setParameter("pattern", pattern);
				return q.getResultList();
			}
		});

		List<Categoria> result = new ArrayList<>();
		for (CategoriaEnt ae : queryResult) {
			Categoria a = new Categoria(ae.getNome());
			result.add(a);
		}
		return result;
	}



	private void addSingleEntity(Session s, Categoria c) {
		if (getSingleEntityByName(s, c.getNome()) == null) {

			CategoriaEnt entity = new CategoriaEnt();
			entity.setId(UUID.randomUUID().toString());
			entity.setNome(c.getNome());

			Query<AllergeneEnt> query = s.createQuery("from Allergene aaa where aaa.nome = :nome", AllergeneEnt.class);
			for (Allergene a : c.getAllergeni()) {
				query.setParameter("nome", a.getNome());
				AllergeneEnt allergeneEnt = query.getSingleResult();
				entity.getAllergeni().add(allergeneEnt);
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
				CategoriaEnt entity = getSingleEntityByName(session, nome);
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
				CategoriaEnt entity = getSingleEntityByName(session, nome);
				if (entity != null) {
					entity.setNome(newName);
					session.update(entity);
				}
				return null;
			}
		});
	}

}
