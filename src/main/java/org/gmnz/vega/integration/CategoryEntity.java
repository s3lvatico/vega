package org.gmnz.vega.integration;


import org.gmnz.vega.base.NamedEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity(name = "Category")
@Table(name = "category")
public class CategoryEntity {

	public static final CategoryEntity ENTITY_CATEGORIA_DEFAULT;

	static {
		ENTITY_CATEGORIA_DEFAULT = new CategoryEntity();
		ENTITY_CATEGORIA_DEFAULT.id = "00000000-0000-0000-0000-000000000000";
		ENTITY_CATEGORIA_DEFAULT.name = NamedEntity.DEFAULT_CATEGORY_NAME;
	}


	@Id
	@Column(name = "uuid")
	private String id;

	@Column(name = "e_name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private List<AllergenEntity> allergens;



	public CategoryEntity() {
		this.allergens = new ArrayList<>();
	}



	public CategoryEntity(String name) {
		this();
		this.name = name;
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



	public List<AllergenEntity> getAllergens() {
		return allergens;
	}



	public void setAllergens(List<AllergenEntity> allergens) {
		this.allergens = allergens;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CategoryEntity that = (CategoryEntity) o;
		return Objects.equals(id, that.id);
	}



	@Override
	public int hashCode() {

		return Objects.hash(id);
	}



	@Override
	public String toString() {
		return "CategoryEntity{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", allergens=" + allergens +
				'}';
	}

}
