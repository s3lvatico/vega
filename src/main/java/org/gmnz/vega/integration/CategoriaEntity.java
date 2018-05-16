package org.gmnz.vega.integration;


import org.gmnz.vega.base.NamedEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity(name = "Categoria")
@Table(name = "vg_categoria")
public class CategoriaEntity {

	public static final CategoriaEntity ENTITY_CATEGORIA_DEFAULT;

	static {
		ENTITY_CATEGORIA_DEFAULT = new CategoriaEntity();
		ENTITY_CATEGORIA_DEFAULT.id = "00000000-0000-0000-0000-000000000000";
		ENTITY_CATEGORIA_DEFAULT.nome = NamedEntity.DEFAULT_CATEGORY_NAME;
	}


	@Id
	private String id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
	private List<AllergeneEntity> allergeni;



	public CategoriaEntity() {
		this.allergeni = new ArrayList<>();
	}



	public CategoriaEntity(String nome) {
		this();
		this.nome = nome;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public List<AllergeneEntity> getAllergeni() {
		return allergeni;
	}



	public void setAllergeni(List<AllergeneEntity> allergeni) {
		this.allergeni = allergeni;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CategoriaEntity that = (CategoriaEntity) o;
		return Objects.equals(id, that.id);
	}



	@Override
	public int hashCode() {

		return Objects.hash(id);
	}



	@Override
	public String toString() {
		return "CategoriaEntity{" +
				"id='" + id + '\'' +
				", nome='" + nome + '\'' +
				", allergeni=" + allergeni +
				'}';
	}

}
