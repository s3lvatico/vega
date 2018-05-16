package org.gmnz.vega.integration;


import javax.persistence.*;
import java.util.Objects;


@Entity(name = "Allergene")
@Table(name = "vg_allergene")
public class AllergeneEntity {

	@Id
	private String id;

	@Column(length = 45)
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria", nullable = false)
	private CategoriaEntity categoria;



	public AllergeneEntity() {
		this.categoria = CategoriaEntity.ENTITY_CATEGORIA_DEFAULT;
	}



	public AllergeneEntity(String nome) {
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



	public CategoriaEntity getCategoria() {
		return categoria;
	}



	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AllergeneEntity that = (AllergeneEntity) o;
		return Objects.equals(id, that.id);
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public String toString() {
		return "AllergeneEntity{" +
				"id='" + id + '\'' +
				", nome='" + nome + '\'' +
				'}';
	}

}
