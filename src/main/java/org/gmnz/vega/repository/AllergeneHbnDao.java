package org.gmnz.vega.repository;


import org.gmnz.vega.base.VegaUtil;
import org.gmnz.vega.domain.Allergen;
import org.gmnz.vega.domain.Category;
import org.gmnz.vega.integration.AllergenEntity;
import org.gmnz.vega.integration.CategoryEntity;
import org.gmnz.vega.integration.EntityFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AllergeneHbnDao extends BaseHibernateDao implements AllergeneDao {


	@Override
	public List<Allergen> findAll() throws DaoException {

		List<AllergenEntity> queryResult = wrapInTransaction(new TxManagedExecutor<List<AllergenEntity>>() {
			@Override
			protected List<AllergenEntity> execute() {
				Query<AllergenEntity> q = session.createQuery("from Allergene a", AllergenEntity.class);
				return q.list();
			}
		});

		List<Allergen> result = new ArrayList<>();
		for (AllergenEntity ae : queryResult) {
			Allergen a = new Allergen(ae.getName());
			result.add(a);
		}
		return result;
	}



	AllergenEntity getSingleEntityByName(Session session, String nome) {
		nome = VegaUtil.normalizeString(nome);
		Query<AllergenEntity> q = session.createQuery("select a from Allergene a join fetch a.categoria where a.nome = :nome", AllergenEntity.class);
		q.setParameter("nome", nome);
		try {
			return q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}



	void addSingleEntity(Session session, Allergen a) throws DaoException {
		if (getSingleEntityByName(session, a.getName()) != null) {
			throw new DaoException("Specified Allergen is already present in the system.");
		}

		if (a.getCategory() == null) {
			a.setCategory(Category.DEFAULT_CATEGORY);
		}
		CategoriaHbnDao categoriaDao = new CategoriaHbnDao();
		String nomeCategoria = a.getCategory().getName();
		AllergenEntity allergenEntity = EntityFactory.getInstance().createAllergeneEntity(a.getName());
		CategoryEntity categoryEntity;
		if (categoriaDao.existsByName(session, nomeCategoria)) {
			categoryEntity = categoriaDao.getSingleEntityByName(session, nomeCategoria);
		} else {
			categoryEntity = CategoryEntity.ENTITY_CATEGORIA_DEFAULT;
		}
		allergenEntity.setCategory(categoryEntity);
		session.save(allergenEntity);
	}



	@Override
	public void create(Allergen allergen) throws DaoException {
		if (allergen == null || VegaUtil.stringNullOrEmpty(allergen.getName())) {
			throw new DaoException("Specified Allergen was null or had an empty name");
		}
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() throws DaoException {
				addSingleEntity(session, allergen);
				return null;
			}
		});
	}



	@Override
	public Allergen findByName(String name) throws DaoException {
		AllergenEntity entity = wrapInTransaction(new TxManagedExecutor<AllergenEntity>() {
			@Override
			protected AllergenEntity execute() {
				return getSingleEntityByName(session, name);
			}
		});
		Allergen allergen = null;
		if (entity != null) {
			allergen = new Allergen(entity.getName());
			Category category = new Category(entity.getCategory().getName());
			allergen.setCategory(category);
		}
		return allergen;
	}


/*
	@Override
	public List<Allergen> findByPattern(String pattern) throws DaoException {
		List<AllergenEntity> queryResult = wrapInTransaction(new TxManagedExecutor<List<AllergenEntity>>() {
			@Override
			protected List<AllergenEntity> execute() {
				String query = "select allergene from Allergen allergene where allergene.nome like :pattern";
				Query<AllergenEntity> q = session.createQuery(query, AllergenEntity.class);
				q.setParameter("pattern", pattern);
				return q.getResultList();
			}
		});

		List<Allergen> result = new ArrayList<>();
		for (AllergenEntity ae : queryResult) {
			Allergen a = new Allergen(ae.getName());
			result.add(a);
		}
		return result;
	}
*/



	@Override
	public void create(Collection<Allergen> allergeni) throws DaoException {
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() throws DaoException {
				for (Allergen a : allergeni) {
					if (a != null) {
						addSingleEntity(session, a);
					}
				}
				return null;
			}
		});
	}



	@Override
	public void update(String nome, String newName) throws DaoException {
		if (VegaUtil.stringNullOrEmpty(nome) || VegaUtil.stringNullOrEmpty(newName)) {
			String errorMessage = String.format("Can't perform update operation because either source entity name [%s] or target entity name [%s] are null or empty", nome, newName);
			throw new DaoException(errorMessage);
		}
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() throws DaoException {
				AllergenEntity srcEntity = getSingleEntityByName(session, nome);
				boolean srcEntityExists = srcEntity != null;
				if (!srcEntityExists) {
					throw new DaoException("Can't rename non existent entity <" + nome + ">.");
				}
				AllergenEntity tgtEntity = getSingleEntityByName(session, newName);
				if (tgtEntity == null) {
					srcEntity.setName(newName);
					session.update(srcEntity);
				} else {
					String errorMessage = String.format("Can't rename <%s> to <%s>. Target entity already exists.", nome, newName);
					throw new DaoException(errorMessage);
				}
				return null;
			}
		});
	}



	@Override
	public void delete(String nome) throws DaoException {
		if (VegaUtil.stringNullOrEmpty(nome)) {
			return;
		}
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() {
				AllergenEntity entity = getSingleEntityByName(session, nome);
				if (entity != null) {
					session.remove(entity);
				}
				return null;
			}
		});
	}


}
