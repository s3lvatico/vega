package org.gmnz.vega.domain;


import org.gmnz.vega.base.NamedEntity;


public class Allergene extends NamedEntity {

	private Categoria categoria;

	public Allergene(String nome) {
		super(nome);
		categoria = Categoria.DEFAULT_CATEGORIA;
	}



	public Categoria getCategoria() {
		return categoria;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Allergene allergene = (Allergene) o;

		return getNome().equals(allergene.getNome());
	}







	@Override
	public String toString() {
		return "Allergene{" +
				"nome='" + getNome() + "\', " +
				"categoria='" + getCategoria().getNome() + '\'' +
				'}';
	}
}
