package org.gmnz.vega.domain;


import org.gmnz.vega.base.NamedEntity;


public class Allergene extends NamedEntity {


	public Allergene(String nome) {
		super(nome);
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Allergene allergene = (Allergene) o;

		return getNome().equals(allergene.getNome());
	}



	@Override
	public int hashCode() {
		return getNome().hashCode();
	}



	@Override
	public String toString() {
		return "Allergene{" +
				"nome='" + getNome() + '\'' +
				'}';
	}
}
