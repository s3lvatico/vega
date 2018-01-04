package org.gmnz.vega.integration;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity(name = "Categoria")
@Table(name = "vg_categoria")
public class CategoriaEntity {
	@Id
	private String id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@OneToMany
	@JoinTable(name = "vg_categoria_allergene",
			joinColumns = {@JoinColumn(name = "id_categoria")},
			inverseJoinColumns = {@JoinColumn(name = "id_allergene")})
	private List<AllergeneEntity> allergeni;



	public CategoriaEntity() {
		this.allergeni = new ArrayList<>();
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
