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
import java.util.List;


public class CategoriaHbnDao extends BaseHibernateDao implements CategoriaDao {


	@Override
	public List<Category> findAll() throws DaoException {
		List<String> queryResult = wrapInTransaction(new TxManagedExecutor<List<String>>() {
			@Override
			protected List<String> execute() {
				Query<String> q = session.createQuery("select c.nome from Categoria c", String.class);
				return q.list();
			}
		});

		List<Category> result = new ArrayList<>();
		for (String entity : queryResult) {
			Category cat = new Category(entity);
			result.add(cat);
		}
		return result;
	}



	boolean existsByName(Session session, String nome) {
		Query<CategoryEntity> q = session.createQuery("select cat from Categoria cat where cat.nome = :nome", CategoryEntity.class);
		q.setParameter("nome", nome);
		try {
			q.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}



	CategoryEntity getSingleEntityByName(Session session, String nome) {
		Query<CategoryEntity> q = session.createQuery("select cat from Categoria cat left join fetch  cat.allergeni where cat.nome = :nome", CategoryEntity.class);
		q.setParameter("nome", nome);
		try {
			return q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}



	@Override
	public Category findByName(String name) throws DaoException {
		if (VegaUtil.stringNullOrEmpty(name)) {
			return null;
		}

		CategoryEntity entity = wrapInTransaction(new TxManagedExecutor<CategoryEntity>() {
			@Override
			protected CategoryEntity execute() {
				return getSingleEntityByName(session, name);
			}
		});
		Category category = null;
		if (entity != null) {
			category = new Category(entity.getName());
			for (AllergenEntity allergenEntity : entity.getAllergens()) {
				Allergen allergenBO = new Allergen(allergenEntity.getName());
				allergenBO.setCategory(category);
				category.add(allergenBO);
			}
		}
		return category;
	}



/*
	@Override
	public List<Category> findByPattern(String pattern) {
		List<CategoryEntity> queryResult = wrapInTransaction(new TxManagedExecutor<List<CategoryEntity>>() {
			@Override
			protected List<CategoryEntity> execute() {
				String query = "select categoria from Category categoria where categoria.nome like :pattern";
				Query<CategoryEntity> q = session.createQuery(query, CategoryEntity.class);
				q.setParameter("pattern", pattern);
				return q.getResultList();
			}
		});

		List<Category> result = new ArrayList<>();
		for (CategoryEntity ae : queryResult) {
			Category a = new Category(ae.getName());
			result.add(a);
		}
		return result;
	}
*/


//	private void addSingleEntity(Session s, Category c) {
//		if (getSingleEntityByName(s, c.getName()) == null) {
//
//			CategoryEntity entity = new CategoryEntity();
//			entity.setUuid(UUID.randomUUID().toString());
//			entity.setName(c.getName());
//
//			Query<AllergenEntity> query = s.createQuery("from Allergen aaa where aaa.nome = :nome", AllergenEntity.class);
//			for (Allergen a : c.getAllergens()) {
//				query.setParameter("nome", a.getName());
//				AllergenEntity allergeneEntity = query.getSingleResult();
//				entity.getAllergens().add(allergeneEntity);
//			}
//
//			s.save(entity);
//		}
//	}



	@Override
	public void create(String nome) throws DaoException {
		if (VegaUtil.stringNullOrEmpty(nome)) {
			throw new DaoException("null or empty name specified for the new Category");
		}
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() throws DaoException {
				if (getSingleEntityByName(session, nome) == null) {
					CategoryEntity categoryEntity = EntityFactory.getInstance().createCategoriaEntity(nome);
					session.save(categoryEntity);
				} else {
					throw new DaoException("specified category <" + nome + "> already exists");
				}
				return null;
			}
		});
	}



	@Override
	public void updateRename(String nome, String newName) throws DaoException {
		if (VegaUtil.stringNullOrEmpty(nome) || VegaUtil.stringNullOrEmpty(newName)) {
			return;
		}
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() throws DaoException {
				if (getSingleEntityByName(session, newName) != null) {
					String errorMessage = String.format("Cannot rename Category <%s> to <%s>. There is already such a category in the system", nome, newName);
					throw new DaoException(errorMessage);
				}
				CategoryEntity entity = getSingleEntityByName(session, nome);
				if (entity != null) {
					entity.setName(newName);
					session.update(entity);
					return null;
				} else {
					String errorMessage = String.format("Cannot find target Category to rename <%s>", nome);
					throw new DaoException(errorMessage);
				}
			}
		});
	}



	@Override
	public void updateAllergeni(Category category) throws DaoException {
		if (category == null) {
			throw new DaoException("null business object specified");
		}
		if (VegaUtil.stringNullOrEmpty(category.getName())) {
			throw new DaoException("null or empty name specified for the new Category");
		}
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() throws DaoException {
				CategoryEntity categoryEntity = getSingleEntityByName(session, category.getName());
				if (categoryEntity != null) { // la category che cerco esiste
					// tutti gli allergeni associati passano preventivamente alla category predefinita
					for (AllergenEntity allergenEntity : categoryEntity.getAllergens()) {
						allergenEntity.setCategory(CategoryEntity.ENTITY_CATEGORIA_DEFAULT);
						session.update(allergenEntity);
					}
					categoryEntity.getAllergens().clear();
					try {
						AllergenEntity allergenEntity;
						Query<AllergenEntity> allergeneQuery = session.createQuery("select allergene from Allergene allergene where allergene.nome = :nome", AllergenEntity.class);
						// gli allereni specificati nel domain object vengono associati a questa category
						for (Allergen a : category.getAllergens()) {
							// getSingleResult() può lanciare eccezione
							allergenEntity = allergeneQuery.setParameter("nome", a.getName()).getSingleResult();
							categoryEntity.getAllergens().add(allergenEntity);
							allergenEntity.setCategory(categoryEntity);
							session.update(allergenEntity);
						}
					} catch (NoResultException e) {
						throw new DaoException("at least one of the specified Allergen(s) was not found in the system", e);
					} catch (Exception e) {
						throw new DaoException("Exception thrown when setting the associated Allergen(s) to this Category", e);
					}
					session.update(categoryEntity);
				} else {
					throw new DaoException(String.format("Specified Category <%s> does not exist", category.getName()));
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
		if (nome.equalsIgnoreCase(CategoryEntity.ENTITY_CATEGORIA_DEFAULT.getName())) {
			throw new DaoException("Default category cannot be removed");
		}
		wrapInTransaction(new TxManagedExecutor<Void>() {
			@Override
			protected Void execute() throws DaoException {
				Query q = session.createQuery("select rd from ReportData rd where rd.vgAllergeneByIdAllergene.categoria.nome = :nome");
				q.setParameter("nome", nome);

				if (q.getResultList().size() != 0) {
					throw new DaoException("Specified Category <" + nome + "> is referenced by at least one report.");
				}
				CategoryEntity categoryEntity = getSingleEntityByName(session, nome);
				if (categoryEntity != null) {
					for (AllergenEntity allergenEntity : categoryEntity.getAllergens()) {
						allergenEntity.setCategory(CategoryEntity.ENTITY_CATEGORIA_DEFAULT);
						session.update(allergenEntity);
					}
					session.remove(categoryEntity);
				} else {
					throw new DaoException("Specified Category <" + nome + "> does not exist.");
				}
				return null;
			}
		});
	}

}
