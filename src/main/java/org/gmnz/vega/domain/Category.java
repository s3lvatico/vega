package org.gmnz.vega.domain;


import java.util.List;

import org.gmnz.vega.base.AbstractListManagedPropertyHolder;
import org.gmnz.vega.base.NamedEntity;


public class Category extends AbstractListManagedPropertyHolder<Allergen> {

	public static final Category DEFAULT_CATEGORY = new Category(NamedEntity.DEFAULT_CATEGORY_NAME);

	private String id;



	public Category(String name) {
		super(name);
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public List<Allergen> getAllergens() {
		return getListProperty();
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Category c = (Category) obj;

		return getName().equals(c.getName());
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [id=").append(id)
		.append(", name=").append(getName())
		.append(", allergens=").append(getAllergens()).append("]");
		return builder.toString();
	}



	
}
