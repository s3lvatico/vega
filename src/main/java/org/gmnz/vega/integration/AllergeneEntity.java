package org.gmnz.vega.integration;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


@Entity(name = "Allergene")
@Table(name = "vg_allergene")
public class AllergeneEntity {

	@Id
	private String id;

	@Column(length = 45)
	private String nome;



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
}
