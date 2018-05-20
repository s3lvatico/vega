package org.gmnz.vega.integration;


import javax.persistence.*;
import java.util.Objects;


@Entity(name = "Allergen")
@Table(name = "allergen")
public class AllergenEntity {

	@Id
	@Column(name = "uuid")
	private String uuid;

	@Column(length = 45, name = "e_name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_category", nullable = false)
	private CategoryEntity category;



	public AllergenEntity() {
		this.category = CategoryEntity.ENTITY_CATEGORIA_DEFAULT;
	}



	public AllergenEntity(String name) {
		this();
		this.name = name;
	}



	public String getUuid() {
		return uuid;
	}



	public void setUuid(String uuid) {
		this.uuid = uuid;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public CategoryEntity getCategory() {
		return category;
	}



	public void setCategory(CategoryEntity category) {
		this.category = category;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AllergenEntity that = (AllergenEntity) o;
		return Objects.equals(uuid, that.uuid);
	}



	@Override
	public int hashCode() {
		return Objects.hash(uuid);
	}



	@Override
	public String toString() {
		return "AllergenEntity{" +
				"uuid='" + uuid + '\'' +
				", name='" + name + '\'' +
				'}';
	}

}
