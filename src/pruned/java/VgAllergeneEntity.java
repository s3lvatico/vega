package org.gmnz.vega.integration;


import javax.persistence.*;
import java.util.Objects;


/**
 * creato da simone in data 30/12/2017.
 */
@Entity
@Table(name = "vg_allergene", schema = "sandbox", catalog = "")
public class VgAllergeneEntity {
	private String id;
	private String nome;



	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	@Basic
	@Column(name = "nome", nullable = false, length = 45)
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
		VgAllergeneEntity that = (VgAllergeneEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(nome, that.nome);
	}



	@Override
	public int hashCode() {

		return Objects.hash(id, nome);
	}
}
