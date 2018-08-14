package org.gmnz.vega.domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Category {

	public static final String DEFAULT_CATEGORY_NAME = "DEFAULT_CATEGORY";

	public static final Category DEFAULT_CATEGORY = new Category(DEFAULT_CATEGORY_NAME);

	private String id;
	private String name;
	private List<Allergen> allergens;



	public Category(String name) {
		this.id = "";
		this.name = name;
		allergens = new ArrayList<>();
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



	public List<Allergen> getAllergens() {
		return Collections.unmodifiableList(allergens);

	}



	public void addAllergen(Allergen allergen) {
		allergens.add(allergen);
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
		builder.append("Category [id=").append(id).append(", name=").append(getName()).append(", allergens=")
				.append(getAllergens()).append("]");
		return builder.toString();
	}



	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
