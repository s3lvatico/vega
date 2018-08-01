package org.gmnz.vega.repository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.gmnz.vega.domain.Category;


class CategoryDaoImpl extends BasicDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAll() throws DaoException {
		return new ArrayList<>(DummyRepository.getRegisteredCategories());
	}



	@Override
	public boolean isCategoryRegistered(String name) throws DaoException {
		Collection<Category> registeredCategories = DummyRepository.getRegisteredCategories();
		Category c = new Category(name);
		return registeredCategories.contains(c);
	}



	@Override
	public void create(String name) throws DaoException {
		DummyRepository.addCategory(new Category(name));
	}



	@Override
	public void updateRename(String oldName, String newName) throws DaoException {
		Iterator<Category> iterator = DummyRepository.getRegisteredCategories().iterator();
		while (iterator.hasNext()) {
			Category c = iterator.next();
			if (c.getName().equals(oldName)) {
				if (c.getAllergens().size() == 0) {
					DummyRepository.removeCategory(new Category(oldName));
					DummyRepository.addCategory(new Category(newName));
					break;
				} else {
					throw new DaoException(
							"CategoryDaoImpl.updateRename error: a category must have no allergens associated in order to be renamed.");
				}
			}
		}

	}



	@Override
	public void delete(String name) throws DaoException {
		Iterator<Category> iterator = DummyRepository.getRegisteredCategories().iterator();
		while (iterator.hasNext()) {
			Category ic = iterator.next();
			if (ic.getName().equals(name)) {
				if (ic.getAllergens().size() == 0) {
					DummyRepository.removeCategory(ic);
					break;
				} else {
					throw new DaoException(
							"CategoryDaoImpl.delete error: a category must have no allergens associated in order to be deleted.");
				}
			}
		}

	}

}
