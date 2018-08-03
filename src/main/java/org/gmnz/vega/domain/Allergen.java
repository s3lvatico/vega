package org.gmnz.vega.domain;


import org.gmnz.vega.base.NamedEntity;


public class Allergen extends NamedEntity {

	private String id;
	private Category category;



	public Allergen(String name) {
		super(name);
		category = Category.DEFAULT_CATEGORY;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Allergen allergen = (Allergen) o;

		return getName().equals(allergen.getName());
	}



	@Override
	public String toString() {
		return "Allergen{" + "name='" + getName() + "\', " + "category='" + getCategory().getName() + '\'' + '}';
	}


}
