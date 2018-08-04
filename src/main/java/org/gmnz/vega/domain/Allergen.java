package org.gmnz.vega.domain;


public class Allergen {

	private String id;
	private String name;
	private Category category;



	public Allergen(String name) {
		this.name = name;
		category = Category.DEFAULT_CATEGORY;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
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
