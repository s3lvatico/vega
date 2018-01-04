package org.gmnz.vega.repository;


import org.gmnz.vega.domain.Allergene;
import org.gmnz.vega.integration.AllergeneEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


public class AllergeneHbnDao extends BaseHibernateDao implements AllergeneDao {


	@Override
	public List<Allergene> findAll() {

		List<AllergeneEntity> queryResult = wrapInTransaction(new TxManagedExecutor<List<AllergeneEntity>>() {
			@Override
			protected List<AllergeneEntity> execute() {
				Query<AllergeneEntity> q = session.createQuery("from Allergene a", AllergeneEntity.class);
				return q.list();
			}
		});

		List<Allergene> result = new ArrayList<>();
		for (AllergeneEntity ae : queryResult) {
			Allergene a = new Allergene(ae.getNome());
			result.add(a);
		}
		return result;
	}



	@Override
	public Allergene findByName(String name) {
		AllergeneEntity entity = wrapInTransaction(new TxManagedExecutor<AllergeneEntity>() {
			@Override
			protected AllergeneEntity execute() {
				return getSingleEntityByName(session, name);
			}
		});
		return entity != null ? new Allergene(entity.getNome()) : null;
	}



	@Override
	public List<Allergene> findByPattern(String pattern) {
		List<AllergeneEntity> queryResult = wrapInTransaction(new TxManagedExecutor<List<AllergeneEntity>>() {
			@Override
			protected List<AllergeneEntity> execute() {
				String query = "select allergene from Allergene allergene where allergene.nome like :pattern";
				Query<AllergeneEntity> q = session.createQuery(query, AllergeneEntity.class);
				q.setParameter("pattern", pattern);
				return q.getResultList();
			}
		});

		List<Allergene> result = new ArrayList<>();
		for (AllergeneEntity ae : queryResult) {
			Allergene a = new Allergene(ae.getNome());
			result.add(a);
		}
		return result;
	}



	private AllergeneEntity getSingleEntityByName(Session session, String nome) {
		Query<AllergeneEntity> q = session.createQuery("select a from Allergene a where a.nome = :nome", AllergeneEntity.class);
		q.setParameter("nome", nome);
		try {
			return q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}



	private void addSingleEntity(Session s, Allergene a) {
		if (getSingleEntityByName(s, a.getNome()) == null) {
			AllergeneEntity entity = new AllergeneEntity();
			entity.setId(UUID.randomUUID().toString());
			entity.setNome(a.getNome());
			s.save(entity);
		}
	}



	@Override
	public void create(Allergene allergene) {
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() {
				addSingleEntity(session, allergene);
				return null;
			}
		});
	}



	@Override
	public void create(Collection<Allergene> allergeni) {
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() {
				for (Allergene a : allergeni) {
					addSingleEntity(session, a);
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
				AllergeneEntity entity = getSingleEntityByName(session, nome);
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
				AllergeneEntity entity = getSingleEntityByName(session, nome);
				if (entity != null) {
					entity.setNome(newName);
					session.update(entity);
				}
				return null;
			}
		});
	}

}
