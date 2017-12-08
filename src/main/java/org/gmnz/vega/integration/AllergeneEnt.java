package org.gmnz.vega.integration;

import java.util.Objects;

public class AllergeneEnt {

	private String id;
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
		AllergeneEnt that = (AllergeneEnt) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
