package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.integration.AllergeneEnt;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AllergeneHbnDao extends BaseHibernateDao implements AllergeneDao {


	@Override
	public List<Allergene> findAll() {

		List<AllergeneEnt> queryResult = wrapInTransaction(new TxManagedExecutor<List<AllergeneEnt>>() {
			@Override
			protected List<AllergeneEnt> execute() {
				Query<AllergeneEnt> q = session.createQuery("from Allergene a", AllergeneEnt.class);
				return q.list();
			}
		});

		List<Allergene> result = new ArrayList<>();
		for (AllergeneEnt ae : queryResult) {
			Allergene a = new Allergene(ae.getNome());
			result.add(a);
		}
		closeSession();
		return result;
	}



	@Override
	public Allergene findByName(String name) {
		Session s = openSession();

		AllergeneEnt entity = getSingleEntityByName(s, name);

		closeSession();

		if (entity != null) {
			Allergene a = new Allergene(name);
			return a;
		} else return null;
	}



	@Override
	public List<Allergene> findByPattern(String pattern) {
		List<AllergeneEnt> queryResult = wrapInTransaction(new TxManagedExecutor<List<AllergeneEnt>>() {
			@Override
			protected List<AllergeneEnt> execute() {
				String query = "select allergene from Allergene allergene where allergene.nome like :pattern";
				Query<AllergeneEnt> q = session.createQuery(query, AllergeneEnt.class);
				q.setParameter("pattern", pattern);
				return q.getResultList();
			}
		});

		List<Allergene> result = new ArrayList<>();
		for (AllergeneEnt ae : queryResult) {
			Allergene a = new Allergene(ae.getNome());
			result.add(a);
		}
		return result;
	}



	private AllergeneEnt getSingleEntityByName(Session s, String nome) {
		Query<AllergeneEnt> q = s.createQuery("select a from Allergene a where a.nome = :nome", AllergeneEnt.class);
		q.setParameter("nome", nome);
		try {
			return q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}



	@Override
	public void create(Allergene allergene) {
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() {
				if (getSingleEntityByName(session, allergene.getNome()) == null) {
					AllergeneEnt entity = new AllergeneEnt();
					entity.setId(UUID.randomUUID().toString());
					entity.setNome(allergene.getNome());
					session.save(entity);
				}
				return null;
			}
		});
	}



	@Override
	public void delete(String nome) {
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() {
				AllergeneEnt entity = getSingleEntityByName(session, nome);
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
				AllergeneEnt entity = getSingleEntityByName(session, nome);
				if (entity != null) {
					entity.setNome(newName);
					session.update(entity);
				}
				return null;
			}
		});
	}

}
