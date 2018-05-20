package org.gmnz.vega.domain;


import org.gmnz.vega.base.AbstractListManagedPropertyHolder;
import org.gmnz.vega.base.NamedEntity;

import java.util.List;


public class Category extends AbstractListManagedPropertyHolder<Allergen> {

	public static final Category DEFAULT_CATEGORY = new Category(NamedEntity.DEFAULT_CATEGORY_NAME);


	public Category(String name) {
		super(name);
	}



	public List<Allergen> getAllergens() {
		return getListProperty();
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		Category c = (Category) obj;

		return getName().equals(c.getName());
	}



	@Override
	public String toString() {
		return "Category{" +
				"name='" + getName() + '\'' +
				", allergens=" + listProperty +
				'}';
	}
}
