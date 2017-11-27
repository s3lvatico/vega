package org.gmnz.a2.domain;

public class Allergene {

	private String nome;

	public Allergene(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Allergene allergene = (Allergene) o;

		return nome.equals(allergene.nome);
	}

	@Override
	public int hashCode() {
		return nome.hashCode();
	}

	@Override
	public String toString() {
		return "Allergene{" +
				"nome='" + nome + '\'' +
				'}';
	}
}
